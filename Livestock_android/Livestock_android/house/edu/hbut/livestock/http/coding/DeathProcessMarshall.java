package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.DeathProcess;

/**
 * 
 * @author Hang
 * 
 */
public class DeathProcessMarshall extends BaseGETMarshall<DeathProcess, Date> {

	@Override
	public String marshall(DeathProcess t) {
		String[] params = new String[] { DeathProcess.PROCESS_DATE,
				FMT.format(t.getProcessDate()),

				DeathProcess.DEPARTMENT_ID, t.getDepartment().getDepartmentName(),

				DeathProcess.LIVESTOCK_ID, t.getLivestock().getLivestockid(),

				DeathProcess.REASON, t.getReason(),

				DeathProcess.REMARK, t.getRemark() };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(Date id) {
		StringBuilder sb = new StringBuilder(DeathProcess.PROCESS_DATE);
		sb.append('=');
		sb.append(FMT.format(id));
		return sb.toString();
	}

	@Override
	public String marshall(Date id, Date headerDate, int start, int count) {
		StringBuilder sb = new StringBuilder(toUri(DeathProcess.PROCESS_DATE,
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
	public String marshall(DeathProcess t, int start, int count) {
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
