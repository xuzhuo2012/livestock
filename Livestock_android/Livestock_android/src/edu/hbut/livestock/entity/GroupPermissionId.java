package edu.hbut.livestock.entity;

public class GroupPermissionId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -251577504617768413L;
	
	private String groupid;
	
	private String moduleid;
	
	//  Ù–‘√˚
	public static final String GROUPID = "groupid";
	
	public static final String MODULEID = "moduleid";

	// Constructors

	/** default constructor */
	public GroupPermissionId() {
	}

	/** full constructor */
	public GroupPermissionId(String groupid, String moduleid) {
		this.groupid = groupid;
		this.moduleid = moduleid;
	}

	// Property accessors

	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GroupPermissionId))
			return false;
		GroupPermissionId castOther = (GroupPermissionId) other;

		return ((this.getGroupid() == castOther.getGroupid()) || (this
				.getGroupid() != null && castOther.getGroupid() != null && this
				.getGroupid().equals(castOther.getGroupid())))
				&& ((this.getModuleid() == castOther.getModuleid()) || (this
						.getModuleid() != null
						&& castOther.getModuleid() != null && this
						.getModuleid().equals(castOther.getModuleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupid() == null ? 0 : this.getGroupid().hashCode());
		result = 37 * result
				+ (getModuleid() == null ? 0 : this.getModuleid().hashCode());
		return result;
	}

}