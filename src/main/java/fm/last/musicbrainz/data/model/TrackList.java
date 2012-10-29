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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

@Access(AccessType.FIELD)
@Entity
@Table(name = "tracklist", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class TrackList {

  @Id
  @Column(name = "id")
  private int id;

  @OneToMany(targetEntity = Track.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "tracklist")
  @OrderBy("position")
  private final List<Track> tracks = Lists.newArrayList();

  public List<Track> getTracks() {
    return Collections.unmodifiableList(tracks);
  }

}
