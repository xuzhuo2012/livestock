package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.QuarantineApply;
import edu.hbut.livestock.entity.QuarantineApplyId;

/**
 * 
 * @author xiaowu
 * 
 */

public class QuarantineaApplyMarshall extends
		BaseGETMarshall<QuarantineApply, QuarantineApplyId> {

	@Override
	public String marshall(QuarantineApply t) {
		String[] params = new String[] { 
				QuarantineApply.APPLY_DATE, FMT.format(t.getId().getApplyDate()),
				QuarantineApply.USERID,t.getId().getUserid(),
				QuarantineApply.COUNT,Integer.toString(t.getCount()),
				QuarantineApply.RESULT,t.getResult(),
				QuarantineApply.FLAG,t.getFlag(),
				QuarantineApply.OPERATER,t.getOperator()
						 };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(QuarantineApplyId id) {
		String[] params = new String[] { 
				QuarantineApply.APPLY_DATE,FMT.format(id.getApplyDate()),
				QuarantineApply.USERID,id.getUserid()
		};

		return toUri(params);
	}

	@Override
	public String marshall(QuarantineApplyId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { QuarantineApply.APPLY_DATE,
				FMT.format(id.getApplyDate()), START_LINE,
				Integer.toString(start),

				MAX_COUNT, Integer.toString(count),

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(QuarantineApplyId id, Date headerDate) {
		String[] params = new String[] { QuarantineApply.APPLY_DATE,
				FMT.format(id.getApplyDate()),

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(QuarantineApply t, int start, int count) {
		String[] params = new String[] { QuarantineApply.RESULT, t.getResult(),

		QuarantineApply.COUNT, Integer.toString(t.getCount()),

		QuarantineApply.FLAG, t.getFlag(),

		QuarantineApply.OPERATER, t.getOperator(),

		QuarantineApply.APPLY_DATE, FMT.format(t.getId().getApplyDate()),

		START_LINE, Integer.toString(start),

		MAX_COUNT, Integer.toString(count),

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
