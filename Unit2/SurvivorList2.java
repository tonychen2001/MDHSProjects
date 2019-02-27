/*--------------------------------------------------------------------------------------*/
/*  SurvivorList.java  -  This program provides the following functions:                */
/*  ~ load() allow the a user to load an undetermined number of names of the show's     */
/*    contestants into a linked list                                                    */
/*  ~ circularList() turn the list into a circular linked list                          */
/*  ~ backup() create a backup of the circular linked list                              */
/*  ~ eliminate() allow the user to eliminate contestants from the list                 */
/*  ~ display() display the original list of contestants recursively                    */
/*  ~ display a congratulatory message to the last standing contestant                  */
/*  Course Code: ICS4U1                                                                 */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: October 29, 2018                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: The names of the contestants                                                 */
/*  Output: The names of the contestants and a congratulatory message to the winner     */
/*--------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;

public class SurvivorList2
{

    // method to load contestant names into a list
    public static Node load () throws IOException
    {

	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	// declare a contestant list
	Node head = null;
	// declare a variable to store a contestant name
	String contestant = null;

	// loop to add the names of contestants into the list
	// until user enters "fin"
	do
	{
	    // get user input for contestant names
	    System.out.print ("Please enter contestant name: (\"fin\" to stop) ");
	    contestant = stdin.readLine ();

	    // if the user does not enter "fin", add the contestant name
	    // into the list
	    if (!contestant.equals ("fin"))
	    {
		// create a new node to store the name of a contestant
		Node node = new Node ();
		node.data = contestant;
		// make this new node the head of the existing list
		node.next = head;
		head = node;
	    }
	}
	while (!contestant.equals ("fin"));

	return head;
    }


    // method to make the linked list circular
    public static void circularList (Node head)
    {
	Node node = head;
	// loop to link the last node to the head of the list
	while (node != null)
	{
	    // if the node is last in the list
	    if (node.next == null)
	    {
		// link the last node to the head to complete
		// the circular linked list
		node.next = head;
		break;
	    }
	    // check the next node if this is not the last node
	    // in the list
	    else
	    {
		node = node.next;
	    }
	}
    }


    // method to create a backup list of the contestant names
    public static Node backup (Node head)
    {
	Node node = head;

	// create a backup list
	Node backupNode = new Node ();
	backupNode.data = node.data;

	// declare the head backup list
	Node backupHead = backupNode;

	// loop to create circular backup list
	while (true)
	{
	    // if you have returned to the head of the list
	    if (node.next.data.equals (head.data))
	    {
		// link the last node to the head of the backup list
		// and then end the method
		backupNode.next = backupHead;
		break;
	    }
	    else
	    {
		// backup the next node in the list
		node = node.next;
		// create an empty node to store the data in
		Node dupNode = new Node ();
		dupNode.data = node.data;
		// connect the backup list to the new node
		backupNode.next = dupNode;
		backupNode = dupNode;
	    }
	}
	return backupHead;
    }


    // method to take user input for eliminating contestants from the list
    public static Node eliminateContestant (Node head) throws IOException
    {
	BufferedReader stdin = new BufferedReader (new InputStreamReader (System.in));
	// declare a variable to store a contestant's name to be eliminated
	String contestant = null;

	while (true)
	{
	    // get user input for an eliminated contestant name
	    System.out.print ("\nEnter the name of a contestant that has been eliminated: ");
	    contestant = stdin.readLine ();

	    // return a new list without the eliminated contestant name
	    // by calling the "eliminate" method
	    Node newHead = eliminate (head, contestant);

	    head = newHead;

	    // if there's only one contestant remaining end the method
	    if (newHead.data.equals (newHead.next.data))
	    {
		break;
	    }
	}
	return head;
    }


    // method to eliminate a contestant from the list
    // loser: the contestant to be eliminated
    public static Node eliminate (Node head, String loser)
    {
	Node node = head;
	while (true)
	{
	    // if the contestant is located at the head of the list
	    if (loser.equals (head.data))
	    {
		// find the node before the head
		while (node.next != head)
		{
		    node = node.next;
		}
		// eliminate the head from the list
		node.next = head.next;
		// assign a new head of the list
		head = head.next;
		return head;
	    }
	    // if the contestant is not located at the head of the list
	    else
	    {
		while (true)
		{
		    // if the contestant to be eliminated is at the next node
		    if (node.next.data.equals (loser))
		    {
			// remove the node with the eliminated contestant
			node.next = node.next.next;
			return head;
		    }
		    // if the contestant is not found in the list
		    // display an appropriate message
		    else if (node.next == head)
		    {
			System.out.println ("The name you have entered is not in the list");
			System.out.println ();
			return head;
		    }
		    // if none of the cases above, move on to the next node
		    else
		    {
			node = node.next;
		    }
		}
	    }
	}
    }


    // recursive method to display a circular linked list by
    // displaying the head of the list then
    // removing the head from the list and making the next node the new head
    public static void display (Node head)
    {
	// display the head of the list
	System.out.println (head.data);
	// base case: only one node left in the list
	if (head.next == head)
	{
	    return;
	}
	else
	{
	    // call the "eliminate" method to eliminate the head and
	    // make the next node the new head of the list
	    head = eliminate (head, head.data);
	    // recursively call this method to repeat the process
	    display (head);
	}
    }


    public static void main (String str[]) throws IOException
    {
	// get contestants list from user input
	Node head = load ();

	// make the linked list circular
	circularList (head);

	// backup the circluar linked list
	Node backupCircular = backup (head);

	// eliminate the losers, and the last one left is the winner
	head = eliminateContestant (head);

	// print the original list of contestants using the backup circular list
	System.out.println ("\nList of Original Contestants");
	display (backupCircular);

	// display a congratulatory message to the winner
	System.out.print ("\nCongratulations to ");
	display (head);

    }
}
