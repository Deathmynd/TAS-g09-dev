package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtQuestion;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DbQuestions extends DbAbstract{
	
	static public void insertQuestion(int idQuestion, DtQuestion aDtQuestion)
	{
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Insert
			
			try{
				Statement st = conn.createStatement();
				
				String question = aDtQuestion.value.getValue();
				int answer1 = aDtQuestion.getAnswer(1);
				int answer2 = aDtQuestion.getAnswer(2);
				int answer3 = aDtQuestion.getAnswer(3);
				int answer4 = aDtQuestion.getAnswer(4);
				int answer5 = aDtQuestion.getAnswer(5);
								
				log.debug("[DATABASE]-Insert question");
				int val = st.executeUpdate("INSERT INTO "+ dbName+ ".questions" +
											"(id,question,answer1,answer2,answer3,answer4,answer5)" + 
											"VALUES("+idQuestion+",'"+question+"',"+answer1+
											","+answer2+","+answer3+","+answer4+","+answer5+")");
				
				log.debug(val + " row affected");
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}

	
			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			logException(e);
		}	
	}
	
	static public DtQuestion getQuestion(int id){
		
		DtQuestion aDtQuestion = new DtQuestion(null);
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Select
			
			try{
				String sql = "SELECT * FROM "+ dbName + ".questions WHERE id = " + id;

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);
				
				if(res.next()) {				
					
					id = res.getInt("id");
					
					PtString question = new PtString(res.getString("question"));
										
					aDtQuestion = new DtQuestion(question);
					
					Hashtable<Integer, Integer> answersTable = new Hashtable<Integer, Integer>();
					answersTable.put(1, res.getInt("answer1"));
					answersTable.put(2, res.getInt("answer2"));
					answersTable.put(3, res.getInt("answer3"));
					answersTable.put(4, res.getInt("answer4"));
					answersTable.put(5, res.getInt("answer5"));
					
				}
								
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}
			conn.close();
			log.debug("Disconnected from database");
			
			
		} catch (Exception e) {
			logException(e);
		}
		
		return aDtQuestion;

	}
	
	static public void deleteQuestion(int id){
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Delete
			
			try{
				String sql = "DELETE FROM "+ dbName+ ".questions WHERE id = ?";

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, (new Integer(id)).toString());
				int rows = statement.executeUpdate();
				log.debug(rows+" row deleted");				
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}


			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			logException(e);
		}
	}

	static public Hashtable<Integer, DtQuestion> getSystemQuestions(){

		Hashtable<Integer, DtQuestion> cmpSystemDtQuestions = new Hashtable<Integer, DtQuestion>();

		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");



			/********************/
			//Select

			try{
				String sql = "SELECT * FROM "+ dbName + ".questions ";


				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);

				DtQuestion aDtQuestion = new DtQuestion(null);

				while(res.next()) {				

					int id = res.getInt("id");
					
					PtString question = new PtString(res.getString("question"));
										
					aDtQuestion = new DtQuestion(question);
					
					Hashtable<Integer, Integer> answersTable = new Hashtable<Integer, Integer>();
					answersTable.put(1, res.getInt("answer1"));
					answersTable.put(2, res.getInt("answer2"));
					answersTable.put(3, res.getInt("answer3"));
					answersTable.put(4, res.getInt("answer4"));
					answersTable.put(5, res.getInt("answer5"));
					
					cmpSystemDtQuestions.put(id, aDtQuestion);
				}


			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}
			conn.close();
			log.debug("Disconnected from database");


		} catch (Exception e) {
			logException(e);
		}

		return cmpSystemDtQuestions;

	}

}