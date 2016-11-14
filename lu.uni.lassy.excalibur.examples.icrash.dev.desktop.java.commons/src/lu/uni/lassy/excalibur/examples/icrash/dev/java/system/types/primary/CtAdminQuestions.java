package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class CtAdminQuestions  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	private Integer key = 0;

	/** A hashtable of the questions which sended by administrator */
	private Hashtable<Integer,DtQuestion> administratorQuestions = new Hashtable<Integer,DtQuestion>();
	
	public Integer getNextKey()	{
		return ++key;
	}
	
	public PtBoolean init(Hashtable<Integer,DtQuestion> initAdministratorQuestions){
		administratorQuestions = initAdministratorQuestions;
		return new PtBoolean(true);
	}
	
	public Integer addQuestion(DtQuestion question){
		administratorQuestions.put(getNextKey(), question);
		return key;
	}
	
	public DtQuestion getQuestion(Integer aKey){
		if (aKey<=0 && administratorQuestions.size() < aKey)
			return null;
		return administratorQuestions.get(aKey);
	}
	
	public Hashtable<Integer, DtQuestion> getHashtable(){
		return administratorQuestions;
	}
	
	public int numOfQuestions()
	{
		return administratorQuestions.size();
	}
}