package myProjects;
import java.util.Scanner;
import java.util.Random;
public class Matrices {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int matrixSize = 7;
    static String[][] battleMap = new String[matrixSize][matrixSize];
    static String emptyCell = " [  ] ";

    static void InitializeBattleMap() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                battleMap[i][j] = emptyCell;
            }
        }
    }
    
    public static boolean canPlaceShip(String[][] battleMap, int rowRandom, int columnRandom, int shipSize, boolean horizontalOrVertical) {
    int size = battleMap.length;
        if (horizontalOrVertical) {
            if (columnRandom + shipSize > size) return false;
            for (int i = 0; i < shipSize; i++) {
                if (battleMap[rowRandom][columnRandom + i].equals("1xSHIP")) return false;
                else if (battleMap[rowRandom][columnRandom + i].equals("2xSHIP")) return false;
                else if (battleMap[rowRandom][columnRandom + i].equals("3xSHIP")) return false;
            }
            if (!isAreaClear(battleMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) return false;
        }
        else {
            if (rowRandom + shipSize > size) return false;
            for (int i = 0; i < shipSize; i++) {
                if (battleMap[rowRandom + i][columnRandom].equals("1xSHIP")) return false;
                else if (battleMap[rowRandom + i][columnRandom].equals("2xSHIP")) return false;
                else if (battleMap[rowRandom + i][columnRandom].equals("3xSHIP")) return false;
            }
            if (!isAreaClear(battleMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) return false;
        }
        return true;
    }

    static void printBattleMap() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(battleMap[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public static boolean isAreaClear(String[][] battleMap, int rowRandom, int columnRandom, int shipSize, boolean horizontalOrVertical) {
        int size = battleMap.length;
        if (horizontalOrVertical) {
            for (int i = -1; i <= shipSize; i++) {
                for (int j = -1; j <= 1; j++) {
                    int checkRow = rowRandom + j;
                    int checkCol = columnRandom + i;

                    if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("1xSHIP")) {
                        return false;
                    }
                    else if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("2xSHIP")) {
                        return false;
                    }
                    else if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("3xSHIP")) {
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

                    if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("1xSHIP")) {
                        return false;
                    }
                    if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("2xSHIP")) {
                        return false;
                    }
                    if (isInsideBoard(checkRow, checkCol, size) && battleMap[checkRow][checkCol].equals("3xSHIP")) {
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
        boolean placed = false;
        while(!placed) {
            int columnRandom = rand.nextInt(matrixSize);
            int rowRandom = rand.nextInt(matrixSize);
            boolean horizontalOrVertical = rand.nextBoolean();
            if (canPlaceShip(battleMap, rowRandom, columnRandom, shipSize, horizontalOrVertical)) {
                for (int i = 0; i < shipSize; i++) {
                    if (horizontalOrVertical) {
                        if (shipSize == 1) {
                            battleMap[rowRandom][columnRandom + i] = "1xSHIP";
                        } else if (shipSize == 2) {
                            battleMap[rowRandom][columnRandom + i] = "2xSHIP";
                        } else if (shipSize == 3) {
                            battleMap[rowRandom][columnRandom + i] = "3xSHIP";
                        }
                    } else {
                        if (shipSize == 1) {
                            battleMap[rowRandom + i][columnRandom] = "1xSHIP";
                        }
                        if (shipSize == 2) {
                            battleMap[rowRandom + i][columnRandom] = "2xSHIP";
                        }
                        if (shipSize == 3) {
                            battleMap[rowRandom + i][columnRandom] = "3xSHIP";
                        }
                    }
                    placed = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("!HINT!");
        System.out.println(" ");
        InitializeBattleMap();
        placeShips(3);
        placeShips(2);
        placeShips(2);
        placeShips(1);
        placeShips(1);
        placeShips(1);
        placeShips(1);
        printBattleMap();
        System.out.println("!HINT!");
        System.out.println(" ");
        System.out.print("Welcome to the Battle Map. Please enter your name: ");
        String username = sc.nextLine();
    }
}



