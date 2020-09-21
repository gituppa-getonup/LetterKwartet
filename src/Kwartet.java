
public class Kwartet {
	private KaartGroep groep;
	private Speler speler;
	
	public KaartGroep getGroep() {
		return groep;
	}
		
	public void setGroep(KaartGroep groep) {
		this.groep = groep;
	}
	
	public Speler getSpeler() {
		return speler;
	}
	
	public void setSpeler(Speler speler) {
		this.speler = speler;
	}
	
	public Kwartet(KaartGroep groep, Speler speler) {
		this.groep = groep;
		this.speler = speler;
		System.out.println("Speler " + speler + " heeft kwartet met de groep " + groep + " !!!");
	}

}
