/*
 * Copyright 2012 Last.fm
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

import java.util.Collections;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Access(AccessType.FIELD)
@Entity
@Table(name = "release", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Release {

  @Id
  @Column(name = "id")
  private int id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "name")
  private ReleaseName name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "release_group", nullable = false)
  private ReleaseGroup releaseGroup;

  @Column(name = "gid", nullable = false, unique = true)
  @Type(type = "pg-uuid")
  private UUID gid;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "release_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  @Type(type = "pg-uuid")
  private final Set<UUID> redirectedGids;

  @ManyToOne(targetEntity = ArtistCredit.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_credit", nullable = true)
  private ArtistCredit artistCredit;

  @Column(name = "comment")
  private String comment;

  @OneToMany(targetEntity = Medium.class, fetch = FetchType.LAZY, mappedBy = "release", orphanRemoval = true)
  @OrderBy("position")
  private final List<Medium> mediums;

  @Column(name = "last_updated")
  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  private DateTime lastUpdated;

  @Column(name = "date_year")
  private Short year;

  @Column(name = "date_month")
  private Short month;

  @Column(name = "date_day")
  private Short day;

  @Column(name = "status")
  @Type(type = "fm.last.musicbrainz.data.hibernate.ReleaseStatusUserType")
  private ReleaseStatus status;

  public Release() {
    redirectedGids = Sets.newHashSet();
    mediums = Lists.newArrayList();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name.getName();
  }

  public ArtistCredit getArtistCredit() {
    return artistCredit;
  }

  public String getComment() {
    return comment;
  }

  /**
   * Returns and immutable list of {@link Medium}s ordered by position.
   */
  public List<Medium> getMediums() {
    return Collections.unmodifiableList(mediums);
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

  /**
   * See {@link ReleaseDateFactory} for how partial release dates are handled.
   */
  public DateTime getReleaseDate() {
    return ReleaseDateFactory.INSTANCE.valueOf(year, month, day);
  }

  public ReleaseStatus getStatus() {
    return status;
  }

  public ReleaseGroup getReleaseGroup() {
    return releaseGroup;
  }

}
