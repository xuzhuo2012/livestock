package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.Antiepidemic;
import edu.hbut.livestock.entity.AntiepidemicId;

/**
 * Ïû¶¾¼ÇÂ¼ ±àÂë
 * 
 * @author xiaowu
 * 
 */
public class AntiepidemicMarshall extends
		BaseGETMarshall<Antiepidemic, AntiepidemicId> {

	@Override
	public String marshall(Antiepidemic t) {
		String[] params = new String[] { 
				Antiepidemic.ANTIEPIDEMIC_DATE,FMT.format(t.getId().getAntiepidemicDate()),

				Antiepidemic.SAMPLE_COUNT,t.getSampleCount()+"",

				Antiepidemic.DEPARTMENT_ID, t.getDepartment().getDepartmentName(),

				Antiepidemic.MONITOR_RESULT, t.getMonitorResult(),

				Antiepidemic.DEAL_RESULT, t.getDealResult()

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(AntiepidemicId id) {
		String[] params = new String[] { 
				Antiepidemic.ANTIEPIDEMIC_DATE,FMT.format(id.getAntiepidemicDate()),
				Antiepidemic.USERID,id.getUserid(),
				Antiepidemic.HOUSEID,id.getHouseid()+"",
				Antiepidemic.MONITOR_ITEM,id.getMonitorItem()};
		return toUri(params);
	}

	@Override
	public String marshall(AntiepidemicId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { 
				Antiepidemic.ANTIEPIDEMIC_DATE,FMT.format(id.getAntiepidemicDate()),
				Antiepidemic.USERID,id.getUserid(),
				Antiepidemic.HOUSEID,id.getHouseid()+"",
				Antiepidemic.MONITOR_ITEM,id.getMonitorItem(),
				START_LINE,Integer.toString(start), 
				MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();

	}

	@Override
	public String marshall(AntiepidemicId id, Date headerDate) {
		String[] params = new String[] { 
				Antiepidemic.ANTIEPIDEMIC_DATE,
				FMT.format(id.getAntiepidemicDate()) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(Antiepidemic t, int start, int count) {
		String[] params = new String[] { 
				Antiepidemic.ANTIEPIDEMIC_DATE,FMT.format(t.getId().getAntiepidemicDate()),

				Antiepidemic.SAMPLE_COUNT,t.getSampleCount()+"",

				Antiepidemic.DEPARTMENT_ID, t.getDepartment().getDepartmentName(),

				Antiepidemic.MONITOR_RESULT, t.getMonitorResult(),

				Antiepidemic.DEAL_RESULT, t.getDealResult(),

				START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
