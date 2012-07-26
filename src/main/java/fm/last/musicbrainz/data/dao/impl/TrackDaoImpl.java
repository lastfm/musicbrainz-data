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
package fm.last.musicbrainz.data.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fm.last.musicbrainz.data.dao.TrackDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Track;

@Repository("musicBrainzTrackDaoImpl")
@Transactional("musicBrainzTransactionManager")
public class TrackDaoImpl extends AbstractMusicBrainzHibernateDao<Track> implements TrackDao {

  protected TrackDaoImpl() {
    super(Track.class);
  }

  @Override
  public List<Track> getByArtist(Artist musicBrainzArtist) {
    return list(query(
        "select track from " + Track.class.getSimpleName()
            + " track join track.artistCredit.artistCreditNames artistCreditNames"
            + " where artistCreditNames.artist.id = :artist").setInteger("artist", musicBrainzArtist.getId()));
  }
}
