package com.qzn.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.qzn.auth.UserInfo;
import com.qzn.auth.UpdateSet;
import com.qzn.models.validator.constraints.Email;

@Entity
@Table(name = "users")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User extends Model<Long> implements UserInfo, UpdateSet {

	private static final long serialVersionUID = 1L;

	public static final Integer LOGIN_LOCK_STATUS_UNLOCK = 0; // 未锁定
	public static final Integer LOGIN_LOCK_STATUS_LOCKING = 1; // 锁定中

	public static final Integer PASSWORD_STATUS_SYSINIT = 0; // 初期化
	public static final Integer PASSWORD_STATUS_USERRESET = 1; // 再设定

	public static final Integer DELETE_FLAG_UNDELETE = 0; // 未删除
	public static final Integer DELETE_FLAG_DELETED = 1; // 已删除

	@Id
	@TableGenerator(name = "user", table = "sys_sequences", pkColumnName = "seq_key", valueColumnName = "seq_value", pkColumnValue = "user_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user")
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	@NotBlank(message = "{errors.validation.input.required}")
	@Length(max = 128, message = "{errors.validation.input.maxlength}")
	private String username;

	@Column(name = "fullname")
	@NotBlank(message = "{errors.validation.input.required}")
	@Length(max = 128, message = "{errors.validation.input.maxlength}")
	private String fullname;

	@Column(name = "password")
	@Length(max = 64, message = "{errors.validation.input.maxlength}")
	private String password;

	@Column(name = "age")
	private Integer age;

	@Column(name = "sex")
	private Integer sex;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	@Email(message = "{errors.validation.format.email}")
	@Length(max = 128, message = "{errors.validation.input.maxlength}")
	private String email;

	@Column(name = "power")
	private Integer power;

	@Column(name = "login_lock_status")
	private Integer loginLockStatus;

	@Column(name = "login_error_count")
	private Integer loginErrorCount;

	@Column(name = "password_status")
	private Integer passwordStatus;

	@Column(name = "delete_flag")
	private Integer deleteFlag;

	@Column(name = "update_user_id")
	private Long updateUserId;

	@Column(name = "update_datetime")
	private Timestamp updateDatetime;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name = "create_datetime")
	private Timestamp createDatetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getLoginLockStatus() {
		return loginLockStatus;
	}

	public void setLoginLockStatus(Integer loginLockStatus) {
		this.loginLockStatus = loginLockStatus;
	}

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Integer getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(Integer passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

}
