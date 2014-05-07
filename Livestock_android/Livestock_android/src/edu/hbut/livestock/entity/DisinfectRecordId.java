package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * DisinfectRecordId entity. @author MyEclipse Persistence Tools
 */

public class DisinfectRecordId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3568444466613237051L;

	public static final String DISINFECT_DATE = "disinfectDate";

	public static final String USERID = "userid";
	
	private String userid;
	
	private Date disinfectDate;

	// Constructors

	/** default constructor */
	public DisinfectRecordId() {
	}

	/** full constructor */
	public DisinfectRecordId(String userid, Date disinfectDate) {
		this.userid = userid;
		this.disinfectDate = disinfectDate;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getDisinfectDate() {
		return this.disinfectDate;
	}

	public void setDisinfectDate(Date disinfectDate) {
		this.disinfectDate = disinfectDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DisinfectRecordId))
			return false;
		DisinfectRecordId castOther = (DisinfectRecordId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getDisinfectDate() == castOther.getDisinfectDate()) || (this
						.getDisinfectDate() != null
						&& castOther.getDisinfectDate() != null && this
						.getDisinfectDate()
						.equals(castOther.getDisinfectDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getDisinfectDate() == null ? 0 : this.getDisinfectDate()
						.hashCode());
		return result;
	}

}