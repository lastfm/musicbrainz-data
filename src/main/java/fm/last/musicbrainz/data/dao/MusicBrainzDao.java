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
