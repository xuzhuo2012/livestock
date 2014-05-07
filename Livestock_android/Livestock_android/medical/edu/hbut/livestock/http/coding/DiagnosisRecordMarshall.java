package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.DiagnosisRecord;
import edu.hbut.livestock.entity.DiagnosisRecordId;

/**
 * Ïû¶¾¼ÇÂ¼ ±àÂë
 * 
 * @author xiaowu
 * 
 */
public class DiagnosisRecordMarshall extends
		BaseGETMarshall<DiagnosisRecord, DiagnosisRecordId> {

	@Override
	public String marshall(DiagnosisRecord t) {
		String[] params = new String[] { 
				DiagnosisRecord.DIAGNOSISDATE,FMT.format(t.getId().getDiagnosisDate()),

				DiagnosisRecord.AGE,t.getAge()+"",

				DiagnosisRecord.REASON, t.getReason(),

				DiagnosisRecord.DOCTOR, t.getDoctor(),

				DiagnosisRecord.MEDICINE, t.getMedicine(),

				DiagnosisRecord.METHOD, t.getMethod(),

				DiagnosisRecord.RESULT, t.getResult()

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(DiagnosisRecordId id) {
		String[] params = new String[] { 
				DiagnosisRecord.DIAGNOSISDATE,FMT.format(id.getDiagnosisDate()),
				DiagnosisRecord.LIVESTOCKID,id.getLivestockid()+""};
		return toUri(params);
	}

	@Override
	public String marshall(DiagnosisRecordId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { 
				DiagnosisRecord.DIAGNOSISDATE,FMT.format(id.getDiagnosisDate()),
				DiagnosisRecord.LIVESTOCKID,id.getLivestockid()+"",
				START_LINE,Integer.toString(start), 
				MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();

	}

	@Override
	public String marshall(DiagnosisRecordId id, Date headerDate) {
		String[] params = new String[] { 
				DiagnosisRecord.DIAGNOSISDATE,
				FMT.format(id.getDiagnosisDate()) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(DiagnosisRecord t, int start, int count) {
		String[] params = new String[] { 
				DiagnosisRecord.DIAGNOSISDATE,FMT.format(t.getId().getDiagnosisDate()),

				DiagnosisRecord.AGE,t.getAge()+"",

				DiagnosisRecord.REASON, t.getReason(),

				DiagnosisRecord.DOCTOR, t.getDoctor(),

				DiagnosisRecord.MEDICINE, t.getMedicine(),

				DiagnosisRecord.METHOD, t.getMethod(),

				DiagnosisRecord.RESULT, t.getResult(),

				START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
