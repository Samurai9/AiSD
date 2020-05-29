package AiSD.Task6;

import java.util.Arrays;
import java.util.Scanner;

public class KaratsubaV2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter first number (only 1 and 0)");
        String firstNumber = scan.nextLine();
        System.out.println("Enter second number (only 1 and 0)");
        String secondNumber = scan.nextLine();

        boolean[] first = stringToBoolean(firstNumber.toString());
        boolean[] second = stringToBoolean(secondNumber.toString());

        boolean[] result = multiplication(first, second);
        showArray(result, "Result");
    }



    private static boolean[] multiplication(boolean[] first, boolean[] second){
        boolean[] result;
        //TODO Выход из рекурсии
        if(first.length == 1 || second.length == 1){
            result = new boolean[1];
            if(first[0] && second[0]){
                result[0] = true;
            } else{
                result[0] = false;
            }
        } else {

            //Делаем четное количество чисел
            if(first.length % 2 == 1){
                first = addZero(first);
            }
            if(second.length % 2 == 1){
                second = addZero(second);
            }

            //Дописываем незначащие нули, если длины не совпадают
            while (first.length != second.length){
                if (first.length > second.length){
                    second = addZero(second);
                } else {
                    first = addZero(first);
                }
            }

            //Подгоняем до степени двойки
            while (checkPowOfTwo(first.length)){
                first = addZero(first);
            }
            while (checkPowOfTwo(second.length)){
                second = addZero(second);
            }

            //ac*2^n + ((a+b)(c+d)-ac-bd)*2^(n/2) + bd
            boolean[] a = Arrays.copyOfRange(first, 0,first.length/2);
            boolean[] b = Arrays.copyOfRange(first, first.length/2, first.length);
            boolean[] c = Arrays.copyOfRange(second, 0,first.length/2);
            boolean[] d = Arrays.copyOfRange(second, first.length/2, first.length);

            boolean[] prod1 = multiplication(a,c);
            boolean[] prod2 = multiplication(b,d);
            boolean[] prod3 = multiplication(sum(a,b), sum(c,d));

            // ac*2^n
            boolean[] prod1Pow = new boolean[prod1.length + first.length];
            for (int i = 0; i < prod1.length; i++){
                prod1Pow[i] = prod1[i];
            }

            //(adbc-ac-bd)*2^(n/2)
            boolean[] acbd = minus(prod3, prod1);
            boolean[] tempRes = minus(acbd, prod2);
            boolean[] tempResPow = new boolean[tempRes.length + first.length/2];
            for (int i = 0; i < tempRes.length; i++){
                tempResPow[i] = tempRes[i];
            }

            while (prod1Pow.length != tempResPow.length){
                if (prod1Pow.length > tempResPow.length){
                    tempResPow = addZero(tempResPow);
                } else {
                    prod1Pow = addZero(prod1Pow);
                }
            }

            //ac*2^n+(adbc-ac-bd)*2^n
            result = sum(prod1Pow, tempResPow);

            //ac*2^n+(adbc-ac-bd)*2^n + secondOper;

            while (result.length != prod2.length){
                if (result.length > prod2.length){
                    prod2 = addZero(prod2);
                } else {
                    result = addZero(result);
                }
            }

            result = sum(result, prod2);
        }

        return result;
    }



    private static boolean[] sum(boolean[] first, boolean[] second){


        //Дописываем незначащие нули, если длины не совпадают
        while (first.length != second.length){
            if (first.length > second.length){
                second = addZero(second);
            } else {
                first = addZero(first);
            }
        }


        int size = first.length;
        //Если первые два числа - 1, то 1+1 = 10 => размер увеличиться на один
        if (first[0] && second[0]){
            size++;
            first = addZero(first);
            second = addZero(second);
        }
        boolean[] result = new boolean[size];

        //Сама сумма двух boolean массивов
        //Эта переменная для запоминания единицы, при 1+1 = 10
        boolean tempBool = false;
        for (int i = result.length-1; i >= 0; i--) {
            if (!first[i] && !second[i] && !tempBool) { //0+0 и нет единицы
                result[i] = false;
            } else if (!first[i] && !second[i] && tempBool) { //0+0 и есть единица
                result[i] = true;
                tempBool = false;
            } else if ((first[i] && !second[i] && !tempBool) || (!first[i] && second[i] && !tempBool)) { //1+0 || 0+1 и нет единицы
                result[i] = true;
            } else if ((first[i] && !second[i] && tempBool) || (!first[i] && second[i] && tempBool)) { //1+0 || 0+1 и есть единица
                result[i] = false;
                tempBool = true;
            } else if (first[i] && second[i] && !tempBool) { //1+1 и нет единицы
                result[i] = false;
                tempBool = true;
            } else if (first[i] && second[i] && tempBool) {//1+1 и есть единица
                result[i] = true;
                tempBool = true;
            }
        }
        return result;
    }

    private static boolean[] minus(boolean[] first, boolean[] second){
        String firstString = booleanToString(first);
        String secondString = booleanToString(second);
        String result = Integer.toBinaryString(Integer.parseInt(firstString, 2) - Integer.parseInt(secondString, 2));
        return stringToBoolean(result);
    }

    private static boolean[] addZero(boolean[] data){
        boolean[] tempBool = new boolean[data.length+1];
        tempBool[0] = false;
        for (int i = 0; i < data.length; i++) {
            tempBool[i+1] = data[i];
        }
        return tempBool;
    }










    private static boolean checkPowOfTwo(int num){
        while (num != 1 && num % 2 == 0) {
            num /= 2;
        }
        return num != 1;
    }

    private static void showArray(boolean[] data, String description){
        System.out.print(description + ": ");
        for (int i = 0; i < data.length; i++) {
            if(data[i]){
                System.out.print('1');
            } else{
                System.out.print('0');
            }
        }
        System.out.println();
    }

    private static boolean[] stringToBoolean(String number){
        boolean [] result = new boolean[number.length()];
        for (int i = 0; i < number.length(); i++) {
            if(number.charAt(i) == '1'){
                result[i] = true;
            } else{
                result[i] = false;
            }
        }
        return result;
    }

    private static String booleanToString(boolean[] number){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number.length; i++) {
            if(number[i]){
                result.append('1');
            } else{
                result.append('0');
            }
        }
        return result.toString();
    }
}
