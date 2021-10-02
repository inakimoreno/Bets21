import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;

@RunWith(MockitoJUnitRunner.class)
public class EmaitzaIpiniMockInt {

	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
    //Event mockedEvent=Mockito.mock(Event.class);
	
	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	public void test1() {
		sut.emaitzaIpini(new Question(), new Pronostikoa());
		Mockito.verify(dataAccess,Mockito.times(1)).emaitzaIpini(Mockito.any(Question.class), Mockito.any(Pronostikoa.class));	
	}
	
	@Test
	public void test2() {
		try {
			Mockito.doReturn(new Exception()).when(dataAccess.emaitzaIpini(null, null));
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void test3() {
		try {
			Mockito.doReturn(new Exception()).when(dataAccess.emaitzaIpini(new Question(), new Pronostikoa()));
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
}
