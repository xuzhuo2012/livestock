package edu.hbut.livestock.http;

/**
 * ����
 * @author Hang
 *
 */
public class CommunicationCalls {

	public static CommunicationCall getDefaultCall() {
		return HttpGetCall.getHttpCall();
	}
}
