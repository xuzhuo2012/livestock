package edu.hbut.livestock.entity;

public class House implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8574173149105667514L;
	
	private HouseId id;
	
	private User user;
	
	private String animalType;
	
	private int livestockCount;

	//  Ù–‘√˚
	public static final String HOUSE = "id";
	public static final String HOUSEID = "id.houseid";
	public static final String USERID = "id.userid";
	public static final String ANIMAL_TYPE = "animalType";
	public static final String LIVESTOCK_COUNT = "livestockCount";

	// Constructors

	/** default constructor */
	public House() {
	}

	/** minimal constructor */
	public House(HouseId id, User user) {
		this.id = id;
		this.user = user;
	}

	/** full constructor */
	public House(HouseId id, User user, String animalType,
			int livestockCount) {
		this.id = id;
		this.user = user;
		this.animalType = animalType;
		this.livestockCount = livestockCount;
	}

	// Property accessors
	public HouseId getId() {
		return this.id;
	}

	public void setId(HouseId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAnimalType() {
		return this.animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public int getLivestockCount() {
		return this.livestockCount;
	}

	public void setLivestockCount(int livestockCount) {
		this.livestockCount = livestockCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		House other = (House) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}