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

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class ArtistCreditNameCompositeKey implements Serializable {

  private static final long serialVersionUID = 21062012L;

  int artistCredit;
  short position;

  @Override
  public int hashCode() {
    return new HashCodeBuilder(2105, 2011).append(artistCredit).append(position).toHashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (object == this) {
      return true;
    }
    if (object.getClass() != getClass()) {
      return false;
    }
    ArtistCreditNameCompositeKey rhs = (ArtistCreditNameCompositeKey) object;
    return new EqualsBuilder().append(artistCredit, rhs.artistCredit).append(position, rhs.position).isEquals();
  }

}
