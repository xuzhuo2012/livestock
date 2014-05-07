package edu.hbut.livestock.entity;

public class Info {
	
	private UserInfo userInfo;
	
	public static final String USER_INFO = "userInfo";

	public static final String SEX = "userInfo.sex";

	public static final String PHONE = "userInfo.phone";

	public static final String USER_NAME = "userInfo.userName";

	public static final String ADDRESS = "userInfo.address";

	public static final String POSTCODE = "userInfo.postCode";

	public static final String EMAIL = "userInfo.email";

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
