import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccessEmaitzaIpini;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;
import test.dataAccess.TestDataAccess;

public class EmaitzaIpini2DAB {

	
	private Pronostikoa pr;
	private Event ev;
	private Question qu;
	
	//sut:system under test
	static DataAccessEmaitzaIpini sut=new DataAccessEmaitzaIpini(true);
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	
	@Test
	public void test1() {
		try {
			ev = new Event(1,"proba ebentua", new Date());
			qu = new Question(1,"proba galdera",1,ev);
			pr = new Pronostikoa("Pronostikoa1",2,qu);
			testDA.open();
			testDA.addEventToDB(ev);
			testDA.addQuestionToDB(qu);
			testDA.addPronostikoa(pr);
			testDA.close();
			sut.emaitzaIpini(qu, pr);
			assertTrue(true);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test2() {
		try {
			sut.emaitzaIpini(null, null);
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	/*
	@Test
	public void test3() {
		try {
			sut.emaitzaIpini(qu, pr);
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	*/
	
}

