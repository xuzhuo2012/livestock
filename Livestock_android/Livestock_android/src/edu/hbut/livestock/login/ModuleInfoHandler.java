package edu.hbut.livestock.login;

import edu.hbut.livestock.util.ModuleId;

/**
 * 定义处理功能模块的接口，主要用于解析module.xml文件，并将文件内容存入hash表
 * 
 * @author Hang
 * 
 */
public interface ModuleInfoHandler {

	/**
	 * 加入模块与父模块的对应关系
	 * 
	 * @param func
	 *            模块
	 * @param parent
	 *            父模块
	 */
	void put(ModuleId func, ModuleId parent);

	/**
	 * 获取父模块
	 * 
	 * @param func
	 *            模块
	 * @return 父模块
	 */
	ModuleId get(ModuleId func);
}
