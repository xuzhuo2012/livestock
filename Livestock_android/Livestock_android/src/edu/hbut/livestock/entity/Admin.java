package edu.hbut.livestock.entity;

public class Admin extends Info implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3574861184833433170L;
	// Fields

	private String userid;
	
	private String password;
	
	public static final String USERID = "userid";
	
	public static final String PASSWORD = "password";

	// Constructors

	/** default constructor */
	public Admin() {
	}

	// Property accessors
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}