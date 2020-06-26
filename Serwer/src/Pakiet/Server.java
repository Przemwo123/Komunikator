package Pakiet;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {

  List<PrintWriter> tablicaKlientow = new ArrayList<PrintWriter>();
  private String historia="";
  private boolean czyInfoODolonczeniu = false;

  public void start() {
      System.out.println("Server start!");
      MainServer.gui.wypisz(getTime()+"Server start!");

      try {
          @SuppressWarnings("resource")
          ServerSocket serverSock = new ServerSocket(9090);
          while(true) {
              Socket socketKlienta = serverSock.accept();
              tablicaKlientow.add(new PrintWriter(socketKlienta.getOutputStream()));//nowy klient

              Thread t = new Thread(new ObslugaKlientow(socketKlienta,tablicaKlientow.size()-1));
              t.start();
          }
      } catch(Exception ex) {
          ex.printStackTrace();
      }
  }

    private String getTime(){
        Date nowDate = new Date();
        SimpleDateFormat time = new SimpleDateFormat("(HH:mm:ss) ");

        return time.format(nowDate);
    }

  public class ObslugaKlientow implements Runnable {
      BufferedReader bufferedReader;
      int numerKlienta;

      public ObslugaKlientow(Socket clientSocket,int numer) {
          numerKlienta = numer;

          try {
              InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
              bufferedReader = new BufferedReader(isReader);
              System.out.println("Uzytkownik nr." +numerKlienta+ " dolonczyl do sesji");
              MainServer.gui.wypisz(getTime()+"Uzytkownik nr." +numerKlienta+ " dolonczyl do sesji!");
              PrintWriter pisarz = tablicaKlientow.get(numerKlienta);

              if(!historia.equals("")){
                  pisarz.println(historia);
                  pisarz.flush();
              }

              czyInfoODolonczeniu = true;
              rozeslijDoWszystkich(" Dolonczyl do sesji!",numerKlienta);

          } catch(Exception ex) {
              ex.printStackTrace();
          }
      }

      public void run() {
          String wiadomosc;

          try {
              while ((wiadomosc = bufferedReader.readLine()) != null) {
                  System.out.println("Uzytkownik nr."+numerKlienta+": " + wiadomosc);
                  MainServer.gui.wypisz(getTime()+"Uzytkownik nr."+numerKlienta+": " + wiadomosc);
                  rozeslijDoWszystkich(": " + wiadomosc,numerKlienta);
              }
          } catch(Exception ex) {
              System.out.println("Uzytkownik nr." +numerKlienta+ " opuscil sesje!");
              MainServer.gui.wypisz(getTime()+"Uzytkownik nr." +numerKlienta+ " opuscil sesje!");
              rozeslijDoWszystkich(" opuscil sesje!",numerKlienta);
              tablicaKlientow.remove(numerKlienta);
              tablicaKlientow.add(numerKlienta,null);
          }
      }
  }

  public void rozeslijDoWszystkich(String message,int numerKlienta) {

      if(historia.equals("")) historia += getTime()+"Uzytkownik nr."+numerKlienta+message;
      else historia += "\n"+getTime()+"Uzytkownik nr."+numerKlienta+message;

      Iterator<PrintWriter> it = tablicaKlientow.iterator();
      int i=0;
      while(it.hasNext()) {
          try {
              PrintWriter pisarz = (PrintWriter) it.next();
              if(pisarz != null){
                  if(czyInfoODolonczeniu){
                      if(i==numerKlienta){
                          pisarz.println("SERWER: #Witaj drogi uzytkowniku nr."+numerKlienta+"#");
                          pisarz.flush();
                      }else {
                          pisarz.println(getTime()+"Uzytkownik nr."+numerKlienta+" dolonczyl do sesji!");
                          pisarz.flush();
                      }
                  }else {
                      if(i==numerKlienta){
                          pisarz.println(getTime()+"Ja"+message);
                          pisarz.flush();
                      }else {
                          pisarz.println(getTime()+"Uzytkownik nr."+numerKlienta+message);
                          pisarz.flush();
                      }
                  }
              }

          } catch(Exception ex) {
              ex.printStackTrace();
          }
          i++;
      }
      czyInfoODolonczeniu=false;
  }
}
       