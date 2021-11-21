package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Jugador;
import util.JugadorHandler;

public class SAX {
		
		private SAXParserFactory saxParserFactory;
		private SAXParser saxParser;
		
		public SAX() throws ParserConfigurationException, SAXException {
				this.saxParserFactory = SAXParserFactory.newInstance();
				this.saxParser = saxParserFactory.newSAXParser();
		}
		
		public ArrayList<Jugador> getPlayers() throws SAXException, IOException {
				File file= new File("files/entrada.xml");
				JugadorHandler handler = new JugadorHandler();
				saxParser.parse(file, handler);
				ArrayList<Jugador> jugadores = handler.getJugadores();
				return jugadores;
		}
}
