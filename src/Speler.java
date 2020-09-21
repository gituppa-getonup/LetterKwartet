
public class Speler {

	private String naam;	
	private SpelerType spelerType;
	private boolean heeftBeurt;
	
	@Override
	public String toString() {
		return naam;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public SpelerType getSpelerType() {
		return spelerType;
	}

	public void setSpelerType(SpelerType spelerType) {
		this.spelerType = spelerType;
	}
	
	public Speler(String naam, SpelerType spelerType) {
		this.naam = naam;
		this.spelerType = spelerType;
	}	

	public enum SpelerType { MENS, COMPUTER };
	
    public boolean isAanDeBeurt() {
    	return heeftBeurt;
    }
    
    public void zetAanBeurt(boolean heeftBeurt) {
    	this.heeftBeurt = heeftBeurt;
    }
    
    
    
    
	
}



