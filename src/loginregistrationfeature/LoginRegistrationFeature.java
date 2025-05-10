/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginregistrationfeature;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class LoginRegistrationFeature {
    private static String storedUsername;
    private static String storedPassword;
    private static String storedPhoneNumber;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username, password, phoneNumber;
        
        System.out.println("Welcome to the Registration System");
        
        String registrationStatus;
        // Registration Process
        do {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            registrationStatus = register(username, password, phoneNumber);
            System.out.println(registrationStatus);
            

            
        } while (!registrationStatus.contains("Welcome"));
        
        // Login Process
        System.out.println("\nPlease login with your credentials");
        boolean loginSuccessful = false;
        
        while (!loginSuccessful) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            
            String loginStatus = returnLoginStatus(username, password);
            System.out.println(loginStatus);
            loginSuccessful = loginInUser(username, password);
        }
        
        scanner.close();
    }
    
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    public static boolean checkPasswordComplexity(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("\\d").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
        
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }
    
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+?27[0-9]{9}$");
    }
    
    public static String register(String username, String password, String phoneNumber) {
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validPhone = checkCellPhoneNumber(phoneNumber);
        
        if (!validUsername) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!validPassword) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!validPhone) {
            return "Phone number is not correctly formatted.";
        }
        
        // Store the valid credentials
        storedUsername = username;
        storedPassword = password;
        storedPhoneNumber = phoneNumber;
        
        // Extract first and last name from username (assuming format: first_last)
        String[] nameParts = username.split("_");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";
        
        return "Welcome " + firstName + "_" + lastName + ", it is great to see you.";
    }
    
    public static boolean loginInUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
    
    public static String returnLoginStatus(String username, String password) {
        if (loginInUser(username, password)) {
            return "A successful login";
        } else {
            return "A failed login";
        }
    }
}