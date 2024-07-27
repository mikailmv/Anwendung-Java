import java.util.ArrayList;
import java.util.List;

public class FinanzManager {
    private List<Transaktion> transaktionen;

    public FinanzManager() {
        transaktionen = new ArrayList<>();
    }

    public void addTransaktion(Transaktion transaktion) {
        transaktionen.add(transaktion);
    }

    public double getGesamteinnahmen() {
        return transaktionen.stream()
                .filter(Transaktion::istEinnahme)
                .mapToDouble(Transaktion::getBetrag)
                .sum();
    }

    public double getGesamtausgaben() {
        return transaktionen.stream()
                .filter(t -> !t.istEinnahme())
                .mapToDouble(Transaktion::getBetrag)
                .sum();
    }

    public double getKontostand() {
        return getGesamteinnahmen() - getGesamtausgaben();
    }

    public List<Transaktion> getTransaktionen() {
        return transaktionen;
    }
}
