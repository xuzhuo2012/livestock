package edu.hbut.livestock.entity;

import java.sql.Timestamp;

/**
 * Feed entity. @author MyEclipse Persistence Tools
 */

public class Feed extends HeaderParamSupportEntity implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4360040197839902949L;
	
	private Timestamp changeDate;
	
	private String feedName;
	
	private String factory;
	
	private String batchNumber;
	
	private String changeType;
	
	private int changeCount = 0;
	
	private String remark;
	
	//  Ù–‘√˚
	public static final String CHANGE_DATE = "changeDate";// id

	public static final String FEED_NAME = "feedName";

	public static final String FACTORY = "factory";

	public static final String BATCH_NUMBER = "batchNumber";

	public static final String CHANGE_TYPE = "changeType";

	public static final String CHANGE_COUNT = "changeCount";

	public static final String REMARK = "remark";

	// Constructors

	/** default constructor */
	public Feed() {
	}

	public Timestamp getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}

	public String getFeedName() {
		return this.feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}