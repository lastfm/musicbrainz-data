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

import java.util.Map;

import com.google.common.collect.Maps;

public enum ReleaseStatus {
  /* */
  OFFICIAL(1, "Official"),
  /* */
  PROMOTION(2, "Promotion"),
  /* */
  BOOTLEG(3, "Bootleg"),
  /* */
  PSEUDO_RELEASE(4, "Pseudo-Release"),
  /* */
  UNDEFINED(null, null);

  private static final Map<Integer, ReleaseStatus> idToType;

  static {
    idToType = Maps.newHashMap();
    for (ReleaseStatus value : values()) {
      idToType.put(value.getId(), value);
    }
  }

  private final Integer id;
  private final String name;

  private ReleaseStatus(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public static ReleaseStatus valueOf(Integer id) {
    ReleaseStatus status = idToType.get(id);
    if (status == null) {
      throw new IllegalArgumentException("Unrecognized release status: " + id);
    }
    return status;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
