package uppgift2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class BensinräknarGUI extends Bensinräknare {
    private JFrame frame;
    private JTextField nyMätarFält, gammalMätarFält, literFält;
    private JLabel resultatMil, resultatFörbrukning, resultatLiter;

    public BensinräknarGUI() {
        super(0, 0, 0);

        frame = new JFrame("Bensinräknare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 0));

        // toppen av GUIn
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        nyMätarFält = createSmallTextField();
        gammalMätarFält = createSmallTextField();
        literFält = createSmallTextField();

        // inmatningsfält
        JLabel lblNu = new JLabel("Ange mätarställning nu:");
        JLabel lblGammal = new JLabel("Ange mätarställning för ett år sedan:");
        JLabel lblLiter = new JLabel("Ange antal liter förbrukad bensin:");
        int labelWidth = 240;
        Dimension labelSize = new Dimension(labelWidth, 20);
        lblNu.setPreferredSize(labelSize);
        lblGammal.setPreferredSize(labelSize);
        lblLiter.setPreferredSize(labelSize);

        inputPanel.add(createInputRow(lblNu, nyMätarFält));
        inputPanel.add(createInputRow(lblGammal, gammalMätarFält));
        inputPanel.add(createInputRow(lblLiter, literFält));

        frame.add(inputPanel, BorderLayout.CENTER);

        // botten av GUIn
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 0, 0));
        resultatMil = new JLabel("Antal körda mil: -");
        resultatLiter = new JLabel("Antal liter bensin: -");
        resultatFörbrukning = new JLabel("Förbrukning per mil: -");

        bottomPanel.add(resultatMil);
        bottomPanel.add(resultatLiter);
        bottomPanel.add(resultatFörbrukning);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // så man slipper en knapp för att uppdatera
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
        frame.setSize(420, 160);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private JPanel createInputRow(JLabel label, JTextField field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.add(label);
        row.add(field);
        return row;
    }

    private JTextField createSmallTextField() {
        JTextField field = new JTextField(10);
        field.setFont(field.getFont().deriveFont(12f));
        Dimension d = field.getPreferredSize();
        d.height = 22;
        field.setPreferredSize(d);
        return field;
    }

    private void updateResults() {
        try {
            int ny = Integer.parseInt(nyMätarFält.getText());
            int gammal = Integer.parseInt(gammalMätarFält.getText());
            int liter = Integer.parseInt(literFält.getText());

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