package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class DtQuestion, which holds a datatype of the Question to human.
 */
public class DtQuestion extends DtString implements JIntIs{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;	

	/** The maximum length a question can be. */
	private int _maxLength = 255;
	
	/**amount of possible answers */
	private int maxAnswerValue = 5;

	private int minAnswerValue = 1;
	
	
	/** Statistics of answers */
	
	private Hashtable<Integer,Integer> answersStatistic = new Hashtable<Integer, Integer>();
	
	/**
	 * Instantiates a new datatype login.
	 *
	 * @param s The primitive type of string to create the datatype
	 */
	public DtQuestion(PtString s) {
		super(s);
		
		for (int i = minAnswerValue; i<=maxAnswerValue; i++)
		{
			answersStatistic.put(i, 0);
		}
	}
	

	/**
	 * 
	 */
	public PtBoolean is() {
		return new PtBoolean(this.value.getValue().length() <= _maxLength);
	}
	
	/**
	 * Inskrement the counter of answer
	 * @param key The value of answer
	 * @return true, if counter incremented
	 * 			false, if answer doesn't exist
	 */
	public PtBoolean putAnswer(Integer key)
	{
		if (minAnswerValue <= key && key <= maxAnswerValue)
		{
			answersStatistic.put(key, answersStatistic.get(key)+1);
			return new PtBoolean(true);
		}
		return new PtBoolean(false);
	}
	
	/**
	 * @return The number of the answered humans
	 */
	public int getSizeOfAnswers()
	{
		int size = 0;
		for (int i =0; i<maxAnswerValue; i++)
		{
			size += answersStatistic.get(i);
		}
		return size;
	}
	
	/**
	 * @param key Number of the answer
	 * @return Number of answers
	 */
	public int getAnswer(int key)
	{
		return answersStatistic.get(key);
	}
	
	public int getMinAnswerValue()
	{
		return minAnswerValue;
	}
	
	public int getMaxAnswerValue()
	{
		return maxAnswerValue;
	}
	
}
