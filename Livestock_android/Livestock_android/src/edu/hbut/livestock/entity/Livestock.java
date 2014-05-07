package edu.hbut.livestock.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Livestock entity. @author MyEclipse Persistence Tools
 */

public class Livestock extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -676634003004580L;
	
	private String livestockid;
	
	private Date createTime;
	
	private String livestockType;
	
	private Set<DiagnosisRecord> diagnosisRecords = new HashSet<DiagnosisRecord>(0);
	
	private Set<DeathProcess> dethProcesses = new HashSet<DeathProcess>(0);
	
	//  Ù–‘√˚
	public static final String LIVESTOCKID = "livestockid";
	
	public static final String CREATE_TIME = "createTime";
	
	public static final String LIVESTOCK_TYPE = "livestockType";
	
	// Constructors

	/** default constructor */
	public Livestock() {
	}

	/** minimal constructor */
	public Livestock(String livestockid) {
		this.livestockid = livestockid;
	}

	/** full constructor */
	public Livestock(String livestockid, Date createTime, String livestockType,
			Set<DiagnosisRecord> diagnosisRecords, Set<DeathProcess> dethProcesses) {
		this.livestockid = livestockid;
		this.createTime = createTime;
		this.livestockType = livestockType;
		this.diagnosisRecords = diagnosisRecords;
		this.dethProcesses = dethProcesses;
	}

	// Property accessors

	public String getLivestockid() {
		return this.livestockid;
	}

	public void setLivestockid(String livestockid) {
		this.livestockid = livestockid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLivestockType() {
		return this.livestockType;
	}

	public void setLivestockType(String livestockType) {
		this.livestockType = livestockType;
	}

	public Set<DiagnosisRecord> getDiagnosisRecords() {
		return this.diagnosisRecords;
	}

	public void setDiagnosisRecords(Set<DiagnosisRecord> diagnosisRecords) {
		this.diagnosisRecords = diagnosisRecords;
	}

	public Set<DeathProcess> getDethProcesses() {
		return this.dethProcesses;
	}

	public void setDethProcesses(Set<DeathProcess> dethProcesses) {
		this.dethProcesses = dethProcesses;
	}

}