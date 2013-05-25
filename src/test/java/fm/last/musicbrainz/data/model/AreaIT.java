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
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;

public class AreaIT extends AbstractHibernateModelIT {

  @Test
  public void allFieldsArePopulated() {
    Set<UUID> expectedGids = Sets.newHashSet(UUID.fromString("c741c28e-cbec-3977-88c8-583a8af62522"),
        UUID.fromString("6b43e5f2-49e8-46ce-94cb-a9b23e5bb4e8"));

    Area area = (Area) session.load(Area.class, 151);
    assertThat(area.getId(), is(151));
    assertThat(area.getName(), is("Netherlands Antilles"));
    assertThat(area.getType(), is(AreaType.COUNTRY));
    assertThat(area.getLastUpdated(), is(DateTime.parse("2013-05-15T16:49:41")));
    assertThat(area.getGids(), is(expectedGids));
    assertThat(area.getBeginDate().toLocalDate(), is(LocalDate.parse("1954-09-12")));
    assertThat(area.getEndDate().toLocalDate(), is(LocalDate.parse("2010-12-09")));
    assertThat(area.hasEnded(), is(true));
  }

  @Test
  public void areaTypeIsUndefinedWhenNotSpecified() {
    Area area = (Area) session.load(Area.class, 1178);
    assertThat(area.getType(), is(AreaType.UNDEFINED));
  }

  @Test
  public void beginAndEndDateAreNullWhenNotSpecified() {
    Area area = (Area) session.load(Area.class, 1178);
    assertThat(area.getBeginDate(), is(nullValue()));
    assertThat(area.getEndDate(), is(nullValue()));
  }

  @Test
  public void areaWithoutRedirectedGidsHasOneGid() {
    Area area = (Area) session.load(Area.class, 1178);
    assertThat(area.getGids(), hasSize(1));
  }
}
