package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.ArretaElkarrizketa;
import domain.ArretaMezua;
import domain.Bezeroa;
import domain.BezeroaContainer;
import domain.BezeroartekoMezua;
import domain.Errepikapena;
import domain.ErrepikatuakContainer;
import domain.Event;
import domain.Langilea;
import domain.Mezua;
import domain.Mugimendua;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.PronostikoaContainer;
import domain.Question;
import exceptions.EventAlreadyExist;
import exceptions.EventFinished;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessRemoveMezua {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccessRemoveMezua(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());
		open(initializeMode);

	}

	public DataAccessRemoveMezua() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1,"Atl�tico-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alav�s-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Espa�ol-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Legan�s", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alav�s-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Espa�ol-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "M�laga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Legan�s", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}

			Admin a1 = new Admin("Aitor Paredes Zatarain", "Admin aaaaaaaa", "666666666 Admindb.@gmail.com", UtilDate.newDate(2001,2,12));
			
			Langilea l1 = new Langilea("Zdravko Todorov Petkov", "zdra aaaaaaaa", "987654321 zdra@gmail.com", UtilDate.newDate(2001,7,23));
			Langilea l2 = new Langilea("I�aki Moreno Artabe", "inakimoreno aaaaaaaa", "384625395 inakimoreno@gmail.com", UtilDate.newDate(2001,7,23));
			
			Bezeroa b1 = new Bezeroa("pepe popo pupu", "pepopu12301 aaaaaaaa", "123456789 pepopu12301@gmail.com",UtilDate.newDate(2001,8,9));
			Bezeroa b2 = new Bezeroa("Koldo Beitialarrangoitia Munez", "kobemu aaaaaaaa", "123456789 kobemu@gmail.com",UtilDate.newDate(2001,8,9));
			b2.setPublikoa(false);
			Bezeroa b3 = new Bezeroa("Jose Miguel Perez", "JoseMi aaaaaaaa", "123456789 JoseMiguel@gmail.com",UtilDate.newDate(2001,8,9));
			Bezeroa b4 = new Bezeroa("Antxon Urrutia Garcia", "antxon aaaaaaaa", "123456789 antxon@gmail.com",UtilDate.newDate(2001,8,9));
			Bezeroa b5 = new Bezeroa("Saioa Goikoetxea Ugarte", "Saioo99 b", "123456789 Saioo99@gmail.com",UtilDate.newDate(2001,8,9));
			
			
			Event event1 = new Event(21,"Eibar-Celta", UtilDate.newDate(2021, 2, 17));
			Event event2 = new Event(22,"Granada-Athletic", UtilDate.newDate(2021, 2, 17));
			
			Question ques1 = event1.addQuestion("Zeinek irabaziko du partidua?", 1);
			Question ques2 = event1.addQuestion("Zeinek sartuko du lehenengo gola?", 1);
			Question ques3 = event2.addQuestion("Zeinek irabaziko du partidua?", 1);
			Question ques4 = event2.addQuestion("Golik sartuko al da lehen zatian?", 1);
			
			Pronostikoa pronos1, pronos2, pronos3, pronos4, pronos5, pronos6, pronos7, pronos8, pronos9, pronos10, pronos11, pronos12, pronos13, pronos14, pronos15, pronos16, pronos17;
			pronos1 = ques1.addPronostic("1", 1.2);
			pronos2 = ques1.addPronostic("X", 1.5);//
			pronos3 = ques1.addPronostic("2", 1.8);
			pronos4 = ques2.addPronostic("1", 1.2);//
			pronos5 = ques2.addPronostic("2", 1.6);
			pronos6 = ques2.addPronostic("Golik ez", 1.8);
			pronos7 = ques3.addPronostic("1", 2.2);//
			pronos8 = ques3.addPronostic("X", 1.4);
			pronos9 = ques3.addPronostic("2", 1.2);
			pronos10 = ques4.addPronostic("Bai", 1.3);
			pronos11 = ques4.addPronostic("Ez", 2.5);//
		
			q1.addPronostic("1", 1.2);
			q1.addPronostic("X", 1.5);//
			q1.addPronostic("2", 1.8);
			q2.addPronostic("1", 1.2);//
			q2.addPronostic("2", 1.6);
			 q2.addPronostic("Golik ez", 1.8);
			 
			pronos12 = q3.addPronostic("1", 1.2);
			pronos13 = q3.addPronostic("X", 1.5);//
			pronos14 = q3.addPronostic("2", 1.8);
			pronos15 = q4.addPronostic("<2",1.2);//
			pronos16 = q4.addPronostic("3", 1.6);
			pronos17 = q4.addPronostic(">3",1.8);
			
			
			Errepikapena errepikapenBerria = b2.addErrepikatzailea(b5, 2, 10, 0.2);
			b5.addErrepikatua(errepikapenBerria);
			
			ArrayList<Pronostikoa> p = new ArrayList<Pronostikoa>();
			p.add(pronos2);
			p.add(pronos4);
			Apustua apustua1 = b2.addApustua(p, 2, null);
			Apustua apustu2=b5.addApustua(p, 4, b2);
			
			pronos2.addApustua(apustua1);
			pronos2.addApustua(apustu2);
			pronos4.addApustua(apustu2);
			pronos4.addApustua(apustua1);
			
			db.persist(apustua1);
			db.persist(apustu2);
			
			Mugimendua m1,m2,m3,m4;
			m1 = b2.addMugimendua("Bankuko diru-sarrera", 52, "bankua", UtilDate.newDate(2021, 2, 15));
			m2 = b2.addMugimendua("Apustua egin", -2, "jokatu", UtilDate.newDate(2021, 2, 16));
			m3 = b2.addMugimendua("Bankuko diru-sarrera", 30, "bankua", UtilDate.newDate(2021, 2, 15));
			m4 = b5.addMugimendua("Apustu errepikatua egin ("+b2+")", -4, "jokatu", UtilDate.newDate(2021, 2, 16));
			
			db.persist(event1);
			db.persist(event2);
			
			db.persist(ques1);
			db.persist(ques2);
			db.persist(ques3);
			db.persist(ques4);
			
			db.persist(pronos1);
			db.persist(pronos2);
			db.persist(pronos3);
			db.persist(pronos4);
			db.persist(pronos5);
			db.persist(pronos6);
			db.persist(pronos7);
			db.persist(pronos8);
			db.persist(pronos9);
			db.persist(pronos10);
			db.persist(pronos11);
			
			db.persist(m1);
			db.persist(m2);
			db.persist(m3);
			db.persist(m4);			
			db.persist(a1);
			db.persist(l2);
			db.persist(l1);
			db.persist(b1);
			db.persist(b2);
			db.persist(b3);
			db.persist(b4);
			db.persist(b5);


			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			
			db.persist(pronos12);
			db.persist(pronos13);
			db.persist(pronos14);
			db.persist(pronos15);
			db.persist(pronos16);
			db.persist(pronos17);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public void removeMezua(Mezua mezua) {
		if(mezua instanceof BezeroartekoMezua) {
			BezeroartekoMezua m = db.find(BezeroartekoMezua.class, mezua.getIdentifikadorea());
			Bezeroa nork = m.getIgorlea();
			Bezeroa nori = m.getHartzailea();
			db.getTransaction().begin();
			nork.ezabatuBidalitakoBezeroMezua(m);
			nori.ezabatuJasotakoBezeroMezua(m);
			db.remove(m);
			db.getTransaction().commit();
		}else {
			ArretaMezua m = db.find(ArretaMezua.class, mezua.getIdentifikadorea());
			ArretaElkarrizketa elkarrizketa = m.getElkarrizketa();
			db.getTransaction().begin();
			if(elkarrizketa.isAmaituta()) {
				elkarrizketa.removeMezua(m);
				db.remove(m);
				if(elkarrizketa.mezurikEz()) {
					db.remove(elkarrizketa);
				}
			}else {
				m.setIkusgaiBezeroarentzat(false);
			}
			db.getTransaction().commit();
		}
    }
}
