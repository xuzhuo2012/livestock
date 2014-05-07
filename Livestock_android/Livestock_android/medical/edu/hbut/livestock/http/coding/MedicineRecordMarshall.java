package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.MedicineRecord;

/**
 * Ïû¶¾¼ÇÂ¼ ±àÂë
 * 
 * @author xiaowu
 * 
 */
public class MedicineRecordMarshall extends
		BaseGETMarshall<MedicineRecord, Date> {

	@Override
	public String marshall(MedicineRecord t) {
		String[] params = new String[] { 
				MedicineRecord.STARTTIME,FMT.format(t.getStartTime()),

				MedicineRecord.PRODUCTNAME,t.getProductName(),

				MedicineRecord.MEDICINETYPE, t.getMedicineType(),

				MedicineRecord.STANDARD, t.getStandard(),

				MedicineRecord.VALIDITYPERIOD, t.getValidityPeriod()+"",

				MedicineRecord.FACTORY, t.getFactory(),

				MedicineRecord.BATCHNUMBER, t.getBatchNumber()+"",
				
				MedicineRecord.ENDTIME, FMT.format(t.getEndTime())

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(Date id) {
		StringBuilder sb = new StringBuilder(MedicineRecord.STARTTIME);
		sb.append('=');
		sb.append(FMT.format(id));
		return sb.toString();
	}

	@Override
	public String marshall(Date id, Date headerDate, int start,
			int count) {
		StringBuilder sb = new StringBuilder(toUri(MedicineRecord.STARTTIME,
				FMT.format(id), START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)));
		appendHeaderDate(headerDate, sb);
		return sb.toString();

	}

	@Override
	public String marshall(Date id, Date headerDate) {
		StringBuilder sb = new StringBuilder(marshallId(id));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(MedicineRecord t, int start, int count) {
		StringBuilder sb = new StringBuilder(marshall(t));
		appendHeaderDate(t.getHeaderDate(), sb);
		sb.append('&');
		sb.append(START_LINE);
		sb.append('=');
		sb.append(start);
		sb.append('&');
		sb.append(MAX_COUNT);
		sb.append('=');
		sb.append(count);
		return sb.toString();
	}

}
