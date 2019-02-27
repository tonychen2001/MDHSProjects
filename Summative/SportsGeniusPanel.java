
/*--------------------------------------------------------------------------------------*/
/*  SportsGeniusPanel.java  -  This class encapsulates the logic and graphics of the	*/
/* 							   question page             							    */
/*                                                           					        */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: January 10, 2019                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: user answer, user action (MainMenu, Exit, Submit)                            */
/*  Output: # of correct answers, # of questions answered, time limit, pop up screen	*/
/* 			if user answers incorrectly									       		    */
/*--------------------------------------------------------------------------------------*/

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class SportsGeniusPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	Container container;
	JPanel gamePanel; // entire question page panel
	JPanel qnaPanel; // panel for the question, image, and answer (left side)
	JPanel questionPanel; // panel that contains the text pane
	JPanel answerPanel; // panel that contains the multiple choice answers
	JPanel inputAnswerPanel; // panel that allows the user to type in their answer
	JTextField answerText; // for the user typed in answer
	JScrollPane paneScrollPane;  // hidden scroll bar for the question panel
	JTextPane textPane; // contains the question and sport image
	JLabel questionPicLbl; // question image
	JPanel actionPanel;  // panel that contains the submit button

	JRadioButton radioAnswer1; // multiple choice 1
	JRadioButton radioAnswer2; // multiple choice 2
	JRadioButton radioAnswer3; // multiple choice 3
	JRadioButton radioAnswer4; // multiple choice 4

	JPanel timerPanel; // panel that contains the time limit
	Timer timer; // java swing built in timer for the count down
	long lastTickTime; // the time that starts the timing
	int countDown = 12; // default time limit for intermediate
	int counter; // counter for the current time left
	JLabel counterLbl; // display the counter
	
	JButton btnSubmit; // button to submit user answer
	JButton btnExit; // button to exit the entire program
	JButton btnMainMenu; // button to return to main menu

	JPanel summaryPanel; // panel that contains summaryTable, summaryLblPanel, and summaryActionPanel
	JPanel summaryActionPanel; // panel that contains main menu and exit buttons
	JPanel summaryLblPanel; // chart image
	JTable summaryTable; // contains score counter
	DefaultTableModel tableModel;

	// define fonts used
	Font font12 = new Font("Average", Font.BOLD, 12);
	Font font14 = new Font("Average", Font.BOLD, 14);
	Font font16 = new Font("Average", Font.BOLD, 16);

	QnA[] questions; // array to hold 8 randomly generated questions
	int qaCount = 0; // index of the questions array

	int questionAnswered = 0; // counter to keep track of the # of questions answered
	int correctAnswers = 0; // counter to keep track of the # of questions answered correctly

	String userAnswer; // both user multiple choice and input answer

	// constructor method
	public SportsGeniusPanel() {
		// generate 8 random questions
		questions = SportsGeniusInfo.selectQuestions(); 
		// set time limit to 8 if difficulty is "hard"
		if (SportsGeniusInfo.difficulty.equals(SportsGeniusInfo.HARD)) 
			countDown = 8;
		initialize();
	}

	// create question page
	private void initialize() {
		UIManager.getDefaults().put("TitledBorder.font", new javax.swing.plaf.FontUIResource(font14));
		// gamePanel = new JPanel();
		setLayout(new BorderLayout());
		add(createQnAPanel(), BorderLayout.CENTER);
		add(createSummaryActionPanel(), BorderLayout.EAST);
		// add(gamePanel);
		if (timer != null)
			startTiming();

		qaCount = 0; // set index to 0
	}

	/**
	 * @return the gamePanel
	 */
	public JPanel getGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(createQnAPanel(), BorderLayout.CENTER);
		gamePanel.add(createSummaryActionPanel(), BorderLayout.EAST);
		add(gamePanel);
		return gamePanel;
	}

	// method to create a panel holding the question, image, and answer area (left side of page)
	private JPanel createQnAPanel() {

		// panel for btnSubmit
		JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnSubmit = createButton("Submit");
		submitPanel.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// if user presses btnSubmit, stop timing
				if (timer != null)
					stopTiming();

				// disable btnSubmit
				btnSubmit.setEnabled(false); 
				// correctAnswers + 1 if user answers correctly
				if (userAnswer != null && userAnswer.equalsIgnoreCase(questions[qaCount].getAnswer())) {
					correctAnswers++;
				// if user answers incorrectly
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, correct answer is: " + questions[qaCount].getAnswer(),
							"Wrong Answer", JOptionPane.INFORMATION_MESSAGE);
				}

				// update score counter
				tableModel.setValueAt(qaCount + 1, 1, 1);
				tableModel.setValueAt(correctAnswers, 3, 1);

				// check if all 8 questions are finished
				if (qaCount >= questions.length - 1) {
					JOptionPane.showMessageDialog(null,
							"Great work! You finished all question\nNow go back to main menu", "Congratulations",
							JOptionPane.INFORMATION_MESSAGE);
					;
					removeAll();
					CardLayout cl = (CardLayout) (SportsGeniusInfo.panelInCards.getLayout());
					cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.mainPanelName);
					return;
				}

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							answerNextQuestion();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		qnaPanel = new JPanel();
		qnaPanel.setLayout(new BoxLayout(qnaPanel, BoxLayout.Y_AXIS));
		qnaPanel.add(createQuestionPanel());

		// if no multiple choice options, create user input answer question
		if (questions[qaCount].getOptions() == null) {
			qnaPanel.add(createInputAnswerPanel());
		// else create multiple choice question
		} else {
			qnaPanel.add(createAnswerPanel());
		}
		qnaPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		qnaPanel.add(submitPanel);

		return qnaPanel;
	}

	// method to create the panel that contains the main menu and exit buttons
	private JPanel createSummaryActionPanel() {
		if (summaryActionPanel != null)
			return summaryActionPanel;

		summaryActionPanel = new JPanel();

		summaryActionPanel.setLayout(new BoxLayout(summaryActionPanel, BoxLayout.Y_AXIS));

		summaryActionPanel.add(createSummaryPanel());
		summaryActionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		summaryActionPanel.add(createActionPanel());

		return summaryActionPanel;
	}

	// method to create the panel that contains the score counter, main menu, and exit buttons
	private JPanel createSummaryPanel() {
		if (summaryPanel != null)
			return summaryPanel;

		summaryPanel = new JPanel();
		summaryPanel.setLayout(new BorderLayout());
		summaryPanel.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Summary"),
						javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		summaryPanel.add(createSummaryLblPanel(), BorderLayout.NORTH);
		summaryPanel.add(createSummaryTable(), BorderLayout.CENTER);

		return summaryPanel;
	}
	
	// method to create panel that contains the text pane(question) and image
	private JPanel createQuestionPanel() {

		questionPanel = new JPanel();

		questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
		questionPanel.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Question"),
						javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		questionPanel.add(createPaneScrollPane());
		questionPanel.add(createQuestionPicLbl(), BorderLayout.WEST);

		return questionPanel;

	}

	// method to create the scroll bar for the text pane
	private JScrollPane createPaneScrollPane() {

		paneScrollPane = new JScrollPane(createTextPane());
		paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		paneScrollPane.setPreferredSize(new Dimension(350, 110));
		paneScrollPane.setMinimumSize(new Dimension(10, 10));
		paneScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

		return paneScrollPane;
	}

	// method to create the text pane with the sport image and question
	private JTextPane createTextPane() {

		String[] initString = { " ", questions[qaCount].getDescriptions() };

		String[] initStyles = { "icon", "bold" };

		textPane = new JTextPane();

		StyledDocument doc = textPane.getStyledDocument();
		addStylesToDocument(doc);

		try {
			for (int i = 0; i < initString.length; i++) {
				doc.insertString(doc.getLength(), initString[i], doc.getStyle(initStyles[i]));
			}
		} catch (BadLocationException ble) {

		}

		textPane.setEditable(false);
		textPane.setFocusable(false);
		return textPane;
	}

	// customize the text pane
	private void addStylesToDocument(StyledDocument doc) {
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "Average");

		Style s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);
		StyleConstants.setFontSize(s, 16);
		StyleConstants.setForeground(s, new Color(136, 0, 21));

		s = doc.addStyle("icon", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);

		ImageIcon sportIcon = null;
		if (SportsGeniusInfo.sport.equals(SportsGeniusInfo.VOLLEYBALL))
			sportIcon = createImageIcon("summative/volleyball32.jpg");
		if (SportsGeniusInfo.sport.equals(SportsGeniusInfo.BASKETBALL))
			sportIcon = createImageIcon("summative/basketball32.jpg");
		if (SportsGeniusInfo.sport.equals(SportsGeniusInfo.SOCCER))
			sportIcon = createImageIcon("summative/soccer32.jpg");
		if (SportsGeniusInfo.sport.equals(SportsGeniusInfo.BADMINTON))
			sportIcon = createImageIcon("summative/badminton32.jpg");

		if (sportIcon != null) {
			StyleConstants.setIcon(s, sportIcon);
		}

	}

	// question image
	private JLabel createQuestionPicLbl() {

		questionPicLbl = new JLabel(
				createImageIcon("summative/" + SportsGeniusInfo.sport + "/" + questions[qaCount].getImageName()));
		questionPicLbl.setPreferredSize(new Dimension(192, 127));
		return questionPicLbl;
	}

	// method for multiple choice options
	private JPanel createAnswerPanel() {
		answerPanel = new JPanel();

		answerPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createTitledBorder("Select one"),
				javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		answerPanel.setLayout(new GridLayout(3, 2));

		// create 4 radio buttons for each choice
		radioAnswer1 = createRadioAnswer(questions[qaCount].getOptions()[0]);
		radioAnswer2 = createRadioAnswer(questions[qaCount].getOptions()[1]);
		radioAnswer3 = createRadioAnswer(questions[qaCount].getOptions()[2]);
		radioAnswer4 = createRadioAnswer(questions[qaCount].getOptions()[3]);

		ButtonGroup group = new ButtonGroup();
		group.add(radioAnswer1);
		group.add(radioAnswer2);
		group.add(radioAnswer3);
		group.add(radioAnswer4);

		radioAnswer1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		radioAnswer2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		radioAnswer3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		radioAnswer4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});

		answerPanel.add(radioAnswer1);
		answerPanel.add(radioAnswer2);
		answerPanel.add(radioAnswer3);
		answerPanel.add(radioAnswer4);

		if (!SportsGeniusInfo.difficulty.equals(SportsGeniusInfo.EASY))
			answerPanel.add(createTimerPanel());

		return answerPanel;
	}

	// generic method to create a radio button with "text" text
	private JRadioButton createRadioAnswer(String text) {

		JRadioButton radioAnswer = new JRadioButton(text);
		radioAnswer.setActionCommand(text);
		radioAnswer.setFont(font12);
		return radioAnswer;
	}

	// method for the timer panel
	private JPanel createTimerPanel() {

		timerPanel = new JPanel();
		timerPanel.setLayout(new GridBagLayout());

		counter = countDown;

		counterLbl = new JLabel();

		counterLbl.setFont(new Font("Average", Font.BOLD, 12));

		lastTickTime = 0;
		// set timer to refresh every second
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//display time limit
				counterLbl.setText(String.format("%02d seconds left", counter));

				// if time limit runs out
				if (counter <= 0) {

					// stop timing
					timer.stop();

					// disable submit button
					btnSubmit.setEnabled(false);

					// display correct answer
					JOptionPane.showMessageDialog(null, "Correct answer is: " + questions[qaCount].getAnswer(),
							"Nice Try", JOptionPane.INFORMATION_MESSAGE);

					tableModel.setValueAt(qaCount + 1, 1, 1);

					// check if all 8 questions are finished
					if (qaCount >= questions.length - 1) {

						JOptionPane.showMessageDialog(null,
								"Great effort! You finished all questions\nNow go back to main menu", "Game Over",
								JOptionPane.INFORMATION_MESSAGE);
						;
						removeAll();

						CardLayout cl = (CardLayout) (SportsGeniusInfo.panelInCards.getLayout());
						cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.mainPanelName);
						return;
					}

					answerNextQuestion();
				} else
					counter--;
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(4, 4, 4, 4);
		timerPanel.add(counterLbl, gbc);

		return timerPanel;
	}

	// start timing if timer is created
	public void startTiming() {
		if (!timer.isRunning()) {
			lastTickTime = System.currentTimeMillis();
			timer.start();
		}
	}

	// method to stop timer
	public void stopTiming() {
		timer.stop();
	}

	// generic method to create a button with "text" text
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(font12);
		return button;
	}

	// summary panel image
	private JPanel createSummaryLblPanel() {

		if (summaryLblPanel != null)
			return summaryLblPanel;

		summaryLblPanel = new JPanel();
		JLabel summaryLbl = new JLabel(createImageIcon("summative/chart48.jpg"));

		summaryLbl.setPreferredSize(new Dimension(64, 44));

		summaryLblPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		summaryLblPanel.add(summaryLbl);// , BorderLayout.WEST);

		return summaryLblPanel;
	}

	// table that contains the score counter
	private JTable createSummaryTable() {

		if (summaryTable != null)
			return summaryTable;

		String[] columnNames = { "Name", "Value", };

		Object[][] data = { { "", "" }, { "Question Answered:", "0" }, { "", "" }, { "Correct Answers:", "0" } };
		tableModel = new DefaultTableModel(data, columnNames);
		summaryTable = new JTable(tableModel);
		summaryTable.setPreferredScrollableViewportSize(new Dimension(230, 110));
		summaryTable.getColumn("Name").setPreferredWidth(200);
		summaryTable.getColumn("Value").setPreferredWidth(30);
		summaryTable.setFillsViewportHeight(true);
		summaryTable.setShowGrid(false);
		summaryTable.setTableHeader(null);
		summaryTable.setFont(font14);
		summaryTable.setForeground(new Color(136, 0, 21));
		summaryTable.setRowSelectionAllowed(false);
		summaryTable.setFocusable(false);
		summaryTable.setEnabled(false);

		return summaryTable;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = this.getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// create user input answer panel
	private JPanel createInputAnswerPanel() {

		inputAnswerPanel = new JPanel();

		inputAnswerPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createTitledBorder("Type Your Answer(One word)"),
				javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		inputAnswerPanel.setLayout(new FlowLayout(3));

		JLabel answerLbl = new JLabel("Your answer: ", JLabel.TRAILING);
		answerText = new JTextField();
		answerText.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				userAnswer = answerText.getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		answerText.setColumns(20);
		answerText.setFont(font14);
		answerLbl.setLabelFor(answerText);
		answerLbl.setFont(font12);

		inputAnswerPanel.add(answerLbl);
		inputAnswerPanel.add(answerText);
		if (!SportsGeniusInfo.difficulty.equals(SportsGeniusInfo.EASY))
			inputAnswerPanel.add(createTimerPanel());

		return inputAnswerPanel;
	}

	// create panel containing the main menu and exit button
	private JPanel createActionPanel() {
		if (actionPanel != null)
			return actionPanel;

		actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
		actionPanel.setBorder(
				javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Actions"),
						javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		btnExit = createButton("Exit");
		btnExit.addActionListener(new ActionListener() {

			// exit program if click Exit
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnMainMenu = createButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {

			// if click main menu button
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				
				// stop timer
				if (timer != null) {
					stopTiming();
					timer = null;
				}
				// show the main menu screen
				CardLayout cl = (CardLayout) (SportsGeniusInfo.panelInCards.getLayout());
				cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.mainPanelName);
			}
		});

		actionPanel.add(btnMainMenu);
		actionPanel.add(Box.createRigidArea(new Dimension(90, 0)));
		actionPanel.add(btnExit);

		return actionPanel;
	}

	// method to move onto next question
	private void answerNextQuestion() {
		// add 1 to question index
		qaCount++;
		// remove current question page
		SportsGeniusInfo.panelInCards.remove(1);
		// create and display question page with next question
		SportsGeniusInfo.panelInCards.add(getGamePanel(), SportsGeniusInfo.gamePanelName);
		CardLayout cl = (CardLayout) (SportsGeniusInfo.panelInCards.getLayout());
		cl.show(SportsGeniusInfo.panelInCards, SportsGeniusInfo.gamePanelName);
		
		// start timing if there is a timer
		if (timer != null)
			startTiming();
	}

}
