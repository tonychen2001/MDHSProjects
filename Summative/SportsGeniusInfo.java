/*--------------------------------------------------------------------------------------*/
/*  SportsGeniusInfo.java  -  This class contains all of the information and data about
/* 							  the game             							    */
/*                                                           					        */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: January 10, 2019                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input:                                           */
/*  Output:      */
/*--------------------------------------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JPanel;

public class SportsGeniusInfo {

	//Mainmenu and game page
	public static JPanel panelInCards;
	
	//Give a name to mainmenu and game panel
	public final static String mainPanelName = "MAIN";
	public final static String gamePanelName = "GAME";
	public final static String tutorialPanelName = "TUTORIAL";
	//public final static String basketballPanelName = "BASKETBALL";
	
	//Linkedlist to save questions and answers
	public static Node basketballQnA;
	public static Node soccerQnA;
	public static Node volleyballQnA;
	public static Node badmintonQnA;

	//total number of basketball questions
	public static int numOfBasketballQnA = 0;
	
	//total number of soccer questions
	public static int numOfSoccerQnA = 0;
		
	//total number of volleyball questions
	public static int numOfVolleyballQnA = 0;
		
	//total number of badminton questions
	public static int numOfBadmintonQnA = 0;

	//game difficulty levels
	public static final String EASY = "Easy";
	public static final String INTERMEDIATE = "Intermediate";
	public static final String HARD = "Hard";

	//default difficulty
	public static String difficulty = EASY;

	//for sports in this game
	public static final String SOCCER = "Soccer";
	public static final String BASKETBALL = "Basketball";
	public static final String BADMINTON = "Badminton";
	public static final String VOLLEYBALL = "Volleyball";

	//sport game to be selected for play 
	public static String sport = "";

	//number of questions will be played for each game
	final static int qaNum = 8;
	
	static Random rnd = new Random();

	public static void loadSportQuestion() {

		//load basketball game questions and answers
		loadBasketBallQnA();
		loadSoccerQnA();
		loadVolleyballQnA();
		loadBadmintonQnA();
	
	}

	private static void loadBasketBallQnA() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Summative/BasketBall/BasketBallQ&A.txt"));

			String line = null;

			Node head = null;
			// read question from each line
			while ((line = reader.readLine()) != null) {
				numOfBasketballQnA++;

				StringTokenizer st = new StringTokenizer(line, ":");

				QnA qna = new QnA();
				qna.setID(Integer.parseInt(st.nextToken().trim()));
				qna.setDescriptions(st.nextToken().trim());
				qna.setAnswer(st.nextToken().trim());
				qna.setImageName(st.nextToken().trim());

				Node qnaNode = new Node();
				qnaNode.next = head;
				qnaNode.data = qna;
				head = qnaNode;

			}
			basketballQnA = head;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//load answers
		loadBasketBallChoices();
	}

	private static void loadBasketBallChoices() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src/Summative/BasketBall/BasketBallOptions.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line, ":");

				int id = Integer.parseInt(st.nextToken().trim());

				String[] options = new String[4];
				options[0] = st.nextToken().trim();
				options[1] = st.nextToken().trim();
				options[2] = st.nextToken().trim();
				options[3] = st.nextToken().trim();

				//find according question to the answer
				QnA qna = findQnAById(basketballQnA, id);

				//if answers found, save them into question
				if (qna != null)
					qna.setOptions(options);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}
	
	private static void loadSoccerQnA() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Summative/Soccer/SoccerQ&A.txt"));

			String line = null;

			Node head = null;
			// read question from each line
			while ((line = reader.readLine()) != null) {
				numOfSoccerQnA++;

				StringTokenizer st = new StringTokenizer(line, ":");

				QnA qna = new QnA();
				qna.setID(Integer.parseInt(st.nextToken().trim()));
				qna.setDescriptions(st.nextToken().trim());
				qna.setAnswer(st.nextToken().trim());
				qna.setImageName(st.nextToken().trim());

				Node qnaNode = new Node();
				qnaNode.next = head;
				qnaNode.data = qna;
				head = qnaNode;

			}
			soccerQnA = head;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//load answers
		loadSoccerChoices();
	}

	private static void loadSoccerChoices() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src/Summative/Soccer/SoccerOptions.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line, ":");

				int id = Integer.parseInt(st.nextToken().trim());

				String[] options = new String[4];
				options[0] = st.nextToken().trim();
				options[1] = st.nextToken().trim();
				options[2] = st.nextToken().trim();
				options[3] = st.nextToken().trim();

				//find according question to the answer
				QnA qna = findQnAById(soccerQnA, id);

				//if answers found, save them into question
				if (qna != null)
					qna.setOptions(options);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}
	
	private static void loadVolleyballQnA() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Summative/Volleyball/VolleyballQ&A.txt"));

			String line = null;

			Node head = null;
			// read question from each line
			while ((line = reader.readLine()) != null) {
				numOfVolleyballQnA++;

				StringTokenizer st = new StringTokenizer(line, ":");

				QnA qna = new QnA();
				qna.setID(Integer.parseInt(st.nextToken().trim()));
				qna.setDescriptions(st.nextToken().trim());
				qna.setAnswer(st.nextToken().trim());
				qna.setImageName(st.nextToken().trim());

				Node qnaNode = new Node();
				qnaNode.next = head;
				qnaNode.data = qna;
				head = qnaNode;

			}
			volleyballQnA = head;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//load answers
		loadVolleyballChoices();
	}

	private static void loadVolleyballChoices() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src/Summative/Volleyball/VolleyballOptions.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line, ":");

				int id = Integer.parseInt(st.nextToken().trim());

				String[] options = new String[4];
				options[0] = st.nextToken().trim();
				options[1] = st.nextToken().trim();
				options[2] = st.nextToken().trim();
				options[3] = st.nextToken().trim();

				//find according question to the answer
				QnA qna = findQnAById(volleyballQnA, id);

				//if answers found, save them into question
				if (qna != null)
					qna.setOptions(options);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}
	
	private static void loadBadmintonQnA() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/Summative/Badminton/BadmintonQ&A.txt"));

			String line = null;

			Node head = null;
			// read question from each line
			while ((line = reader.readLine()) != null) {
				numOfBadmintonQnA++;

				StringTokenizer st = new StringTokenizer(line, ":");

				QnA qna = new QnA();
				qna.setID(Integer.parseInt(st.nextToken().trim()));
				qna.setDescriptions(st.nextToken().trim());
				qna.setAnswer(st.nextToken().trim());
				qna.setImageName(st.nextToken().trim());

				Node qnaNode = new Node();
				qnaNode.next = head;
				qnaNode.data = qna;
				head = qnaNode;

			}
			badmintonQnA = head;
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//load answers
		loadBadmintonChoices();
	}

	private static void loadBadmintonChoices() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src/Summative/Badminton/BadmintonOptions.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line, ":");

				int id = Integer.parseInt(st.nextToken().trim());

				String[] options = new String[4];
				options[0] = st.nextToken().trim();
				options[1] = st.nextToken().trim();
				options[2] = st.nextToken().trim();
				options[3] = st.nextToken().trim();

				//find according question to the answer
				QnA qna = findQnAById(badmintonQnA, id);

				//if answers found, save them into question
				if (qna != null)
					qna.setOptions(options);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}

	private static QnA findQnAById(Node sportQnA, int id) {

		if (id == sportQnA.data.getID()) {
			return sportQnA.data;
		} else {
			Node node = sportQnA.next;
			while (node != null) {
				if (id == node.data.getID()) {
					return node.data;

				}
				node = node.next;
			}
		}

		return null;
	}

	//randomly select qaNum(8) questions from selected sport
	public static QnA[] selectQuestions() {
		QnA[] questions = new QnA[qaNum];
		QnA qna = null;

		int i = 0;
		while (i < qaNum) {
			if (sport.equals(BASKETBALL)) {
				int id = rnd.nextInt(numOfBasketballQnA-1) + 1;
				qna = findQnAById(basketballQnA, id);
			}
			else if (sport.equals(SOCCER)) {
				int id = rnd.nextInt(numOfSoccerQnA-1) + 1;
				qna = findQnAById(soccerQnA, id);
			}
			else if (sport.equals(VOLLEYBALL)) {
				int id = rnd.nextInt(numOfVolleyballQnA-1) + 1;
				qna = findQnAById(volleyballQnA, id);
			}
			else if (sport.equals(BADMINTON)) {
				int id = rnd.nextInt(numOfBadmintonQnA-1) + 1;
				qna = findQnAById(badmintonQnA, id);
			}

			//if difficulty is EASY, answers must be multiple choices
			if (difficulty.equals(EASY) && qna.getOptions() == null) {
				continue;
			}

			//check if question is already selected
			if (qnaExisted(qna.getID(), questions)) {
				continue;
			}

			questions[i] = qna;
			i++;
		}

		return questions;
	}

	private static boolean qnaExisted(int id, QnA[] qnas) {
		for (int i = 0; i < qnas.length; i++) {
			if (qnas[i] != null && qnas[i].getID() == id) {
				return true;
			}
		}

		return false;
	}
}
