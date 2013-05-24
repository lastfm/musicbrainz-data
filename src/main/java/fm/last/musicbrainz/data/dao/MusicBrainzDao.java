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

import java.util.UUID;

interface MusicBrainzDao<T> {

  /**
   * @return Null if no entity with {@code id} exists
   */
  T getById(int id);

  /**
   * Finds an entity by canonical GID or redirected GID.
   * 
   * @return Null if no entity with associated {@code gid} exists
   */
  T getByGid(UUID gid);

}
