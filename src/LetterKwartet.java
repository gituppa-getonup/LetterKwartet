import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LetterKwartet {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welkom bij het spel Letter Kwartet.");
		System.out.println("Hoe heet je?");
		String spelerNaam = System.console().readLine();
		System.out.println("Welkom, " + spelerNaam + "!");
		Speler menselijkeSpeler = new Speler(spelerNaam, Speler.SpelerType.MENS);
				
		List<Speler> spelerNamenCPU = new ArrayList<>();
		spelerNamenCPU.add(new Speler("Ender", Speler.SpelerType.COMPUTER));
		spelerNamenCPU.add(new Speler("Gwen", Speler.SpelerType.COMPUTER));
		spelerNamenCPU.add(new Speler("Aidan", Speler.SpelerType.COMPUTER));
		spelerNamenCPU.add(new Speler("Jerre", Speler.SpelerType.COMPUTER));
		
		spelerNamenCPU.removeIf(sCPU -> sCPU.getNaam().equalsIgnoreCase(spelerNaam));
		
		if(spelerNamenCPU.size() == 4) {
		    spelerNamenCPU.remove(3);
		}
		
		List<Speler> spelerslijst = new ArrayList<>();
		spelerslijst.add(menselijkeSpeler);
		spelerslijst.addAll(spelerNamenCPU);

		System.out.println("Even de kaarten schudden...");
		Thread.sleep((long)(10000 * Math.random()));
		
		Spel spel = new Spel(spelerslijst);
		spel.voorbereiden();
		if(spel.getGeldigheid()==false) {
			System.out.println("Met " + spel.getKaartenlijst().size() + " kaarten kan ik geen spel starten met "
					+ spel.getSpelerslijst().size() + " spelers.");
			System.out.println("Kies een aantal spelers waarbij alle kaarten verdeeld kunnen worden.");
			return;
		}
		//spel.toonSpel();	
		spel.speel();
	}

}
