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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

@Access(AccessType.FIELD)
@Entity
@Table(name = "release_group", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReleaseGroup extends AbstractCoreEntity<ReleaseName> {

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "release_group_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  @Type(type = "pg-uuid")
  private final Set<UUID> redirectedGids = Sets.newHashSet();

  @ManyToOne(targetEntity = ArtistCredit.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_credit", nullable = true)
  private ArtistCredit artistCredit;

  @Column(name = "type")
  @Type(type = "fm.last.musicbrainz.data.hibernate.ReleaseGroupPrimaryTypeUserType")
  private ReleaseGroupPrimaryType type;

  @ElementCollection
  @CollectionTable(name = "release_group_secondary_type_join", joinColumns = @JoinColumn(name = "release_group"))
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "secondary_type")
  @Type(type = "fm.last.musicbrainz.data.hibernate.ReleaseGroupSecondaryTypeUserType")
  private Set<ReleaseGroupSecondaryType> secondaryTypes;


  public ArtistCredit getArtistCredit() {
    return artistCredit;
  }

  public ReleaseGroupPrimaryType getType() {
    return type;
  }

  public Set<ReleaseGroupSecondaryType> getSecondaryTypes() {
    return secondaryTypes;
  }

    /**
   * Returns an immutable set of all associated GIDs (canonical and redirected).
   */
  public Set<UUID> getGids() {
    return new ImmutableSet.Builder<UUID>().addAll(redirectedGids).add(gid).build();
  }

}
