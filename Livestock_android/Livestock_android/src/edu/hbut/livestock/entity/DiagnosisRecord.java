package edu.hbut.livestock.entity;

/**
 * DiagnosisRecord entity. @author MyEclipse Persistence Tools
 */

public class DiagnosisRecord extends HeaderParamSupportEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2498494845219546513L;
	// Fields
	
	public static final String ID = "id";
	
	public static final String LIVESTOCKID = "id.livestockid";
	
	public static final String DIAGNOSISDATE = "id.diagnosisDate";
	
	public static final String AGE = "age";
	
	public static final String REASON = "reason";
	
	public static final String DOCTOR = "doctor";
	
	public static final String MEDICINE = "medicine";
	
	public static final String METHOD = "method";
	
	public static final String RESULT = "result";

	private DiagnosisRecordId id;
	
	private Livestock livestock;
	
	private int age;
	
	private String reason;
	
	private String doctor;
	
	private String medicine;
	
	private String method;
	
	private String result;

	// Constructors

	/** default constructor */
	public DiagnosisRecord() {
	}

	/** minimal constructor */
	public DiagnosisRecord(DiagnosisRecordId id, Livestock livestock) {
		this.id = id;
		this.livestock = livestock;
	}

	/** full constructor */
	public DiagnosisRecord(DiagnosisRecordId id, Livestock livestock,
			int age, String reason, String doctor, String medicine,
			String method, String result) {
		this.id = id;
		this.livestock = livestock;
		this.age = age;
		this.reason = reason;
		this.doctor = doctor;
		this.medicine = medicine;
		this.method = method;
		this.result = result;
	}

	// Property accessors

	public DiagnosisRecordId getId() {
		return this.id;
	}

	public void setId(DiagnosisRecordId id) {
		this.id = id;
	}

	public Livestock getLivestock() {
		return this.livestock;
	}

	public void setLivestock(Livestock livestock) {
		this.livestock = livestock;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getMedicine() {
		return this.medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}