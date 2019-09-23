package baen;

import java.util.Date;

public class Client {

	private Integer clientId;
	
	/**
	 * 登录账户
	 */
	private String clientName;
	
	/**
	 * 登录密码
	 */
	private String clientPwd;
	
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	
	private Integer sex;
	
	private Integer age;
	
	private String phone;
	
	private String email;
	
	private Integer isActive;
	
	/**
	 * 注册时间
	 */
	private Date regionTime;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Date getRegionTime() {
		return regionTime;
	}

	public void setRegionTime(Date regionTime) {
		this.regionTime = regionTime;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientPwd=" + clientPwd + ", name="
				+ name + ", sex=" + sex + ", age=" + age + ", phone=" + phone + ", email=" + email + ", isActive="
				+ isActive + ", regionTime=" + regionTime + "]";
	}
	
	
}
