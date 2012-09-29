package fm.last.musicbrainz.data.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.DateTime;

@Embeddable
@Access(AccessType.PROPERTY)
public class PartialDate {

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

  /**
   * A naive way of handling partial release dates (i.e. data for year, month or day when a release was released is not
   * present).
   */
  public DateTime toDateTime() {
    if (year == null) {
      return null;
    }
    return new DateTime(year, month == null ? 1 : month, day == null ? 1 : day, 0, 0);
  }

}
