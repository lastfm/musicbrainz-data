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
package fm.last.musicbrainz.data.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 */
public enum ReleaseGroupSecondaryType {
  /* */
  COMPILATION(1, "Compilation"),
  /* */
  SOUNDTRACK(2, "Soundtrack"),
  /* */
  SPOKENWORD(3, "Spokenword"),
  /* */
  INTERVIEW(4, "Interview"),
  /* */
  AUDIOBOOK(6, "Live"),
  /* */
  REMIX(7, "Remix"),
  /* */
  DJ_MIX(8, "DJ-mix"),
  /* */
  MIXTAPE_STREET(9, "Mixtape/Street"),
  /* */
  UNDEFINED(null, null);

  private static final Map<Integer, ReleaseGroupSecondaryType> idToType;

  static {
    idToType = Maps.newHashMap();
    for (ReleaseGroupSecondaryType value : values()) {
      idToType.put(value.getId(), value);
    }
  }

  private final Integer id;
  private final String name;

  private ReleaseGroupSecondaryType(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public static ReleaseGroupSecondaryType valueOf(Integer id) {
    ReleaseGroupSecondaryType type = idToType.get(id);
    if (type == null) {
      throw new IllegalArgumentException("Unrecognized release group secondary type: " + id);
    }
    return type;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
