package manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import dao.DOM;
import dao.Reader;
import dao.SAX;
import model.Jugador;


public class Manager {
		private static Manager m = new Manager();
		private String[][][] screens= new String[3][5][10];
		private  ArrayList<Jugador> players;
		
		private Manager(){}

		public static Manager getInstance(){
				if (m==null)
						m = new Manager();
				return m;
		}
		
		public void init() throws ParserConfigurationException, SAXException, IOException, TransformerException{
				readXML();
				readScreens();
				generateXML();
		}
		
		private void readXML() throws ParserConfigurationException, SAXException, IOException {
				SAX sax=new SAX();
				players=sax.getPlayers();
		}
		
		private void readScreens() {
				Reader read= new Reader("files/pantallas.txt");
				String line;
				int screenNumber=0, row=0;
				
				while((line = read.getLine())!=null) {
						if(line.charAt(0)=='#') {
								screenNumber++;
								row=0;
						}else {
								screens[screenNumber-1][row]=line.split(" ");
								row++;
						}
				}		
				read.close();
		}
		
		private void generateXML() throws ParserConfigurationException, IOException, TransformerException {
				DOM dom = new DOM();
				dom.generateDocument(screens, players, "extra.xml", true);
				dom.generateDocument(screens, players, "output.xml", false);
		}		
}