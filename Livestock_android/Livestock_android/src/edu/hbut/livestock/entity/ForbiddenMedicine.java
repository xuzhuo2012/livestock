package edu.hbut.livestock.entity;

public class ForbiddenMedicine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5134828952278792356L;
	
	private int id;
	
	private String medicineName;
	
	private String animal;
	
	private String forbiddenUsage;
	
	//  Ù–‘√˚
	public static final String ID = "id";
	
	public static final String MEDICINE_NAME = "medicineName";
	
	public static final String ANIMAL = "animal";
	
	public static final String FORBIDDEN_USAGE = "forbiddenUsage";

	// Constructors

	/** default constructor */
	public ForbiddenMedicine() {
	}

	/** full constructor */
	public ForbiddenMedicine(int id, String medicineName, String animal,
			String forbiddenUsage) {
		this.id = id;
		this.medicineName = medicineName;
		this.animal = animal;
		this.forbiddenUsage = forbiddenUsage;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicineName() {
		return this.medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getAnimal() {
		return this.animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getForbiddenUsage() {
		return this.forbiddenUsage;
	}

	public void setForbiddenUsage(String forbiddenUsage) {
		this.forbiddenUsage = forbiddenUsage;
	}

}