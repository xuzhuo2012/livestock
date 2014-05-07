package edu.hbut.livestock.entity;

import java.util.HashSet;
import java.util.Set;

public class User extends Info implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid;
	
	private Department department;
	
	private UserGroup userGroup;
	
	private String userPassword;
	
	private String userState;
	
	private String userPermission;
	
	private Set<House> houses = new HashSet<House>(0);

	//  Ù–‘√˚
	public static final String USERID = "userid";

	public static final String DEPARTMENT = "department";

	public static final String DEPARTMENT_ID = "department.departmentName";

	public static final String USER_GROUP = "userGroup";

	public static final String GROUPID = "userGroup.groupid";

	public static final String USER_PERMISSION = "userPermission";

	public static final String USER_STATE = "userState";
	
	public static final String PASSWORD = "userPassword";
	
	public static final String GROUP = "userGroup";
	
	// Constructors
	/*
	 *private String userid;
	private String userName;
	private String sex;
	private String phone;
	private String address;
	private String postcode;
	private String email;
	private Admin admin;
	private User user; 
	 */

	/** default constructor */
	public User() {
	}

	// Property accessors
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserState() {
		return this.userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserPermission() {
		return this.userPermission;
	}

	public void setUserPermission(String userPermission) {
		this.userPermission = userPermission;
	}

	public Set<House> getHouses() {
		return this.houses;
	}

	public void setHouses(Set<House> houses) {
		this.houses = houses;
	}

}