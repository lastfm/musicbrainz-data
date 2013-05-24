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

import fm.last.musicbrainz.data.dao.TrackDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Track;

@Repository("musicBrainzTrackDaoImpl")
@Transactional("musicBrainzTransactionManager")
public class TrackDaoImpl extends AbstractMusicBrainzHibernateDao<Track> implements TrackDao {

  public TrackDaoImpl() {
    super(Track.class);
  }

  @Override
  public Track getById(int id) {
    return get(id);
  }

  @Override
  public Track getByGid(UUID gid) {
    return uniqueResult(query(
        "from " + Track.class.getSimpleName() + " track left outer join track.redirectedGids gids"
            + " where track.gid = :gid or :gid in (gids)").setParameter("gid", gid, PostgresUUIDType.INSTANCE));
  }

  @Override
  public List<Track> getByArtist(Artist musicBrainzArtist) {
    return list(query(
        "select track from " + Track.class.getSimpleName()
            + " track join track.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artist").setInteger("artist", musicBrainzArtist.getId()));
  }

  @Override
  public List<Track> getByArtistAndName(Artist artist, String trackName) {
    return list(query(
        "select track from " + Track.class.getName()
            + " track join track.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artistId and upper(track.name.name) = upper(:name)").setInteger(
        "artistId", artist.getId()).setString("name", trackName));
  }
}
