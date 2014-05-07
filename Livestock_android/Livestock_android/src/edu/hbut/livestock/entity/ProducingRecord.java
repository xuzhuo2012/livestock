package edu.hbut.livestock.entity;


/**
 * ProducingRecord entity. @author MyEclipse Persistence Tools
 */

public class ProducingRecord extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 242636982269291375L;
	
	private ProducingRecordId id;
	
	private User user;
	
	private String changeType;
	
	private int changeCount;
	
	//  Ù–‘√˚
	public static final String ID = "id";
	
	public static final String USERID = "id.userid";
	
	public static final String HOUSEID = "id.houseid";
	
	public static final String CHANGE_TYPE = "changeType";
	
	public static final String CHANGE_COUNT = "changeCount";
	
	public static final String CHANGE_DATE = "id.changeDate";

	// Constructors

	/** default constructor */
	public ProducingRecord() {
	}

	/** minimal constructor */
	public ProducingRecord(ProducingRecordId id, User user) {
		this.id = id;
		this.user = user;
	}
	// Property accessors

	public ProducingRecordId getId() {
		return this.id;
	}

	public void setId(ProducingRecordId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getChangeType() {
		return this.changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public int getChangeCount() {
		return this.changeCount;
	}

	public void setChangeCount(int changeCount) {
		this.changeCount = changeCount;
	}

}