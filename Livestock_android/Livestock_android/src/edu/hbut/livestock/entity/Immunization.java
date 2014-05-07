package edu.hbut.livestock.entity;

/**
 * Immunization entity. @author MyEclipse Persistence Tools
 */

public class Immunization extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6322318018547573616L;
	public static final String USERID =  "id.userid";
	public static final String HOUSEID =  "id.houseid";
	public static final String IMMUNIZATIONTIME = "id.immunizationTime";
	public static final String TOTALCOUNT = "totalCount";
	public static final String VACCINE = "vaccine";
	public static final String VACCINEFACTORY = "vaccineFactory";
	public static final String BATCHNUMBER = "batchNumber";
	public static final String DOSAGE = "dosage";
	public static final String IMMUNIZATIONMETHOD = "immunizationMethod";
	public static final String ID = "id";
	
	private ImmunizationId id;
	private House house;
	private User user;
	private int totalCount;
	private String vaccine;
	private String vaccineFactory;
	private String batchNumber;
	private int dosage;
	private String immunizationMethod;

	// Constructors

	/** default constructor */
	public Immunization() {
	}

	/** minimal constructor */
	public Immunization(ImmunizationId id, House house) {
		this.id = id;
		this.house = house;
	}

	/** full constructor */
	public Immunization(ImmunizationId id, House house, int totalCount,
			String vaccine, String vaccineFactory, String batchNumber,
			int dosage, String immunizationMethod) {
		this.id = id;
		this.house = house;
		this.totalCount = totalCount;
		this.vaccine = vaccine;
		this.vaccineFactory = vaccineFactory;
		this.batchNumber = batchNumber;
		this.dosage = dosage;
		this.immunizationMethod = immunizationMethod;
	}

	// Property accessors

	public ImmunizationId getId() {
		return this.id;
	}

	public void setId(ImmunizationId id) {
		this.id = id;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getVaccine() {
		return this.vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public String getVaccineFactory() {
		return this.vaccineFactory;
	}

	public void setVaccineFactory(String vaccineFactory) {
		this.vaccineFactory = vaccineFactory;
	}

	public String getBatchNumber() {
		return this.batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public int getDosage() {
		return this.dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getImmunizationMethod() {
		return this.immunizationMethod;
	}

	public void setImmunizationMethod(String immunizationMethod) {
		this.immunizationMethod = immunizationMethod;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}