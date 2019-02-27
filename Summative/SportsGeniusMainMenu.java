/*--------------------------------------------------------------------------------------*/
/*  SportsGeniusMainMenu.java  -  This class encapsulates the logic and graphics of the */
/* 							      main menu screen.This is the entry point of my program/*                                                           					        */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: January 10, 2019                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: User action (sport, difficulty)                                              */
/*  Output: all the screens  															*/
/*--------------------------------------------------------------------------------------*/

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SportsGeniusMainMenu implements ActionListener {

	
	private JFrame frmSummative;//summative application window
	
	JPanel mainMenuPanel;//entire Panel
	JPanel northPanel;//panel for Start Menu label
	JPanel southPanel;//panel for startPanel
	JPanel difficultyPanel;//panel for difficulty JComboBox
	JPanel startPanel;//panel for START button
	JPanel sportPanel;//panel for 4 sports choice
	
	JPanel tutorialPanel;//panel for tutorial page

	JLabel startMenuLabel;// label for Start Menu image
	JLabel difficultyChooseLabel;//label for difficulty prompt image

	@SuppressWarnings("rawtypes")
	JComboBox difficultyJComBox;

	JButton btnBasketball;//for choosing basketball
	JButton btnSoccer;//for choosing soccer
	JButton btnBadminton;//for choosing badminton
	JButton btnVolleyball;//for choosing volleyball

	JButton btnStart;
	
	Container frmContainer;//frame content pane;

	/**
	 * Launch the application.
	 * This is the entry point of this summative application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SportsGeniusMainMenu window = new SportsGeniusMainMenu();
					window.frmSummative.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SportsGeniusMainMenu() {
		SportsGeniusInfo.loadSportQuestion();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//setup application outlook
		frmSummative = new JFrame();
		frmSummative.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Summative/frameIcon.jpg"));

		frmSummative.setTitle("Sports Genius");
		frmSummative.setBounds(300, 200, 640, 480);
		frmSummative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSummative.setResizable(false);

		//using cardlayout
		SportsGeniusInfo.panelInCards = new JPanel(new CardLayout());
		//adding MainMenu panel and Tutorial panel into cards
		SportsGeniusInfo.panelInCards.add(createMainMenuPanel(), SportsGeniusInfo.mainPanelName) ;
		SportsGeniusInfo.panelInCards.add(createTutorialPanel(), SportsGeniusInfo.tutorialPanelName) ;

		//adding Cards into frame content pane
		frmContainer = frmSummative.getContentPane();
		frmContainer.add(SportsGeniusInfo.panelInCards);
		
		//showing Tutorial page first
		CardLayout cl = (CardLayout)(SportsGeniusInfo.panelInCards.getLayout());
        cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.tutorialPanelName);
		
		frmContainer.invalidate();
		frmContainer.validate();
}
	
	// method to create the main menu panel
	private JPanel createMainMenuPanel() {
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new BorderLayout(0, 0));
		mainMenuPanel.add(createNorthPanel(), BorderLayout.NORTH);
		mainMenuPanel.add(createSouthPanel(), BorderLayout.SOUTH);
		mainMenuPanel.add(createSportPanel(), BorderLayout.CENTER);
		return mainMenuPanel;
	}
	
	//handle the sport selection
	public void actionPerformed(ActionEvent evt) {
		//get selected sport name 
		SportsGeniusInfo.sport = evt.getActionCommand();
		
		//reset to unselected sport image
		btnBasketball.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnBasketball.JPG"))
				.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
		btnSoccer.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnSoccer.JPG"))
				.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
		btnBadminton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnBadminton.JPG"))
				.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
		btnVolleyball.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnVolleyball.JPG"))
				.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));

		//toggle the selected sport image for basketball
		if (evt.getSource() == btnBasketball) {
			btnBasketball.setIcon(
					new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnBasketballToggle.JPG"))
							.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
			return;
		}

		//toggle the selected sport image for soccer
		if (evt.getSource() == btnSoccer) {
			btnSoccer.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnSoccerToggle.JPG"))
					.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
			return;
		}

		//toggle the selected sport image for badminton
		if (evt.getSource() == btnBadminton) {
			btnBadminton.setIcon(
					new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnBadmintonToggle.JPG"))
							.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
			return;
		}

		//toggle the selected sport image for volleyball
		if (evt.getSource() == btnVolleyball) {
			btnVolleyball.setIcon(
					new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnVolleyballToggle.JPG"))
							.getImage().getScaledInstance(330, 150, Image.SCALE_DEFAULT)));
			return;
		}
	}

	// create north panel
	private JPanel createNorthPanel() {
		if (northPanel != null)
			return northPanel;

		northPanel = new JPanel();
		northPanel.setBackground(new Color(109, 158, 235));
		northPanel.add(createStartMenuLabel());

		return northPanel;
	}

	private JPanel createSouthPanel() {
		if (southPanel != null)
			return southPanel;

		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 1, 0, 0));
		southPanel.add(createDifficultyPanel());
		southPanel.add(createStartPanel());

		return southPanel;
	}

	// create panel to holds the difficulty setting
	private JPanel createDifficultyPanel() {

		if (difficultyPanel != null)
			return difficultyPanel;

		difficultyPanel = new JPanel();
		difficultyPanel.setBackground(new Color(109, 158, 235));
		difficultyPanel.setLayout(new GridLayout(1, 2));
		difficultyPanel.add(createDifficultyChooseLabel());
		difficultyPanel.add(createDifficultyJComBox());

		return difficultyPanel;
	}

	// create panel that holds the start button
	private JPanel createStartPanel() {
		if (startPanel != null)
			return startPanel;

		startPanel = new JPanel();
		startPanel.setBackground(new Color(109, 158, 235));
		startPanel.setLayout(new GridLayout(1, 3));

		startPanel.add(createFillerLabel());
		startPanel.add(createBtnStart());
		startPanel.add(createFillerLabel());

		return startPanel;
	}

	// create panel that holds all the sports buttons
	private JPanel createSportPanel() {

		if (sportPanel != null) {
			return sportPanel;
		}

		sportPanel = new JPanel();
		sportPanel.setBackground(new Color(109, 158, 235));
		sportPanel.setLayout(new GridLayout(2, 2, 0, 0));

		btnSoccer = createButton("btnSoccer.JPG", SportsGeniusInfo.SOCCER);
		btnBasketball = createButton("btnBasketball.JPG", SportsGeniusInfo.BASKETBALL);
		btnBadminton = createButton("btnBadminton.JPG", SportsGeniusInfo.BADMINTON);
		btnVolleyball = createButton("btnVolleyball.JPG", SportsGeniusInfo.VOLLEYBALL);

		sportPanel.add(btnSoccer);
		sportPanel.add(btnBasketball);
		sportPanel.add(btnBadminton);
		sportPanel.add(btnVolleyball);

		return sportPanel;
	}

	// create tutorial page panel
	private JPanel createTutorialPanel() {

		tutorialPanel = new SportsGeniusTutorial("Tutorial");

		return tutorialPanel;
	}

	// Create main menu title
	private JLabel createStartMenuLabel() {

		if (startMenuLabel != null)
			return startMenuLabel;

		startMenuLabel = new JLabel("");
		startMenuLabel.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/StartMenu.JPG"))
				.getImage().getScaledInstance(500, 80, Image.SCALE_DEFAULT)));

		return startMenuLabel;
	}

	// choose difficulty picture
	private JLabel createDifficultyChooseLabel() {
		if (difficultyChooseLabel != null)
			return difficultyChooseLabel;

		difficultyChooseLabel = new JLabel("");
		difficultyChooseLabel
				.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/chooseDifficulty.JPG"))
						.getImage().getScaledInstance(310, 40, Image.SCALE_DEFAULT)));

		return difficultyChooseLabel;
	}

	// empty label for a filler
	private JLabel createFillerLabel() {
		return new JLabel();
	}

	// JComboBox to select the difficulty setting
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox createDifficultyJComBox() {
		if (difficultyJComBox != null) {
			return difficultyJComBox;
		}

		String[] difficulties = { SportsGeniusInfo.EASY, SportsGeniusInfo.INTERMEDIATE, SportsGeniusInfo.HARD };
		difficultyJComBox = new JComboBox(difficulties);
		difficultyJComBox.setFont(new Font("Average", Font.PLAIN, 20));
		difficultyJComBox.setOpaque(false);
		difficultyJComBox.setMaximumRowCount(3);
		difficultyJComBox.setBackground(new Color(109, 158, 235));
		difficultyJComBox.setSelectedIndex(0);
		difficultyJComBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SportsGeniusInfo.difficulty = (String) difficultyJComBox.getSelectedItem();
				System.out.println(SportsGeniusInfo.difficulty + " selected");
			}
		});

		return difficultyJComBox;
	}

	// create Start button to start the questions
	private JButton createBtnStart() {

		if (btnStart != null)
			return btnStart;

		btnStart = new JButton();
		btnStart.setBackground(new Color(109, 158, 235));
		btnStart.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/btnStart.png")).getImage()
				.getScaledInstance(200, 40, Image.SCALE_DEFAULT)));
		
		//handle start event
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if a sport is selected
				if (!SportsGeniusInfo.sport.equals(SportsGeniusInfo.SOCCER)
						&& !SportsGeniusInfo.sport.equals(SportsGeniusInfo.BASKETBALL)
						&& !SportsGeniusInfo.sport.equals(SportsGeniusInfo.BADMINTON)
						&& !SportsGeniusInfo.sport.equals(SportsGeniusInfo.VOLLEYBALL)) {
					//if no sport select, popup an error message
					JOptionPane.showMessageDialog(frmSummative, "You must select a Sport to continue", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						//create selected sport lesson and add it into Cards
						SportsGeniusInfo.panelInCards.add(new SportsGeniusTutorial(SportsGeniusInfo.sport), SportsGeniusInfo.sport) ;
						CardLayout cl = (CardLayout)(SportsGeniusInfo.panelInCards.getLayout());
				        //showing selected sport lesson
						cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.sport);
						
						frmContainer.invalidate();
						frmContainer.validate();
					}
				});

				return;
			}
		});
		

		return btnStart;
	}

	// generic method to create a button with a picture that has the file name "picName"
	private JButton createButton(String picName, String actionCommand) {

		JButton button = new JButton();
		button.addActionListener(this);
		button.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Summative/" + picName)).getImage()
				.getScaledInstance(330, 150, Image.SCALE_DEFAULT)));

		button.setActionCommand(actionCommand);

		return button;
	}
}
