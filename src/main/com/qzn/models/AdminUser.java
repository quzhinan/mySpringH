package com.qzn.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "admin_user")
public class AdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Size(max = 1,message = "{typeMismatch.admin_user.id}")
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
//	@Size(max = 1,message = "{typeMismatch.adminuser.id}")
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "sex")
	private Integer sex;
	
	@Column(name = "address")
	private String address;
	
	@Column(name="createdate", insertable = false, updatable = false)
	private Timestamp createDate;
	
	@Column(name="updatedate", insertable = false, updatable = false)
	private Timestamp updateDate;

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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	

}
