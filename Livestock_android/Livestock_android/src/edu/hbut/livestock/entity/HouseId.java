package edu.hbut.livestock.entity;

public class HouseId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3180778705514204018L;
	private int houseid;
	
	private String userid;
	
	public static final String HOUSEID = "houseid";
	
	public static final String USERID = "userid";

	// Constructors

	/** default constructor */
	public HouseId() {
	}

	/** full constructor */
	public HouseId(int houseid, String userid) {
		this.houseid = houseid;
		this.userid = userid;
	}

	// Property accessors

	public int getHouseid() {
		return this.houseid;
	}

	public void setHouseid(int houseid) {
		this.houseid = houseid;
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
		HouseId other = (HouseId) obj;
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