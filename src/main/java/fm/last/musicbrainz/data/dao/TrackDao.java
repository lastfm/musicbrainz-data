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
package fm.last.musicbrainz.data.dao;

import java.util.List;

import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Track;

/**
 * Provides access to {@link Track}s.
 */
public interface TrackDao extends MusicBrainzDao<Track> {

  /**
   * Ignores the casing of {@code trackName}.
   * 
   * @return Empty list if no {@link Track}s are found
   */
  List<Track> getByArtistAndName(Artist artist, String trackName);

  /**
   * Also returns {@link Track}s where the {@link Artist} collaborated with other {@link Artist}s.
   * 
   * @return Empty list if {@link Artist} has no {@link Track}s
   */
  List<Track> getByArtist(Artist artist);

}