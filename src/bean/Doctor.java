package bean;

public class Doctor {

	
private Integer doctorId;
	
	/**
	 * 登录账户
	 */
	private String doctorName;
	
	/**
	 * 登录密码
	 */
	private String doctorPwd;
	
	
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
	 * 咨询师等级
	 */
	private String level;
	
	
	/**
	 * 擅长方向
	 */
	private String skill;
	
	
	/**
	 * 咨询师个人照片
	 */
	private String img;
	
	
	/**
	 * 一般咨询地点
	 */
	private String place;


	public Integer getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getDoctorPwd() {
		return doctorPwd;
	}


	public void setDoctorPwd(String doctorPwd) {
		this.doctorPwd = doctorPwd;
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


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public String getSkill() {
		return skill;
	}


	public void setSkill(String skill) {
		this.skill = skill;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorPwd=" + doctorPwd + ", name="
				+ name + ", sex=" + sex + ", age=" + age + ", phone=" + phone + ", email=" + email + ", isActive="
				+ isActive + ", level=" + level + ", skill=" + skill + ", img=" + img + ", place=" + place + "]";
	}
	
	
}
