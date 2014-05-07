package edu.hbut.livestock.entity;

import java.util.Date;

/**
 * DiagnosisRecordId entity. @author MyEclipse Persistence Tools
 */

public class DiagnosisRecordId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8473834295586624965L;
	public static final String LIVESTOCKID = "livestockid";
	public static final String DIAGNOSISDATE = "diagnosisDate";
	private String livestockid;
	private Date diagnosisDate;

	// Constructors

	/** default constructor */
	public DiagnosisRecordId() {
	}

	/** full constructor */
	public DiagnosisRecordId(String livestockid, Date diagnosisDate) {
		this.livestockid = livestockid;
		this.diagnosisDate = diagnosisDate;
	}

	// Property accessors

	public String getLivestockid() {
		return this.livestockid;
	}

	public void setLivestockid(String livestockid) {
		this.livestockid = livestockid;
	}

	public Date getDiagnosisDate() {
		return this.diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DiagnosisRecordId))
			return false;
		DiagnosisRecordId castOther = (DiagnosisRecordId) other;

		return ((this.getLivestockid() == castOther.getLivestockid()) || (this
				.getLivestockid() != null && castOther.getLivestockid() != null && this
				.getLivestockid().equals(castOther.getLivestockid())))
				&& ((this.getDiagnosisDate() == castOther.getDiagnosisDate()) || (this
						.getDiagnosisDate() != null
						&& castOther.getDiagnosisDate() != null && this
						.getDiagnosisDate()
						.equals(castOther.getDiagnosisDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getLivestockid() == null ? 0 : this.getLivestockid()
						.hashCode());
		result = 37
				* result
				+ (getDiagnosisDate() == null ? 0 : this.getDiagnosisDate()
						.hashCode());
		return result;
	}

}