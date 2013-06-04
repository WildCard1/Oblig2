/**
 *Mainklassen kaller på forskjellige metoder for å kjøre programmet.
 *@author henrihan<henrik.hansen@student.jus.uio.no>
 *@version 1.0
 *******************************************************************/ 
class Oblig2 {
	public static void main(String[]args) {	
	Test t = new Test();
	t.opprettObj();
	t.vilNoenHaBoka(new Bok("Java"));
	t.vilNoenHaBoka(new Bok("krim"));
	t.vilNoenHaBoka(new Bok("historie"));
	t.vilNoenHaBoka(new Bok("sports"));
	t.vilNoenHaBoka(new Bok("poesi"));
	t.vilNoenHaBoka(new Bok("sports"));
	t.vilNoenHaBoka(new Bok("poesi"));
	t.vilNoenHaBoka(new Bok("baby"));
	t.vilNoenHaBoka(new Bok("poesi"));
	t.vennskap();
	}
}
/**
 * Denne klassen er testklassen. Den oppretter objektene til de
 * forskjellige personobjektene, og legger inn vennskap. Samt spoer
 * den om hvem som vil ha hvilken bok.
 */
class Test{
	Person jeg, emil, lisa, ramzi;
    /** Oppretter personobjektene. Legger inn navnet paa personen og bokkategorien h*n foretrekker*/
	public void opprettObj() {
		jeg = new Person("Ego", "poesi");
		emil = new Person("Emil", "sports");
		lisa = new Person("Lisa", "krim");
		ramzi = new Person("Ramzi", "mat");
	}
    /** Deklarer hvem som er venn med hvem*/
	public void vennskap() {
		jeg.blirVennMed(lisa);
		lisa.blirVennMed(ramzi);
		ramzi.blirVennMed(emil);
		jeg.skrivUtAlt();
	}
    /**Sjekker om noen er interessert i den aktuelle boka
     *@param b1 Bokobjektet som gjelder naa.
     */
	public void vilNoenHaBoka(Bok b1) {
		if (emil.vilJegHaBoka(b1)) {
			emil.mittBibliotek.settInn(b1);
		}else if(ramzi.vilJegHaBoka(b1)){
			ramzi.mittBibliotek.settInn(b1);
		}else if(lisa.vilJegHaBoka(b1)){
			lisa.mittBibliotek.settInn(b1);
		}else if(jeg.vilJegHaBoka(b1)){
			jeg.mittBibliotek.settInn(b1);			
		}
	}
}
/** Personklassen inneholder en god del metoder og data; bestemt fra metodekallene i klassen "Test".
 * @param navn navnet paa personobjektet
 * @param bestevenn Peker paa personobjektet som er bestevennen med innevarende personobjekt.
 * @param lonewolf Kun en string-variabel for aa ungaa at
 * nullpointer-exception skjer hvis du proever aa kalla paa et
 * personobjekt som ikke har en venn(bestevenn).
 * @param bokKat String-variabel for som forteller hvilken bokkategori
 * personobjektet foretrekker aa lese.
 * @param mittBibliotek peker til den generiske klassen "Beholder" for aa holde paa boeker.
 */
class Person {
	private String navn;
	private Person bestevenn;
	private String lonewolf = "ingen";
	private String bokKat;
	Beholder<Bok> mittBibliotek = new Beholder<Bok>();
    /** Konstruktoer som legger inn navnet og foretrukken bokkategori i objektvariablene med samme navn
     * @param navn Navnet til personobjektet, som kommer ifra metodekallet i testklassen
     * @param bokKat paa bokkategorien personobjektet foretrekker, som
     * kommer ifra metodekallet i testklassen.
     */
	Person(String navn, String bokKat) {
		this.navn = navn;
		this.bokKat = bokKat;
	}
    /** Metode for aa skrive ut navnet til personobjektet(da objektvariabelen er satt som private)*/
	public String hentNavn (){
		return navn;
	}
    /** Metode for aa legge til venn(bestevenn) i personobjekt-peker "bestevenn"
     * @param p Personobjektet som skal legges til som bestevenn, kalt paa av testklassen*/
	public void blirVennMed (Person p){
		bestevenn = p;
	}
    /** Metode som returnerer bestevenn(venn) til personobjektet.*/
	public Person hentBestevenn (){
	    return bestevenn;
			
	}
    /** Metode som returnerer navnet til bestevennnen til
     * personobjektet. "Ingen" (lonewolf) for aa ungaa
     * nullpointer-exception.*/
	public String minBesteVennHeter () {
		if(bestevenn != null){
			return bestevenn.hentNavn();
		}else{
			return lonewolf;
		}
	}
    /** Metode som returnerer true hvis bokkategorien til boken er det
     * samme som bokkategorien som personbobjektet foretrekker
     * @param b Det gjeldende bokobjektet.
     */
	private boolean mittInteressefelt(Bok b) {
		if (b.kategori().equals(bokKat))
		    return true;
		return false;
	}
    /** Metode som returnerer om personen vil ha den gjeldende boka eller ikke
     * @param b Det gjeldende bokobjektet.
     */
	public boolean vilJegHaBoka(Bok b) {
		if (mittInteressefelt(b) == true)
			return true;
		return false;
	}
    /** Skriver ut hvem personbobjektet er venn med, samt hvilken
     * bokkategori personen foretrekker, og hvor mange boeker av denne
     *kategorien personen allerede har.*/ 
	public void skrivUtMeg () {
		System.out.println(navn + " er venn med " + minBesteVennHeter ());
		System.out.println(navn + " liker " + bokKat + "boeker og har " + mittBibliotek.hentAntall() + " av dem. ");
	}
    /** Kjoerer foerst skrivUtMeg()-metoden, og etterpaa skriver ut info om vennen.*/
	public void skrivUtAlt(){
		skrivUtMeg();
		if(hentBestevenn() != null)
			hentBestevenn().skrivUtAlt();
	}
	
}

/** Generisk klasse som fungerer som en beholder. I denne oppgaven brukes den bare som beholder for boeker.
 *@param alle En array-peker til T som kan holde paa 100 T-objekter(i denne oppgaven kun bok-objekter).
 *@param antall Teller for aa holde styr paa hvilken index i "alle" vi jobber med.
 */
class Beholder<T> {
	private T[] alle = (T[]) new Object[100];
	private int antall = 0;
	/** Metode for aa sette inn boeker i "alle".*/
	public void settInn(T det) {
		alle[antall] = det;
		antall++;
	}
	/** Metode for aa taa ut boeker fra alle objektet. Returnerer
	 * objektet som tas ut, samt fjernes dette objektet naar noe
	 * lagres over det i etterkant*/
	public T taUt()	{
		antall --;
		return alle[antall];
	}
	/** Metode som returnerer hvor mange objekter(her Bok-objekter) en person har valgt aa ta imot*/
	public int hentAntall() {
		return antall;
	}
}

/** Klasse for aa definere en bok
 * @param kat Hvilken kategori boka gaar under.
 */
class Bok {
	private String kat;
    /** Konstruktoer som legger verdien i kat fra metodekallet i objektvariabelen kat
     * @param kat Kategorien til den gjeldende boka, som stammer ifra metodekallet*/
    Bok(String kat) {
	this.kat = kat;
	}
    /** Returnerer bokkategorien(da variabelen "kat" er private)*/
	public String kategori() {
		return kat;
	}	
}
