import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Pertsona;
import exceptions.UserAlreadyExist;

@RunWith(MockitoJUnitRunner.class)
public class RegisterMockInt {

	DataAccess mockDA = Mockito.mock(DataAccess.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(mockDA);
	
	private Date date = UtilDate.newDate(1990,10,10);
	
	@Test
	public void test1() {
		
		Pertsona user = new Admin("Antxon", "Urrutia", "Garcia", "antxon18", "12345678",
				"666666666","antxon@gmail.com", date);
		
		try {
			
			Mockito.when(mockDA.register("Antxon", "Urrutia", "Garcia", "antxon18", "12345678",
					"666666666","antxon@gmail.com", date, "admin")).thenReturn(user);
			
			Pertsona p = sut.register("Antxon", "Urrutia", "Garcia", "antxon18", "12345678",
				"666666666","antxon@gmail.com", date, "admin");
			
			assertEquals("Antxon", p.getIzena());
			assertEquals("Urrutia", p.getAbizena1());
			assertEquals("Garcia", p.getAbizena2());
			assertEquals("antxon18", p.getErabiltzaileIzena());
			assertEquals("12345678", p.getPasahitza());
			assertEquals("666666666",p.getTelefonoZbkia());
			assertEquals("antxon@gmail.com", p.getEmail());
			assertEquals(date, p.getJaiotzeData());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test5() {
		
		Pertsona user = null;
		
		try {
			
			Mockito.when(mockDA.register("Antxon", null, "Garcia", "antxon18", "12345678",
					"666666666","antxon@gmail.com", date, "admin")).thenReturn(user);
			
			Pertsona p = sut.register("Antxon", null, "Garcia", "antxon18", "12345678",
				"666666666","antxon@gmail.com", date, "admin");
			
			assertNull(p);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test14() {
		
		try {
			
			Mockito.when(mockDA.register("Antxon", "Urrutia", "Garcia", "antxon18", "12345678",
					"666666666","antxon@gmail.com", date, "admin")).thenThrow(UserAlreadyExist.class);
			
			Pertsona p = sut.register("Antxon", "Urrutia", "Garcia", "antxon18", "12345678",
				"666666666","antxon@gmail.com", date, "admin");
			
			fail();
		}catch (UserAlreadyExist e) {
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	

}
