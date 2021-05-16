package life;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GameOfLife();
            }
        });
    }

}

