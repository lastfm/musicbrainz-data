/*
 * Copyright 2012 Aurélien Mino <aurelien.mino@gmail.com>
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

import java.util.Map;

import com.google.common.collect.Maps;

public enum Gender {
  /* */
  MALE(1, "Male"),
  /* */
  FEMALE(2, "Female"),
  /* */
  OTHER(3, "Other"),
  /* */
  UNDEFINED(null, null);

  private static final Map<Integer, Gender> idToType;

  static {
    idToType = Maps.newHashMap();
    for (Gender value : values()) {
      idToType.put(value.getId(), value);
    }
  }

  private final Integer id;
  private final String name;

  private Gender(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public static Gender valueOf(Integer id) {
    Gender type = idToType.get(id);
    if (type == null) {
      throw new IllegalArgumentException("Unrecognized artist genre: " + id);
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
