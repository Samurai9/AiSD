package AiSD.Task6;

import java.util.Scanner;

public class Karatsuba10 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter first number");
        long firstNum = scan.nextLong();
        System.out.println("Enter second number");
        long secondNumber = scan.nextLong();
        long result = multiplication(firstNum, secondNumber);
        System.out.println(result);
    }

    private static long multiplication(long first, long second) {
        if (first < 10 || second < 10){
            return first * second;
        }

        int maxLength = Math.max(String.valueOf(first).length(), String.valueOf(second).length());

        long a = getFirstPart(first);
        long b = getSecondPart(first);
        long c = getFirstPart(second);
        long d = getSecondPart(second);

        long firstOper = multiplication(a, c);
        long secondOper = multiplication(b, d);
        long thirdOper = multiplication(a+b, c+d);

        return (long) (firstOper*Math.pow(10, maxLength) + (thirdOper-secondOper-firstOper) * Math.pow(10, maxLength/2) + secondOper);
    }

    private static long getFirstPart(long number){
        String tempString = Long.toString(number);
        return Long.parseLong(tempString.substring(0, tempString.length()/2));
    }

    private static long getSecondPart(long number){
        String tempString = Long.toString(number);
        return Long.parseLong(tempString.substring(tempString.length()/2, tempString.length()));
    }
}
