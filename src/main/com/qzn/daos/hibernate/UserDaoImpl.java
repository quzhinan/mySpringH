package com.qzn.daos.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.qzn.controllers.Pagination;
import com.qzn.daos.UserDao;
import com.qzn.models.User;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	@Override
	public Class<User> getModelClass() throws DataAccessException {
		return User.class;
	}

	public Pagination<User> loadPage(int pageSize, int startIndex, Map<String, String> search) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> values = new HashMap<String, Object>();
		hql.append(" FROM User WHERE 1=1");
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(values);
		return loadPage(query, pageSize, startIndex);
	}

}