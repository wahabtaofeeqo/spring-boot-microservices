
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ExceptionDemo {
    
    void sayHi() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Name");
        
        String name = s.nextLine();
        if(name.equalsIgnoreCase("Tao"))
            throw new RuntimeException("Demo Throw");
        
        System.out.println("Welcome");
    }
    
    public static void main(String[] args) {
        
        ExceptionDemo demo = new ExceptionDemo();
        try {
            demo.sayHi();
        } catch (RuntimeException e) {
            System.out.println("Done");
        }
//        try {
//            System.out.println(2/0);
//            System.out.println("Done");
//        }
//        catch (ArithmeticException e) {
//            System.out.println("Catched");
//        }
//        catch(Exception e) {
//            System.out.println("Exception");
//        }
    }
}
