package com.qzn.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin_user")
public class adminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137279744955162255L;

	@Id
//	@Size(max = 1,message = "{typeMismatch.admin_user.id}")
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;
	
	@Size(max = 1,message = "{typeMismatch.admin_user.id}")
	@Column(name = "username")
	private String name;

	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "sex")
	private int sex;
	
	@Column(name = "address")
	private String address;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updatedate")
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	

}
