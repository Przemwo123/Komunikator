package pakiet;

import GUI.ProgramGUI;
import Model.Client;

public class MainKlient {
	public static Client client = new Client();
	public static ProgramGUI gui = new ProgramGUI();

	public static void main(String[] args) {
		gui.start();
		client.run();
	}
}