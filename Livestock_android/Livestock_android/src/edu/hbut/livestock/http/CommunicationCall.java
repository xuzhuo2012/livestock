package edu.hbut.livestock.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface CommunicationCall {

	public static final String FAILED = "{ result : 'Send message failed!'}";

	/**
	 * Ã·ΩªGet«Î«Û
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public abstract String queryString(String url) throws ClientProtocolException, IOException;

	public abstract String queryByUri(String source, String uri) throws ClientProtocolException, IOException;
	
}