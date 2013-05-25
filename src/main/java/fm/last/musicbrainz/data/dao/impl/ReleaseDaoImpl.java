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

import fm.last.musicbrainz.data.dao.ReleaseDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Release;

@Repository("musicBrainzReleaseDaoImpl")
@Transactional("musicBrainzTransactionManager")
public class ReleaseDaoImpl extends AbstractMusicBrainzHibernateDao<Release> implements ReleaseDao {

  public ReleaseDaoImpl() {
    super(Release.class);
  }

  @Override
  public Release getById(int id) {
    return get(id);
  }

  @Override
  public Release getByGid(UUID gid) {
    return uniqueResult(query(
        "from " + Release.class.getSimpleName() + " release left outer join release.redirectedGids gids"
            + " where release.gid = :gid or :gid in (gids)").setParameter("gid", gid, PostgresUUIDType.INSTANCE));
  }

  @Override
  public List<Release> getByArtist(Artist artist) {
    return list(query(
        "select release from " + Release.class.getSimpleName()
            + " release join release.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artistId").setInteger("artistId", artist.getId()));
  }

  @Override
  public List<Release> getByArtistAndName(Artist artist, String releaseName) {
    return list(query(
        "select release from " + Release.class.getName()
            + " release join release.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artistId and upper(release.name.name) = upper(:name)").setInteger(
        "artistId", artist.getId()).setString("name", releaseName));
  }
}
