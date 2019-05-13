package elainkauppa.model;

import java.util.Random;

public class Elain {
	private int id;
	private String laji;
	private String nimi;
	private String kuvaus;
	private double hinta;
	// korottaa eläimen id:tä
	private Random random = new Random();
	int min = 1;
	int max = 1000;
	
	// Älä poista, käytössä testeissä
	public Elain(int id, String laji, String nimi, String kuvaus, double hinta) {
		this.id = id;
		this.laji = laji;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}
	
	public Elain(String laji, String nimi, String kuvaus, double hinta) {
		this.id = random.nextInt(max + 1 - min) + min;
		this.laji = laji;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
	}

	public Elain() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLaji() {
		return laji;
	}

	public void setLaji(String laji) {
		this.laji = laji;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "Elain [id=" + id + ", laji=" + laji + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", hinta=" + hinta
				+ "]";
	}
}
