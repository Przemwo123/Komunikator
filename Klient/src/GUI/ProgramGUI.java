package GUI;

import Model.Client;
import pakiet.MainKlient;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramGUI {
	
	private JFrame frame;
	private Client client;
    public JButton przyciskWyslij;

	public JTextArea odebraneWiadomosci;
	JTextField wiadomosc;

	public void start() {
        this.client = MainKlient.client;
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
		frame = new JFrame("Ale czad! v0.0.2");
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

	    wiadomosc = new JTextField(10);
	    wiadomosc.setFont(new Font("Tahoma", Font.PLAIN, 15));

	    przyciskWyslij = new JButton("Wyslij!");
	    przyciskWyslij.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    przyciskWyslij.setFont(new Font("Tahoma", Font.PLAIN, 22));
        przyciskWyslij.setEnabled(false);

	    przyciskWyslij.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    		if(!wiadomosc.getText().equals("")){
					client.wyslijWiadomosc(wiadomosc.getText());
					wiadomosc.setText("");
					wiadomosc.requestFocus();
				}
	    	}
	    });

	    frame.getContentPane().add(BorderLayout.CENTER, panelGlowny);
	    GroupLayout gl_panelGlowny = new GroupLayout(panelGlowny);
	    gl_panelGlowny.setHorizontalGroup(
	    	gl_panelGlowny.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panelGlowny.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_panelGlowny.createParallelGroup(Alignment.LEADING)
	    				.addComponent(przewijanie, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
	    				.addGroup(gl_panelGlowny.createSequentialGroup()
	    					.addComponent(wiadomosc, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(przyciskWyslij, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
	    			.addContainerGap())
	    );
	    gl_panelGlowny.setVerticalGroup(
	    	gl_panelGlowny.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_panelGlowny.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(przewijanie, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_panelGlowny.createParallelGroup(Alignment.TRAILING, false)
	    				.addComponent(wiadomosc)
	    				.addComponent(przyciskWyslij, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    panelGlowny.setLayout(gl_panelGlowny);
	    frame.setSize(486,433);
	    frame.setVisible(true);
	}
	
	public void wypisz(String wiadom) {
		odebraneWiadomosci.append(wiadom + "\n");
	}
}
