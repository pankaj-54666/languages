package com.examples;
import java.util.*;

//javac -d . FileHandling.java && java com/examples/FileHandling
class FileHandling{


//Read & write to terminal

    void testReadWrite(){
        System.out.printf("Enter: testReadWrite\n");

    }

    public static void main(String args[]){
        System.out.printf("Inside Main\n");

        Scanner s = new Scanner(System.in);

        String name;
        System.out.printf("Please Entery Your Name: ");
        name = s.nextLine();

        System.out.printf("\t:User Name Entered is: %s\n",name);

        FileHandling obj = new FileHandling();
        obj.testReadWrite();

    }

}