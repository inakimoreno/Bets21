import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import test.dataAccess.TestDataAccess;
import domain.Pertsona;
import exceptions.UserAlreadyExist;

import java.util.Date;
import domain.Admin;

public class RegisterDAW {
	
	private static boolean setUpDone = false;
	
	static DataAccess sut = new DataAccess(true);
	
	static TestDataAccess testDA = new TestDataAccess();
	
	private Date date = UtilDate.newDate(1990,10,10);
	private Pertsona usr = new Admin("Antxon Urrutia Garcia", "antxon18 12345678",
			"666666666 antxon@gmail.com", date);

	@Before
	public void dbInit() {
		if (setUpDone == true) {
			return;
		}
		testDA.open();
		testDA.addUser(new Admin("Aitor Paredes Zatarain", "Admin aaaaaaaa", "666666666 Admindb.@gmail.com", date));
		testDA.close();
		setUpDone = true;
	}
	
	@Test
	public void test1() {
		try {

		testDA.open();
		testDA.addUser(usr);
		testDA.close();
		
		sut.register("Antxon Urrutia Garcia", "antxon18 12345678 admin",
				"666666666 antxon@gmail.com", date);
		fail();
		} catch (UserAlreadyExist e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			testDA.open();
			testDA.removeUser(usr);
			testDA.close();
		}
	}
	
	@Test
	public void test2() {
		Pertsona newUser = new Admin("Antxon Urrutia Garcia", "antxon18 12345678",
				"666666666 antxon@gmail.com", date); 
		try {
		
		newUser = sut.register("Antxon Urrutia Garcia", "antxon18 12345678 admin",
				"666666666 antxon@gmail.com", date);
		testDA.open();
		Pertsona dbUser = testDA.getUser(newUser);
		testDA.close();
		assertEquals(newUser.getErabiltzaileIzena(),dbUser.getErabiltzaileIzena());
		
		} catch (Exception e) {
			fail();
		} finally {
			testDA.open();
			testDA.removeUser(newUser);
			testDA.close();
		}
	}
	
	@Test
	public void test3() {
		
		Pertsona newUser = new Admin("Antxon Urrutia Garcia", "antxon18 12345678",
				"666666666 antxon@gmail.com", date); 
		
		try {
		
		newUser = sut.register("Antxon Urrutia Garcia", "antxon18 12345678 langilea",
				"666666666 antxon@gmail.com", date);
		testDA.open();
		Pertsona dbUser = testDA.getUser(newUser);
		testDA.close();
		assertEquals(newUser.getErabiltzaileIzena(),dbUser.getErabiltzaileIzena());
		
		} catch (Exception e) {
			fail();
		} finally {
			testDA.open();
			testDA.removeUser(newUser);
			testDA.close();
		}
	}
	
	@Test
	public void test4() {
		
		Pertsona newUser = new Admin("Antxon Urrutia Garcia", "antxon18 12345678",
				"666666666 antxon@gmail.com", date); 
		
		try {
		
		newUser = sut.register("Antxon Urrutia Garcia", "antxon18 12345678 bezeroa",
				"666666666 antxon@gmail.com", date);
		testDA.open();
		Pertsona dbUser = testDA.getUser(newUser);
		testDA.close();
		assertEquals(newUser.getErabiltzaileIzena(),dbUser.getErabiltzaileIzena());
		
		} catch (Exception e) {
			fail();
		} finally {
			testDA.open();
			testDA.removeUser(newUser);
			testDA.close();
		}
	}

}