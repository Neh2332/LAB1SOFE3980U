package com.ontariotechu.sofe3980U;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first binary number:");
        String bin1 = scanner.nextLine().trim();
        System.out.println("Enter the second binary number:");
        String bin2 = scanner.nextLine().trim();

        Binary binary1 = new Binary(bin1);
        Binary binary2 = new Binary(bin2);

        System.out.println("Binary 1: " + binary1.getValue());
        System.out.println("Binary 2: " + binary2.getValue());

       
        Binary orResult = Binary.or(binary1, binary2);
        Binary andResult = Binary.and(binary1, binary2);
        Binary multiplyResult = Binary.multiply(binary1, binary2);
        

        System.out.println("OR: " + orResult.getValue());
        System.out.println("AND: " + andResult.getValue());
        System.out.println("Multiply: " + multiplyResult.getValue());

        scanner.close();
    }
}