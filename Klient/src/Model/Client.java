package Model;

import GUI.ProgramGUI;
import pakiet.MainKlient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	static BufferedReader bufferedReader;
	static PrintWriter strumien;
	public static boolean czyPolonczony = false;
	private static boolean czyNapis = false;

	public void run() {
		Thread t = new Thread(new szukanieSewera());
		t.start();
	}

	public static class szukanieSewera implements Runnable {

		public void run() {
			while(!czyPolonczony){
				try {
					Socket socket = new Socket("127.0.0.1", 9090);
					InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
					bufferedReader = new BufferedReader(inputStreamReader);

					strumien = new PrintWriter(socket.getOutputStream());

					Thread odbieranieWiadomosci = new Thread(new OdbiorcaWiadomosci(bufferedReader));
					odbieranieWiadomosci.start();

					MainKlient.gui.przyciskWyslij.setEnabled(true);
					czyPolonczony=true;

				} catch(IOException ex) {
					if(!czyNapis){
						czyNapis = true;
						MainKlient.gui.wypisz("Brak połączenia z serwerem!");
					}
					System.out.println("Brak połączenia z serwerem!");
					czyPolonczony=false;
					//ex.printStackTrace();
				}
			}
		}
	}

	public void wyslijWiadomosc(String wiadomosc){
		try {
  	         strumien.println(wiadomosc);
  	         strumien.flush();

  	      } catch(Exception ex) {
			System.out.println("Serwer nie odpowiada!");
			//ex.printStackTrace();
  	      }
	}
}