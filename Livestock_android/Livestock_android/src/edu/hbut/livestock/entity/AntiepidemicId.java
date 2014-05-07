package edu.hbut.livestock.entity;

import java.util.Date;

/**
 * AntiepidemicId entity. @author MyEclipse Persistence Tools
 */

public class AntiepidemicId extends HeaderParamSupportEntity implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5277905378613044089L;

	private Date antiepidemicDate;

	private int houseid;

	private String monitorItem;

	private String userid;

	public static final String MONITOR_ITEM = "monitorItem";

	public static final String HOUSEID = "houseid";

	public static final String ANTIEPIDEMIC_DATE = "antiepidemicDate";

	public static final String USERID = "userid";

	// Constructors

	/** default constructor */
	public AntiepidemicId() {
	}

	/** full constructor */
	public AntiepidemicId(Date antiepidemicDate, int houseid,
			String monitorItem, String userid) {
		this.antiepidemicDate = antiepidemicDate;
		this.houseid = houseid;
		this.monitorItem = monitorItem;
		this.userid = userid;
	}

	// Property accessors

	public Date getAntiepidemicDate() {
		return this.antiepidemicDate;
	}

	public void setAntiepidemicDate(Date antiepidemicDate) {
		this.antiepidemicDate = antiepidemicDate;
	}

	public int getHouseid() {
		return this.houseid;
	}

	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}

	public String getMonitorItem() {
		return this.monitorItem;
	}

	public void setMonitorItem(String monitorItem) {
		this.monitorItem = monitorItem;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((antiepidemicDate == null) ? 0 : antiepidemicDate.hashCode());
		result = prime * result + houseid;
		result = prime * result
				+ ((monitorItem == null) ? 0 : monitorItem.hashCode());
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
		AntiepidemicId other = (AntiepidemicId) obj;
		if (antiepidemicDate == null) {
			if (other.antiepidemicDate != null)
				return false;
		} else if (!antiepidemicDate.equals(other.antiepidemicDate))
			return false;
		if (houseid != other.houseid)
			return false;
		if (monitorItem == null) {
			if (other.monitorItem != null)
				return false;
		} else if (!monitorItem.equals(other.monitorItem))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}