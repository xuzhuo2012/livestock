package edu.hbut.livestock.http;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.AsyncTask;

import edu.hbut.livestock.entity.ProducingRecord;
import edu.hbut.livestock.entity.ProducingRecordId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.MarshallException;
import edu.hbut.livestock.http.coding.ProducingRecordMarshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author Hang
 * 
 */
public class ProducingRecordRemoteProcedureCall extends
		BaseRemoteProcedureCall<ProducingRecord, ProducingRecordId> {

	@SuppressWarnings("unchecked")
	private static Marshall<ProducingRecord, ProducingRecordId> marshall = (Marshall<ProducingRecord, ProducingRecordId>) ObjectConfigedFactory
			.getMarshallFactory().getBean(ProducingRecord.class);

	@SuppressWarnings("unchecked")
	private static Unmarshall<ProducingRecord> unmarshall = (Unmarshall<ProducingRecord>) ObjectConfigedFactory
			.getUnmarshallFactory().getBean(ProducingRecord.class);

	private static CommunicationCall call = CommunicationCalls.getDefaultCall();

	public ProducingRecordRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_producingRecordAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_producingRecordAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperty_producingRecordAction";
	}

	@Override
	protected String getListSource() {
		return "list_producingRecordAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_producingRecordAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_producingRecordAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_liveStockAction";
	}

	public void queryByHouse(final int houseid, final int start,
			final int count, final Date headerDate,
			final AsyncCallback<List<ProducingRecord>> action) {
		new AsyncTask<String, Integer, List<ProducingRecord>>() {

			@Override
			protected List<ProducingRecord> doInBackground(String... params) {
				/*
				 * 请求
				 */
				ProducingRecordMarshall marshall = (ProducingRecordMarshall) getMarshall();
				CommunicationCall call = getCall();
				try {
					return getUnmarshall().unmarshallList(
							call.queryByUri("producingRecordFindAction", marshall
									.marshallHouseQuery(houseid, start, count,
											headerDate)));
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
			protected void onPostExecute(List<ProducingRecord> result) {
				/*
				 * 结果处理
				 */
				action.call(result);
			}

		}.execute();
	}
}
