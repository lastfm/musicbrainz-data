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

import fm.last.musicbrainz.data.dao.RecordingDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Recording;

@Repository("musicBrainzRecordingDaoImpl")
@Transactional("musicBrainzTransactionManager")
public class RecordingDaoImpl extends AbstractMusicBrainzHibernateDao<Recording> implements RecordingDao {

  public RecordingDaoImpl() {
    super(Recording.class);
  }

  @Override
  public Recording getById(int id) {
    return get(id);
  }

  @Override
  public Recording getByGid(UUID gid) {
    return uniqueResult(query(
        "from " + Recording.class.getSimpleName() + " recording left outer join recording.redirectedGids gids"
            + " where recording.gid = :gid or :gid in (gids)").setParameter("gid", gid, PostgresUUIDType.INSTANCE));
  }

  @Override
  public List<Recording> getByArtistAndName(Artist artist, String trackName) {
    return list(query(
        "select recording from " + Recording.class.getName()
            + " recording join recording.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artistId and upper(recording.name.name) = upper(:name)")
        .setInteger("artistId", artist.getId()).setString("name", trackName));
  }

  @Override
  public List<Recording> getByArtist(Artist artist) {
    return list(query(
        "select recording from " + Recording.class.getName()
            + " recording join recording.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artistId").setInteger("artistId", artist.getId()));
  }

}
