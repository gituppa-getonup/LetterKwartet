import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Spel {

    private List<Kaart> kaartenlijst;
    private List<Speler> spelerslijst;
    private List<Kwartet> kwartetlijst;
    private boolean geldigheid;
    private List<KaartGroep> kaartgroeplijst;

    public List<KaartGroep> getKaartgroeplijst() {
	return kaartgroeplijst;
    }

    public void setKaartgroeplijst(List<KaartGroep> kaartgroeplijst) {
	this.kaartgroeplijst = kaartgroeplijst;
    }

    public List<Kaart> getKaartenlijst() {
	return kaartenlijst;
    }

    public void setKaartenlijst(List<Kaart> kaartenlijst) {
	this.kaartenlijst = kaartenlijst;
    }

    public List<Speler> getSpelerslijst() {
	return spelerslijst;
    }

    public void setSpelerslijst(List<Speler> spelerslijst) {
	this.spelerslijst = spelerslijst;
    }

    public List<Kwartet> getKwartetlijst() {
	return kwartetlijst;
    }

    public void voegSpelerToe(Speler speler) {
	spelerslijst.add(speler);
    }

    public void voegKaartToe(Kaart kaart) {
	kaartenlijst.add(kaart);
    }

    public Spel(List<Speler> spelerslijst) {
	this.spelerslijst = spelerslijst;
    }

    public void setGeldigheid(boolean geldigheid) {
	this.geldigheid = geldigheid;
    }

    public boolean getGeldigheid() {
	return geldigheid;
    }

    public void voorbereiden() {
	maakSchoneKaartenLijst();
	controleerGeldigheid();
	deelKaarten();
	kwartetlijst = new ArrayList<>();
	spelerslijst.get(0).zetAanBeurt(true);
    }

    public void speel() {

	while (kwartetlijst.size() < this.getKaartgroeplijst().size()) {
	    speelRonde();
	}
	System.out.println("Het spel is klaar.");
	this.getKwartetlijst().stream().forEach(kw -> System.out.println(kw.getSpeler() + " heeft kwartet met " + kw.getGroep()));
	// todo: pronounce winner.
    }

    public void maakSchoneKaartenLijst() {
	KaartGroep p = new KaartGroep("p");
	KaartGroep k = new KaartGroep("k");
	KaartGroep ui = new KaartGroep("ui");
	KaartGroep oe = new KaartGroep("oe");
	KaartGroep b = new KaartGroep("b");
	KaartGroep w = new KaartGroep("w");
	KaartGroep m = new KaartGroep("m");
	KaartGroep aa = new KaartGroep("aa");

	List<KaartGroep> kaartgroeplijst = new ArrayList<>();
	kaartgroeplijst.add(p);
	kaartgroeplijst.add(k);
	kaartgroeplijst.add(ui);
	kaartgroeplijst.add(oe);
	kaartgroeplijst.add(b);
	kaartgroeplijst.add(w);
	kaartgroeplijst.add(m);
	kaartgroeplijst.add(aa);

	this.setKaartgroeplijst(kaartgroeplijst);

	kaartenlijst = new ArrayList<>();
	kaartenlijst.add(new Kaart(p, "wip"));
	kaartenlijst.add(new Kaart(p, "pit"));
	kaartenlijst.add(new Kaart(p, "poes"));
	kaartenlijst.add(new Kaart(p, "drop"));
	kaartenlijst.add(new Kaart(k, "broek"));
	kaartenlijst.add(new Kaart(k, "kaart"));
	kaartenlijst.add(new Kaart(k, "wolk"));
	kaartenlijst.add(new Kaart(k, "klok"));
	kaartenlijst.add(new Kaart(ui, "vuist"));
	kaartenlijst.add(new Kaart(ui, "ruik"));
	kaartenlijst.add(new Kaart(ui, "duif"));
	kaartenlijst.add(new Kaart(ui, "huis"));
	kaartenlijst.add(new Kaart(oe, "boek"));
	kaartenlijst.add(new Kaart(oe, "voet"));
	kaartenlijst.add(new Kaart(oe, "stoel"));
	kaartenlijst.add(new Kaart(oe, "koe"));
	kaartenlijst.add(new Kaart(b, "buik"));
	kaartenlijst.add(new Kaart(b, "bos"));
	kaartenlijst.add(new Kaart(b, "bijl"));
	kaartenlijst.add(new Kaart(b, "boos"));
	kaartenlijst.add(new Kaart(w, "wiel"));
	kaartenlijst.add(new Kaart(w, "wolk"));
	kaartenlijst.add(new Kaart(w, "wip"));
	kaartenlijst.add(new Kaart(w, "weg"));
	kaartenlijst.add(new Kaart(m, "bloem"));
	kaartenlijst.add(new Kaart(m, "mol"));
	kaartenlijst.add(new Kaart(m, "muur"));
	kaartenlijst.add(new Kaart(m, "raam"));
	kaartenlijst.add(new Kaart(aa, "aap"));
	kaartenlijst.add(new Kaart(aa, "maan"));
	kaartenlijst.add(new Kaart(aa, "draak"));
	kaartenlijst.add(new Kaart(aa, "kaart"));

	kaartenlijst = schudKaarten(kaartenlijst);

	this.setKaartenlijst(kaartenlijst);
    }

    public List<Kaart> schudKaarten(List<Kaart> kaartenlijst) {
	List<Kaart> kaartenlijstGeschud = new ArrayList<>();

	while (!kaartenlijst.isEmpty()) {
	    int aantalKaarten = kaartenlijst.size();
	    Kaart eenKaart = kaartenlijst.get(Spel.haalRandomIndex(aantalKaarten));

	    kaartenlijst.remove(eenKaart);
	    kaartenlijstGeschud.add(eenKaart);
	}

	return kaartenlijstGeschud;
    }

    public static int haalRandomIndex(int aantal) {
	int max = aantal - 1;

	return (int) (Math.random() * max);
    }

    public void deelKaarten() {
	int kaartenPerSpeler = kaartenlijst.size() / spelerslijst.size();

	// de kaartenstapel index wordt buiten de for loop gehouden om te voorkomen dat
	// deze bij schakelen naar de volgende speler gereset wordt.
	int kaartenStapelIndex = 0;

	for (int s = 0; s < spelerslijst.size(); s++) { // itereeer de spelers.
	    for (int kaartenPerSpelerIndex = 0; kaartenPerSpelerIndex < kaartenPerSpeler; kaartenPerSpelerIndex++, kaartenStapelIndex++) {
		kaartenlijst.get(kaartenStapelIndex).setSpeler(spelerslijst.get(s)); // wijs een kaart uit de stapel toe
										     // aan de speler.
	    }
	}
    }

    public void toonSpel() {
	spelerslijst.stream().forEach(s -> System.out.println("Speler: " + s));
	kaartenlijst.stream().forEach(k -> System.out
		.println("Kaart: " + k.getGroep() + " " + k.getWoord() + " is van speler " + k.getSpeler()));
    }

    public void controleerGeldigheid() {
	this.setGeldigheid(kaartenlijst.size() % spelerslijst.size() == 0);
    }

    public void speelRonde() {
	spelerslijst.stream().filter(s -> s.isAanDeBeurt()).forEach(s -> {
	    System.out.println(s.getNaam() + " is aan de beurt.");
	    speelRonde(s);
	});

    }

    public void speelRonde(Speler speler) {
	checkKwartet(speler);

	Comparator<Kaart> kaartsorteerder = (Kaart k1, Kaart k2) -> k1.getGroep().getGroep()
		.compareTo(k2.getGroep().getGroep());

	List<Kaart> eigenKaarten = this.getKaartenlijst().stream().filter(k -> k.getSpeler().equals(speler))
		.sorted(kaartsorteerder.thenComparing(Kaart::getWoord)).collect(Collectors.toList());

	if (eigenKaarten.size() == 0) {
	    schuifBeurtOp(speler);
	    System.out.println(speler + " heeft geen kaarten meer.");
	    return;
	}

	final Set<KaartGroep> eigenKaartgroepen = eigenKaarten.stream().map(k -> k.getGroep())
		.collect(Collectors.toSet());

	List<Kaart> vraagbareKaarten = new ArrayList<>();
	vraagbareKaarten = this.getKaartenlijst().stream()
		.filter(k -> !(k.getSpeler().equals(speler)) && eigenKaartgroepen.contains(k.getGroep()))
		.sorted(kaartsorteerder.thenComparing(Kaart::getWoord)).collect(Collectors.toList());

	boolean gekregen = false;

	if (speler.getSpelerType().equals(Speler.SpelerType.MENS)) {
	    gekregen = speelAlsMens(speler, eigenKaarten, vraagbareKaarten, eigenKaartgroepen);
	} else {
	    try {
		Thread.sleep((long)(10000 * Math.random()));
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    gekregen = speelAlsComputer(speler, vraagbareKaarten);
	}
	
	if (gekregen) {
	    checkKwartet(speler);
	} else {
	    schuifBeurtOp(speler);
	}

    }

    private boolean speelAlsMens(Speler speler, List<Kaart> eigenKaarten, List<Kaart> vraagbareKaarten,
	    Set<KaartGroep> eigenKaartgroepen) {

	System.out.println(speler.getNaam() + ", je hebt op dit moment de volgende kaarten: ");
	eigenKaarten.stream().forEach(k -> System.out.println("van de groep " + k.getGroep() + " heb je " + k));

	System.out.println("dus je kan de volgende kaarten nog vragen: ");

	vraagbareKaarten.stream().forEach(k -> System.out.println("van de groep " + k.getGroep() + " de kaart " + k));
	
	System.out.println("Je kan vragen bij de volgende spelers:");
	spelerslijst.stream().filter(s -> s.getSpelerType().equals(Speler.SpelerType.COMPUTER)).forEach(System.out::println);

	boolean gevraagdeGroepBekend = false;
	String mogelijkeGroep = null;

	while (!gevraagdeGroepBekend) {
	    System.out.println("Van welke groep wil je een kaart vragen?");
	    mogelijkeGroep = System.console().readLine().toLowerCase();
	    final String mogelijkeGroepFinal = mogelijkeGroep;

	    gevraagdeGroepBekend = eigenKaartgroepen.stream().filter(g -> g.getGroep().equals(mogelijkeGroepFinal))
		    .collect(Collectors.toList()).size() == 1;
	    if (!gevraagdeGroepBekend) {
		System.out.println(mogelijkeGroep + " is niet een groep waar je van kan vragen.");
	    }

	}

	boolean gevraagdWoordBekend = false;
	String mogelijkWoord = null;

	while (!gevraagdWoordBekend) {
	    System.out.println("En welk woord wil je van die groep vragen?");
	    mogelijkWoord = System.console().readLine().toLowerCase();
	    final String mogelijkWoordFinal = mogelijkWoord;
	    final String mogelijkeGroepFinal = mogelijkeGroep;
	    gevraagdWoordBekend = vraagbareKaarten.stream().filter(
		    k -> k.getWoord().equals(mogelijkWoordFinal) && k.getGroep().getGroep().equals(mogelijkeGroepFinal))
		    .collect(Collectors.toList()).size() == 1;
	    if (!gevraagdWoordBekend) {
		System.out.println(mogelijkWoord
			+ " is niet een woord dat je nu kan vragen of het woord staat in een andere groep.");
	    }
	}

	final String mogelijkeGroepFinal = mogelijkeGroep;
	final String mogelijkWoordFinal = mogelijkWoord;

	Kaart mogelijkeKaart = vraagbareKaarten.stream().filter(
		k -> k.getGroep().getGroep().equals(mogelijkeGroepFinal) && k.getWoord().equals(mogelijkWoordFinal))
		.collect(Collectors.toList()).get(0);

	boolean gevraagdeSpelerBekend = false;
	Speler mogelijkeSpeler = null;

	while (!gevraagdeSpelerBekend) {
	    System.out.println("En aan wie wil je die kaart vragen?");
	    String mogelijkeSpelerNaam = System.console().readLine();
	    List<Speler> gevraagdeSpelerlijst = this.getSpelerslijst().stream()
		    .filter(s -> s.getNaam().equalsIgnoreCase(mogelijkeSpelerNaam)).collect(Collectors.toList());

	    if (gevraagdeSpelerlijst.size() == 1) {
		mogelijkeSpeler = gevraagdeSpelerlijst.get(0);
		gevraagdeSpelerBekend = true;
	    } else {
		System.out.println(mogelijkeSpelerNaam + " is niet de naam van een andere speler in dit spel.");
	    }
	}

	return vraag(mogelijkeKaart, speler, mogelijkeSpeler);

    }

    private boolean speelAlsComputer(Speler speler, List<Kaart> vraagbareKaarten) {

	vraagbareKaarten = schudKaarten(vraagbareKaarten);
	Kaart vraagKaart = vraagbareKaarten.get(0);

	List<Speler> andereSpelers = this.getSpelerslijst().stream().filter(s -> !s.equals(speler))
		.collect(Collectors.toList());

	Speler gevraagdeSpeler = andereSpelers.get(haalRandomIndex(andereSpelers.size()));

	return vraag(vraagKaart, speler, gevraagdeSpeler);
    }

    public boolean vraag(Kaart kaart, Speler vragende, Speler gevraagde) {
	System.out.println(vragende + " zegt: " + gevraagde + ", mag ik van jou van de " + kaart.getGroep() + ", " + kaart + "?");
	if (kaart.getSpeler().equals(gevraagde)) {
	    kaart.setSpeler(vragende);
	    System.out.println(gevraagde + " zegt: Ja.");
	    System.out.println(gevraagde + " geeft " + kaart + " aan " + vragende);
	    return true;
	} else {
	    System.out.println(gevraagde + " zegt: Nee, die heb ik niet.");
	    return false;
	}
    }

    public void checkKwartet(Speler speler) {
	this.getKaartenlijst().stream().filter(k -> k.getSpeler().equals(speler))
		.collect(Collectors.groupingBy(Kaart::getGroep, Collectors.counting())).entrySet().stream()
		.filter(e -> e.getValue().longValue() == 4).map(e -> e.getKey()).forEach(kg -> legKwartet(kg, speler));
    }

    public void legKwartet(KaartGroep kaartgroep, Speler speler) {
	this.getKaartenlijst().stream().filter(k -> k.getGroep().getGroep().equals(kaartgroep.getGroep()))
		.forEach(k -> this.getKaartenlijst().remove(k));

	kwartetlijst.add(new Kwartet(kaartgroep, speler));
    }

    public void schuifBeurtOp(Speler speler) {
	int indexHuidigeSpeler = this.getSpelerslijst().indexOf(speler);
	//System.out.println("DEBUG index huidige speler " + indexHuidigeSpeler);
	int indexNieuweSpeler = (indexHuidigeSpeler == this.getSpelerslijst().size() - 1) ? 0 : ++indexHuidigeSpeler;
	//System.out.println("DEBUG index nieuwe speler " + indexNieuweSpeler);
	
	speler.zetAanBeurt(false);
	Speler nieuweSpeler = this.getSpelerslijst().get(indexNieuweSpeler);
	nieuweSpeler.zetAanBeurt(true);
	System.out.println("De beurt schuift op van " + speler + " naar " + nieuweSpeler);
    }

}

//.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByValue())).collect(Collectors.toMap(
//        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
