package com.qzn.auth;

import java.io.Serializable;
import java.sql.Timestamp;

public interface UpdateSet extends Serializable {

	Timestamp getCreateDatetime();

	void setCreateDatetime(Timestamp createDatetime);

	Timestamp getUpdateDatetime();

	void setUpdateDatetime(Timestamp updateDatetime);

}
