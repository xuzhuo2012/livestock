package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * DethProcess entity. @author MyEclipse Persistence Tools
 */

public class DeathProcess extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3087190635205174235L;
	
	private Date processDate;
	
	private Department department;
	
	private Livestock livestock;
	
	private String reason;
	
	private String remark;
	
	//  Ù–‘√˚
	public static final String PROCESS_DATE = "processDate";
	
	public static final String DEPARTMENT_ID = "department.departmentName";
	
	public static final String DEPARTMENT = "department";
	
	public static final String LIVESTOCK_ID = "livestock.livestockid";
	
	public static final String LIVESTOCK = "livestock";
	
	public static final String REASON = "reason";
	
	public static final String REMARK = "remark";

	// Constructors

	/** default constructor */
	public DeathProcess() {
	}

	/** minimal constructor */
	public DeathProcess(Date processDate) {
		this.processDate = processDate;
	}

	/** full constructor */
	public DeathProcess(Date processDate, Department department,
			Livestock livestock, String reason, String remark) {
		this.processDate = processDate;
		this.department = department;
		this.livestock = livestock;
		this.reason = reason;
		this.remark = remark;
	}

	// Property accessors

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Livestock getLivestock() {
		return this.livestock;
	}

	public void setLivestock(Livestock livestock) {
		this.livestock = livestock;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}