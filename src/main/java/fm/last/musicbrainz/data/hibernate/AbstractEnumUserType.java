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
package fm.last.musicbrainz.data.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

abstract class AbstractEnumUserType<E extends Enum<E>> implements UserType {

  private final Class<E> clazz;
  private static final int[] SQL_TYPES = { Types.INTEGER };

  protected AbstractEnumUserType(Class<E> clazz) {
    this.clazz = clazz;
  }

  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

  @Override
  public Class<?> returnedClass() {
    return clazz;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == y) {
      return true;
    }
    if (null == x || null == y) {
      return false;
    }
    Class<?> javaClass = returnedClass();
    if (!javaClass.equals(x.getClass()) || !javaClass.equals(y.getClass())) {
      return false;
    }
    return x.equals(y);
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return x.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
    Integer name = resultSet.getInt(names[0]);
    if (resultSet.wasNull()) {
      name = null;
    }
    return getEnumConstant(name);
  }

  protected abstract E getEnumConstant(Integer value);

  @SuppressWarnings("unchecked")
  @Override
  public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException,
    SQLException {
    if (null == value) {
      preparedStatement.setNull(index, Types.INTEGER);
    } else {
      Integer enumValue = getIntegerValue((E) value);
      preparedStatement.setInt(index, enumValue);
    }
  }

  protected abstract Integer getIntegerValue(E value);

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

}
