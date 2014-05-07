package edu.hbut.livestock.entity;

import java.util.HashSet;
import java.util.Set;


public class UserGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4071387092598483L;

	private String groupid;

	private Set<GroupPermission> groupPermissions = new HashSet<GroupPermission>();

	private Set<User> users = new HashSet<User>(0);

	//  Ù–‘√˚
	public static final String GROUPID = "groupid";

	// Constructors

	/** default constructor */
	public UserGroup() {
	}

	/** minimal constructor */
	public UserGroup(String groupid) {
		this.groupid = groupid;
	}

	/** full constructor */
	public UserGroup(String groupid, Set<GroupPermission> groupPermissions,
			Set<User> users) {
		this.groupid = groupid;
		this.groupPermissions = groupPermissions;
		this.users = users;
	}

	// Property accessors
	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public Set<GroupPermission> getGroupPermissions() {
		return this.groupPermissions;
	}

	public void setGroupPermissions(Set<GroupPermission> groupPermissions) {
		this.groupPermissions = groupPermissions;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}