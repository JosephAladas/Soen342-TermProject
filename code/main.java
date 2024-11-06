package code;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static Map<Pair<UserType, String>, User> userMap = new HashMap<>();
    
    public static void main(String[] args) {
        Admin.getInstance(); 
        Scanner sc = new Scanner(System.in);

        menu();
        String choice = sc.next();

        // SIGN UP
        
        if(choice.equals("2")){
            Guardian g = new Guardian();
            System.out.print("Age: \n");
            int age = sc.nextInt();
            if(age <= 17){
                System.out.println("You are underage and need to have a guardian. Does your guardian have an account? Y or N");
                String ans = sc.next();
                if(ans.equals("Y")){
                    System.out.print("Guardian username: ");
                    String guardianUsername = sc.next();

                    // add logic to find guardian from list of users
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    g = null;
                }
                else{
                    System.out.println("Please sign up guardian first.");
                    // add break statement for when loop is integrated
                    //
                    //
                    //
                    //
                    //
                }
            }
            System.out.print("Name: \n");
            String name = sc.next();
            System.out.print("Phone Number: \n");
            Long numb = sc.nextLong();
            System.out.print("Username: \n");
            String user = sc.next();
            System.out.print("Password: \n");
            String pass = sc.next();

            signUpClient(name, numb, age, user, pass, g);

        }
        
    }
    public static void menu(){
        System.out.println("WELCOME");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. View Lessons");
        System.out.print("Choice: ");
    }

    public static boolean signUpClient(String name, long phone, int age, String user, String pass, Guardian guardian){
        try {
            User tempClient = new Client(name, phone, age, user, pass, guardian);
        Pair<UserType, String> key = new Pair<>(UserType.CLIENT, tempClient.getUsername());
        if (!userMap.containsKey(key)) {
            userMap.put(key, tempClient);
            return true;
        } else {
            System.out.println("An instructor with this username already exists.");
            return false;
        }
        } catch (Exception e) {
            System.out.println("Error in signing up client");
            return false;
        }
    }
}

