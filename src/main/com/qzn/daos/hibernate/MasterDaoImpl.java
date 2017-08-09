package com.qzn.daos.hibernate;

import org.springframework.dao.DataAccessException;

import com.qzn.daos.MasterDao;
import com.qzn.models.Master;

public class MasterDaoImpl extends AbstractDao<Master, Long> implements MasterDao {

	@Override
	public Class<Master> getModelClass() throws DataAccessException {
		return Master.class;
	}

}
