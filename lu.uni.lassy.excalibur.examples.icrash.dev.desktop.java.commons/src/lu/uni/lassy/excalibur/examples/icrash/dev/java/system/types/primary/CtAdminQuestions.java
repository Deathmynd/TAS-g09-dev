package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;

public class CtAdminQuestions  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	private PtInteger key = new PtInteger(0);

	/** A hashtable of the questions which sended by administrator */
	private Hashtable<PtInteger,DtQuestion> administratorQuestions = new Hashtable<PtInteger,DtQuestion>();
	
	public PtInteger getNextKey()	{
		int k = key.getValue();
		k++;
		key = new PtInteger(k);
		return new PtInteger(k);
	}
	
	public PtBoolean init(Hashtable<PtInteger,DtQuestion> initAdministratorQuestions){
		administratorQuestions = initAdministratorQuestions;
		return new PtBoolean(true);
	}
	
	public PtInteger addQuestion(DtQuestion question){
		administratorQuestions.put(getNextKey(), question);
		return new PtInteger(key.getValue());
	}
	
	public DtQuestion getQuestion(PtInteger aKey){
		int k = aKey.getValue();
		if (k<0 || administratorQuestions.size() <= k)
			return null;
		return administratorQuestions.get(aKey);
	}
	
	public Hashtable<PtInteger, DtQuestion> getHashtable(){
		return administratorQuestions;
	}
	
	public PtBoolean setHashtable(Hashtable<PtInteger, DtQuestion> newTable){
		administratorQuestions = newTable;
		return new PtBoolean(true);
	}
	
	public int size()
	{
		return administratorQuestions.size();
	}
}