package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * QuarantineApplyId entity. @author MyEclipse Persistence Tools
 */

public class QuarantineApplyId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106281801517529894L;
	
	public static final String APPLY_DATE = "applyDate";

	public static final String USERID = "userid";
	
	private Date applyDate;
	
	private String userid;

	// Constructors

	/** default constructor */
	public QuarantineApplyId() {
	}

	/** full constructor */
	public QuarantineApplyId(Date applyDate, String userid) {
		this.applyDate = applyDate;
		this.userid = userid;
	}

	// Property accessors

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof QuarantineApplyId))
			return false;
		QuarantineApplyId castOther = (QuarantineApplyId) other;

		return ((this.getApplyDate() == castOther.getApplyDate()) || (this
				.getApplyDate() != null && castOther.getApplyDate() != null && this
				.getApplyDate().equals(castOther.getApplyDate())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getApplyDate() == null ? 0 : this.getApplyDate().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		return result;
	}

}