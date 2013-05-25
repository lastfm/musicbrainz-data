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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Access(AccessType.FIELD)
@Entity
@Table(name = "release_unknown_country", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class ReleaseUnknownCountry {

  @Id
  @Column(name = "release")
  private int release;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "date_day")) })
  private PartialDate releaseDate;

  PartialDate getReleaseDate() {
    return releaseDate;
  }
}
