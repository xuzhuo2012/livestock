package edu.hbut.livestock.entity;

public class SysModule implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8449630358365051386L;
	
	private String moduleid;
	
	private SysModule sysModule;
	
	private String discription;
	
	//  Ù–‘√˚
	public static final String MODULEID = "moduleid";
	
	public static final String DISCRIPTION = "discription";
	
	public static final String PARENT = "sysModule";

	// private Set<GroupPermission> groupPermissions = new
	// HashSet<GroupPermission>(0);
	// private Set<SysModule> sysModules = new HashSet<SysModule>(0);

	// Constructors

	/** default constructor */
	public SysModule() {
	}

	/** minimal constructor */
	public SysModule(String moduleid) {
		this.moduleid = moduleid;
	}

	/** full constructor */
	public SysModule(String moduleid, SysModule sysModule, String discription) {
		this.moduleid = moduleid;
		this.sysModule = sysModule;
		this.discription = discription;
	}

	// Property accessors
	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public SysModule getSysModule() {
		return this.sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public String getDiscription() {
		return this.discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "SysModule [moduleid=" + moduleid + ", sysModule=" + sysModule
				+ ", discription=" + discription + "]";
	}

}