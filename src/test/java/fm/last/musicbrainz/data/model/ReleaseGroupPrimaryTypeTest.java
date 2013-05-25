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

public class ReleaseGroupPrimaryTypeTest {

  @Test
  public void statusReturnsReleaseGroupPrimaryTypeAlbum() {
    assertThat(ReleaseGroupPrimaryType.valueOf(1), is(ReleaseGroupPrimaryType.ALBUM));
  }

  @Test
  public void statusReturnsReleaseGroupPrimaryTypeSingle() {
    assertThat(ReleaseGroupPrimaryType.valueOf(2), is(ReleaseGroupPrimaryType.SINGLE));
  }

  @Test
  public void statusReturnsReleaseGroupPrimaryTypeEp() {
    assertThat(ReleaseGroupPrimaryType.valueOf(3), is(ReleaseGroupPrimaryType.EP));
  }

  @Test
  public void statusReturnsReleaseGroupPrimaryTypeOther() {
    assertThat(ReleaseGroupPrimaryType.valueOf(11), is(ReleaseGroupPrimaryType.OTHER));
  }

  @Test
  public void statusReturnsReleaseGroupPrimaryTypeBroadcast() {
    assertThat(ReleaseGroupPrimaryType.valueOf(12), is(ReleaseGroupPrimaryType.BROADCAST));
  }

  @Test
  public void nullReturnsReleaseGroupPrimaryTypeUndefined() {
    Integer id = null;
    assertThat(ReleaseGroupPrimaryType.valueOf(id), is(ReleaseGroupPrimaryType.UNDEFINED));
  }

  @Test(expected = IllegalArgumentException.class)
  public void unrecognisedStatusThrowsException() {
    ReleaseGroupPrimaryType.valueOf(5);
  }

}
