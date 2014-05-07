package edu.hbut.livestock.entity;

import java.sql.Timestamp;;

/**
 * ProducingRecordId entity. @author MyEclipse Persistence Tools
 */

public class ProducingRecordId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7317734528168243553L;
	
	private String userid;
	
	private int houseid;
	
	private Timestamp changeDate;
	
	// Ù–‘
	public static final String USERID = "userid";
	
	public static final String HOUSEID = "houseid";
	
	public static final String CHANGE_DATE = "changeDate";

	// Constructors

	/** default constructor */
	public ProducingRecordId() {
	}

	public ProducingRecordId(String userid, int houseid, Timestamp changeDate) {
		super();
		this.userid = userid;
		this.houseid = houseid;
		this.changeDate = changeDate;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public Timestamp getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getHouseid() {
		return this.houseid;
	}

	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((changeDate == null) ? 0 : changeDate.hashCode());
		result = prime * result + houseid;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		ProducingRecordId other = (ProducingRecordId) obj;
		if (changeDate == null) {
			if (other.changeDate != null)
				return false;
		} else if (!changeDate.equals(other.changeDate))
			return false;
		if (houseid != other.houseid)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}