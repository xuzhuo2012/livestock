package edu.hbut.livestock.http;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;

import edu.hbut.livestock.entity.Feed;
import edu.hbut.livestock.http.coding.FeedMarshall;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.MarshallException;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 远程调用，对Feed类对应的表进行操作
 * 
 * @author Hang
 * 
 */
public class FeedRemoteProcedureCall extends
		BaseRemoteProcedureCall<Feed, Timestamp> {

	@SuppressWarnings("unchecked")
	public FeedRemoteProcedureCall() {
		super((Marshall<Feed, Timestamp>) ObjectConfigedFactory.getMarshallFactory()
				.getBean(Feed.class), (Unmarshall<Feed>) ObjectConfigedFactory
				.getUnmarshallFactory().getBean(Feed.class), CommunicationCalls
				.getDefaultCall());
	}

	@Override
	protected String getAddSource() {
		return "add_feedAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_feedAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperties_feedAction";
	}

	@Override
	protected String getListSource() {
		return "list_feedAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_feedAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_feedAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_feedAction";
	}

	/**
	 * 执行查询操作，按时间查询
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param start
	 *            起始行
	 * @param count
	 *            最大数据行数
	 * @return 查询结果集合
	 */
	public void findByTime(final Date startTime, final Date endTime, final int start, final int count, final AsyncCallback<List<Feed>> action) {
		new AsyncTask<String, Integer, List<Feed>>() {

			@Override
			protected List<Feed> doInBackground(String... params) {
				/*
				 * 请求
				 */
				FeedMarshall marshall = (FeedMarshall) getMarshall();
				CommunicationCall call = getCall();
				try {
					return getUnmarshall().unmarshallList(
							call.queryByUri("feedFindAction",
									marshall.marshallQueryTime(startTime, endTime,
											start, count)));
				} catch (MarshallException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<Feed> result) {
				/*
				 * 结果处理
				 */
				action.call(result);
			}
			
		}.execute();
	}
}
