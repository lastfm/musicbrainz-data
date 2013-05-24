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

public class ReleaseStatusTest {

  @Test
  public void statusReturnsReleaseStateOfficial() {
    assertThat(ReleaseStatus.valueOf(1), is(ReleaseStatus.OFFICIAL));
  }

  @Test
  public void statusReturnsReleaseStatePromotion() {
    assertThat(ReleaseStatus.valueOf(2), is(ReleaseStatus.PROMOTION));
  }

  @Test
  public void statusReturnsReleaseStateBootleg() {
    assertThat(ReleaseStatus.valueOf(3), is(ReleaseStatus.BOOTLEG));
  }

  @Test
  public void statusReturnsReleaseStatePseudoRelease() {
    assertThat(ReleaseStatus.valueOf(4), is(ReleaseStatus.PSEUDO_RELEASE));
  }

  @Test
  public void nullReturnsReleaseStateUndefined() {
    Integer id = null;
    assertThat(ReleaseStatus.valueOf(id), is(ReleaseStatus.UNDEFINED));
  }

  @Test(expected = IllegalArgumentException.class)
  public void unrecognisedStatusThrowsException() {
    ReleaseStatus.valueOf(5);
  }

}
