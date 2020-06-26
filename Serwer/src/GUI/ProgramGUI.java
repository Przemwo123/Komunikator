package GUI;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

public class ProgramGUI {
	
	private JFrame frame;

	public JTextArea odebraneWiadomosci;

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramGUI.this.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProgramGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("*Serwer* Ale czad! v0.0.3f1");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel panelGlowny = new JPanel();

	    odebraneWiadomosci = new JTextArea(15,35);
	    odebraneWiadomosci.setEditable(false);
	    odebraneWiadomosci.setLineWrap(true);
	    odebraneWiadomosci.setWrapStyleWord(true);
	    odebraneWiadomosci.setFont(new Font("Monospaced", Font.PLAIN, 12));

	    JScrollPane przewijanie = new JScrollPane(odebraneWiadomosci);
	    przewijanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	    frame.getContentPane().add(BorderLayout.CENTER, panelGlowny);
	    GroupLayout gl_panelGlowny = new GroupLayout(panelGlowny);
	    gl_panelGlowny.setHorizontalGroup(
	    	gl_panelGlowny.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelGlowny.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panelGlowny.createParallelGroup(Alignment.LEADING)
	    				.addComponent(przewijanie, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, 450)
	    				.addGroup(gl_panelGlowny.createSequentialGroup()
	    					.addGap(0)))
	    			.addContainerGap())
	    );
	    gl_panelGlowny.setVerticalGroup(
	    	gl_panelGlowny.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panelGlowny.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(przewijanie, GroupLayout.DEFAULT_SIZE, 450, 450)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panelGlowny.createParallelGroup(Alignment.TRAILING, false))
						.addGap(12)
	    			.addContainerGap())
	    );
	    panelGlowny.setLayout(gl_panelGlowny);
	    frame.setSize(450,500);
	    frame.setVisible(true);
	}
	
	public void wypisz(String wiadom) {
		odebraneWiadomosci.append(wiadom + "\n");
	}
}
