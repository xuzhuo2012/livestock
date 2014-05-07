package edu.hbut.livestock.entity;

/**
 * FeddingRecordId entity. @author MyEclipse Persistence Tools
 */

public class FeedingRecordId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5289297713236084743L;
	
	private String feedName;
	
	private String factory;
	
	private int houseid;
	
	private String batchNumber;
	
	public static final String FEED_NAME = "feedName";
	
	public static final String FACTORY = "factory";
	
	public static final String HOUSEID = "houseid";
	
	public static final String BATCH_NUMBER = "batchNumber";

	// Constructors

	/** default constructor */
	public FeedingRecordId() {
	}

	/** full constructor */
	public FeedingRecordId(String feedName, String factory, int houseid,
			String batchNumber) {
		this.feedName = feedName;
		this.factory = factory;
		this.houseid = houseid;
		this.batchNumber = batchNumber;
	}

	// Property accessors

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

	public int getHouseid() {
		return this.houseid;
	}

	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}

	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((batchNumber == null) ? 0 : batchNumber.hashCode());
		result = prime * result + ((factory == null) ? 0 : factory.hashCode());
		result = prime * result
				+ ((feedName == null) ? 0 : feedName.hashCode());
		result = prime * result + houseid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedingRecordId other = (FeedingRecordId) obj;
		if (batchNumber == null) {
			if (other.batchNumber != null)
				return false;
		} else if (!batchNumber.equals(other.batchNumber))
			return false;
		if (factory == null) {
			if (other.factory != null)
				return false;
		} else if (!factory.equals(other.factory))
			return false;
		if (feedName == null) {
			if (other.feedName != null)
				return false;
		} else if (!feedName.equals(other.feedName))
			return false;
		if (houseid != other.houseid)
			return false;
		return true;
	}

}