package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.Antiepidemic;
import edu.hbut.livestock.entity.AntiepidemicId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author xiaowu
 *
 */
public class AntiepidemicRemoteProcedureCall extends BaseRemoteProcedureCall<Antiepidemic, AntiepidemicId>{

	@SuppressWarnings("unchecked")
	private static Marshall<Antiepidemic, AntiepidemicId> marshall=(Marshall<Antiepidemic, AntiepidemicId>) ObjectConfigedFactory.getMarshallFactory().getBean(Antiepidemic.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<Antiepidemic> unmarshall=(Unmarshall<Antiepidemic>) ObjectConfigedFactory.getUnmarshallFactory().getBean(Antiepidemic.class);

	private static CommunicationCall call=CommunicationCalls.getDefaultCall();
	
	public AntiepidemicRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_antiepidemicAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_antiepidemicAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByproperties_antiepidemicAction";
	}

	@Override
	protected String getListSource() {
		return "list_antiepidemicAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_antiepidemicAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findbyproperty_antiepidemicAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_antiepidemicAction";
	}

}
