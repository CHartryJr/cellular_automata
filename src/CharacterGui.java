import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * @author ChatGPT
 */
public class CharacterGui 
{
    private static final int MAX_ARRAY_SIZE = 285;
    private static final int FRAME_RATE = 25;
    private static final int MAX_ARRAY_COUNT = 1000;
    private static char pixel = '*';
    private JFrame frame;
    private JTextArea displayArea;
    private JButton playStopButton;
    private JTextField ruleInput;
    private boolean isPlaying = false;
    private Timer timer;
    private int [] startGen;
    private Wolfram ca;
    private List<char[]> arrays;

   
    public CharacterGui() {
        startGen = new int[MAX_ARRAY_SIZE / 2];
        startGen[MAX_ARRAY_SIZE / 4] = 1;
        ca = new Wolfram();
        arrays = new ArrayList<>();
        initializeGUI();
        setupTimer();
    }

    private void initializeGUI() {
        frame = new JFrame("Character Array Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());


        // Display Area
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        playStopButton = new JButton("Play");
        playStopButton.addActionListener(e -> togglePlayStop());
        controlPanel.add(playStopButton);

        ruleInput = new JTextField("Enter Rule", 20);
        controlPanel.add(ruleInput);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void setupTimer() {
        timer = new Timer(1000 / FRAME_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    generateAndDisplayArray();
                }
            }
        });
    }

    private void togglePlayStop() {
        isPlaying = !isPlaying;
        playStopButton.setText(isPlaying ? "Stop" : "Play");
        if (isPlaying) {
            timer.start();
        } else {
            timer.stop();
        }
    }

    private void generateAndDisplayArray() {
        char[] newArray = generateCharacterArray();
        arrays.add(newArray);
        displayArea.append(new String(newArray) + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength());

        if (arrays.size() > MAX_ARRAY_COUNT) {
            arrays.clear();
            displayArea.setText("");
        }
    }

    private char[] generateCharacterArray() 
    {
        if (!isNumber(ruleInput.getText()))
            return normalize(startGen);
        int newRule = Integer.parseInt(ruleInput.getText());
        if (newRule != ca.getRule()) 
        {
            if (newRule < 0 || newRule > 255) 
            {
                return normalize(startGen); 
            }
            startGen = new int[MAX_ARRAY_SIZE / 2];
            startGen[MAX_ARRAY_SIZE / 4] = 1;
            ca.setRule(newRule);
        }
        startGen = ca.generate(startGen);
        return normalize(startGen);
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static char[] normalize(int[] arr) {
        char[] normVector = new char[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1)
                normVector[i] = pixel;
            else
                normVector[i] = ' ';
        }
        return normVector;
    } 
}