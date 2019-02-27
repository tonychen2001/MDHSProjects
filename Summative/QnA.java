/*--------------------------------------------------------------------------------------*/
/*  QnA.java  -  This class models the questions attributes  						    */
/*                                                           					        */
/*--------------------------------------------------------------------------------------*/
/*  Author: Tony Chen                                                                   */
/*  Date: January 10, 2019                                                              */
/*--------------------------------------------------------------------------------------*/
/*  Input: N/A                                          								*/
/*  Output: N/A  																	    */
/*--------------------------------------------------------------------------------------*/

public class QnA {

	private int ID; //question ID
	private String descriptions; //question itself
	private String answer; //correct answer of the question
	private String imageName; //the name of the image that depicts the question
	private String[] options; //multiple choices for correct answer
	
	/**
	 * @param options the options to set
	 */
	public void setOptions(String[] options) {
		this.options = options;
	}
	/**
	 * @return the options
	 */
	public String[] getOptions() {
		return options;
	}
	
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the descriptions
	 */
	public String getDescriptions() {
		return descriptions;
	}
	/**
	 * @param descriptions the descriptions to set
	 */
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
