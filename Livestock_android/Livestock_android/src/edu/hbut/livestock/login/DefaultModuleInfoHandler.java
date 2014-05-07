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
 * ����XML
 * 
 * @author Hang
 * 
 */
public class DefaultModuleInfoHandler extends DefaultHandler implements
		ModuleInfoHandler {

	/**
	 * ���ڴ洢����ģ��͸�����ģ��Ķ�Ӧ��ϵ
	 */
	private Map<ModuleId, ModuleId> funcMapping;

	/**
	 * �ڽ���������ʹ��ջ�洢��������Ϣ,����ʼ����һ����ǩʱ�����ջ����Ԫ�أ����Ԫ���ǵ�ǰ��ǩ�ĸ���ǩ����Ϣ������ǰ��ǩΪ����ǩ
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
		 * ��ǩ�����󣬽���ǩ��Ϣ�Ƴ�ջ
		 */
		stack.pop();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		/*
		 * ȡ��ջ��Ԫ��
		 */
		try {
			ModuleId parent = stack.peek();
			/*
			 * ���ӹ���ģ���븸����ģ��Ķ�Ӧ��ϵ����
			 */
			ModuleId moduleId = ModuleId.valueOf(qName);
			funcMapping.put(moduleId, parent);
			/*
			 * ��ջ
			 */
			stack.push(moduleId);
		} catch (EmptyStackException e) {
			/*
			 * ��ջ��û��Ԫ��ʱ����ʾǰ����ǩ�����Ĺ���ģ�飬��ʱֻ��Ҫ�ѽ����Ϣ��ջ����
			 */
			stack.push(ModuleId.valueOf(qName));
		}

	}

}
