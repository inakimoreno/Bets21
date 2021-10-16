import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.ArretaElkarrizketa;
import domain.ArretaMezua;
import domain.Bezeroa;
import domain.BezeroartekoMezua;
import domain.Mezua;
import test.dataAccess.TestDataAccess;

public class RemoveMezuaDAW {

	static DataAccess sut = new DataAccess(true);

	static TestDataAccess testDA = new TestDataAccess();

	private BezeroartekoMezua bm;
	private ArretaElkarrizketa ael;

	@Before
	public void init() {

	}

	@Test
	public void test1() {

			testDA.open();
			Bezeroa bez1 = new Bezeroa("dsvddsv svsdxdfv fddsxv", "gdsdgsdg fsddfsd", "fsddf fdsdfs", new Date());
			Bezeroa bez2 = new Bezeroa("dsvdddsv svsddxdfv fdddsxv", "gdsddgsdg fsdddfsd", "fsdddf fdsddfs", new Date());
			BezeroartekoMezua bezm = new BezeroartekoMezua("fsdgds", "vsdvsdv", "vsdvsd", 3.5, 4.3, 6.7, bez1, bez2);
			testDA.addBezeroartekoMezua(bezm);
			Integer id = bezm.getIdentifikadorea();

		try {
			System.out.println(testDA.isBezeroartekoMezuaInDB(id));
			sut.removeMezua(bezm);
			System.out.println(testDA.isBezeroartekoMezuaInDB(id));
			
			assertEquals(false, testDA.isBezeroartekoMezuaInDB(id));
			
			testDA.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {

			testDA.open();
			Bezeroa bez = new Bezeroa("dsvdsv svsdxfv fdsxv", "gsdgsdg fsdfsd", "fsdf fdsfs", new Date());
			ArretaElkarrizketa ael = new ArretaElkarrizketa(bez, "Gaia");
			ael.setId(1);
			ael.setAmaituta(false);
			ArretaMezua am = new ArretaMezua("Proba", ael);
			am.setId(2);
			am.setIkusgaiBezeroarentzat(true);
			Integer id = am.getIdentifikadorea();
			testDA.addArretaMezua(am);
			
		try {
			ArretaMezua aaa = testDA.getArretaMezua(id);
			System.out.println(testDA.getIkusgai(aaa)); 
			sut.removeMezua(aaa);
			aaa = testDA.getArretaMezua(id);
			System.out.println(testDA.getIkusgai(aaa)); 

			assertEquals(false, testDA.getIkusgai(aaa));
			
			testDA.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {

			testDA.open();
			Bezeroa bez = new Bezeroa("dsvddsv svsddxfv fdsxdv", "gsdgdsdg fsdfdsd", "fsddf fddsfs", new Date());
			ArretaElkarrizketa ael = new ArretaElkarrizketa(bez, "Gaia");
			ael.setId(2);
			ael.setAmaituta(true);
			ArretaMezua am = new ArretaMezua("Proba", ael);
			am.setId(3);
			Integer amId = am.getIdentifikadorea();
			Integer elId = am.getElkarrizketa().getIdentifikadorea();
			testDA.addArretaMezua(am);
			
		try {
			ArretaMezua aaa = testDA.getArretaMezua(amId);
			System.out.println(testDA.isArretaMezuaInDB(amId)); 
			System.out.println(testDA.isElkarrizketaInDB(elId));
			sut.removeMezua(aaa);
			aaa = testDA.getArretaMezua(amId);
			System.out.println(testDA.isArretaMezuaInDB(amId)); 
			System.out.println(testDA.isElkarrizketaInDB(elId));

			assertEquals(false, testDA.isElkarrizketaInDB(elId));
			
			testDA.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {

			testDA.open();
			Bezeroa bez1 = new Bezeroa("dsvdddsv svdsddxfv fdsxddv", "gsdgddsdg fsddfdsd", "fsdddf fdddsfs", new Date());
			Bezeroa bez2 = new Bezeroa("dsvddddsv svddsddxfv fdsxdddv", "gsdgdddsdg fsddfddsd", "fsddddf fddddsfs", new Date());
			ArretaElkarrizketa ael1 = new ArretaElkarrizketa(bez1, "Gaia1");
			ael1.setId(3);
			ArretaElkarrizketa ael2 = new ArretaElkarrizketa(bez2, "Gaia2");
			ael2.setId(4);
			ael1.setAmaituta(true);
			ael2.setAmaituta(true);
			ArretaMezua am1 = new ArretaMezua("Proba1", ael1);
			ArretaMezua am2 = new ArretaMezua("Proba2", ael2);
			am1.setId(4);
			am2.setId(5);
			Integer amId = am1.getIdentifikadorea();
			Integer elId = am1.getElkarrizketa().getIdentifikadorea();
			testDA.addArretaMezua(am1);
			testDA.addArretaMezua(am2);
			
		try {
			ArretaMezua aaa = testDA.getArretaMezua(amId);
			System.out.println(testDA.isArretaMezuaInDB(amId)); 
			System.out.println(testDA.isElkarrizketaInDB(elId));
			sut.removeMezua(aaa);
			aaa = testDA.getArretaMezua(amId);
			System.out.println(testDA.isArretaMezuaInDB(amId)); 
			System.out.println(testDA.isElkarrizketaInDB(elId));

			assertEquals(true, testDA.isElkarrizketaInDB(elId));
			
			testDA.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
