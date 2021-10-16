package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso ({Bezeroa.class, Admin.class, Langilea.class})
public abstract class Pertsona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String izena;
	public String abizena1;
	public String abizena2;
	@Id
	@XmlID
	public String erabiltzaileIzena;
	public String pasahitza;
	public String telefonoZbkia;
	public String email;
	public Date jaiotzeData;
	
	public Pertsona(){
		super();
	}
	
	public Pertsona (String pertsonaDatuak, Date jaiotzeData, String erabiltzailea,String kontaktua) {
		String[] izenaParts = pertsonaDatuak.split(" ");
		String[] erabiltzaileaParts = erabiltzailea.split(" ");
		String[] kontaktuaParts = kontaktua.split(" ");
		this.izena = izenaParts[0];
		this.abizena1 = izenaParts[1];
		this.abizena2 = izenaParts[2];
		this.erabiltzaileIzena = erabiltzaileaParts[0];
		this.pasahitza = erabiltzaileaParts[1];
		this.telefonoZbkia = kontaktuaParts[0];
		this.email=kontaktuaParts[1];
		this.jaiotzeData= jaiotzeData;
	}
	
	public String getErabiltzaileIzena() {
		return erabiltzaileIzena;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena1() {
		return abizena1;
	}

	public void setAbizena1(String abizena1) {
		this.abizena1 = abizena1;
	}

	public String getAbizena2() {
		return abizena2;
	}

	public void setAbizena2(String abizena2) {
		this.abizena2 = abizena2;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getTelefonoZbkia() {
		return telefonoZbkia;
	}

	public void setTelefonoZbkia(String telefonoZbkia) {
		this.telefonoZbkia = telefonoZbkia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	public void setErabiltzaileIzena(String erabiltzaileIzena) {
		this.erabiltzaileIzena = erabiltzaileIzena;
	}
}
