import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveLister extends JFrame {
    private JTextArea textArea;
    private JButton startButton, quitButton;

    public RecursiveLister() {
        createUI();
    }

    private void createUI() {
        setTitle("Recursive Lister");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Recursive Directory Lister", JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Text area and scroll pane
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        startButton = new JButton("Start");
        startButton.addActionListener(this::startAction);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startAction(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();
            listFiles(selectedDirectory, 0);
        }
    }

    private void listFiles(File directory, int level) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    textArea.append("Dir: " + file.getName() + "\n");
                    listFiles(file, level + 1); // Recursive call
                } else {
                    textArea.append("File: " + file.getName() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveLister frame = new RecursiveLister();
            frame.setVisible(true);
        });
    }
}
