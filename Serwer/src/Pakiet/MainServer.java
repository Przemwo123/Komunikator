package Pakiet;

import GUI.ProgramGUI;

public class MainServer {
	public static ProgramGUI gui = new ProgramGUI();

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		gui.start();
	}
}