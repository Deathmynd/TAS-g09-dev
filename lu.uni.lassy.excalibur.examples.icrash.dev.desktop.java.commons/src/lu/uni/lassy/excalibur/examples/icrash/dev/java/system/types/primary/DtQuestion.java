package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
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
	
	//����� ���������� ������� "����� ������: ���������� �������"
	private Hashtable<PtInteger,PtInteger> answersStatistic = new Hashtable<PtInteger, PtInteger>();
	
	/**
	 * Instantiates a new datatype login.
	 *
	 * @param s The primitive type of string to create the datatype
	 */
	public DtQuestion(PtString s) {
		super(s);
		
		for (int i = minAnswerValue; i<=maxAnswerValue; i++)
		{
			answersStatistic.put(new PtInteger(i), new PtInteger(0));
		}
	}
	

	/**
	 * 
	 */
	public PtBoolean is() {
		return new PtBoolean(this.value.getValue().length() <= _maxLength);
	}
	
	/**
	 * Inkrement the counter of answer
	 * @param key The value of answer
	 * @return true, if counter incremented
	 * 			false, if answer doesn't exist
	 */
	public PtBoolean putAnswer(PtInteger key)
	{
		int aKey = key.getValue();
		if (minAnswerValue <= aKey && aKey <= maxAnswerValue)
		{
			answersStatistic.get(key).inc();
			return new PtBoolean(true);
		}
		return new PtBoolean(false);
	}
	
	public PtBoolean putTableWithAnswers(Hashtable<PtInteger,PtInteger> newTable)
	{
		answersStatistic = newTable;
		
		return new PtBoolean(true);
	}
	
	/**
	 * @return The number of the answered humans
	 */
	public PtInteger getSizeOfAnswers()
	{
		PtInteger size = new PtInteger(0);
		for (int i =0; i<maxAnswerValue; i++)
		{
			size.add(answersStatistic.get(new PtInteger(i)));
		}
		return size;
	}
	
	/**
	 * @param key Number of the answer
	 * @return Number of answers
	 */
	public int getAnswer(int key)
	{
		return answersStatistic.get(key).getValue();
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
