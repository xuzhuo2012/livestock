package edu.hbut.livestock.entity;

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7051563379899000867L;
	
	private String userid;
	
	private String userName;
	
	private String sex;
	
	private String phone;
	
	private String address;
	
	private String postcode;
	
	private String email;
	
	private Admin admin;
	
	private User user;

	//  Ù–‘√˚
	public static final String USER_NAME = "userName";

	public static final String SEX = "sex";

	public static final String PHONE = "phone";

	public static final String ADDRESS = "address";

	public static final String POSTCODE = "postcode";

	public static final String EMAIL = "email";

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public UserInfo(String userid, String userName, String sex, String phone,
			String address, String postcode, String email, Admin admin,
			User user) {
		this.userid = userid;
		this.userName = userName;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.email = email;
		this.admin = admin;
		this.user = user;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}