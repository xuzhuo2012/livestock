package edu.hbut.livestock.entity;

import java.util.Date;

/**
 * ImmunizationId entity. @author MyEclipse Persistence Tools
 */

public class ImmunizationId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3217387562338524796L;
	public static final String USERID = "userid";
	public static final String IMMUNIZATIONTIME = "immunizationTime";
	public static final String HOUSEID = "houseid";
	
	private String userid;
	private Date immunizationTime;
	private int houseid;

	// Constructors

	/** default constructor */
	public ImmunizationId() {
	}

	/** full constructor */
	public ImmunizationId(String userid, Date immunizationTime, int houseid) {
		this.userid = userid;
		this.immunizationTime = immunizationTime;
		this.houseid = houseid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getImmunizationTime() {
		return this.immunizationTime;
	}

	public void setImmunizationTime(Date immunizationTime) {
		this.immunizationTime = immunizationTime;
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
		result = prime * result + houseid;
		result = prime
				* result
				+ ((immunizationTime == null) ? 0 : immunizationTime.hashCode());
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
		ImmunizationId other = (ImmunizationId) obj;
		if (houseid != other.houseid)
			return false;
		if (immunizationTime == null) {
			if (other.immunizationTime != null)
				return false;
		} else if (!immunizationTime.equals(other.immunizationTime))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}


}