package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * FeddingRecord entity. @author MyEclipse Persistence Tools
 */

public class FeedingRecord extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7519417145141641425L;
	
	private FeedingRecordId id;
	
	private Date startTime;
	
	private int dosage;
	
	private Date endTime;
	
	private String animalType;
	
	private String userid;

	//  Ù–‘√˚
	public static final String ID = "id";
	
	public static final String FEED_NAME = "id.feedName";
	
	public static final String FACTORY = "id.factory";
	
	public static final String HOUSEID = "id.houseid";
	
	public static final String BATCH_NUMBER = "id.batchNumber";
	
	public static final String START_TIME = "startTime";
	
	public static final String DOSAGE = "dosage";
	
	public static final String ENND_TIME = "endTime";
	
	public static final String ANIMAL_TYPE = "animalType";
	
	public static final String LIVESTOCK_COUNT = "livestockCount";
	
	public static final String USERID = "userid";

	/** default constructor */
	public FeedingRecord() {
	}

	// Property accessors

	public FeedingRecordId getId() {
		return this.id;
	}

	public void setId(FeedingRecordId id) {
		this.id = id;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDosage() {
		return this.dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}