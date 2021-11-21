package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
		private FileReader fr;
		private BufferedReader br;

		public Reader(String filepath){
				try {
						fr = new FileReader(filepath);
						br = new BufferedReader(fr);
				} catch (FileNotFoundException e) {
						System.out.println("File not found");
				}		
		}
		
		public void close() {
				try {
						fr.close();
						br.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		public String getLine() {
				try {
						return br.readLine();
				} catch (IOException e) {
						e.printStackTrace();
						return "";
				}
		}
}
