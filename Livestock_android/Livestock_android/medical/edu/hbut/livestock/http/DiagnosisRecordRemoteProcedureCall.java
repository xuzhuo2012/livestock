package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.DiagnosisRecord;
import edu.hbut.livestock.entity.DiagnosisRecordId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author xiaowu
 *
 */
public class DiagnosisRecordRemoteProcedureCall extends BaseRemoteProcedureCall<DiagnosisRecord, DiagnosisRecordId>{

	@SuppressWarnings("unchecked")
	private static Marshall<DiagnosisRecord, DiagnosisRecordId> marshall=(Marshall<DiagnosisRecord, DiagnosisRecordId>) ObjectConfigedFactory.getMarshallFactory().getBean(DiagnosisRecord.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<DiagnosisRecord> unmarshall=(Unmarshall<DiagnosisRecord>) ObjectConfigedFactory.getUnmarshallFactory().getBean(DiagnosisRecord.class);

	private static CommunicationCall call=CommunicationCalls.getDefaultCall();
	
	public DiagnosisRecordRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_diagnosisRecordAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_diagnosisRecordAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByproperties_diagnosisRecordAction";
	}

	@Override
	protected String getListSource() {
		return "list_diagnosisRecordAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_diagnosisRecordAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findbyproperty_diagnosisRecordAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_diagnosisRecordAction";
	}

}
