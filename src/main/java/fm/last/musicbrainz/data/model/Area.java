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
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

@Access(AccessType.FIELD)
@Entity
@Table(name = "area", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Area {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "gid", nullable = false, unique = true)
  @Type(type = "pg-uuid")
  private UUID gid;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "area_gid_redirect", schema = "musicbrainz", joinColumns = @JoinColumn(name = "new_id"))
  @Column(name = "gid")
  @Type(type = "pg-uuid")
  private final Set<UUID> redirectedGids = Sets.newHashSet();

  @JoinColumn(name = "name")
  private String name;

  @Column(name = "type")
  @Type(type = "fm.last.musicbrainz.data.hibernate.AreaTypeUserType")
  private AreaType type;

  @Column(name = "last_updated")
  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  private DateTime lastUpdated;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "begin_date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "begin_date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "begin_date_day")) })
  private PartialDate beginDate;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "end_date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "end_date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "end_date_day")) })
  private PartialDate endDate;

  @Column(name = "ended")
  private boolean ended;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public AreaType getType() {
    return type;
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

  public PartialDate getBeginDate() {
    return beginDate;
  }

  public PartialDate getEndDate() {
    return endDate;
  }

  public boolean hasEnded() {
    return ended;
  }

}
