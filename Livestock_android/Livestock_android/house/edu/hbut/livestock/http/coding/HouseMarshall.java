package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.House;
import edu.hbut.livestock.entity.HouseId;

/**
 * 
 * @author Hang
 * 
 */
public class HouseMarshall extends BaseGETMarshall<House, HouseId> {

	@Override
	public String marshall(House t) {
		String[] params = new String[] { House.HOUSEID,
				Integer.toString(t.getId().getHouseid()),

				House.USERID, t.getId().getUserid(),

				House.ANIMAL_TYPE, t.getAnimalType(),

				House.LIVESTOCK_COUNT, Integer.toString(t.getLivestockCount()) };
		return toUri(params);
	}

	@Override
	public String marshallId(HouseId id) {
		return toUri(House.HOUSEID, Integer.toString(id.getHouseid()),
				House.USERID, id.getUserid());
	}

	@Override
	public String marshall(HouseId id, Date headerDate, int start, int count) {
		String[] params = new String[] { House.HOUSEID,
				Integer.toString(id.getHouseid()),

				House.USERID, id.getUserid(),

				START_LINE, Integer.toString(start),

				MAX_COUNT, Integer.toString(count) };
		return toUri(params);
	}

	@Override
	public String marshall(HouseId id, Date headerDate) {
		return marshallId(id);
	}

	@Override
	public String marshall(House t, int start, int count) {
		String[] params = new String[] { House.HOUSEID,
				Integer.toString(t.getId().getHouseid()),

				House.USERID, t.getId().getUserid(),

				House.ANIMAL_TYPE, t.getAnimalType(),

				House.LIVESTOCK_COUNT, Integer.toString(t.getLivestockCount()),

				START_LINE, Integer.toString(start),

				MAX_COUNT, Integer.toString(count) };
		return toUri(params);
	}

}
