package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.Livestock;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 畜禽管理远程调用
 * 
 * @author Hang
 *
 */
public class LivestockRemoteProcedureCall extends BaseRemoteProcedureCall<Livestock, String> {

	@SuppressWarnings("unchecked")
	private static Marshall<Livestock, String> marshall = (Marshall<Livestock, String>) ObjectConfigedFactory.getMarshallFactory().getBean(Livestock.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<Livestock> unmarshall = (Unmarshall<Livestock>) ObjectConfigedFactory.getUnmarshallFactory().getBean(Livestock.class);
	
	private static CommunicationCall call = CommunicationCalls.getDefaultCall();
	
	public LivestockRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_liveStockAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_liveStockAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperties_liveStockAction";
	}

	@Override
	protected String getListSource() {
		return "list_liveStockAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_liveStockAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_liveStockAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_liveStockAction";
	}

}
