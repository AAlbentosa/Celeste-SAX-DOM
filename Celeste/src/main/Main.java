package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import manager.Manager;

public class Main {

		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
				Manager m = Manager.getInstance();
				m.init();
		}

}
