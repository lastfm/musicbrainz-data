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
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class RecordingIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("4ea1383f-aca7-4a39-9839-576cf3af438b"),
        UUID.fromString("6a27908d-9663-4e61-9ef3-e8b82c31dc14"));

    Recording recording = (Recording) session.load(Recording.class, 1);
    assertThat(recording.getId(), is(1));
    assertThat(recording.getName(), is("The Saint"));
    assertThat(recording.getArtistCredit().getFullName(), is("Rick Astley"));
    assertThat(recording.getComment(), isEmptyString());
    assertThat(recording.getGids(), is(expectedGids));
    assertThat(recording.getLastUpdated(), is(DateTime.parse("2012-04-10T14:00:00")));
    assertThat(recording.getLength(), is(1000));
  }

  @Test
  public void recordingWithoutRedirectedGidsHasOneGid() {
    Recording recording = (Recording) session.load(Recording.class, 2);
    assertThat(recording.getGids().size(), is(1));
  }

  @Test
  public void artistCreditReferenceDoesNotHitDatabase() {
    Recording recording = (Recording) session.load(Recording.class, 1);
    ArtistCredit credit = recording.getArtistCredit();
    assertThat(fetchCount(), is(1L));
  }
}
