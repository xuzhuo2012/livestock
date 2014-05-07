package edu.hbut.livestock.login;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.hbut.livestock.util.ModuleId;

/**
 * 解析XML
 * 
 * @author Hang
 * 
 */
public class DefaultModuleInfoHandler extends DefaultHandler implements
		ModuleInfoHandler {

	/**
	 * 用于存储功能模块和父功能模块的对应关系
	 */
	private Map<ModuleId, ModuleId> funcMapping;

	/**
	 * 在解析过程中使用栈存储父结点的信息,当开始处理一个标签时，如果栈中有元素，则该元素是当前标签的父标签的信息，否则当前标签为根标签
	 */
	private Stack<ModuleId> stack;

	public DefaultModuleInfoHandler() {
		funcMapping = new HashMap<ModuleId, ModuleId>();

		stack = new Stack<ModuleId>();
	}

	@Override
	public void put(ModuleId func, ModuleId parent) {
		funcMapping.put(func, parent);
	}

	@Override
	public ModuleId get(ModuleId func) {
		return funcMapping.get(func);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		/*
		 * 标签结束后，将标签信息移出栈
		 */
		stack.pop();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		/*
		 * 取得栈顶元素
		 */
		try {
			ModuleId parent = stack.peek();
			/*
			 * 将子功能模块与父功能模块的对应关系保存
			 */
			ModuleId moduleId = ModuleId.valueOf(qName);
			funcMapping.put(moduleId, parent);
			/*
			 * 入栈
			 */
			stack.push(moduleId);
		} catch (EmptyStackException e) {
			/*
			 * 当栈中没有元素时，表示前当标签是最大的功能模块，此时只需要把结点信息入栈即可
			 */
			stack.push(ModuleId.valueOf(qName));
		}

	}

}
