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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Access(AccessType.FIELD)
@Entity
@IdClass(ReleaseCountryCompositeKey.class)
@Table(name = "release_country", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReleaseCountry {

  @Id
  @Column(name = "release")
  private int release;

  @Id
  @Column(name = "country", insertable = false, updatable = false)
  private int countryId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "country", nullable = false)
  private Area country;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "date_day")) })
  private PartialDate releaseDate;

  public ReleaseCountry() {
  }

  /** ONLY FOR TESTING **/
  ReleaseCountry(int releaseId, int countryId, PartialDate releaseDate) {
    release = releaseId;
    this.countryId = countryId;
    this.releaseDate = releaseDate;
  }

  public Area getCountry() {
    return country;
  }

  public PartialDate getReleaseDate() {
    return releaseDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + countryId;
    result = prime * result + release;
    result = prime * result + (releaseDate == null ? 0 : releaseDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ReleaseCountry other = (ReleaseCountry) obj;
    if (countryId != other.countryId) {
      return false;
    }
    if (release != other.release) {
      return false;
    }
    if (releaseDate == null) {
      if (other.releaseDate != null) {
        return false;
      }
    } else if (!releaseDate.equals(other.releaseDate)) {
      return false;
    }
    return true;
  }

}
