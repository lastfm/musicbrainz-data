package fm.last.musicbrainz.data.model;

import java.util.Map;

import com.google.common.collect.Maps;

public enum AreaType {
  /* */
  COUNTRY(1, "Country"),
  /* */
  SUBDIVISION(2, "Subdivision"),
  /* */
  CITY(3, "City"),
  /* */
  UNDEFINED(null, null);

  private static final Map<Integer, AreaType> idToType;

  static {
    idToType = Maps.newHashMap();
    for (AreaType value : values()) {
      idToType.put(value.getId(), value);
    }
  }

  private Integer id;
  private String name;

  private AreaType(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public static AreaType valueOf(Integer id) {
    AreaType type = idToType.get(id);
    if (type == null) {
      throw new IllegalArgumentException("Unrecognized area type: " + id);
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
