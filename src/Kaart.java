
public class Kaart {
	
	private KaartGroep groep;
	private String woord;
	private Speler speler;
	
	@Override
	public String toString() {
		return woord;
	}
	
	public KaartGroep getGroep() {
		return groep;
	}
	
	public void setGroep(KaartGroep groep) {
		this.groep = groep;
	}
	
	public String getWoord() {
		return woord;
	}
	
	public void setWoord(String woord) {
		this.woord = woord;
	}
	
	public Speler getSpeler() {
		return speler;
	}
	
	public void setSpeler(Speler speler) {
		this.speler = speler;
	}
	
	public Kaart(KaartGroep groep, String woord) {
		this.groep = groep;
		this.woord = woord;
	}

}

