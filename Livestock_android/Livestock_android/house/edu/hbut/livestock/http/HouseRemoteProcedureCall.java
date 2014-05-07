package edu.hbut.livestock.http;

import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;
import edu.hbut.livestock.http.coding.Marshall;
import edu.hbut.livestock.http.coding.Unmarshall;
import edu.hbut.livestock.http.factory.ObjectConfigedFactory;

/**
 * 
 * @author Hang
 * 
 */
public class HouseRemoteProcedureCall extends BaseRemoteProcedureCall<House, HouseId> {

	@SuppressWarnings("unchecked")
	private static Marshall<House, HouseId> marshall = (Marshall<House, HouseId>) ObjectConfigedFactory
			.getMarshallFactory().getBean(House.class);

	@SuppressWarnings("unchecked")
	private static Unmarshall<House> unmarshall = (Unmarshall<House>) ObjectConfigedFactory
			.getUnmarshallFactory().getBean(House.class);

	private static CommunicationCall call = CommunicationCalls.getDefaultCall();

	public HouseRemoteProcedureCall() {
		super(marshall, unmarshall, call);
	}

	@Override
	protected String getAddSource() {
		return "add_houseAction";
	}

	@Override
	protected String getDeleteSource() {
		return "delete_houseAction";
	}

	@Override
	protected String getFindByPropertiesSource() {
		return "findByProperties_houseAction";
	}

	@Override
	protected String getListSource() {
		return "list_houseAction";
	}

	@Override
	protected String getFindByIdSource() {
		return "findById_houseAction";
	}

	@Override
	protected String getUpdateSource() {
		return "update_houseAction";
	}

	@Override
	protected String getFindByPropertySource() {
		return "findByProperty_houseAction";
	}

}
