package com.qzn.auth;

import java.io.Serializable;
import java.sql.Timestamp;

public interface UpdateSet extends Serializable {

	Long getCreateUserId();

	void setCreateUserId(Long id);

	Timestamp getCreateDatetime();

	void setCreateDatetime(Timestamp createDatetime);

	Long getUpdateUserId();

	void setUpdateUserId(Long id);

	Timestamp getUpdateDatetime();

	void setUpdateDatetime(Timestamp updateDatetime);

}
