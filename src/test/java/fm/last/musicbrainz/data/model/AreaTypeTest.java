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

public class AreaTypeTest {

  @Test
  public void idReturnsAreaTypeCountry() {
    assertThat(AreaType.valueOf(1), is(AreaType.COUNTRY));
  }

  @Test
  public void idReturnsAreaTypeSubdivision() {
    assertThat(AreaType.valueOf(2), is(AreaType.SUBDIVISION));
  }

  @Test
  public void idReturnsAreaTypeCity() {
    assertThat(AreaType.valueOf(3), is(AreaType.CITY));
  }

  @Test
  public void nullReturnsAreaTypeUndefined() {
    Integer id = null;
    assertThat(AreaType.valueOf(id), is(AreaType.UNDEFINED));
  }

  @Test(expected = IllegalArgumentException.class)
  public void unrecognisedIdThrowsException() {
    AreaType.valueOf(5);
  }

}
