package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.Immunization;
import edu.hbut.livestock.entity.ImmunizationId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author xiaowu
 *
 */
public class ImmunizationRemoteProcedureCall extends BaseRemoteProcedureCall<Immunization, ImmunizationId>{

	@SuppressWarnings("unchecked")
	private static Marshall<Immunization, ImmunizationId> marshall=(Marshall<Immunization, ImmunizationId>) ObjectConfigedFactory.getMarshallFactory().getBean(Immunization.class);
	
	@SuppressWarnings("unchecked")
	private static Unmarshall<Immunization> unmarshall=(Unmarshall<Immunization>) ObjectConfigedFactory.getUnmarshallFactory().getBean(Immunization.class);

	private static CommunicationCall call=CommunicationCalls.getDefaultCall();
	
	public ImmunizationRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_immunizationAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_immunizationAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByproperties_immunizationAction";
	}

	@Override
	protected String getListSource() {
		return "list_immunizationAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_immunizationAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findbyproperty_immunizationAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_immunizationAction";
	}

}
