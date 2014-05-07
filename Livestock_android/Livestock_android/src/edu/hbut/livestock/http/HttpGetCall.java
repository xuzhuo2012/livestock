package edu.hbut.livestock.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import edu.hbut.livestock.util.SettingSystem;

import android.util.Log;

/**
 * 提交HTTP请求
 * 
 * @author Hang
 * 
 */
public final class HttpGetCall implements CommunicationCall {
	
	public static String BASE_URL = SettingSystem.NET_WORK;//"http://192.168.1.107:8080/livestock/";

	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	/**
	 * 单例
	 */
	private static HttpGetCall httpCall = new HttpGetCall();

	private HttpGetCall() {
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static HttpGetCall getHttpCall() {
		return httpCall;
	}

	public HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url.replaceAll(" ", "%20"));
		if (ElectronicRecipesUtils.getSessionId().length() != 0) {
			request.setHeader("Cookie",
					"JSESSIONID=" + ElectronicRecipesUtils.getSessionId());
		}
		return request;
	}

	public HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url.replaceAll(" ", "%20"));
		Log.v("SessionId", "'" + ElectronicRecipesUtils.getSessionId() + "'");
		if (ElectronicRecipesUtils.getSessionId().length() != 0) {
			request.setHeader("Cookie",
					"JSESSIONID=" + ElectronicRecipesUtils.getSessionId());
		}
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.hbut.livestock.http.CommunicationCall#queryStringForGet(java.lang
	 * .String)
	 */
	@Override
	public String queryString(String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = getHttpGet(url.replaceAll(" ", "%20"));
		Log.v("url", url);
		return getResponseText(httpGet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.hbut.livestock.http.CommunicationCall#queryByUriGet(java.lang.String,
	 * java.lang.String)
	 */
	/*
	 * 设置传入的参数并且得到服务器返回的值
	 * 	 * @see edu.hbut.livestock.http.CommunicationCall#queryByUri(java.lang.String, java.lang.String)
	 */
	@Override
	public String queryByUri(String source, String uri)
			throws ClientProtocolException, IOException {

		if (uri != null) {
			StringBuilder sb;
			sb = new StringBuilder(BASE_URL);
			sb.append(source);
			sb.append("?");
			sb.append(uri);
			sb.append("&clientFlag=").append(SettingSystem.CLIENTFLAG);
			return queryString(sb.toString());
		}
		return BASE_URL;
	}

	/**
	 * 获取返回字符串
	 * 
	 * @param httpGet
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String getResponseText(HttpGet httpGet) throws ClientProtocolException, IOException {
		HttpResponse response;

		response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
				String s;
				while ((s = reader.readLine()) != null) {
					sb.append(s);
				}

			} finally {
				reader.close();
			}
			Log.v("net", "success" + response.getStatusLine().getStatusCode());
			return sb.toString();

		} else {
			Log.v("net", "wrong" + response.getStatusLine().getStatusCode());
		}
		return "{result:" + response.getStatusLine().getStatusCode() + "}";
	}
}
