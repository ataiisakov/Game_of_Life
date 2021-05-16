package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;


public class GameOfLife extends JFrame {
    private final Font font  = new Font(Font.MONOSPACED,Font.PLAIN,12);
    private final MainPanel mainPanel;
    private JLabel genLabel;
    private JLabel aliveLabel;
    private String genText = "Generation #%d";
    private String aliveText = "Alive: %d";
    private JToggleButton playPauseToggleBtn;
    private JButton resetBtn;
    private Grid grid;
    private Timer timer;
    private int numOfGen = 1;
    boolean stop = true;
    private int gridSize = 50;
    private int cellSize = 10;
    int speed = 200;

    public GameOfLife(){

        grid = new Grid(gridSize);
        setTitle("Game of Life");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,650);
        setLocationRelativeTo(null);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,10,500,speed);
        slider.setPaintTicks(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(50);
        JLabel speedMode = new JLabel("Speed mode");
        slider.addChangeListener(this::handleSpeedChange);

        timer = new Timer(speed, this::step);

        genLabel = new JLabel(String.format(genText, numOfGen));
        genLabel.setName("GenerationLabel");

        aliveLabel = new JLabel(String.format(aliveText, grid.numOfAlive()));
        aliveLabel.setName("AliveLabel");

        playPauseToggleBtn = new JToggleButton("Play / Pause");
        playPauseToggleBtn.setName("PlayToggleButton");

        resetBtn = new JButton("Reset");
        resetBtn.setName("ResetButton");

        resetBtn.addActionListener(this::handleReset);
        playPauseToggleBtn.addItemListener(this::handlePlayPause);

        genLabel.setFont(font);
        aliveLabel.setFont(font);

        mainPanel = new MainPanel(cellSize,grid);
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        leftPanel.add(playPauseToggleBtn);
        leftPanel.add(resetBtn);
        leftPanel.add(genLabel);
        leftPanel.add(aliveLabel);
        leftPanel.add(speedMode);
        leftPanel.add(slider);


        add(leftPanel,BorderLayout.WEST);
        add(mainPanel,BorderLayout.CENTER);


        setVisible(true);
    }

    private void handleSpeedChange(ChangeEvent event) {
        speed = ((JSlider)event.getSource()).getValue();
        timer.setDelay(speed);
    }

    private void handleReset(ActionEvent event) {
        if(stop){
            numOfGen = 1;
            grid.reset();
            genLabel.setText(String.format(genText, numOfGen));
            aliveLabel.setText(String.format(aliveText, grid.numOfAlive()));
            mainPanel.repaint();
        }
    }

    private void step(ActionEvent event) {
        genLabel.setText(String.format(genText, numOfGen++));
        aliveLabel.setText(String.format(aliveText, grid.numOfAlive()));
        grid.computeNextGeneration();
        mainPanel.repaint();
    }

    private void handlePlayPause(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();

        if (state == ItemEvent.SELECTED) {
            timer.start();
            stop = false;
        }else {
            timer.stop();
            stop = true;
        }

    }


}
