package bean;

/**
 * @author h w j
 * @instruction 
 * 管理员admin表，对应的实体类
 *
 */
public class Admin {

	/**
	 * 管理员id
	 */
	private Integer adminId;
	
	/**
	 * 登录账户
	 */
	private String adminName;
	
	/**
	 * 登录密码
	 */
	private String adminPwd;
	
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	
	/**
	 * 账户是否激活
	 */
	private Integer is_active;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
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

	public Integer getIs_active() {
		return is_active;
	}

	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPwd=" + adminPwd + ", name=" + name
				+ ", sex=" + sex + ", age=" + age + ", phone=" + phone + ", email=" + email + ", is_active=" + is_active
				+ "]";
	}
	
	
}
