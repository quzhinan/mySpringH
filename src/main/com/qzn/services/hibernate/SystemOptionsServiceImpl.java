package com.qzn.services.hibernate;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.qzn.daos.MasterDao;
import com.qzn.models.Master;
import com.qzn.services.SystemOptionsService;

public class SystemOptionsServiceImpl implements SystemOptionsService {

	@Autowired
	private MasterDao masterDao;

	@Transactional(readOnly = true, rollbackFor = ServiceException.class)
	public List<Master> loadAllMasters() throws ServiceException {
		return masterDao.findAllAscOrderBy("id");
	}

}
