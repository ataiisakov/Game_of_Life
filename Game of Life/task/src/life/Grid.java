package life;

import java.util.Random;

public class Grid {
    private int size;

    private boolean[][] currGen;
    private boolean[][] nextGen;
    private Random random;

    public Grid(int size) {
        this.size = size;
        this.random = new Random();
        random.setSeed(random.nextLong());
        this.currGen = new boolean[size][size];
        this.nextGen = new boolean[size][size];
        init();
    }
    public void reset(){
        init();
    }
    private void init() {
        //glider
//        glider();
        //random
        randomCells();
    }
    private void randomCells(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currGen[i][j] = random.nextBoolean();
            }
        }
    }

    private void glider(){
        currGen[3][0] = true;
        currGen[3][1] = true;
        currGen[3][2] = true;
        currGen[2][2] = true;
        currGen[1][1] = true;
    }

    private void copy(boolean[][] currGen, boolean[][] nextGen) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currGen[i][j] = nextGen[i][j];
            }
        }
    }

    private int countNeighbors(int y, int x) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int col = (y + i + size) % size;
                int row = (x + j + size) % size;
                if (currGen[col][row]) {
                    count++;
                }
            }
        }

        return count;
    }

    public void computeNextGeneration() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int count = countNeighbors(i, j);
                if (!currGen[i][j] && count == 3) {
                    nextGen[i][j] = true;
                } else if (currGen[i][j] && (count < 2 || count > 3)) {
                    nextGen[i][j] = false;
                } else if (currGen[i][j] && (count == 3 || count == 2)) {
                    nextGen[i][j] = true;
                }

            }
        }
        copy(currGen, nextGen);
    }

    private void show(boolean[][] grid) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean[][] getCurrGen() {
        return currGen;
    }
    public int numOfAlive(){
        int countAlive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currGen[i][j]) {
                    countAlive++;
                }
            }
        }
        return countAlive;
    }

    public int getSize() {
        return size;
    }


}
