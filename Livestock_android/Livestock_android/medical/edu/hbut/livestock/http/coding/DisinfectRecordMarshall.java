package edu.hbut.livestock.http.coding;

import java.sql.Date;
import edu.hbut.livestock.entity.DisinfectRecord;
import edu.hbut.livestock.entity.DisinfectRecordId;

/**
 * Ïû¶¾¼ÇÂ¼ ±àÂë
 * 
 * @author xiaowu
 * 
 */
public class DisinfectRecordMarshall extends
		BaseGETMarshall<DisinfectRecord, DisinfectRecordId> {

	@Override
	public String marshall(DisinfectRecord t) {
		String[] params = new String[] { DisinfectRecord.DISINFECT_DATE,
				FMT.format(t.getId().getDisinfectDate()),

				DisinfectRecord.DISINFECT_MEDICINE_NAME,
				t.getDisinfectMedicineName(),

				DisinfectRecord.DISINFECT_PLACE, t.getDisinfectPlace(),

				DisinfectRecord.DOSAGE, Integer.toString(t.getDosage()),

				DisinfectRecord.MEDICINE_FACTORY, t.getMedicineFactory(),

				DisinfectRecord.METHOD, t.getMethod(),

				DisinfectRecord.OPERATOR, t.getOperator()

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(DisinfectRecordId id) {
		String[] params = new String[] { DisinfectRecord.USERID,
				id.getUserid(), DisinfectRecord.DISINFECT_DATE,
				FMT.format(id.getDisinfectDate()) };
		return toUri(params);
	}

	@Override
	public String marshall(DisinfectRecordId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { DisinfectRecord.DISINFECT_DATE,
				FMT.format(id.getDisinfectDate()),

				DisinfectRecord.USERID, id.getUserid(), START_LINE,
				Integer.toString(start), MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();

	}

	@Override
	public String marshall(DisinfectRecordId id, Date headerDate) {
		String[] params = new String[] { DisinfectRecord.DISINFECT_DATE,
				FMT.format(id.getDisinfectDate()) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(DisinfectRecord t, int start, int count) {
		String[] params = new String[] { DisinfectRecord.DISINFECT_DATE,
				FMT.format(t.getId().getDisinfectDate()),

				DisinfectRecord.DISINFECT_MEDICINE_NAME,
				t.getDisinfectMedicineName(),

				DisinfectRecord.DISINFECT_PLACE, t.getDisinfectPlace(),

				DisinfectRecord.DOSAGE, Integer.toString(t.getDosage()),

				DisinfectRecord.MEDICINE_FACTORY, t.getMedicineFactory(),

				DisinfectRecord.METHOD, t.getMethod(),

				DisinfectRecord.OPERATOR, t.getOperator(),

				START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
