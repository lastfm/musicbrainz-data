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
package fm.last.musicbrainz.data.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.type.PostgresUUIDType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fm.last.musicbrainz.data.dao.ArtistDao;
import fm.last.musicbrainz.data.model.Artist;

@Repository("musicBrainzArtistDaoImpl")
@Transactional("musicBrainzTransactionManager")
public class ArtistDaoImpl extends AbstractMusicBrainzHibernateDao<Artist> implements ArtistDao {

  public ArtistDaoImpl() {
    super(Artist.class);
  }

  @Override
  public Artist getById(int id) {
    return get(id);
  }

  @Override
  public List<Artist> getByName(String name) {
    return list(query("from " + Artist.class.getSimpleName() + " where upper(name.name) = upper(:name)").setString(
        "name", name));
  }

  @Override
  public Artist getByGid(UUID gid) {
    return uniqueResult(query(
        "from " + Artist.class.getSimpleName() + " artist left outer join artist.redirectedGids gids "
            + "where artist.gid = :gid or :gid in (gids)").setParameter("gid", gid, PostgresUUIDType.INSTANCE));
  }
}
