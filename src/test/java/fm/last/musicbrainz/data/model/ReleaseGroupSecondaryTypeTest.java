/*
 * Copyright 2013 The musicbrainz-data Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package fm.last.musicbrainz.data.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReleaseGroupSecondaryTypeTest {

    @Test
    public void statusReturnsReleaseGroupSecondyTypeCompilation() {
        assertThat(ReleaseGroupSecondaryType.valueOf(1), is(ReleaseGroupSecondaryType.COMPILATION));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeSoundtrack() {
        assertThat(ReleaseGroupSecondaryType.valueOf(2), is(ReleaseGroupSecondaryType.SOUNDTRACK));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeSpokenword() {
        assertThat(ReleaseGroupSecondaryType.valueOf(3), is(ReleaseGroupSecondaryType.SPOKENWORD));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeInterview() {
        assertThat(ReleaseGroupSecondaryType.valueOf(4), is(ReleaseGroupSecondaryType.INTERVIEW));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeAudioBook() {
        assertThat(ReleaseGroupSecondaryType.valueOf(5), is(ReleaseGroupSecondaryType.AUDIOBOOK));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeLive() {
        assertThat(ReleaseGroupSecondaryType.valueOf(6), is(ReleaseGroupSecondaryType.LIVE));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeRemix() {
        assertThat(ReleaseGroupSecondaryType.valueOf(7), is(ReleaseGroupSecondaryType.REMIX));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeDJMix() {
        assertThat(ReleaseGroupSecondaryType.valueOf(8), is(ReleaseGroupSecondaryType.DJ_MIX));
    }

    @Test
    public void statusReturnsReleaseGroupSecondyTypeMixtapeStreet() {
        assertThat(ReleaseGroupSecondaryType.valueOf(9),
                is(ReleaseGroupSecondaryType.MIXTAPE_STREET));
    }

    @Test
    public void nullReturnsReleaseGroupSecondaryTypeUndefined() {
        Integer id = null;
        assertThat(ReleaseGroupSecondaryType.valueOf(id), is(ReleaseGroupSecondaryType.UNDEFINED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unrecognisedStatusThrowsException() {
        ReleaseGroupSecondaryType.valueOf(10);
    }

}
