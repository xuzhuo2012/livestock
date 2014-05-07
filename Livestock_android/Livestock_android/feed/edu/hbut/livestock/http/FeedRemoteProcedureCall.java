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
 * Զ�̵��ã���Feed���Ӧ�ı���в���
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
	 * ִ�в�ѯ��������ʱ���ѯ
	 * 
	 * @param startTime
	 *            ��ʼʱ��
	 * @param endTime
	 *            ����ʱ��
	 * @param start
	 *            ��ʼ��
	 * @param count
	 *            �����������
	 * @return ��ѯ�������
	 */
	public void findByTime(final Date startTime, final Date endTime, final int start, final int count, final AsyncCallback<List<Feed>> action) {
		new AsyncTask<String, Integer, List<Feed>>() {

			@Override
			protected List<Feed> doInBackground(String... params) {
				/*
				 * ����
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
				 * �������
				 */
				action.call(result);
			}
			
		}.execute();
	}
}
