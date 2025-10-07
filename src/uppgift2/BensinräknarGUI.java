package uppgift2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BensinräknarGUI extends Bensinräknare {
    private JFrame frame;
    private JTextField nyMätarFält;
    private JTextField gammalMätarFält;
    private JTextField literFält;
    private JLabel resultatMil;
    private JLabel resultatFörbrukning;
    private JLabel resultatLiter;

    public BensinräknarGUI() {
        super(0, 0, 0);

        frame = new JFrame("Bensinräknare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Ange mätarställning nu:"));
        nyMätarFält = new JTextField();
        inputPanel.add(nyMätarFält);

        inputPanel.add(
                new JLabel("Ange mätarställning för ett år sedan:")
        );
        gammalMätarFält = new JTextField();
        inputPanel.add(gammalMätarFält);

        inputPanel.add(new JLabel("Ange antal liter förbrukad bensin:"));
        literFält = new JTextField();
        inputPanel.add(literFält);

        JPanel resultPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        resultatMil = new JLabel("Antal körda mil: -");
        resultatLiter = new JLabel("Antal liter bensin: -");
        resultatFörbrukning = new JLabel("Förbrukning per mil: -");
        resultPanel.add(resultatMil);
        resultPanel.add(resultatLiter);
        resultPanel.add(resultatFörbrukning);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        DocumentListener updater = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateResults();
            }

            public void removeUpdate(DocumentEvent e) {
                updateResults();
            }

            public void changedUpdate(DocumentEvent e) {
                updateResults();
            }
        };
        nyMätarFält.getDocument().addDocumentListener(updater);
        gammalMätarFält.getDocument().addDocumentListener(updater);
        literFält.getDocument().addDocumentListener(updater);

        frame.pack();
        frame.setVisible(true);
    }

    private void updateResults() {
        try {
            int ny = Integer.parseInt(nyMätarFält.getText().trim());
            int gammal = Integer.parseInt(gammalMätarFält.getText().trim());
            int liter = Integer.parseInt(literFält.getText().trim());

            setNyMätarställning(ny);
            setGammalMätarställning(gammal);
            setAntalLiter(liter);

            resultatMil.setText("Antal körda mil: " + getAntalMil());
            resultatFörbrukning.setText(
                    String.format("Förbrukning per mil: %.2f", getFörbrukingPerMil())
            );
            resultatLiter.setText("Antal liter bensin: " + getAntalLiter());
        } catch (NumberFormatException e) {
            resultatMil.setText("Antal körda mil: -");
            resultatLiter.setText("Antal liter bensin: -");
            resultatFörbrukning.setText("Förbrukning per mil: -");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BensinräknarGUI::new);
    }
}