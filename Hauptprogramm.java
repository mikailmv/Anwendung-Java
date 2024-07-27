import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Hauptprogramm {
    private static FinanzManager finanzManager = new FinanzManager();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Persönliche Finanzverwaltung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JList<String> transaktionsListe = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(transaktionsListe);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel eingabepanel = new JPanel();
        eingabepanel.setLayout(new GridLayout(4, 2));

        JTextField beschreibungFeld = new JTextField();
        JTextField betragFeld = new JTextField();
        JTextField datumFeld = new JTextField("YYYY-MM-DD");

        JButton hinzufuegenButton = new JButton("Hinzufügen");
        JButton aktualisierenButton = new JButton("Aktualisieren");

        eingabepanel.add(new JLabel("Beschreibung:"));
        eingabepanel.add(beschreibungFeld);
        eingabepanel.add(new JLabel("Betrag:"));
        eingabepanel.add(betragFeld);
        eingabepanel.add(new JLabel("Datum (YYYY-MM-DD):"));
        eingabepanel.add(datumFeld);
        eingabepanel.add(hinzufuegenButton);
        eingabepanel.add(aktualisierenButton);

        frame.add(eingabepanel, BorderLayout.SOUTH);

        hinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String beschreibung = beschreibungFeld.getText().trim();
                    double betrag = Double.parseDouble(betragFeld.getText().trim());
                    LocalDate datum = LocalDate.parse(datumFeld.getText().trim());
                    boolean istEinnahme = betrag > 0;

                    Transaktion transaktion = new Transaktion(beschreibung, betrag, datum, istEinnahme);
                    finanzManager.addTransaktion(transaktion);
                    beschreibungFeld.setText("");
                    betragFeld.setText("");
                    datumFeld.setText("YYYY-MM-DD");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ungültige Eingabe. Bitte überprüfen Sie alle Felder.");
                }
            }
        });

        aktualisierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTransaktionsListe();
            }
        });

        frame.setVisible(true);
    }

    private static void updateTransaktionsListe() {
        listModel.clear();
        List<Transaktion> transaktionen = finanzManager.getTransaktionen();
        for (Transaktion transaktion : transaktionen) {
            listModel.addElement(transaktion.toString());
        }
        listModel.addElement("Gesamteinnahmen: " + finanzManager.getGesamteinnahmen());
        listModel.addElement("Gesamtausgaben: " + finanzManager.getGesamtausgaben());
        listModel.addElement("Kontostand: " + finanzManager.getKontostand());
    }
}

