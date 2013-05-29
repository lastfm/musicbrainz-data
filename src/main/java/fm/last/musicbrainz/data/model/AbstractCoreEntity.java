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

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@MappedSuperclass
public abstract class AbstractCoreEntity<ClassName extends AbstractName> {

  @Id
  @Column(name = "id")
  protected int id;

  @Column(name = "gid", nullable = false, unique = true)
  @Type(type = "pg-uuid")
  protected UUID gid;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "name")
  protected ClassName name;

  @Column(name = "comment")
  protected String comment;

  @Column(name = "last_updated")
  @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
  protected DateTime lastUpdated;

  public int getId() {
    return id;
  }

  public String getName() {
    return name.getName();
  }

  public String getComment() {
    return comment;
  }

  public DateTime getLastUpdated() {
    return lastUpdated;
  }

}
