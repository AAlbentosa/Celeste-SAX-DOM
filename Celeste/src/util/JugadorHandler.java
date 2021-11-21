package util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Jugador;

public class JugadorHandler extends DefaultHandler{
	
	
		private ArrayList<Jugador> players = new ArrayList<Jugador>();
		private Jugador player = new Jugador();
		private StringBuilder buffer = new StringBuilder();
		
		public ArrayList<Jugador> getJugadores() {
			return players;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			
				switch(qName) {
						case "partidas":
								break;								

						case "partida":
								player = new Jugador();
								players.add(player);	
								player.setName(attributes.getValue("jugador"));
								break;						

						case "puntuacion":
								buffer.delete(0, buffer.length());
								break;								

						case "pantalla":
								player.setCompleted(attributes.getValue("estado").equals("completa")?true:false);
								buffer.delete(0, buffer.length());
								break;
				}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
				switch(qName) {
						case  "puntuacion":
								player.setPuntuation(Integer.parseInt(buffer.toString()));
								break;
						
						case  "pantalla":
								player.setScreenNumber(Integer.parseInt(buffer.toString().substring(1)));
								break;
				}	
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
				buffer.append(ch, start, length);
		}
}
