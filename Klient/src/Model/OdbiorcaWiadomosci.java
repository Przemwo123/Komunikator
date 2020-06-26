package Model;

import GUI.ProgramGUI;
import pakiet.MainKlient;

import java.io.BufferedReader;

public class OdbiorcaWiadomosci implements Runnable {
    static BufferedReader bufferedReader;
    String wiadom;

    public OdbiorcaWiadomosci(BufferedReader bufferedReader1){
        bufferedReader = bufferedReader1;
    }

	private ProgramGUI gui;
	public void run() {
      try {
        while ((wiadom = bufferedReader.readLine()) != null) {
          MainKlient.gui.wypisz(wiadom);
        }
      } catch(Exception ex) {
          System.out.println("Utracono połączenie!");
          Client.czyPolonczony = false;
          MainKlient.gui.przyciskWyslij.setEnabled(false);
          MainKlient.gui.wypisz("Utracono połączenie!");
          MainKlient.client.run();
    	  //ex.printStackTrace();
      }
    }
}
