import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterKwartet {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welkom bij het spel Letter Kwartet.");
		System.out.println("Hoe heet je?");
		String spelerNaam = System.console().readLine();
		System.out.println("Welkom, " + spelerNaam + "!");
		Speler menselijkeSpeler = new Speler(spelerNaam, Speler.SpelerType.MENS);
		
		List<String> spelerNamen = new ArrayList<>();
		spelerNamen.add("Ender");
		spelerNamen.add("Gwen");
		spelerNamen.add("Aidan");
		spelerNamen.add("Jerre");
		
		List<String> spelerNamenCPU = spelerNamen.stream()
		.filter(s -> !s.contains(spelerNaam))
		.collect(Collectors.toList());
		
		List<Speler> spelerslijst = new ArrayList<>();
		spelerslijst.add(menselijkeSpeler);
		while(spelerslijst.size() < 4) {
		    spelerNamenCPU.stream().forEach(s -> spelerslijst.add(new Speler(s, Speler.SpelerType.COMPUTER)));
		}
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
