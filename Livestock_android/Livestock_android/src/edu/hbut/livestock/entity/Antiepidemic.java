package edu.hbut.livestock.entity;

/**
 * Antiepidemic entity. @author MyEclipse Persistence Tools
 */

public class Antiepidemic extends HeaderParamSupportEntity implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -844557788501134527L;

	private MonitorItem monitorItem;

	private Department department;
	
	private User user;

	private House house;

	private int sampleCount;

	private String monitorResult;

	private String dealResult;

	private AntiepidemicId id;

	public static final String DEAL_RESULT = "dealResult";

	public static final String MONITOR_RESULT = "monitorResult";

	public static final String SAMPLE_COUNT = "sampleCount";

	public static final String DEPARTMENT = "department";

	public static final String DEPARTMENT_ID = "deparment.departmentName";

	public static final String ID = "id";

	public static final String MONITOR_ITEM = "id.monitorItem";

	public static final String HOUSEID = "id.houseid";

	public static final String ANTIEPIDEMIC_DATE = "id.antiepidemicDate";

	public static final String USERID = "id.userid";

	// Constructors

	/** default constructor */
	public Antiepidemic() {
	}

	/** minimal constructor */
	public Antiepidemic(AntiepidemicId id, MonitorItem monitorItem, House house) {
		this.id = id;
		this.monitorItem = monitorItem;
		this.house = house;
	}

	/** full constructor */
	public Antiepidemic(AntiepidemicId id, MonitorItem monitorItem,
			Department department, House house, int sampleCount,
			String monitorResult, String dealResult) {
		this.id = id;
		this.monitorItem = monitorItem;
		this.department = department;
		this.house = house;
		this.sampleCount = sampleCount;
		this.monitorResult = monitorResult;
		this.dealResult = dealResult;
	}

	// Property accessors

	public AntiepidemicId getId() {
		return this.id;
	}

	public void setId(AntiepidemicId id) {
		this.id = id;
	}

	public MonitorItem getMonitorItem() {
		return this.monitorItem;
	}

	public void setMonitorItem(MonitorItem monitorItem) {
		this.monitorItem = monitorItem;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public int getSampleCount() {
		return this.sampleCount;
	}

	public void setSampleCount(int sampleCount) {
		this.sampleCount = sampleCount;
	}

	public String getMonitorResult() {
		return this.monitorResult;
	}

	public void setMonitorResult(String monitorResult) {
		this.monitorResult = monitorResult;
	}

	public String getDealResult() {
		return this.dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}