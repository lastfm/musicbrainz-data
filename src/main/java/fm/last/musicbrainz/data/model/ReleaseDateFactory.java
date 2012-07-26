/*
 * Copyright 2012 Last.fm
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

import org.joda.time.DateTime;

/**
 * A naive way of handling partial release dates (i.e. data for year, month or day when a release was released is not
 * present).
 */
enum ReleaseDateFactory {
  /* */
  INSTANCE;

  public DateTime valueOf(Short year, Short month, Short day) {
    if (year == null) {
      return null;
    }
    return new DateTime(year, month == null ? 1 : month, day == null ? 1 : day, 0, 0);
  }
}