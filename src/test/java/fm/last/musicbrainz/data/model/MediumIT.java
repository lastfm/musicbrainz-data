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
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class MediumIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Medium medium = (Medium) session.load(Medium.class, 2);
    assertThat(medium.getId(), is(2));
    assertThat(medium.getLastUpdated(), is(DateTime.parse("2012-04-10T14:00:00")));
    assertThat(medium.getName(), is("Disc 1"));
    assertThat(medium.getPosition(), is(1));
    assertThat(medium.getRelease().getId(), is(5));

    Set<Integer> expectedTrackIds = Sets.newHashSet(3, 4);
    Set<Integer> actualTrackIds = Sets.newHashSet();
    for (Track track : medium.getTracks()) {
      actualTrackIds.add(track.getId());
    }
    assertThat(actualTrackIds, is(expectedTrackIds));
  }

  @Test
  public void tracksAreOrderedByPosition() {
    Medium medium = (Medium) session.load(Medium.class, 2);
    List<Track> tracks = medium.getTracks();
    assertThat(tracks.get(0).getPosition(), is(lessThan(tracks.get(1).getPosition())));
  }

  @Test
  public void releaseReferenceDoesNotHitDatabase() {
    Medium medium = (Medium) session.load(Medium.class, 2);
    Release release = medium.getRelease();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void tracksReferenceDoesNotHitDatabase() {
    Medium medium = (Medium) session.load(Medium.class, 2);
    List<Track> tracks = medium.getTracks();
    assertThat(fetchCount(), is(1L));
  }
}
