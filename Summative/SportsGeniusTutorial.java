/*--------------------------------------------------------------------------------------*/
/*  SportsGeniusTutorial.java  -  This class encapsulates the logic and graphics of the
/* 							   	  tutorial page             							    */
/*                                                           					        */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: January 10, 2019                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: User action (Continue)     			                                        */
/*  Output: N/A  																		*/
/*--------------------------------------------------------------------------------------*/

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class SportsGeniusTutorial extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Font used in this page
	Font font16 = new Font("Average", Font.BOLD, 16);

	//create tutorial page based on the "title" passed in
	public SportsGeniusTutorial(String title) {
		initialize(title);
	}

	private void initialize(String title) {
		UIManager.getDefaults().put("TitledBorder.font", new javax.swing.plaf.FontUIResource(font16));
		//use BorderLayout
		setLayout(new BorderLayout());
		
		//create a text area
		JTextPane textPane = createTextPane(title);
		//add a vertical scroll bar to the text area
		JScrollPane paneScrollPane = new JScrollPane(textPane);
		paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		paneScrollPane.setPreferredSize(new Dimension(640, 400));
		
		//JPanel for text area
		JPanel centerPanel = new JPanel(new GridLayout(1, 0));
		centerPanel.add(paneScrollPane);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		//"Continue" button
		JButton btnContinue = new JButton("Continue");
		//set title as action command
		btnContinue.setActionCommand(title);
		btnContinue.addActionListener(this);
		
		//JPanel for Continue button
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(btnContinue);
		
		//use "CENTER" and "SOUTH" area
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		
	}

	//handle "Continue" button action
	public void actionPerformed(ActionEvent e) {
		//retrieve action command
		String actionCommand = e.getActionCommand();
		//get cardlayout
		CardLayout cl = (CardLayout) (SportsGeniusInfo.panelInCards.getLayout());
		
		if (actionCommand.equalsIgnoreCase("Tutorial")) {//from Tutorial page
			//go to Main Menu
			cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.mainPanelName);
		}else {//from Sport lesson page
			//create question page and show it
			SportsGeniusInfo.panelInCards.add(new SportsGeniusPanel(), SportsGeniusInfo.gamePanelName) ;
			cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.gamePanelName);
		}

		
	}

	//create text area(JTextPane)
	private JTextPane createTextPane(String title) {
		//read html based on title
		java.net.URL tutorialURL = SportsGeniusTutorial.class.getResource("Summative/"+title+".html");
		JTextPane textPane = new JTextPane();

		try {
			//display html page into text area(JTextPane)
			textPane.setPage(tutorialURL);
		} catch (Exception e) {

		}
		textPane.setEditable(false);

		return textPane;
	}
	
	
}
