package yl.xml.practice01;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadComplexXml {

	public static void main(String[] args) {
		Computer computer = new Computer();
		Contact contact = new Contact();
		Student student = new Student();
		File xml = new File("src/yl/xml/practice01/complex.xml");
		System.out.println(xml.exists());
		System.out.println(ReadComplexXml.class);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(xml);
			Element root = document.getRootElement();
			Element element;
			@SuppressWarnings("rawtypes")
			Iterator iterator = root.elementIterator();
			while (iterator.hasNext()) {
				element = (Element) iterator.next();
				String eleName = element.getName();
				// ----------- 读取到computer
				if ("computer".equals(eleName)) {
					String id = element.attributeValue("id");
					String brand = element.elementText("brand");
					String ip = element.elementText("ip");
					computer.setId(id);
					computer.setBrand(brand);
					computer.setIp(ip);
				}
				// ----------- 读取到student
				if ("student".equals(eleName)) {
					@SuppressWarnings("rawtypes")
					Iterator i = element.elementIterator();
					String id = element.attributeValue("id");
					student.setId(id);
					while (i.hasNext()) {
						Element ele = (Element) i.next();
						if ("contact".equals(ele.getName())) {
							String telephone = ele.elementText("telephone");
							String email = ele.elementText("email");
							contact.setTelephone(telephone);
							contact.setEmail(email);
						}
						else {
							if ("name".equals(ele.getName()))
								student.setName(ele.getStringValue());
							else if ("age".equals(ele.getName())) {
								Integer age = null;
								try {
									age = Integer.parseInt(ele.getStringValue());
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								student.setAge(age);
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(computer.getId());
		System.out.println(computer.getBrand());
		System.out.println(computer.getIp());
		System.out.println(student.getName());
		System.out.println(contact.getEmail());
	}

}
