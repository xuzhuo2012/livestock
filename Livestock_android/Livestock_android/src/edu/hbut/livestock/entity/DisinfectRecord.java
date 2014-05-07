package edu.hbut.livestock.entity;

public class DisinfectRecord extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9020262792619489511L;
	
	public static final String DISINFECT_DATE = "id.disinfectDate";

	public static final String DISINFECT_MEDICINE_NAME = "disinfectMedicineName";

	public static final String DISINFECT_PLACE = "disinfectPlace";

	public static final String DOSAGE = "dosage";

	public static final String MEDICINE_FACTORY = "medicineFactory";

	public static final String METHOD = "method";

	public static final String OPERATOR = "operator";

	public static final String USERID = "id.userid";

	public static final String ID = "id";
	
	private DisinfectRecordId id;
	
	private User user;
	
	private String disinfectPlace;
	
	private String disinfectMedicineName;
	
	private String medicineFactory;
	
	private int dosage;
	
	private String method;
	
	private String operator;

	// Constructors

	/** default constructor */
	public DisinfectRecord() {
	}

	/** minimal constructor */
	public DisinfectRecord(DisinfectRecordId id, User user) {
		this.id = id;
		this.user = user;
	}

	/** full constructor */
	public DisinfectRecord(DisinfectRecordId id, User user,
			String disinfectPlace, String disinfectMedicineName,
			String medicineFactory, int dosage, String method,
			String operator) {
		this.id = id;
		this.user = user;
		this.disinfectPlace = disinfectPlace;
		this.disinfectMedicineName = disinfectMedicineName;
		this.medicineFactory = medicineFactory;
		this.dosage = dosage;
		this.method = method;
		this.operator = operator;
	}

	// Property accessors

	public DisinfectRecordId getId() {
		return this.id;
	}

	public void setId(DisinfectRecordId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDisinfectPlace() {
		return this.disinfectPlace;
	}

	public void setDisinfectPlace(String disinfectPlace) {
		this.disinfectPlace = disinfectPlace;
	}

	public String getDisinfectMedicineName() {
		return this.disinfectMedicineName;
	}

	public void setDisinfectMedicineName(String disinfectMedicineName) {
		this.disinfectMedicineName = disinfectMedicineName;
	}

	public String getMedicineFactory() {
		return this.medicineFactory;
	}

	public void setMedicineFactory(String medicineFactory) {
		this.medicineFactory = medicineFactory;
	}

	public int getDosage() {
		return this.dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}