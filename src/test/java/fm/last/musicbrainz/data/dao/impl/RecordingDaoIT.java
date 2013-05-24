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
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;
import fm.last.musicbrainz.data.dao.RecordingDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Recording;

public class RecordingDaoIT extends AbstractHibernateModelIT {

  @Autowired
  private RecordingDao dao;

  @Test
  public void getByExistingIdReturnsOneRecording() {
    Recording recording = dao.getById(1);
    assertThat(recording.getName(), is("The Saint"));
  }

  @Test
  public void getByNotExistingIdReturnsNull() {
    Recording recording = dao.getById(9001);
    assertThat(recording, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneRecording() {
    UUID gid = UUID.fromString("4ea1383f-aca7-4a39-9839-576cf3af438b");
    Recording recording = dao.getByGid(gid);
    assertThat(recording.getName(), is("The Saint"));
  }

  @Test
  public void getByNotExistingGidReturnsNull() {
    UUID gid = UUID.fromString("b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d");
    Recording recording = dao.getByGid(gid);
    assertThat(recording, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneRecordingThatHasNoRedirectedGids() {
    UUID gid = UUID.fromString("2ea1383f-aca7-4a39-9839-576cf3af438b");
    Recording recording = dao.getByGid(gid);
    assertThat(recording.getName(), is("The Sinner"));
  }

  @Test
  public void getByArtistAndNameReturnsTwoRecordings() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    String trackName = "Never Gonna Give You Up";
    List<Recording> recordings = dao.getByArtistAndName(artist, trackName);
    assertThat(recordings, hasSize(2));
    for (Recording recording : recordings) {
      assertThat(recording.getName(), is(trackName));
    }
  }

  @Test
  public void getByArtistAndUppercaseNameReturnsTwoRecordings() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    List<Recording> recordings = dao.getByArtistAndName(artist, "NEVER GONNA GIVE YOU UP");
    assertThat(recordings, hasSize(2));
  }

  @Test
  public void getByArtistAndLowercaseNameReturnsTwoRecordings() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    List<Recording> recordings = dao.getByArtistAndName(artist, "never gonna give you up");
    assertThat(recordings, hasSize(2));
  }

  @Test
  public void getByArtistAndNotExistingNameReturnsEmptyList() {
    Artist artist = (Artist) session.load(Artist.class, 4);
    List<Recording> recordings = dao.getByArtistAndName(artist, "does not exists");
    assertThat(recordings, hasSize(0));
  }

  @Test
  public void getByArtistReturnsAllRecordings() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    List<Recording> recordings = dao.getByArtist(artist);
    assertThat(recordings, hasSize(4));
  }

  @Test
  public void getByArtistReturnsNoRecordingsForEmptyArtist() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    List<Recording> recordings = dao.getByArtist(artist);
    assertThat(recordings, hasSize(0));
  }

}
