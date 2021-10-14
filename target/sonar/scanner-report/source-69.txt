import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.ArretaMezua;
import domain.BezeroartekoMezua;

public class RemoveMezuaMockInt {
	
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
	
	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	public void test1() {
		sut.removeMezua(new BezeroartekoMezua());
		Mockito.verify(dataAccess,Mockito.times(1)).removeMezua(Mockito.any(BezeroartekoMezua.class));	
	}
	
	@Test
	public void test2() {
		sut.removeMezua(new ArretaMezua());
		Mockito.verify(dataAccess,Mockito.times(1)).removeMezua(Mockito.any(ArretaMezua.class));	
	}
	
	@Test
	public void test3() {
		try {
			Mockito.doThrow(new Exception()).when(dataAccess).removeMezua(null);
			sut.removeMezua(null);
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
}
