
// lay down ground work for keeping data after "app" closes

// VCS, Versioning Control Systems
// 

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String name ="", breed;
        int age, count = 0;
        Dog [] dogPound = new Dog[10];
        Scanner keyboard = new Scanner(System.in);

        // Reading from binary file Dogs.dat
        File binaryFile = new File("Dogs.dat");
        // Check if file exists && None-Zero size
        // anything over 1 k
        System.out.println("Previously saved dogs from binary file: ");
        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            // TODO Magic - read from binary file
            try
            {
                // READ THE FILE
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile)); //handled exception
                // Read the entire array into dogPound
                dogPound = (Dog[])fileReader.readObject(); // handled exception

                // Loop through the array and print out all object
                while (dogPound[count]!=null)
                {
                    System.out.print(count+1 + ": ");
                    System.out.println(dogPound[count++]);
                }
                // CLOSE FILE
                fileReader.close();

            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else
            System.out.println("[None, please enter new dog data]");

        do
        {
            System.out.print("\nPlease enter the dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if(name.equalsIgnoreCase("quit")) {break;}
            System.out.print("Please enter the dog's breed: ");
            breed = keyboard.nextLine();
            System.out.print("Please enter the dog's age: ");
            age = keyboard.nextInt();
            keyboard.nextLine();

            // TODO 1) Create a new Dog object
            // TODO 2) Add to array
            // TODO 3) updating count
            dogPound [count++] = new Dog(name, breed, age);

            // After we quit, its writes to binary file
        } while(true);

        // After user enters quit
        // write dogPount array to binary file
        try
        {
            ObjectOutputStream fileWrite = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWrite.writeObject(dogPound);
            fileWrite.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}

//530-6 questions for sample midterm
//6-920 exam
// txt files are slow/ inefficient
//Open: Scanner / BufferedReader
//Write: PrintWrite / BufferedReader

//