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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;

public class PartialDateTest {

  @Test
  public void yearIsNullReturnsNullReleaseDate() {
    assertThat(new PartialDate(null, (short) 10, (short) 5).toLocalDate(), is(nullValue()));
  }

  @Test
  public void monthIsNullReturnsReleaseDateFromJanuary() {
    assertThat(new PartialDate((short) 2011, null, (short) 5).toLocalDate(), is(LocalDate.parse("2011-01-01")));
    assertThat(new PartialDate((short) 2011, null, null).toLocalDate(), is(LocalDate.parse("2011-01-01")));
  }

  @Test
  public void dayIsNullReturnsReleaseDateFromFirstOfMonth() {
    assertThat(new PartialDate((short) 2011, (short) 5, null).toLocalDate(), is(LocalDate.parse("2011-05-01")));
  }

  @Test
  public void equalsTests() {
    PartialDate date1 = new PartialDate((short) 2011, (short) 5, null);
    PartialDate date2 = new PartialDate((short) 2011, (short) 5, (short) 1);
    assertFalse(date1.equals(date2));
    PartialDate date3 = new PartialDate((short) 2011, (short) 5, (short) 1);
    assertTrue(date2.equals(date3));
    PartialDate date4 = new PartialDate(null, (short) 5, (short) 1);
    assertFalse(date1.equals(date4));
  }

  @Test
  public void compareToTests() {
    PartialDate date2 = new PartialDate((short) 2011, (short) 5, (short) 1);
    PartialDate date3 = new PartialDate((short) 2011, (short) 5, (short) 1);
    assertThat(date2.compareTo(date3), is(0));
    PartialDate date1 = new PartialDate((short) 2011, (short) 5, null);
    assertThat(date1.compareTo(date2), is(-1));
    PartialDate date4 = new PartialDate(null, (short) 5, (short) 1);
    assertThat(date1.compareTo(date4), is(1));
  }

  @Test
  public void toStringTests() {
    PartialDate date;
    date = new PartialDate((short) 2011, null, null);
    assertThat(date.toString(), is("2011"));
    date = new PartialDate((short) 2011, (short) 5, null);
    assertThat(date.toString(), is("2011-05"));
    date = new PartialDate((short) 2011, (short) 5, (short) 6);
    assertThat(date.toString(), is("2011-05-06"));
  }

}
