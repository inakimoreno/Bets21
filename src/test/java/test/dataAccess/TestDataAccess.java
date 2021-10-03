package test.dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Apustua;
import domain.Bezeroa;
import domain.Errepikapena;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {

		System.out.println("Creating TestDataAccess instance");

		open();

	}


	public void open(){

		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
			return false;
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev=null;
		db.getTransaction().begin();
		try {
			ev=new Event(desc,d);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return ev;
	}
	public boolean existQuestion(Event ev,Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else 
			return false;

	}

	public Pronostikoa addPronostikoa() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			db.persist(pron);
			db.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public void addPronostikoa(Pronostikoa pr) {
		System.out.println(">> DataAccessTest: addPronostikoa");	
		db.getTransaction().begin();
		try {
			db.persist(pr);
			db.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pronostikoa addPronostikoaWithApustuak() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			Apustua ap = new Apustua();
			ap.setAsmatutakoKop(0);
			pron.addApustua(ap);
			db.persist(pron);
			db.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public Pronostikoa apustuaIrabazi() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		Apustua ap = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			Bezeroa bez = new Bezeroa();
			ap = new Apustua();
			db.persist(bez);
			ap.setBezeroa(bez);
			ap.setKopurua(5);
			ap.setKuotaTotala(5);
			ap.setPronostikoKop(1);
			ap.setAsmatutakoKop(0);
			System.out.println(ap.getAsmatutakoKop());
			db.persist(ap);
			pron.addApustua(ap);
			db.persist(pron);
			db.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public Pronostikoa apustuaIrabaziErrepikapenarekin() {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		Apustua ap = null;
		db.getTransaction().begin();
		try {
			pron = new Pronostikoa();
			Bezeroa bez = new Bezeroa("a","","","","","","",new Date());
			Bezeroa noriBez = new Bezeroa("b","","","","","","",new Date());
			Errepikapena err = new Errepikapena(bez, noriBez, 20,20,2);
			bez.addErrepikatua(err);
			ap = new Apustua();
			db.persist(bez);
			ap.setBezeroa(bez);
			ap.setKopurua(10);
			ap.setKuotaTotala(1.5);
			ap.setPronostikoKop(1);
			ap.setAsmatutakoKop(0);
			ap.setErrepikatua(bez);
			System.out.println(ap.getAsmatutakoKop());
			db.persist(ap);
			pron.addApustua(ap);
			db.persist(pron);
			db.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pron;
	}
	
	public void addEventToDB(Event ev) {
		db.getTransaction().begin();
		db.persist(ev);
		db.getTransaction().commit();
	}
	
	public void addQuestionToDB(Question qu) {
		db.getTransaction().begin();
		db.persist(qu);
		db.getTransaction().commit();
	}
	
	public Pertsona addUser(Pertsona usr) {
		System.out.println(">> DataAccessTest: addUser");
		db.getTransaction().begin();
		db.persist(usr);
		db.getTransaction().commit();
		return usr;
	}
	
	public boolean removeUser(Pertsona user) {
		System.out.println(">> DataAccessTest: removeEvent");
		Pertsona usr = db.find(Pertsona.class, user.erabiltzaileIzena);
		if (usr != null) {
			db.getTransaction().begin();
			db.remove(usr);
			db.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	public Pertsona getUser(Pertsona user) {
		System.out.println(">> DataAccessTest: removeEvent");
		Pertsona usr = db.find(Pertsona.class, user.erabiltzaileIzena);
		if (usr != null) {
			return usr;
		}
		return null;
	}
}
