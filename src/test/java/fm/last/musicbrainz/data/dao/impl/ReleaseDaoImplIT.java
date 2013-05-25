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
package fm.last.musicbrainz.data.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import fm.last.musicbrainz.data.AbstractHibernateModelIT;
import fm.last.musicbrainz.data.dao.ReleaseDao;
import fm.last.musicbrainz.data.model.Artist;
import fm.last.musicbrainz.data.model.Release;

public class ReleaseDaoImplIT extends AbstractHibernateModelIT {

  @Autowired
  private ReleaseDao dao;

  @Test
  public void getByExistingIdReturnsOneRelese() {
    Release release = dao.getById(1);
    assertThat(release.getName(), is("The Best of Rick Astley"));
  }

  @Test
  public void getByNotExistingIdReturnsNull() {
    Release release = dao.getById(9001);
    assertThat(release, is(nullValue()));
  }

  @Test
  public void getByExistingGidReturnsOneRelease() {
    UUID gid = UUID.fromString("e1f5f807-3851-48fb-838b-fb8a069f53e7");
    Release release = dao.getByGid(gid);
    assertThat(release.getName(), is("The Best of Rick Astley"));
  }

  @Test
  public void getByNotExistingGidReturnsNull() {
    UUID gid = UUID.fromString("b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d");
    Release release = dao.getByGid(gid);
    assertThat(release, is(nullValue()));
  }

  @Test
  public void getByExistingRedirectedGidReturnsOneRelease() {
    UUID gid = UUID.fromString("5d32bacc-d62a-4e77-9f0e-d934e53d5359");
    Release release = dao.getByGid(gid);
    assertThat(release.getName(), is("Multi-Disc Extravaganza"));
  }

  @Test
  public void getByExistingGidReturnsOneReleaseThatHasNoRedirectedGids() {
    UUID gid = UUID.fromString("f80addb0-1f8c-3c37-a4a9-6f8867be35fe");
    Release release = dao.getByGid(gid);
    assertThat(release.getName(), is("The Warning"));
  }

  @Test
  public void getByExistingArtistReturnsThreeReleases() {
    Set<String> expectedReleaseNames = Sets.newHashSet("The Warning", "One Life Stand", "Multi-Disc Extravaganza");
    Set<String> actualReleaseNames = Sets.newHashSet();
    Artist artist = (Artist) session.load(Artist.class, 4);
    for (Release release : dao.getByArtist(artist)) {
      actualReleaseNames.add(release.getName());
    }
    assertThat(actualReleaseNames, is(expectedReleaseNames));
  }

  @Test
  public void getByArtistThatHasNoReleasesReturnsEmptyList() {
    Artist artist = (Artist) session.load(Artist.class, 1);
    List<Release> releases = dao.getByArtist(artist);
    assertThat(releases, hasSize(0));
  }

  @Test
  public void getByArtistAndNameReturnsTwoReleases() {
    Artist artist = (Artist) session.load(Artist.class, 4);
    String releaseName = "The Warning";
    List<Release> releases = dao.getByArtistAndName(artist, releaseName);
    assertThat(releases, hasSize(2));
    for (Release release : releases) {
      assertThat(release.getName(), is(releaseName));
    }
  }

  @Test
  public void getByArtistAndUppercaseNameReturnsTwoReleases() {
    Artist artist = (Artist) session.load(Artist.class, 4);
    List<Release> releases = dao.getByArtistAndName(artist, "THE WARNING");
    assertThat(releases, hasSize(2));
  }

  @Test
  public void getByArtistAndLowercaseNameReturnsTwoReleases() {
    Artist artist = (Artist) session.load(Artist.class, 4);
    List<Release> releases = dao.getByArtistAndName(artist, "the warning");
    assertThat(releases, hasSize(2));
  }

  @Test
  public void getByArtistAndNotExistingNameReturnsEmptyList() {
    Artist artist = (Artist) session.load(Artist.class, 3);
    List<Release> releases = dao.getByArtistAndName(artist, "does not exist");
    assertThat(releases, hasSize(0));
  }
}
