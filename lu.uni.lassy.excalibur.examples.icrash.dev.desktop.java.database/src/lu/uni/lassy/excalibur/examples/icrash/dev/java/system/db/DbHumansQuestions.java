package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DbHumansQuestions extends DbAbstract{

	static public void insertAssociations(Hashtable<CtHuman, Integer> assTable)
	{
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Insert
			int val = 1;
			
			try{

				Statement st = conn.createStatement();
				
				for(Map.Entry<CtHuman, Integer> entry : assTable.entrySet())
				{
					String humanPhone = entry.getKey().id.value.getValue();
					
					Integer questionID = entry.getValue();
					
					log.debug("[DATABASE]-Insert human to question association");
					val = st.executeUpdate("INSERT INTO "+ dbName+ ".humansquestions" +
							"(humanPhone,questionID)" + 
							"VALUES('"+humanPhone+"',"+questionID+")");
				}

				log.debug(val + " table affected");

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
	
	static public Hashtable<CtHuman, Integer> getAssociation(){

		Hashtable<CtHuman, Integer> assCtHumanDtQuestion = new Hashtable<CtHuman, Integer>();

		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Select
			//SELECT * FROM icrashfx.humans, icrashfx.humansquestions 
			//where icrashfx.humansquestions.humanPhone = icrashfx.humans.phone;

			try{
				String sql = "SELECT * FROM "+ dbName + ".humans, "
						+ dbName + ".humansquestions where " + dbName + ".humansquestions.humanPhone = " 
						+ dbName + ".humans.phone";


				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);

				CtHuman aCtHuman = null;
				
				while(res.next()) {				

					aCtHuman = new CtHuman();
					
					//human's id
					DtPhoneNumber aId = new DtPhoneNumber(new PtString(res.getString("phone")));
					//human's kind  -> [witness,victim,anonym]
					String theKind = res.getString("kind");
					EtHumanKind aKind = null;
					if(theKind.equals(EtHumanKind.witness.name()))
						aKind = EtHumanKind.witness;
					if(theKind.equals(EtHumanKind.victim.name()))
						aKind = EtHumanKind.victim;
					if(theKind.equals(EtHumanKind.anonym.name()))
						aKind = EtHumanKind.anonym;

					aCtHuman.init(aId,aKind);
					
					Integer questionId =  res.getInt("questionId");
					
					assCtHumanDtQuestion.put(aCtHuman, questionId);
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

		return assCtHumanDtQuestion;

	}
	
	static public void deleteAssociations(int id){
		
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
}
