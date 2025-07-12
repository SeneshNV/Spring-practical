package org.example;

public class Main {
//    public static int total(int[] numbers){
//        int sum = 0;
//        for(int num : numbers){
//            if(num>0){
//                sum = sum + num;
//            }
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//        int[] numbers = {10,20,30,-40, 50};
//
//        int sum_of_num = total(numbers);
//
//        System.out.println(sum_of_num);
//    }

//    public static boolean isPrime(int number){
//        if(number <= 1){
//            return false;
//        }
//
//        for(int i=2; i <= Math.sqrt(number); i++){
//            if(number % i == 0){
//                return false;
//            }
//        }
//        return true;
//    }

//        public static void main(String[] args) {
//        int number = 29;
//
//          boolean ans = isPrime(number);
//
//        System.out.println(ans? "Prime num" : "Not Prime");
//    }

    public static String reverse(String text){
        StringBuilder rev = new StringBuilder(text);
        String newTest = rev.reverse().toString();
        return newTest;
    }

    public static void main(String[] args) {
        System.out.println(reverse("Hi Senesh"));
    }

}