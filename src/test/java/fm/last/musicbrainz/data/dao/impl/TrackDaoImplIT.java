/*
 * Copyright 2012 The musicbrainz-data Authors
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
package fm.last.musicbrainz.data.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;
import fm.last.musicbrainz.data.dao.TrackDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Track;

public class TrackDaoImplIT extends AbstractHibernateModelIT {

  @Autowired
  private TrackDao dao;

  @Test
  public void getByExistingArtistReturnsThreeTracks() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    List<Track> tracks = dao.getByArtist(artist);
    Set<Integer> expectedTrackIds = Sets.newHashSet(1, 2, 3);
    Set<Integer> actualTrackIds = Sets.newHashSet();
    for (Track track : tracks) {
      actualTrackIds.add(track.getId());
    }
    assertThat(actualTrackIds, is(expectedTrackIds));
  }

  @Test
  public void getByArtistThatHasNoTracksReturnsEmptyList() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    List<Track> tracks = dao.getByArtist(artist);
    assertThat(tracks, hasSize(0));
  }
}
