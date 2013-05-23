/*
 * Copyright 2012 The musicbrainz-data Authors
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

import java.util.Comparator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Embeddable
@Access(AccessType.PROPERTY)
public class PartialDate implements Comparable<PartialDate> {

  @Column(name = "date_year")
  private Short year;

  @Column(name = "date_month")
  private Short month;

  @Column(name = "date_day")
  private Short day;

  public PartialDate() {
  }

  public PartialDate(Short year, Short month, Short day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public Short getYear() {
    return year;
  }

  public void setYear(Short year) {
    this.year = year;
  }

  public Short getMonth() {
    return month;
  }

  public void setMonth(Short month) {
    this.month = month;
  }

  public Short getDay() {
    return day;
  }

  public void setDay(Short day) {
    this.day = day;
  }

  public LocalDate toLocalDate() {
    if (year == null) {
      return null;
    }
    return LocalDate.parse(toString());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PartialDate) {
      PartialDate other = (PartialDate) obj;
      return Objects.equal(year, other.getYear()) && Objects.equal(month, other.getMonth())
          && Objects.equal(day, other.getDay());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(year, month, day);
  }

  @Override
  public String toString() {
    if (year == null) {
      return "";
    }
    String separator = "-";
    StringBuilder sb = new StringBuilder(year);
    sb.append(String.format("%04d", year));
    if (month != null) {
      sb.append(separator).append(String.format("%02d", month));
      if (day != null) {
        sb.append(separator).append(String.format("%02d", day));
      }
    }
    return sb.toString();
  }

  @Override
  public int compareTo(PartialDate other) {
    Comparator<Short> comparator = Ordering.natural().nullsFirst();
    return ComparisonChain.start().compare(year, other.getYear(), comparator)
        .compare(month, other.getMonth(), comparator).compare(day, other.getDay(), comparator).result();
  }

}
