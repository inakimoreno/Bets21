package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin extends Pertsona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Admin(){
		super();
	}

	public Admin(String pertsonaDatuak, String erabiltzailea,String kontaktua, Date jaiotzeData) {
		super(pertsonaDatuak, jaiotzeData, erabiltzailea,kontaktua);
	}
}
