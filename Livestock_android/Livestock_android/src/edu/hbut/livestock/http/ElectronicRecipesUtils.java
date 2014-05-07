package edu.hbut.livestock.http;

public class ElectronicRecipesUtils {

	private static String sessionId = "";

	private ElectronicRecipesUtils() {
	}

	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		ElectronicRecipesUtils.sessionId = sessionId;
	}
}
