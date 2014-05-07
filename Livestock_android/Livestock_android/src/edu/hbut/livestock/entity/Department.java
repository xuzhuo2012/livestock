package edu.hbut.livestock.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
public class Department implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2068190338672674785L;
	
	private String departmentName;
	
	private String departmentAddress;
	
	private String tellphone;
	
	private String contactName;
	
	private Set<User> users = new HashSet<User>(0);

	//  Ù–‘√˚
	public static final String DEPARTMENT_ID = "departmentName";

	public static final String DEPARTMENT_ADDRESS = "departmentAddress";
	
	public static final String TELLPHONE = "tellphone";
	
	public static final String CONTACT_NAME = "contactName";

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String departmentName, String departmentAddress,
			String tellphone, String contactName) {
		this.departmentName = departmentName;
		this.departmentAddress = departmentAddress;
		this.tellphone = tellphone;
		this.contactName = contactName;
	}

	/** full constructor */
	public Department(String departmentName, String departmentAddress,
			String tellphone, String contactName, Set<User> users) {
		this.departmentName = departmentName;
		this.departmentAddress = departmentAddress;
		this.tellphone = tellphone;
		this.contactName = contactName;
		this.users = users;
	}

	// Property accessors
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentAddress() {
		return this.departmentAddress;
	}

	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress = departmentAddress;
	}

	public String getTellphone() {
		return this.tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}