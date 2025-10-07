package uppgift1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class BildVisare {
    private static int currentIndex = 0;
    private static java.util.List<Path> imageFiles;
    private static JLabel imageLabel;

    static void main() {
        JFrame frame = new JFrame("Bildvisare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 400);

        Path folder = Paths.get("C:/Users/joakim/IdeaProjects/Sprint3/src/uppgift1");
        imageFiles = loadImagesFromDirectory(folder);

        imageLabel = new JLabel("", JLabel.CENTER);
        frame.add(imageLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Växla bild");
        nextButton.addActionListener(_ -> showNextImage());
        frame.add(nextButton, BorderLayout.SOUTH);

        if (!imageFiles.isEmpty()) {
            showImage(currentIndex);
        } else {
            imageLabel.setText("Hittade inga bilder.");
        }

        frame.setVisible(true);
    }

    private static List<Path> loadImagesFromDirectory(Path folder) {
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.{jpg,jpeg,png}")) {
            for (Path entry : stream) {
                files.add(entry);
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }
        return files;
    }

    private static void showNextImage() {
        if (imageFiles.isEmpty()) return;
        currentIndex = (currentIndex + 1) % imageFiles.size();
        showImage(currentIndex);
    }

    private static void showImage(int index) {
        ImageIcon icon = new ImageIcon(imageFiles.get(index).toAbsolutePath().toString());
        Image scaled = icon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));
        imageLabel.setText(null);
    }
}