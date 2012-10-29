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
package fm.last.musicbrainz.data.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class TrackIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Track track = (Track) session.load(Track.class, 3);
    assertThat(track.getArtistCredit().getFullName(), is("Rick Astley"));
    assertThat(track.getId(), is(3));
    assertThat(track.getName(), is("The Saint"));
    assertThat(track.getPosition(), is(1));
    assertThat(track.getRecording(), is(not(nullValue())));
  }

  @Test
  public void artistCreditReferenceDoesNotHitDatabase() {
    Track track = (Track) session.load(Track.class, 3);
    ArtistCredit artistCredit = track.getArtistCredit();
    assertThat(fetchCount(), is(1L));
  }

}
