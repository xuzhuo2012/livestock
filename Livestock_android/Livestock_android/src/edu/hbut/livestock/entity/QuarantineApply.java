package edu.hbut.livestock.entity;

/**
 * QuarantineApply entity. @author MyEclipse Persistence Tools
 */

public class QuarantineApply extends HeaderParamSupportEntity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5990537497788472714L;
	
	public static final String ID = "id";

	public static final String COUNT = "count";

	public static final String FLAG = "flag";

	public static final String OPERATER = "operator";

	public static final String RESULT = "result";

	public static final String APPLY_DATE = "id.applyDate";
	
	public static final String USERID = "id.userid";
	
	private QuarantineApplyId id;
	
	private User user;
	
	private String flag;
	
	private String operator;
	
	private String result;
	
	private int count;

	// Constructors

	/** default constructor */
	public QuarantineApply() {
	}

	/** minimal constructor */
	public QuarantineApply(QuarantineApplyId id, User user) {
		this.id = id;
		this.user = user;
	}

	/** full constructor */
	public QuarantineApply(QuarantineApplyId id, User user, String flag,
			String operator, String result, int count) {
		this.id = id;
		this.user = user;
		this.flag = flag;
		this.operator = operator;
		this.result = result;
		this.count = count;
	}

	// Property accessors

	public QuarantineApplyId getId() {
		return this.id;
	}

	public void setId(QuarantineApplyId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}