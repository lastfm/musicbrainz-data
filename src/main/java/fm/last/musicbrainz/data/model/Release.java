/*
 * Copyright 2012 Last.fm
 * Copyright 2012 Aurélien Mino <aurelien.mino@gmail.com>
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
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Access(AccessType.FIELD)
@Entity
@Table(name = "release", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Release extends AbstractCoreEntity<ReleaseName> {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "release_group", nullable = false)
  private ReleaseGroup releaseGroup;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "release_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  @Type(type = "pg-uuid")
  private final Set<UUID> redirectedGids;

  @ManyToOne(targetEntity = ArtistCredit.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_credit", nullable = true)
  private ArtistCredit artistCredit;

  @OneToMany(targetEntity = Medium.class, fetch = FetchType.LAZY, mappedBy = "release", orphanRemoval = true)
  @OrderBy("position")
  private final List<Medium> mediums;

  @Column(name = "status")
  @Type(type = "fm.last.musicbrainz.data.hibernate.ReleaseStatusUserType")
  private ReleaseStatus status;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "date_day"))
  })
  private PartialDate releaseDate;

  @ManyToOne
  @JoinColumn(name = "country")
  private Country country;

  public Release() {
    redirectedGids = Sets.newHashSet();
    mediums = Lists.newArrayList();
  }

  public ArtistCredit getArtistCredit() {
    return artistCredit;
  }

  /**
   * Returns and immutable list of {@link Medium}s ordered by position.
   */
  public List<Medium> getMediums() {
    return Collections.unmodifiableList(mediums);
  }

  /**
   * Returns an immutable set of all associated GIDs (canonical and redirected).
   */
  public Set<UUID> getGids() {
    return new ImmutableSet.Builder<UUID>().addAll(redirectedGids).add(gid).build();
  }

  public PartialDate getReleaseDate() {
    return releaseDate;
  }

  public ReleaseStatus getStatus() {
    return status;
  }

  public ReleaseGroup getReleaseGroup() {
    return releaseGroup;
  }

  public Country getCountry() {
    return country;
  }

}
