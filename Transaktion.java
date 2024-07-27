import java.time.LocalDate;

public class Transaktion {
    private String beschreibung;
    private double betrag;
    private LocalDate datum;
    private boolean istEinnahme;

    public Transaktion(String beschreibung, double betrag, LocalDate datum, boolean istEinnahme) {
        this.beschreibung = beschreibung;
        this.betrag = betrag;
        this.datum = datum;
        this.istEinnahme = istEinnahme;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public double getBetrag() {
        return betrag;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public boolean istEinnahme() {
        return istEinnahme;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f am %s", beschreibung, betrag, datum);
    }
}
