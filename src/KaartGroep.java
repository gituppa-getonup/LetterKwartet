
public class KaartGroep {
	
	private String groep;

	public String getGroep() {
		return groep;
	}

	public void setGroep(String groep) {
		this.groep = groep;
	}
	
	public KaartGroep(String groep) {
		this.groep = groep;
	}
	
	@Override
	public String toString() {
		return groep;
	}
	
	
}