package edu.hbut.livestock.entity;

import java.sql.Date;

/**
 * MedicineRecord entity. @author MyEclipse Persistence Tools
 */

public class MedicineRecord extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8303670549327089345L;
	
	public final static String STARTTIME = "startTime";
	public final static String PRODUCTNAME = "productName";
	public final static String FACTORY = "factory";
	public final static String MEDICINETYPE = "medicineType";
	public final static String STANDARD = "standard";
	public final static String VALIDITYPERIOD = "validityPeriod";
	public final static String BATCHNUMBER = "batchNumber";
	public final static String DOSAGE = "dosage";
	public final static String ENDTIME = "endTime";
	public final static String REMARK = "remark";
	
	private java.sql.Date startTime;
	private String productName;
	private String factory;
	private String medicineType;
	private String standard;
	private int validityPeriod;
	private int batchNumber;
	private int dosage;
	private Date endTime;
	private String remark;

	// Constructors

	/** default constructor */
	public MedicineRecord() {
	}

	/** minimal constructor */
	public MedicineRecord(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	/** full constructor */
	public MedicineRecord(java.sql.Date startTime, String productName,
			String factory, String medicineType, String standard,
			int validityPeriod, int batchNumber, int dosage,
			Date endTime, String remark) {
		this.startTime = startTime;
		this.productName = productName;
		this.factory = factory;
		this.medicineType = medicineType;
		this.standard = standard;
		this.validityPeriod = validityPeriod;
		this.batchNumber = batchNumber;
		this.dosage = dosage;
		this.endTime = endTime;
		this.remark = remark;
	}

	// Property accessors

	public java.sql.Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getMedicineType() {
		return this.medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getValidityPeriod() {
		return this.validityPeriod;
	}

	public void setValidityPeriod(int validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public int getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public int getDosage() {
		return this.dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}