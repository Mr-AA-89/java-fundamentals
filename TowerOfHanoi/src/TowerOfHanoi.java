//Tower of Hanoi - Patrick Kubiak - 10/28/2014
//Solves the Tower of Hanoi.

import java.util.Scanner;

public class TowerOfHanoi {
    private static Scanner input = new Scanner(System.in);
    private static int numDisks, start, end, temp;

    public static void main(String[] args)
    {
        System.out.println("\fWelcome to the Tower of Hanoi Program.");
        System.out.print("Enter the number of disks: ");
        numDisks = input.nextInt();
        System.out.print("Enter the starting position: ");
        start = input.nextInt();
        System.out.print("Enter the ending position: ");
        end = input.nextInt();
        System.out.print("Enter the temporary position: ");
        temp = input.nextInt();

        System.out.println("Solution:");
        move(numDisks, start, end, temp);
    }

    public static void move(int n, int start, int end, int temp)
    {
        if (n >= 1)
        {
            move(n - 1, start, temp, end);
            System.out.println("MOVE " + start + " TO " + end);
            move(n - 1, temp, end, start);
        }
    }
}
