package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import model.Jugador;
import util.PathFinder;

public class DOM {
		private DocumentBuilder builder;
	
		public DOM() throws ParserConfigurationException {
				DocumentBuilderFactory factory =DocumentBuilderFactory.newDefaultInstance();
				this.builder = factory.newDocumentBuilder();
		}
		
		public void generateDocument(String[][][] screens, ArrayList<Jugador> players, String filename, boolean showPath) throws IOException, TransformerException {
				Document document = builder.newDocument();
				Element pantallas=createPantallas(document);
			
				for(Jugador player:players) {
						int screenNumber=player.isCompleted()?player.getScreenNumber():player.getScreenNumber()-1;
						if(!showPath)
							screenNumber--;
						
						List<String> path=getPath(screens, screenNumber, showPath, player);
						
						Element pantalla=createPantalla(player.getName(), screenNumber, document);
						pantallas.appendChild(pantalla);	
						 
						for(String position: path)
								pantalla.appendChild(createPixel(document, position, screens, screenNumber));

						pantalla.appendChild(createPuntuation(document, player.getPuntuation()));
				}
				generateXML(document, filename);
		}

		private Node createPuntuation(Document document, int playerPuntuacion) {
				Element puntuation=document.createElement("puntuacion");
				puntuation.appendChild(document.createTextNode(""+playerPuntuacion));
				return puntuation;
		}

		private Element createPixel(Document document, String position, String[][][] screens, int screenNumber) {
				Element pixel=document.createElement("pixel");
				String[] coordinates=position.split("-");
				pixel.setAttribute("fil", coordinates[0]);
				pixel.setAttribute("col", coordinates[1]);
				pixel.appendChild(document.createTextNode(screens[screenNumber][Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])]));
				return pixel;
		}

		private Element createPantalla(String name, int screenNumber, Document document) {
				Element pantalla=document.createElement("pantalla");
				pantalla.setAttribute("jugador", name);
				pantalla.setAttribute("nivel", ""+(screenNumber+1));
			 
				return pantalla;
		}

		private Element createPantallas(Document document) {
				Element pantallas=document.createElement("pantallas");
				document.appendChild(pantallas);
				return pantallas;
		}

		private List<String> getPath(String[][][] screens, int screenNumber, boolean showPath, Jugador player) {
				List<String> path = new ArrayList<String>();
				PathFinder pf = new PathFinder();
				
				if(showPath)
						return pf.createGraph(screens[screenNumber], player.getName());

				for(int x=0; x<screens[screenNumber].length; x++)
						for(int y=0; y<screens[screenNumber][x].length; y++)
								path.add(x+"-"+y);
				return path;
		}

		private void generateXML(Document document, String docName) throws IOException, TransformerException {
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer();
				File file = new File("files/"+docName);
				FileWriter fw=new FileWriter(file);
				transformer.transform(new DOMSource(document), new StreamResult(new PrintWriter(fw)));
				fw.close();
		}
}
