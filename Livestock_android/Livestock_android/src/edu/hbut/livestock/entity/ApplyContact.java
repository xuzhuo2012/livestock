package edu.hbut.livestock.entity;

public class ApplyContact implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3857840533567262214L;
	
	private int id;
	
	private String contactName;
	
	private String sex;
	
	private String phone;
	
	private String area;
	
	
	//  Ù–‘√˚
	public static final String ID = "id";
	
	public static final String CONTACT_NAME = "contactName";
	
	public static final String SEX = "sex";
	
	public static final String PHONE = "phone";
	
	public static final String AREA  = "area";
	
	// Constructors

	/** default constructor */
	public ApplyContact() {
	}

	/** full constructor */
	public ApplyContact(int id, String contactName, String sex,
			String phone, String area) {
		this.id = id;
		this.contactName = contactName;
		this.sex = sex;
		this.phone = phone;
		this.area = area;
	}

	// Property accessors
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "ApplyContact [id=" + id + ", contactName=" + contactName
				+ ", sex=" + sex + ", phone=" + phone + ", area=" + area + "]";
	}

}