package com.caipiao.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String phone;//电话

    private String password;//密码

    private Date modifiedTime;//修改时间

    private Date createTime;//创建时间
    
    private String registerCode;//注册码
    
    private Integer type;//用户类型
    
    private String token;//判断用户唯一标识
    
    private String limitData;//启用禁用
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLimitData() {
		return limitData;
	}

	public void setLimitData(String limitData) {
		this.limitData = limitData;
	}
	
	
}