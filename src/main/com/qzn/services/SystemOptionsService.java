package com.qzn.services;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.qzn.models.Master;

public interface SystemOptionsService {

	List<Master> loadAllMasters() throws ServiceException;

}
