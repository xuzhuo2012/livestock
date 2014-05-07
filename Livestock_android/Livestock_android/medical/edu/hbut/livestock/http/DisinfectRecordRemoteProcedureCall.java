package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author xiaowu
 *
 */
public class DisinfectRecordRemoteProcedureCall extends BaseRemoteProcedureCall<DisinfectRecord, DisinfectRecordId>{

	@SuppressWarnings("unchecked")
	private static Marshall<DisinfectRecord, DisinfectRecordId> marshall=(Marshall<DisinfectRecord, DisinfectRecordId>) ObjectConfigedFactory.getMarshallFactory().getBean(DisinfectRecord.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<DisinfectRecord> unmarshall=(Unmarshall<DisinfectRecord>) ObjectConfigedFactory.getUnmarshallFactory().getBean(DisinfectRecord.class);

	private static CommunicationCall call=CommunicationCalls.getDefaultCall();
	
	public DisinfectRecordRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_disinfectAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_disinfectAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByproperties_disinfectAction";
	}

	@Override
	protected String getListSource() {
		return "list_disinfectAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_disinfectAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findbyproperty_disinfectAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_disinfectAction";
	}

}
