package myProjects;
import java.util.Scanner;
import java.util.Random;
public class Matrices {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int matrixSize = 7;
    static String[][] computerMap = new String[matrixSize][matrixSize];        //matrix of computer
    static String[][] userMap = new String[matrixSize][matrixSize];         //matrix of user
    static String username;
    static int amountOfDecks = 0;
    static String oneDeckShip = "1xSHIP";
    static String twoDeckShip = "2xSHIP";
    static String threeDeckShip = "3xSHIP";

    public static void callIntroduction(String username){
        System.out.print("Welcome to the Battle Map. Please enter your name: ");
        username = sc.nextLine();
        System.out.println("Hello " + username + ". Let's play sea battle! ");
        System.out.println(" ");
    }

    static void InitializeBattleMap() {
        String emptyCell = " [  ] ";
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                computerMap[i][j] = emptyCell;
            }
        }
    }

    public static boolean canPlaceShip(String[][] enemyMap, int rowRandom, int columnRandom, int shipSize, boolean horizontalOrVertical) {
    int size = enemyMap.length;
        if (horizontalOrVertical) {
            if (columnRandom + shipSize > size) return false;
            for (int i = 0; i < shipSize; i++) {
                if (enemyMap[rowRandom][columnRandom + i].equals(oneDeckShip)) return false;
                else if (enemyMap[rowRandom][columnRandom + i].equals(twoDeckShip)) return false;
                else if (enemyMap[rowRandom][columnRandom + i].equals(threeDeckShip)) return false;
            }
            if (!isAreaClear(enemyMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) return false;
        }
        else {
            if (rowRandom + shipSize > size) return false;
            for (int i = 0; i < shipSize; i++) {
                if (enemyMap[rowRandom + i][columnRandom].equals(oneDeckShip)) return false;
                else if (enemyMap[rowRandom + i][columnRandom].equals(twoDeckShip)) return false;
                else if (enemyMap[rowRandom + i][columnRandom].equals(threeDeckShip)) return false;
            }
            if (!isAreaClear(enemyMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) return false;
        }
        return true;
    }

    static void printComputerMap() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(computerMap[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static boolean isAreaClear(String[][] enemyMap, int rowRandom, int columnRandom, int shipSize, boolean horizontalOrVertical) {
        int size = enemyMap.length;
        if (horizontalOrVertical) {
            for (int i = -1; i <= shipSize; i++) {
                for (int j = -1; j <= 1; j++) {
                    int checkRow = rowRandom + j;
                    int checkCol = columnRandom + i;

                    if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(oneDeckShip)) {
                        return false;
                    }
                    else if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(twoDeckShip)) {
                        return false;
                    }
                    else if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(threeDeckShip)) {
                        return false;
                    }
                }
            }
        }
        else {
            for (int i = -1; i <= shipSize; i++) {
                for (int j = -1; j <= 1; j++) {
                    int checkRow = rowRandom + i;
                    int checkCol = columnRandom + j;

                    if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(oneDeckShip)) {
                        return false;
                    }
                    if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(twoDeckShip)) {
                        return false;
                    }
                    if (isInsideBoard(checkRow, checkCol, size) && enemyMap[checkRow][checkCol].equals(threeDeckShip)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isInsideBoard(int rowRandom, int columnRandom, int size) {
        return rowRandom >= 0 && rowRandom < size && columnRandom >= 0 && columnRandom < size;
    }

    static void placeShips(int shipSize){
        amountOfDecks += shipSize;
        boolean placed = false;
        while(!placed) {
            int columnRandom = rand.nextInt(matrixSize);
            int rowRandom = rand.nextInt(matrixSize);
            boolean horizontalOrVertical = rand.nextBoolean();
            if (canPlaceShip(computerMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) {
                for (int i = 0; i < shipSize; i++) {
                    if (horizontalOrVertical) {
                        if (shipSize == 1) {
                            computerMap[rowRandom][columnRandom + i] = oneDeckShip;
                        } else if (shipSize == 2) {
                            computerMap[rowRandom][columnRandom + i] = twoDeckShip;
                        } else if (shipSize == 3) {
                            computerMap[rowRandom][columnRandom + i] = threeDeckShip;
                        }
                    } else {
                        if (shipSize == 1) {
                            computerMap[rowRandom + i][columnRandom] = oneDeckShip;
                        }
                        if (shipSize == 2) {
                            computerMap[rowRandom + i][columnRandom] = twoDeckShip;
                        }
                        if (shipSize == 3) {
                            computerMap[rowRandom + i][columnRandom] = threeDeckShip;
                        }
                    }
                    placed = true;
                }
            }
        }
    }

    static void printUserMap() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(userMap[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }

    static void giveAShot() {
         String hit = " SHOT ";
         String shipIsDead = " DROWN";
         String miss = "  XX  ";
         String shot = sc.nextLine();
        if (shot.equals("a1")) {
            if (computerMap[0][0].equals(twoDeckShip) || computerMap[0][0].equals(threeDeckShip)) {
                userMap[0][0] = hit;
                amountOfDecks--;
            } else if (computerMap[0][0].equals(oneDeckShip)) {
                userMap[0][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][0] = miss;
            }
        }
        if (shot.equals("a2")) {
            if (computerMap[0][1].equals(twoDeckShip) || computerMap[0][1].equals(threeDeckShip)) {
                userMap[0][1] = hit;
                amountOfDecks--;
            } else if (computerMap[0][1].equals(oneDeckShip)) {
                userMap[0][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][1] = miss;
            }
        }
        if (shot.equals("a3")) {
            if (computerMap[0][2].equals(twoDeckShip) || computerMap[0][2].equals(threeDeckShip)) {
                userMap[0][2] = hit;
                amountOfDecks--;
            } else if (computerMap[0][2].equals(oneDeckShip)) {
                userMap[0][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][2] = miss;
            }
        }
        if (shot.equals("a4")) {
            if (computerMap[0][3].equals(twoDeckShip) || computerMap[0][3].equals(threeDeckShip)) {
                userMap[0][3] = hit;
                amountOfDecks--;
            } else if (computerMap[0][3].equals(oneDeckShip)) {
                userMap[0][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][3] = miss;
            }
        }
        if (shot.equals("a5")) {
            if (computerMap[0][4].equals(twoDeckShip) || computerMap[0][4].equals(threeDeckShip)) {
                userMap[0][4] = hit;
                amountOfDecks--;
            } else if (computerMap[0][4].equals(oneDeckShip)) {
                userMap[0][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][4] = miss;
            }
        }
        if (shot.equals("a6")) {
            if (computerMap[0][5].equals(twoDeckShip) || computerMap[0][5].equals(threeDeckShip)) {
                userMap[0][5] = hit;
                amountOfDecks--;
            } else if (computerMap[0][5].equals(oneDeckShip)) {
                userMap[0][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][5] = miss;
            }
        }
        if (shot.equals("a7")) {
            if (computerMap[0][6].equals(twoDeckShip) || computerMap[0][6].equals(threeDeckShip)) {
                userMap[0][6] = hit;
                amountOfDecks--;
            } else if (computerMap[0][6].equals(oneDeckShip)) {
                userMap[0][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[0][6] = miss;
            }
        }
        if (shot.equals("b1")) {
            if (computerMap[1][0].equals(twoDeckShip) || computerMap[1][0].equals(threeDeckShip)) {
                userMap[1][0] = hit;
                amountOfDecks--;
            } else if (computerMap[1][0].equals(oneDeckShip)) {
                userMap[1][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][0] = miss;
            }
        }
        if (shot.equals("b2")) {
            if (computerMap[1][1].equals(twoDeckShip) || computerMap[1][1].equals(threeDeckShip)) {
                userMap[1][1] = hit;
                amountOfDecks--;
            } else if (computerMap[1][1].equals(oneDeckShip)) {
                userMap[1][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][1] = miss;
            }
        }
        if (shot.equals("b3")) {
            if (computerMap[1][2].equals(twoDeckShip) || computerMap[1][2].equals(threeDeckShip)) {
                userMap[1][2] = hit;
                amountOfDecks--;
            } else if (computerMap[1][2].equals(oneDeckShip)) {
                userMap[1][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][2] = miss;
            }
        }
        if (shot.equals("b4")) {
            if (computerMap[1][3].equals(twoDeckShip) || computerMap[1][3].equals(threeDeckShip)) {
                userMap[1][3] = hit;
                amountOfDecks--;
            } else if (computerMap[1][3].equals(oneDeckShip)) {
                userMap[1][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][3] = miss;
            }
        }
        if (shot.equals("b5")) {
            if (computerMap[1][4].equals(twoDeckShip) || computerMap[1][4].equals(threeDeckShip)) {
                userMap[1][4] = hit;
                amountOfDecks--;
            } else if (computerMap[1][4].equals(oneDeckShip)) {
                userMap[1][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][4] = miss;
            }
        }
        if (shot.equals("b6")) {
            if (computerMap[1][5].equals(twoDeckShip) || computerMap[1][5].equals(threeDeckShip)) {
                userMap[1][5] = hit;
                amountOfDecks--;
            } else if (computerMap[1][5].equals(oneDeckShip)) {
                userMap[1][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][5] = miss;
            }
        }
        if (shot.equals("b7")) {
            if (computerMap[1][6].equals(twoDeckShip) || computerMap[1][6].equals(threeDeckShip)) {
                userMap[1][6] = hit;
                amountOfDecks--;
            } else if (computerMap[1][6].equals(oneDeckShip)) {
                userMap[1][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[1][6] = miss;
            }
        }
        if (shot.equals("c1")) {
            if (computerMap[2][0].equals(twoDeckShip) || computerMap[2][0].equals(threeDeckShip)) {
                userMap[2][0] = hit;
                amountOfDecks--;
            } else if (computerMap[2][0].equals(oneDeckShip)) {
                userMap[2][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][0] = miss;
            }
        }
        if (shot.equals("c2")) {
            if (computerMap[2][1].equals(twoDeckShip) || computerMap[2][1].equals(threeDeckShip)) {
                userMap[2][1] = hit;
                amountOfDecks--;
            } else if (computerMap[2][1].equals(oneDeckShip)) {
                userMap[2][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][1] = miss;
            }
        }
        if (shot.equals("c3")) {
            if (computerMap[2][2].equals(twoDeckShip) || computerMap[2][2].equals(threeDeckShip)) {
                userMap[2][2] = hit;
                amountOfDecks--;
            } else if (computerMap[2][2].equals(oneDeckShip)) {
                userMap[2][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][2] = miss;
            }
        }
        if (shot.equals("c4")) {
            if (computerMap[2][3].equals(twoDeckShip) || computerMap[2][3].equals(threeDeckShip)) {
                userMap[2][3] = hit;
                amountOfDecks--;
            } else if (computerMap[2][3].equals(oneDeckShip)) {
                userMap[2][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][3] = miss;
            }
        }
        if (shot.equals("c5")) {
            if (computerMap[2][4].equals(twoDeckShip) || computerMap[2][4].equals(threeDeckShip)) {
                userMap[2][4] = hit;
                amountOfDecks--;
            } else if (computerMap[2][4].equals(oneDeckShip)) {
                userMap[2][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][4] = miss;
            }
        }
        if (shot.equals("c6")) {
            if (computerMap[2][5].equals(twoDeckShip) || computerMap[2][5].equals(threeDeckShip)) {
                userMap[2][5] = hit;
                amountOfDecks--;
            } else if (computerMap[2][5].equals(oneDeckShip)) {
                userMap[2][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][5] = miss;
            }
        }
        if (shot.equals("c7")) {
            if (computerMap[2][6].equals(twoDeckShip) || computerMap[2][6].equals(threeDeckShip)) {
                userMap[2][6] = hit;
                amountOfDecks--;
            } else if (computerMap[2][6].equals(oneDeckShip)) {
                userMap[2][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[2][6] = miss;
            }
        }
        if (shot.equals("d1")) {
            if (computerMap[3][0].equals(twoDeckShip) || computerMap[3][0].equals(threeDeckShip)) {
                userMap[3][0] = hit;
                amountOfDecks--;
            } else if (computerMap[3][0].equals(oneDeckShip)) {
                userMap[3][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][0] = miss;
            }
        }
        if (shot.equals("d2")) {
            if (computerMap[3][1].equals(twoDeckShip) || computerMap[3][1].equals(threeDeckShip)) {
                userMap[3][1] = hit;
                amountOfDecks--;
            } else if (computerMap[3][1].equals(oneDeckShip)) {
                userMap[3][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][1] = miss;
            }
        }
        if (shot.equals("d3")) {
            if (computerMap[3][2].equals(twoDeckShip) || computerMap[3][2].equals(threeDeckShip)) {
                userMap[3][2] = hit;
                amountOfDecks--;
            } else if (computerMap[3][2].equals(oneDeckShip)) {
                userMap[3][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][2] = miss;
            }
        }
        if (shot.equals("d4")) {
            if (computerMap[3][3].equals(twoDeckShip) || computerMap[3][3].equals(threeDeckShip)) {
                userMap[3][3] = hit;
                amountOfDecks--;
            } else if (computerMap[3][3].equals(oneDeckShip)) {
                userMap[3][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][3] = miss;
            }
        }
        if (shot.equals("d5")) {
            if (computerMap[3][4].equals(twoDeckShip) || computerMap[3][4].equals(threeDeckShip)) {
                userMap[3][4] = hit;
                amountOfDecks--;
            } else if (computerMap[3][4].equals(oneDeckShip)) {
                userMap[3][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][4] = miss;
            }
        }
        if (shot.equals("d6")) {
            if (computerMap[3][5].equals(twoDeckShip) || computerMap[3][5].equals(threeDeckShip)) {
                userMap[3][5] = hit;
                amountOfDecks--;
            } else if (computerMap[3][5].equals(oneDeckShip)) {
                userMap[3][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][5] = miss;
            }
        }
        if (shot.equals("d7")) {
            if (computerMap[3][6].equals(twoDeckShip) || computerMap[3][6].equals(threeDeckShip)) {
                userMap[3][6] = hit;
                amountOfDecks--;
            } else if (computerMap[3][6].equals(oneDeckShip)) {
                userMap[3][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[3][6] = miss;
            }
        }
        if (shot.equals("e1")) {
            if (computerMap[4][0].equals(twoDeckShip) || computerMap[4][0].equals(threeDeckShip)) {
                userMap[4][0] = hit;
                amountOfDecks--;
            } else if (computerMap[4][0].equals(oneDeckShip)) {
                userMap[4][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][0] = miss;
            }
        }
        if (shot.equals("e2")) {
            if (computerMap[4][1].equals(twoDeckShip) || computerMap[4][1].equals(threeDeckShip)) {
                userMap[4][1] = hit;
                amountOfDecks--;
            } else if (computerMap[4][1].equals(oneDeckShip)) {
                userMap[4][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][1] = miss;
            }
        }
        if (shot.equals("e3")) {
            if (computerMap[4][2].equals(twoDeckShip) || computerMap[4][2].equals(threeDeckShip)) {
                userMap[4][2] = hit;
                amountOfDecks--;
            } else if (computerMap[4][2].equals(oneDeckShip)) {
                userMap[4][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][2] = miss;
            }
        }
        if (shot.equals("e4")) {
            if (computerMap[4][3].equals(twoDeckShip) || computerMap[4][3].equals(threeDeckShip)) {
                userMap[4][3] = hit;
                amountOfDecks--;
            } else if (computerMap[4][3].equals(oneDeckShip)) {
                userMap[4][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][3] = miss;
            }
        }
        if (shot.equals("e5")) {
            if (computerMap[4][4].equals(twoDeckShip) || computerMap[4][4].equals(threeDeckShip)) {
                userMap[4][4] = hit;
                amountOfDecks--;
            } else if (computerMap[4][4].equals(oneDeckShip)) {
                userMap[4][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][4] = miss;
            }
        }
        if (shot.equals("e6")) {
            if (computerMap[4][5].equals(twoDeckShip) || computerMap[4][5].equals(threeDeckShip)) {
                userMap[4][5] = hit;
                amountOfDecks--;
            } else if (computerMap[4][5].equals(oneDeckShip)) {
                userMap[4][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][5] = miss;
            }
        }
        if (shot.equals("e7")) {
            if (computerMap[4][6].equals(twoDeckShip) || computerMap[4][6].equals(threeDeckShip)) {
                userMap[4][6] = hit;
                amountOfDecks--;
            } else if (computerMap[4][6].equals(oneDeckShip)) {
                userMap[4][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[4][6] = miss;
            }
        }
        if (shot.equals("f1")) {
            if (computerMap[5][0].equals(twoDeckShip) || computerMap[5][0].equals(threeDeckShip)) {
                userMap[5][0] = hit;
                amountOfDecks--;
            } else if (computerMap[5][0].equals(oneDeckShip)) {
                userMap[5][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][0] = miss;
            }
        }
        if (shot.equals("f2")) {
            if (computerMap[5][1].equals(twoDeckShip) || computerMap[5][1].equals(threeDeckShip)) {
                userMap[5][1] = hit;
                amountOfDecks--;
            } else if (computerMap[5][1].equals(oneDeckShip)) {
                userMap[5][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][1] = miss;
            }
        }
        if (shot.equals("f3")) {
            if (computerMap[5][2].equals(twoDeckShip) || computerMap[5][2].equals(threeDeckShip)) {
                userMap[5][2] = hit;
                amountOfDecks--;
            } else if (computerMap[5][2].equals(oneDeckShip)) {
                userMap[5][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][2] = miss;
            }
        }
        if (shot.equals("f4")) {
            if (computerMap[5][3].equals(twoDeckShip) || computerMap[5][3].equals(threeDeckShip)) {
                userMap[5][3] = hit;
                amountOfDecks--;
            } else if (computerMap[5][3].equals(oneDeckShip)) {
                userMap[5][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][3] = miss;
            }
        }
        if (shot.equals("f5")) {
            if (computerMap[5][4].equals(twoDeckShip) || computerMap[5][4].equals(threeDeckShip)) {
                userMap[5][4] = hit;
                amountOfDecks--;
            } else if (computerMap[5][4].equals(oneDeckShip)) {
                userMap[5][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][4] = miss;
            }
        }
        if (shot.equals("f6")) {
            if (computerMap[5][5].equals(twoDeckShip) || computerMap[5][5].equals(threeDeckShip)) {
                userMap[5][5] = hit;
                amountOfDecks--;
            } else if (computerMap[5][5].equals(oneDeckShip)) {
                userMap[5][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][5] = miss;
            }
        }
        if (shot.equals("f7")) {
            if (computerMap[5][6].equals(twoDeckShip) || computerMap[5][6].equals(threeDeckShip)) {
                userMap[5][6] = hit;
                amountOfDecks--;
            } else if (computerMap[5][6].equals(oneDeckShip)) {
                userMap[5][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[5][6] = miss;
            }
        }
        if (shot.equals("g1")) {
            if (computerMap[6][0].equals(twoDeckShip) || computerMap[6][0].equals(threeDeckShip)) {
                userMap[6][0] = hit;
                amountOfDecks--;
            } else if (computerMap[6][0].equals(oneDeckShip)) {
                userMap[6][0] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][0] = miss;
            }
        }
        if (shot.equals("g2")) {
            if (computerMap[6][1].equals(twoDeckShip) || computerMap[6][1].equals(threeDeckShip)) {
                userMap[6][1] = hit;
                amountOfDecks--;
            } else if (computerMap[6][1].equals(oneDeckShip)) {
                userMap[6][1] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][1] = miss;
            }
        }
        if (shot.equals("g3")) {
            if (computerMap[6][2].equals(twoDeckShip) || computerMap[6][2].equals(threeDeckShip)) {
                userMap[6][2] = hit;
                amountOfDecks--;
            } else if (computerMap[6][2].equals(oneDeckShip)) {
                userMap[6][2] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][2] = miss;
            }
        }
        if (shot.equals("g4")) {
            if (computerMap[6][3].equals(twoDeckShip) || computerMap[6][3].equals(threeDeckShip)) {
                userMap[6][3] = hit;
                amountOfDecks--;
            } else if (computerMap[6][3].equals(oneDeckShip)) {
                userMap[6][3] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][3] = miss;
            }
        }
        if (shot.equals("g5")) {
            if (computerMap[6][4].equals(twoDeckShip) || computerMap[6][4].equals(threeDeckShip)) {
                userMap[6][4] = hit;
                amountOfDecks--;
            } else if (computerMap[6][4].equals(oneDeckShip)) {
                userMap[6][4] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][4] = miss;
            }
        }
        if (shot.equals("g6")) {
            if (computerMap[6][5].equals(twoDeckShip) || computerMap[6][5].equals(threeDeckShip)) {
                userMap[6][5] = hit;
                amountOfDecks--;
            } else if (computerMap[6][5].equals(oneDeckShip)) {
                userMap[6][5] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][5] = miss;
            }
        }
        if (shot.equals("g7")) {
            if (computerMap[6][6].equals(twoDeckShip) || computerMap[6][6].equals(threeDeckShip)) {
                userMap[6][6] = hit;
                amountOfDecks--;
            } else if (computerMap[6][6].equals(oneDeckShip)) {
                userMap[6][6] = shipIsDead;
                amountOfDecks--;
            } else {
                userMap[6][6] = miss;
            }
        }
    }

    public static boolean areAllShipsShotted (int amountOfDecks){
        if(amountOfDecks == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        InitializeBattleMap();
        computerMap[0][0] = " [a1] ";computerMap[0][1] = " [a2] ";computerMap[0][2] = " [a3] ";computerMap[0][3] = " [a4] ";computerMap[0][4] = " [a5] ";computerMap[0][5] = " [a6] ";computerMap[0][6] = " [a7] ";
        computerMap[1][0] = " [b1] ";computerMap[1][1] = " [b2] ";computerMap[1][2] = " [b3] ";computerMap[1][3] = " [b4] ";computerMap[1][4] = " [b5] ";computerMap[1][5] = " [b6] ";computerMap[1][6] = " [b7] ";
        computerMap[2][0] = " [c1] ";computerMap[2][1] = " [c2] ";computerMap[2][2] = " [c3] ";computerMap[2][3] = " [c4] ";computerMap[2][4] = " [c5] ";computerMap[2][5] = " [c6] ";computerMap[2][6] = " [c7] ";
        computerMap[3][0] = " [d1] ";computerMap[3][1] = " [d2] ";computerMap[3][2] = " [d3] ";computerMap[3][3] = " [d4] ";computerMap[3][4] = " [d5] ";computerMap[3][5] = " [d6] ";computerMap[3][6] = " [d7] ";
        computerMap[4][0] = " [e1] ";computerMap[4][1] = " [e2] ";computerMap[4][2] = " [e3] ";computerMap[4][3] = " [e4] ";computerMap[4][4] = " [e5] ";computerMap[4][5] = " [e6] ";computerMap[4][6] = " [e7] ";
        computerMap[5][0] = " [f1] ";computerMap[5][1] = " [f2] ";computerMap[5][2] = " [f3] ";computerMap[5][3] = " [f4] ";computerMap[5][4] = " [f5] ";computerMap[5][5] = " [f6] ";computerMap[5][6] = " [f7] ";
        computerMap[6][0] = " [g1] ";computerMap[6][1] = " [g2] ";computerMap[6][2] = " [g3] ";computerMap[6][3] = " [g4] ";computerMap[6][4] = " [g5] ";computerMap[6][5] = " [g6] ";computerMap[6][6] = " [g7] ";

        userMap[0][0] = " [a1] ";userMap[0][1] = " [a2] ";userMap[0][2] = " [a3] ";userMap[0][3] = " [a4] ";userMap[0][4] = " [a5] ";userMap[0][5] = " [a6] ";userMap[0][6] = " [a7] ";
        userMap[1][0] = " [b1] ";userMap[1][1] = " [b2] ";userMap[1][2] = " [b3] ";userMap[1][3] = " [b4] ";userMap[1][4] = " [b5] ";userMap[1][5] = " [b6] ";userMap[1][6] = " [b7] ";
        userMap[2][0] = " [c1] ";userMap[2][1] = " [c2] ";userMap[2][2] = " [c3] ";userMap[2][3] = " [c4] ";userMap[2][4] = " [c5] ";userMap[2][5] = " [c6] ";userMap[2][6] = " [c7] ";
        userMap[3][0] = " [d1] ";userMap[3][1] = " [d2] ";userMap[3][2] = " [d3] ";userMap[3][3] = " [d4] ";userMap[3][4] = " [d5] ";userMap[3][5] = " [d6] ";userMap[3][6] = " [d7] ";
        userMap[4][0] = " [e1] ";userMap[4][1] = " [e2] ";userMap[4][2] = " [e3] ";userMap[4][3] = " [e4] ";userMap[4][4] = " [e5] ";userMap[4][5] = " [e6] ";userMap[4][6] = " [e7] ";
        userMap[5][0] = " [f1] ";userMap[5][1] = " [f2] ";userMap[5][2] = " [f3] ";userMap[5][3] = " [f4] ";userMap[5][4] = " [f5] ";userMap[5][5] = " [f6] ";userMap[5][6] = " [f7] ";
        userMap[6][0] = " [g1] ";userMap[6][1] = " [g2] ";userMap[6][2] = " [g3] ";userMap[6][3] = " [g4] ";userMap[6][4] = " [g5] ";userMap[6][5] = " [g6] ";userMap[6][6] = " [g7] ";

            System.out.println("!HINT!");
            System.out.println(" ");
            placeShips(3);
            placeShips(2);
            placeShips(2);
            placeShips(1);
            placeShips(1);
            placeShips(1);
            placeShips(1);
            printComputerMap();
            System.out.println("!HINT!");
            System.out.println(" ");
            int userScore = 0;
            callIntroduction(username);
            while (!areAllShipsShotted(amountOfDecks)) {
                printUserMap();
                giveAShot();
                userScore++;
                clearScreen();
            }
            if (areAllShipsShotted(amountOfDecks)) {
                callCongratulations(userScore);
            }

    }

    public static void callCongratulations(int userScore){
        System.out.println("Congratulations! You won!");
        System.out.println("You shotted: " + userScore + " times.");
    }

    public static void clearScreen() {
        for(int i = 0; i < 15; i++){
            System.out.println(" ");
        }
    }
}



