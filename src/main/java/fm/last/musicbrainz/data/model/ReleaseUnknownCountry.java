package fm.last.musicbrainz.data.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Access(AccessType.FIELD)
@Entity
@Table(name = "release_unknown_country", schema = "musicbrainz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class ReleaseUnknownCountry {

  @Id
  @Column(name = "release")
  private int release;

  @Embedded
  @AttributeOverrides({ @AttributeOverride(name = "year", column = @Column(name = "date_year")),
    @AttributeOverride(name = "month", column = @Column(name = "date_month")),
    @AttributeOverride(name = "day", column = @Column(name = "date_day")) })
  private PartialDate releaseDate;

  PartialDate getReleaseDate() {
    return releaseDate;
  }
}
