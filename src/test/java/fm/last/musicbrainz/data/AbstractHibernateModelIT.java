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
package fm.last.musicbrainz.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/musicbrainz-data-test.xml" })
@Ignore("This is abstract")
public abstract class AbstractHibernateModelIT {

  private static final String TEST_SQL_PATH = "src/test/sql";

  private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

  @Autowired
  private DataSource dataSource;

  @Autowired
  private SessionFactory sessionFactory;

  protected Session session;

  private Statistics statistics;

  private long fetches;

  @Before
  public void setupDatabases() throws Exception {
    jdbcTemplate.setDataSource(dataSource);
    insertTestData(jdbcTemplate, new File(TEST_SQL_PATH, "musicbrainz-unittest.sql"));
  }

  @Before
  public void initialise() {
    statistics = sessionFactory.getStatistics();
    session = sessionFactory.openSession();

    statistics.setStatisticsEnabled(true);
    markFetchCount();
  }

  @After
  public void tearDown() {
    if (session != null) {
      session.close();
    }
    if (statistics != null) {
      statistics.setStatisticsEnabled(false);
    }
  }

  private void markFetchCount() {
    fetches = statistics.getEntityFetchCount();
  }

  protected long fetchCount() {
    return statistics.getEntityFetchCount() - fetches;
  }

  private void insertTestData(JdbcTemplate jdbcTemplate, File sqlFile) throws IOException {
    List<String> lines = Files.readLines(sqlFile, Charsets.UTF_8);
    jdbcTemplate.batchUpdate(lines.toArray(new String[] {}));
  }
}