package edu.hbut.livestock.http;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;

import edu.hbut.livestock.entity.FeedingRecord;
import edu.hbut.livestock.entity.FeedingRecordId;
import edu.hbut.livestock.http.coding.FeedingMarshall;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 获取圈舍饲料管理访问的资源路径名
 * 
 * @author Hang
 * 
 */
public class FeedingRemoteProcedureCall extends
		BaseRemoteProcedureCall<FeedingRecord, FeedingRecordId> {

	/**
	 * 编码器
	 */
	@SuppressWarnings("unchecked")
	private static Marshall<FeedingRecord, FeedingRecordId> marshall = (Marshall<FeedingRecord, FeedingRecordId>) ObjectConfigedFactory
			.getMarshallFactory().getBean(FeedingRecord.class);

	/**
	 * 解码器
	 */
	@SuppressWarnings("unchecked")
	private static Unmarshall<FeedingRecord> unmarshall = (Unmarshall<FeedingRecord>) ObjectConfigedFactory
			.getUnmarshallFactory().getBean(FeedingRecord.class);

	/**
	 * 通信处理对象
	 */
	private static CommunicationCall call = CommunicationCalls.getDefaultCall();

	public FeedingRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_feedingAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_feedingAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperties_feedingAction";
	}

	@Override
	protected String getListSource() {
		return "list_feedingAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_feedingAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_feedingAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_feedingAction";
	}

	public void findByHouse(final Date headerDate, final int houseid,
			final int start, final int count,
			final AsyncCallback<List<FeedingRecord>> action) {
		new AsyncTask<String, Integer, List<FeedingRecord>>() {

			@Override
			protected List<FeedingRecord> doInBackground(String... args) {
				String uri = ((FeedingMarshall) getMarshall()).marshallHouse(
						headerDate, houseid, start, count);
				try {
					return getUnmarshall().unmarshallList(
							call.queryByUri("feedingFindAction", uri));
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<FeedingRecord> result) {
				action.call(result);
			}

		}.execute();
	}

}
