package edu.hbut.livestock.entity;

public class GroupPermission implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747980099106714052L;
	
	private GroupPermissionId id;
	
	private SysModule sysModule;
	
	private UserGroup userGroup;
	
	//  Ù–‘√˚
	public static final String ID = "id";
	
	public static final String SYS_MODULE = "sysModule";
	
	public static final String SYS_MODULE_ID = "sysModule.moduleid";
	
	public static final String USER_GROUP_ID = "userGroup.groupid";
	
	public static final String USER_GROUP = "userGroup";
	
	public static final String GROUPID = "id.group";
	
	public static final String MODULEID = "id.moduleid";

	// Constructors

	/** default constructor */
	public GroupPermission() {
	}

	/** full constructor */
	public GroupPermission(GroupPermissionId id, SysModule sysModule,
			UserGroup userGroup) {
		this.id = id;
		this.sysModule = sysModule;
		this.userGroup = userGroup;
	}

	public GroupPermissionId getId() {
		return this.id;
	}

	public void setId(GroupPermissionId id) {
		this.id = id;
	}

	public SysModule getSysModule() {
		return this.sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}