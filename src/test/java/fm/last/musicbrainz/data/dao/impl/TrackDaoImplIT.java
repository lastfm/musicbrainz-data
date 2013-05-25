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
package fm.last.musicbrainz.data.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
  public void getByExistingIdReturnsOneTrack() {
    Track track = dao.getById(1);
    assertThat(track.getName(), is("The Saint"));
  }

  @Test
  public void getByNotExistingIdReturnsNull() {
    Track track = dao.getById(9001);
    assertThat(track, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneTrack() {
    UUID gid = UUID.fromString("70c4bd53-f3ef-354f-97a9-7ed76915087a");
    Track track = dao.getByGid(gid);
    assertThat(track.getName(), is("The Saint"));
  }

  @Test
  public void getByNotExistingGidReturnsNull() {
    UUID gid = UUID.fromString("b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d");
    Track track = dao.getByGid(gid);
    assertThat(track, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneTrackThatHasNoRedirectedGids() {
    UUID gid = UUID.fromString("8f64df73-562c-4145-a9db-9736a3f04c27");
    Track track = dao.getByGid(gid);
    assertThat(track.getName(), is("The Saint"));
  }

  @Test
  public void getByArtistAndNameReturnsOneTrack() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    String trackName = "The Saint";
    List<Track> tracks = dao.getByArtistAndName(artist, trackName);
    assertThat(tracks, hasSize(2));
    for (Track track : tracks) {
      assertThat(track.getName(), is(trackName));
    }
  }

  @Test
  public void getByArtistAndUppercaseNameReturnsOneTrack() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    String trackName = "THE SAINT";
    List<Track> tracks = dao.getByArtistAndName(artist, trackName);
    assertThat(tracks, hasSize(2));
  }

  @Test
  public void getByArtistAndLowercaseNameReturnsOneTrack() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    String trackName = "the saint";
    List<Track> tracks = dao.getByArtistAndName(artist, trackName);
    assertThat(tracks, hasSize(2));
  }

  @Test
  public void getByArtistAndNotExistingNameReturnsEmptyList() {
    Artist artist = (Artist) session.load(Artist.class, 4);
    List<Track> tracks = dao.getByArtistAndName(artist, "does not exists");
    assertThat(tracks, hasSize(0));
  }

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
