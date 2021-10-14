import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.ArretaElkarrizketa;
import domain.ArretaMezua;
import domain.Bezeroa;
import domain.BezeroartekoMezua;
import test.dataAccess.TestDataAccess;

public class RemoveMezuaDAB {
	
	static DataAccess sut = new DataAccess(true);
		 
	static TestDataAccess testDA = new TestDataAccess();
	
	@Test
	public void test1() {
		try {
			Bezeroa bez1 = new Bezeroa("dsvddsv", "svsdxdfv", "fddsxv", "gdsdgsdg", "fsddfsd", "fsddf", "fdsdfs", new Date());
			Bezeroa bez2 = new Bezeroa("dsvdddsv", "svsddxdfv", "fdddsxv", "gdsddgsdg", "fsdddfsd", "fsdddf", "fdsddfs", new Date());
			BezeroartekoMezua bezm = new BezeroartekoMezua("Proba mezua", "vsdvsdv", "vsdvsd", 3.5, 4.3, 6.7, bez1, bez2);
			Integer id = bezm.getIdentifikadorea();

			testDA.open();
			testDA.addBezeroartekoMezua(bezm);
			System.out.println(testDA.isBezeroartekoMezuaInDB(id));
			sut.removeMezua(bezm);
			System.out.println(testDA.isBezeroartekoMezuaInDB(id));
			testDA.close();
			
			assertTrue(true);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test2() {
		try {
			Bezeroa bez = new Bezeroa("dsvdsv", "svsdxfv", "fdsxv", "gsdgsdg", "fsdfsd", "fsdf", "fdsfs", new Date());
			ArretaElkarrizketa ael = new ArretaElkarrizketa(bez, "Gaia");
			ArretaMezua am = new ArretaMezua("Proba", ael);
			Integer id = am.getIdentifikadorea();
			
			testDA.open();
			testDA.addArretaMezua(am);
			System.out.println(testDA.isArretaMezuaInDB(id));
			sut.removeMezua(am);
			System.out.println(testDA.isArretaMezuaInDB(id));
			testDA.close();
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test3() {
		try {
			sut.removeMezua(null);
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
}
