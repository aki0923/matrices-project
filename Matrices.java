package myProjects;
import java.util.Scanner;
import java.util.Random;
public class Matrices {
    static Scanner sc = new Scanner(System.in);
    static String username = sc.nextLine();
    static int size = 7;
    static String [][] map = new String[size][size];



    public static void main(String[] args) {
        map[0][0] = "[a1]"; map[0][1] = "[a2]"; map[0][2] = "[a3]"; map[0][3] = "[a4]"; map[0][4] = "[a5]"; map[0][5] = "[a6]"; map[0][6] = "[a7]";
        map[1][0] = "[b1]"; map[1][1] = "[b2]"; map[1][2] = "[b3]"; map[1][3] = "[b4]"; map[1][4] = "[b5]"; map[1][5] = "[b6]"; map[1][6] = "[b7]";
        map[2][0] = "[c1]"; map[2][1] = "[c2]"; map[2][2] = "[c3]"; map[2][3] = "[c4]"; map[2][4] = "[c5]"; map[2][5] = "[c6]"; map[2][6] = "[c7]";
        map[3][0] = "[d1]"; map[3][1] = "[d2]"; map[3][2] = "[d3]"; map[3][3] = "[d4]"; map[3][4] = "[d5]"; map[3][5] = "[d6]"; map[3][6] = "[d7]";
        map[4][0] = "[e1]"; map[4][1] = "[e2]"; map[4][2] = "[e3]"; map[4][3] = "[e4]"; map[4][4] = "[e5]"; map[4][5] = "[e6]"; map[4][6] = "[e7]";
        map[5][0] = "[f1]"; map[5][1] = "[f2]"; map[5][2] = "[f3]"; map[5][3] = "[f4]"; map[5][4] = "[f5]"; map[5][5] = "[f6]"; map[5][6] = "[f7]";
        map[6][0] = "[g1]"; map[6][1] = "[g2]"; map[6][2] = "[g3]"; map[6][3] = "[g4]"; map[6][4] = "[g5]"; map[6][5] = "[g6]"; map[6][6] = "[g7]";
        placeOneCellShip();
        placeOneCellShip();
        placeOneCellShip();
        printMatrix();
    }


    public static void printMatrix() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++ ) {
                System.out.print(map[i][j] + "    ");
                }
            System.out.println();
            System.out.println();
            }
        }
        public static void placeOneCellShip(){
                 int colRandom = new Random().nextInt(size);
            int rowRandom = new Random().nextInt(size);
            for (int i = 0; i < 7; i++) {
                map[rowRandom][colRandom] = "SHIP";
            }
        }





        }



