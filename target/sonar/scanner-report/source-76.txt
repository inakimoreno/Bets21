import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Event;
import domain.Mugimendua;
import domain.Pronostikoa;
import domain.Question;
import test.dataAccess.TestDataAccess;

public class EmaitzaIpiniDAW {

	//sut:system under test
	static DataAccess sut=new DataAccess(true);
	 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	
	
	private Event ev;
	private Question qu;
	private Pronostikoa pr;
	private Apustua ap;
	private Mugimendua mugi;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void test1() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("05/10/2022");
			testDA.open();
			ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
			qu = ev.getQuestions().get(0);
			pr = testDA.addPronostikoa();
			testDA.close();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		try {		
			double emaitza = sut.emaitzaIpini(qu, pr).get(0);
			int esperotakoEmaitza = 0;
			assertEquals(esperotakoEmaitza, emaitza,0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void test2() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("05/10/2022");
			testDA.open();
			ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
			qu = ev.getQuestions().get(0);
			pr = testDA.addPronostikoaWithApustuak();
			testDA.close();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		try {
			double emaitza = sut.emaitzaIpini(qu, pr).get(0);
			double esperotakoEmaitza = 1;
			assertEquals(esperotakoEmaitza, emaitza,0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("05/10/2022");
			testDA.open();
			ev = testDA.addEventWithQuestion("Proba ebentua", oneDate, "Proba galdera",(float)1.0);
			qu = ev.getQuestions().get(0);
			pr = testDA.apustuaIrabazi();
			testDA.close();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		try {
			ap = pr.getApustuak().get(0);
			double emaitza = sut.emaitzaIpini(qu, pr).get(1);
			double esperotakoEmaitza = 25;
			assertEquals(esperotakoEmaitza, emaitza,0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test4() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("05/10/2022");
			testDA.open();
			ev = testDA.addEventWithQuestion("2. Proba ebentua", oneDate, "2. Proba galdera",(float)1.0);
			qu = ev.getQuestions().get(0);
			pr = testDA.apustuaIrabaziErrepikapenarekin();
			testDA.close();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		try {
			ap = pr.getApustuak().get(0);
			double emaitza = sut.emaitzaIpini(qu, pr).get(1);
			double esperotakoEmaitza = 5;
			assertEquals(esperotakoEmaitza, emaitza,0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
