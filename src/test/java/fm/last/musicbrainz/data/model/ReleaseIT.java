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
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class ReleaseIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("6dfe725f-de93-3b08-b3cb-5971e5bd6eb5"),
        UUID.fromString("5d32bacc-d62a-4e77-9f0e-d934e53d5359"));

    Release release = (Release) session.load(Release.class, 5);
    assertThat(release.getArtistCredit().getFullName(), is("Mono and Rick Astley feat. Hot Chip"));
    assertThat(release.getComment(), isEmptyString());
    assertThat(release.getGids(), is(expectedGids));
    assertThat(release.getId(), is(5));
    assertThat(release.getLastUpdated(), is(DateTime.parse("2012-04-10T14:00:00")));
    assertThat(release.getName(), is("Multi-Disc Extravaganza"));
    assertThat(release.getMediums(), hasSize(2));
    assertThat(release.getStatus(), is(ReleaseStatus.UNDEFINED));
    assertThat(release.getReleaseGroup().getId(), is(4));
    assertThat(release.getReleaseDateForUnknownCountry().toLocalDate(), is(LocalDate.parse("2011-07-23")));
    assertThat(release.getReleaseCountries(), hasSize(0));
  }

  @Test
  public void releaseCountriesAreMappedWhenSpecified() {
    Set<ReleaseCountry> expected = Sets.newHashSet(new ReleaseCountry(4, 151, new PartialDate(null, (short) 7,
        (short) 23)), new ReleaseCountry(4, 113, new PartialDate((short) 2010, (short) 7, (short) 23)),
        new ReleaseCountry(4, 203, null));

    Release release = (Release) session.load(Release.class, 4);
    assertThat(release.getReleaseDateForUnknownCountry(), is(nullValue()));
    assertThat(release.getReleaseCountries(), is(expected));
  }

  @Test
  public void releaseStatusIsNotUndefined() {
    Release release = (Release) session.load(Release.class, 4);
    assertThat(release.getStatus(), is(ReleaseStatus.OFFICIAL));
  }

  @Test
  public void mediumsAreOrderedByPosition() {
    Release release = (Release) session.load(Release.class, 5);
    List<Medium> mediums = release.getMediums();
    assertThat(mediums.get(0).getPosition(), is(lessThan(mediums.get(1).getPosition())));
  }

  @Test
  public void releaseWithoutRedirectedGidsHasOneGid() {
    Release release = (Release) session.load(Release.class, 2);
    assertThat(release.getGids(), hasSize(1));
  }

  @Test
  public void artistCreditReferenceDoesNotHitDatabase() {
    Release release = (Release) session.load(Release.class, 5);
    ArtistCredit credit = release.getArtistCredit();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void mediumReferenceDoesNotHitDatabase() {
    Release release = (Release) session.load(Release.class, 5);
    List<Medium> mediums = release.getMediums();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void releaseGroupReferenceDoesNotHitDatabase() {
    Release release = (Release) session.load(Release.class, 5);
    ReleaseGroup releaseGroup = release.getReleaseGroup();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void releaseUnknownCountryReferenceDoesNotHitDatabase() {
    Release release = (Release) session.load(Release.class, 5);
    PartialDate releaseDateForUnknownCountry = release.getReleaseDateForUnknownCountry();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void releaseCountryReferenceDoesNotHitDatabase() {
    Release release = (Release) session.load(Release.class, 5);
    Set<ReleaseCountry> releaseCountries = release.getReleaseCountries();
    assertThat(fetchCount(), is(1L));
  }
}
