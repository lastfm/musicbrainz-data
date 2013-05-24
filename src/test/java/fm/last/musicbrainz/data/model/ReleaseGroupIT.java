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

public class ReleaseGroupIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("a1f5f807-3851-48fb-838b-fb8a069f53e7"),
        UUID.fromString("188711ed-c99b-439c-844a-ca831f63a727"));

    ReleaseGroup releaseGroup = (ReleaseGroup) session.load(ReleaseGroup.class, 1);
    assertThat(releaseGroup.getArtistCredit().getFullName(), is("Rick Astley"));
    assertThat(releaseGroup.getComment(), is("release_group comment"));
    assertThat(releaseGroup.getGids(), is(expectedGids));
    assertThat(releaseGroup.getId(), is(1));
    assertThat(releaseGroup.getLastUpdated(), is(DateTime.parse("2012-04-10T14:00:00")));
    assertThat(releaseGroup.getName(), is("The Best of Rick Astley"));
    assertThat(releaseGroup.getType(), is(ReleaseGroupPrimaryType.UNDEFINED));
  }

  @Test
  public void releaseGroupPrimaryTypeIsNotUndefined() {
    ReleaseGroup releaseGroup = (ReleaseGroup) session.load(ReleaseGroup.class, 2);
    assertThat(releaseGroup.getType(), is(ReleaseGroupPrimaryType.ALBUM));
  }

  @Test
  public void releaseGroupWithoutRedirectedGidsHasOneGid() {
    ReleaseGroup releaseGroup = (ReleaseGroup) session.load(ReleaseGroup.class, 2);
    assertThat(releaseGroup.getGids(), hasSize(1));
  }

  @Test
  public void artistCreditReferenceDoesNotHitDatabase() {
    ReleaseGroup releaseGroup = (ReleaseGroup) session.load(ReleaseGroup.class, 1);
    ArtistCredit credit = releaseGroup.getArtistCredit();
    assertThat(fetchCount(), is(1L));
  }
}
