package edu.hbut.livestock.login;

import edu.hbut.livestock.util.ModuleId;

/**
 * ���崦����ģ��Ľӿڣ���Ҫ���ڽ���module.xml�ļ��������ļ����ݴ���hash��
 * 
 * @author Hang
 * 
 */
public interface ModuleInfoHandler {

	/**
	 * ����ģ���븸ģ��Ķ�Ӧ��ϵ
	 * 
	 * @param func
	 *            ģ��
	 * @param parent
	 *            ��ģ��
	 */
	void put(ModuleId func, ModuleId parent);

	/**
	 * ��ȡ��ģ��
	 * 
	 * @param func
	 *            ģ��
	 * @return ��ģ��
	 */
	ModuleId get(ModuleId func);
}
