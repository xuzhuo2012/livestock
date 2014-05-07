package edu.hbut.livestock.http;


import java.sql.Date;

import edu.hbut.livestock.entity.MedicineRecord;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author xiaowu
 *
 */
public class MedicineRecordRemoteProcedureCall extends BaseRemoteProcedureCall<MedicineRecord, Date>{

	@SuppressWarnings("unchecked")
	private static Marshall<MedicineRecord, Date> marshall=(Marshall<MedicineRecord, Date>) ObjectConfigedFactory.getMarshallFactory().getBean(MedicineRecord.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<MedicineRecord> unmarshall=(Unmarshall<MedicineRecord>) ObjectConfigedFactory.getUnmarshallFactory().getBean(MedicineRecord.class);

	private static CommunicationCall call=CommunicationCalls.getDefaultCall();
	
	public MedicineRecordRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_medicineRecordAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_medicineRecordAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByproperties_medicineRecordAction";
	}

	@Override
	protected String getListSource() {
		return "list_medicineRecordAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_medicineRecordAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findbyproperty_medicineRecordAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_medicineRecordAction";
	}

}
