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
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class ArtistIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("a934e33f-b3cb-47dd-9638-f7f1f25fe162"),
        UUID.fromString("994fcd41-2831-4318-9825-66bacbcf2cfe"));

    Artist artist = (Artist) session.load(Artist.class, 1);
    assertThat(artist.getId(), is(1));
    assertThat(artist.getName(), is("Q and Not U"));
    assertThat(artist.getComment(), is("Soft Pyramids"));
    assertThat(artist.getGids(), is(expectedGids));
    assertThat(artist.getLastUpdated(), is(DateTime.parse("2012-04-10T14:00:00")));
    assertThat(artist.getType(), is(ArtistType.UNDEFINED));
    assertThat(artist.getGender(), is(Gender.UNDEFINED));
    assertThat(artist.getBeginDate().toLocalDate(), is(LocalDate.parse("1950-02-03")));
    assertThat(artist.getEndDate().toLocalDate(), is(LocalDate.parse("2001-04-05")));
    assertThat(artist.getArea().getName(), is("Tokyo"));
    assertThat(artist.getBeginArea().getName(), is("South Korea"));
    assertThat(artist.getEndArea().getName(), is("Netherlands Antilles"));
  }

  @Test
  public void areasAreNullWhenNotSpecified() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    assertThat(artist.getArea(), is(nullValue()));
    assertThat(artist.getBeginArea(), is(nullValue()));
    assertThat(artist.getEndArea(), is(nullValue()));
  }

  @Test
  public void beginAndEndDateAreNullWhenNotSpecified() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    assertThat(artist.getBeginDate(), is(nullValue()));
    assertThat(artist.getEndDate(), is(nullValue()));
  }

  @Test
  public void artistWithoutRedirectedGidsHasOneGid() {
    Artist artist = (Artist) session.load(Artist.class, 3);
    assertThat(artist.getGids(), hasSize(1));
  }

  @Test
  public void genderIsNotUndefinedWhenSpecified() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    assertThat(artist.getGender(), is(Gender.MALE));
  }

  @Test
  public void artistTypeIsNotUndefinedWhenSpecified() {
    Artist artist = (Artist) session.load(Artist.class, 5);
    assertThat(artist.getType(), is(ArtistType.PERSON));
  }

  @Test
  public void areaReferenceDoesNotHitDatabase() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    Area area = artist.getArea();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void beginAreaReferenceDoesNotHitDatabase() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    Area beginArea = artist.getBeginArea();
    assertThat(fetchCount(), is(1L));
  }

  @Test
  public void endAreaReferenceDoesNotHitDatabase() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    Area endArea = artist.getEndArea();
    assertThat(fetchCount(), is(1L));
  }

}
