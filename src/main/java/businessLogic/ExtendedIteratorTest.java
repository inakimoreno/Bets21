package businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Event;

public class ExtendedIteratorTest {

	public static void main(String[] args) {

		// Facade objektua lortu lehendabiziko ariketa erabiliz
		LaunchModeFactory lmFactory = new LaunchModeFactory();
		BLFacade appFacadeInterface;
		appFacadeInterface = lmFactory.createBLFacade();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Date date;
		try {
			date = sdf.parse("01/12/2021");

			ExtendedIterator<Event> i = appFacadeInterface.getEvents(date);

			Event ev;
			System.out.println("____________________________");
			System.out.println("ATZETIK AURRERA");
			System.out.println("____________________________");

			i.goLast();
			
			while (i.hasPrevious()) {
				ev = i.previous();
				System.out.println(ev.toString());
			}

			System.out.println("____________________________");
			System.out.println("AURRETIK ATZERA");
			System.out.println("____________________________");

			i.goFirst();

			while (i.hasNext()) {
				ev = i.next();
				System.out.println(ev.toString());
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
