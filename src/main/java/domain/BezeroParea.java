package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class BezeroParea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@XmlIDREF
	private Bezeroa nork;
	@OneToOne
	@XmlIDREF
	private Bezeroa nori;
	
	public BezeroParea(Bezeroa nork, Bezeroa nori) {
		super();
		this.nork = nork;
		this.nori = nori;
	}

	public Bezeroa getNork() {
		return nork;
	}

	public Bezeroa getNori() {
		return nori;
	}
}
