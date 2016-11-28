package lu.uni.lassy.excalibur.examples.icrash.dev.java.testcases;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbHumans;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbQuestions;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtQuestion;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

public class TestCase_db_table_questions {

	/** The logger used to log issues or information. */
	static Logger log = Log4JUtils.getInstance().getLogger();
	
	
	/**
	 * The main method, which runs the test.
	 *
	 * @param args the arguments passed to the system, these are ignored
	 * @throws RemoteException Thrown if the server is offline
	 * @throws NotBoundException Thrown if the server is not bound correctly in RMI settings
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {

		log.info("---- test insert-------");
		//**********************************************************
		//set up question's id
		
		int id = 1000;
		
		//**********************************************************
		//set up question
		DtQuestion aDtQuestion = new DtQuestion(new PtString("It is tast question, it shuld be removed"));

		DbQuestions.insertQuestion(id, aDtQuestion);
		

		log.info("---- test select -------");	
		
		DtQuestion aDtQuestion2 = DbQuestions.getQuestion(id);
		log.debug("The retrieved question is " + aDtQuestion2.value.getValue());
		log.debug("The retrieved answer1: " + aDtQuestion2.getAnswer(1));
		log.debug("The retrieved answer2: " + aDtQuestion2.getAnswer(2));
		log.debug("The retrieved answer3: " + aDtQuestion2.getAnswer(3));
		log.debug("The retrieved answer4: " + aDtQuestion2.getAnswer(4));
		log.debug("The retrieved answer5: " + aDtQuestion2.getAnswer(5));
		
		DbQuestions.deleteQuestion(id);
		
	}

}
