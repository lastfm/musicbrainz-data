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
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GenderTest {

  @Test
  public void typeReturnsGenderMale() {
    assertThat(Gender.valueOf(1), is(Gender.MALE));
  }

  @Test
  public void typeReturnsGenderFemale() {
    assertThat(Gender.valueOf(2), is(Gender.FEMALE));
  }

  @Test
  public void typeReturnsGenderOther() {
    assertThat(Gender.valueOf(3), is(Gender.OTHER));
  }

  @Test
  public void typeReturnsGenderUndefined() {
    Integer id = null;
    assertThat(Gender.valueOf(id), is(Gender.UNDEFINED));
  }

  @Test(expected = IllegalArgumentException.class)
  public void unrecognisedTypeThrowsException() {
    Gender.valueOf(5);
  }

}
