/*
 * Copyright 2013 The musicbrainz-data Authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.musicbrainz.data.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

@Access(AccessType.FIELD)
@Entity
@Table(name = "track", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Track {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "gid", nullable = false, unique = true)
  @Type(type = "pg-uuid")
  private UUID gid;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "track_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  @Type(type = "pg-uuid")
  private final Set<UUID> redirectedGids = Sets.newHashSet();

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "name")
  private TrackName name;

  @ManyToOne(targetEntity = ArtistCredit.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_credit", nullable = true)
  private ArtistCredit artistCredit;

  @Column(name = "position")
  private int position;

  @Column(name = "number")
  private String number;

  @ManyToOne(targetEntity = Recording.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "recording")
  private Recording recording;

  @Column(name = "length")
  private Integer length;

  @Column(name = "last_updated")
  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  private DateTime lastUpdated;

  public int getId() {
    return id;
  }

  public int getPosition() {
    return position;
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name.getName();
  }

  public ArtistCredit getArtistCredit() {
    return artistCredit;
  }

  public Recording getRecording() {
    return recording;
  }

  /**
   * Length in milliseconds.
   */
  public Integer getLength() {
    return length;
  }

  public DateTime getLastUpdated() {
    return lastUpdated;
  }

  /**
   * Returns an immutable set of all associated GIDs (canonical and redirected).
   */
  public Set<UUID> getGids() {
    return new ImmutableSet.Builder<UUID>().addAll(redirectedGids).add(gid).build();
  }

}
