package edu.hbut.livestock.http.coding;

import java.sql.Date;

import edu.hbut.livestock.entity.Immunization;
import edu.hbut.livestock.entity.ImmunizationId;

/**
 * Ïû¶¾¼ÇÂ¼ ±àÂë
 * 
 * @author xiaowu
 * 
 */
public class ImmunizationMarshall extends
		BaseGETMarshall<Immunization, ImmunizationId> {

	@Override
	public String marshall(Immunization t) {
		String[] params = new String[] { 
				Immunization.IMMUNIZATIONTIME,FMT.format(t.getId().getImmunizationTime()),

				Immunization.TOTALCOUNT,t.getTotalCount()+"",

				Immunization.VACCINE, t.getVaccine(),

				Immunization.VACCINEFACTORY, t.getVaccineFactory(),

				Immunization.BATCHNUMBER, t.getBatchNumber()+"",

				Immunization.DOSAGE, t.getDosage()+"",

				Immunization.IMMUNIZATIONMETHOD, t.getImmunizationMethod()

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

	@Override
	public String marshallId(ImmunizationId id) {
		String[] params = new String[] { 
				Immunization.USERID,id.getUserid(), 
				Immunization.IMMUNIZATIONTIME,FMT.format(id.getImmunizationTime()),
				Immunization.HOUSEID,id.getHouseid()+""};
		return toUri(params);
	}

	@Override
	public String marshall(ImmunizationId id, Date headerDate, int start,
			int count) {
		String[] params = new String[] { 
				Immunization.IMMUNIZATIONTIME,FMT.format(id.getImmunizationTime()),
				Immunization.USERID, id.getUserid(),
				Immunization.HOUSEID,id.getHouseid()+"",
				START_LINE,Integer.toString(start), 
				MAX_COUNT, Integer.toString(count) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();

	}

	@Override
	public String marshall(ImmunizationId id, Date headerDate) {
		String[] params = new String[] { 
				Immunization.IMMUNIZATIONTIME,
				FMT.format(id.getImmunizationTime()) };
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(headerDate, sb);
		return sb.toString();
	}

	@Override
	public String marshall(Immunization t, int start, int count) {
		String[] params = new String[] { 
				Immunization.IMMUNIZATIONTIME,FMT.format(t.getId().getImmunizationTime()),

				Immunization.TOTALCOUNT,t.getTotalCount()+"",

				Immunization.VACCINE, t.getVaccine(),

				Immunization.VACCINEFACTORY, t.getVaccineFactory(),

				Immunization.BATCHNUMBER, t.getBatchNumber()+"",

				Immunization.DOSAGE, t.getDosage()+"",

				Immunization.IMMUNIZATIONMETHOD, t.getImmunizationMethod(),

				START_LINE, Integer.toString(start), MAX_COUNT,
				Integer.toString(count)

		};
		StringBuilder sb = new StringBuilder(toUri(params));
		appendHeaderDate(t.getHeaderDate(), sb);
		return sb.toString();
	}

}
