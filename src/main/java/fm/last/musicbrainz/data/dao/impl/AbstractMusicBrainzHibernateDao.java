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

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract class AbstractMusicBrainzHibernateDao<INTERFACE> {

  private static final Logger log = LoggerFactory.getLogger(AbstractMusicBrainzHibernateDao.class);

  private final Class<? extends INTERFACE> entityClass;

  @Autowired
  @Qualifier("musicBrainzSessionFactory")
  private SessionFactory sessionFactory;

  protected AbstractMusicBrainzHibernateDao(Class<? extends INTERFACE> entityClass) {
    this.entityClass = entityClass;
    log.debug("Instantiated DAO '{}' for entity type '{}'", getClass().getName(), entityClass.getName());
  }

  protected Query query(String hql) {
    return currentSession().createQuery(hql);
  }

  protected Session currentSession() {
    return sessionFactory.getCurrentSession();
  }

  @SuppressWarnings("unchecked")
  protected List<INTERFACE> list(Query query) {
    return query.list();
  }

  @SuppressWarnings("unchecked")
  protected INTERFACE uniqueResult(Query query) {
    return (INTERFACE) query.uniqueResult();
  }

  @SuppressWarnings("unchecked")
  protected INTERFACE get(Serializable id) {
    return (INTERFACE) currentSession().get(entityClass, id);
  }

}
