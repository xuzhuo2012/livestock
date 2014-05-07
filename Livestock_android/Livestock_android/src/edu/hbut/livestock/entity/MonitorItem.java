package edu.hbut.livestock.entity;

public class MonitorItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4930672719191145403L;
	
	private String itemid;
	
	public static final String ID = "itemid";

	// Constructors

	/** default constructor */
	public MonitorItem() {
	}

	/** full constructor */
	public MonitorItem(String itemid) {
		this.itemid = itemid;
	}

	// Property accessors
	public String getItemid() {
		return this.itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

}