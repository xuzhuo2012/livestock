package edu.hbut.livestock.http;

import java.sql.Date;

import edu.hbut.livestock.entity.DeathProcess;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author Hang
 *
 */
public class DeathProcessRemoteProcedureCall extends BaseRemoteProcedureCall<DeathProcess, java.sql.Date> {

	@SuppressWarnings("unchecked")
	private static Marshall<DeathProcess, Date> marshall = (Marshall<DeathProcess, Date>) ObjectConfigedFactory.getMarshallFactory().getBean(DeathProcess.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<DeathProcess> unmarshall = (Unmarshall<DeathProcess>) ObjectConfigedFactory.getUnmarshallFactory().getBean(DeathProcess.class);
	
	private static CommunicationCall call = CommunicationCalls.getDefaultCall();
	
	public DeathProcessRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_dethProcessAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_dethProcessAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperties_dethProcessAction";
	}

	@Override
	protected String getListSource() {
		return "list_dethProcessAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_dethProcessAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_dethProcessAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_dethProcessAction";
	}

}
