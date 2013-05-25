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

import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

@Access(AccessType.FIELD)
@Entity
@Table(name = "artist_credit", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArtistCredit {

  @Id
  @Column(name = "id")
  private int id;

  @OneToMany(targetEntity = ArtistCreditName.class, fetch = FetchType.LAZY, mappedBy = "artistCredit", orphanRemoval = true)
  @OrderBy("position")
  private final List<ArtistCreditName> artistCreditNames = Lists.newArrayList();

  public int getId() {
    return id;
  }

  /**
   * Returns an immutable list of {@link ArtistCreditName}s.
   */
  public List<ArtistCreditName> getArtistCreditNames() {
    return Collections.unmodifiableList(artistCreditNames);
  }

  /**
   * Builds a full name by joining the {@code ArtistCreditName}s and their join phrases.
   */
  public String getFullName() {
    StringBuilder builder = new StringBuilder();
    for (ArtistCreditName artistCreditName : getArtistCreditNames()) {
      builder.append(artistCreditName.getName());
      builder.append(artistCreditName.getJoinPhrase());
    }
    return builder.toString();
  }

}
