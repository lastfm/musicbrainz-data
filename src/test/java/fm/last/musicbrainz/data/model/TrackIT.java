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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class TrackIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("3c22f2da-6c1e-3f0a-baba-4147fede5eae"),
        UUID.fromString("7e53ce62-562c-4145-a9db-9736a3f04c27"));

    Track track = (Track) session.load(Track.class, 3);
    assertThat(track.getId(), is(3));
    assertThat(track.getGids(), is(expectedGids));
    assertThat(track.getArtistCredit().getFullName(), is("Rick Astley"));
    assertThat(track.getName(), is("The Saint"));
    assertThat(track.getPosition(), is(1));
    assertThat(track.getNumber(), is("A-1"));
    assertThat(track.getRecording().getId(), is(1));
    assertThat(track.getLength(), is(254160));
    assertThat(track.getLastUpdated(), is(DateTime.parse("2013-05-24T11:03:00")));
  }

  @Test
  public void trackWithoutRedirectedGidsHasOneGid() {
    Track track = (Track) session.load(Track.class, 4);
    assertThat(track.getGids(), hasSize(1));
  }

  @Test
  public void artistCreditReferenceDoesNotHitDatabase() {
    Track track = (Track) session.load(Track.class, 3);
    ArtistCredit artistCredit = track.getArtistCredit();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void recordingReferenceDoesNotHitDatabase() {
    Track track = (Track) session.load(Track.class, 3);
    Recording recording = track.getRecording();
    assertThat(fetchCount(), is(1L));
  }

}
