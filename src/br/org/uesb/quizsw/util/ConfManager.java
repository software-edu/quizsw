package br.org.uesb.quizsw.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ConfManager {
	
	public static Database getDatabaseInfo() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Database.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Database db = (Database) jaxbUnmarshaller.unmarshal(new File("bin/conf/conf.xml"));
			
			return db;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
