package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;
/**
 * 
 * @author xiaowu
 *
 */

public class QuarantineApplyRemoteProcedureCall extends BaseRemoteProcedureCall<QuarantineApply,QuarantineApplyId>{

	@SuppressWarnings("unchecked")
	private static Marshall<QuarantineApply, QuarantineApplyId> marshall = (Marshall<QuarantineApply,QuarantineApplyId>) ObjectConfigedFactory.getMarshallFactory().getBean(QuarantineApply.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<QuarantineApply> unmarshall = (Unmarshall<QuarantineApply>) ObjectConfigedFactory.getUnmarshallFactory().getBean(QuarantineApply.class);
	
	private static CommunicationCall call = CommunicationCalls.getDefaultCall();
	
	public QuarantineApplyRemoteProcedureCall() {
		super(marshall, unmarshall, call);
		
	}

	@Override
	protected String getAddSource() {
		return "add_quarantineApplyAction";
	}

	@Override
	protected String getDeleteSource() {
		
		return  "delete_quarantineApplyAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		
		return  "findByProperties_quarantineApplyAction";
	}

	@Override
	protected String getListSource() {
		
		return  "list_quarantineApplyAction";
	}

	@Override
	protected String getFindByIdSource() {
		
		return  "findById_quarantineApplyAction";
	}

	@Override
	protected String getFindByPropertySource() {
		
		return  "findByProperty_quarantineApplyAction";
	}

	@Override
	protected String getUpdateSource() {
		
		return  "update_quarantineApplyAction";
	}

}
