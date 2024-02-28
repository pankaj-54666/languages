package com.functional;
import java.util.function.Function;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

    class Person{
    public String name;
    public String getName(){
        return name;
    }
    Person(String name){
        this.name = name;
    }
}

//javac -d . FunctionalProgrammingUsingStreamAPI.java && java com/functional/FunctionProgrammingUsingStreamAPI
class FunctionProgrammingUsingStreamAPI{




/*
(a) stream intermidate function uses
(b) stream terminary function uses
(c) flatMap for converting Stream<List> as Stream only.
(d) var keyword use for auto-type inference.


*/
void streamAPIExample()
{
    // List<Integer> list1 = new ArrayList<>();
    List<Integer> list1 = Arrays.asList(1,4,6,8,-1,5,-6,-9,-10,10);


    /* var keyword in java is used as auto in c++, i.e for type inferrence*/
    var list2 = list1.stream()
                    .filter(x -> x>0)
                    .map(x ->  x+1)
                    .map(x -> x-2)

                    .distinct()
                    .sorted()
                    // .limit(2) .skip(2)
                    .peek(x -> System.out.printf("%d ",x))
                    // .peek(System.out::println)
                    .collect(Collectors.toList()); 

    System.out.println("\nlist1: " + list1);

    System.out.println("list2: " + list2);


    /* flatMap Example  TO-DO: convert Files to arraylist example */
    // Files.lines(Paths.get("stuff.txt"))
    //         .map(line -> line.split("\\s+")) // Stream<String[]>
    //         .flatMap(Arrays::stream) // Stream<String>
    //         .distinct() // Stream<String>
    //         .forEach(System.out::println);
 //https://www.oracle.com/java/technologies/architect-streams-pt2.html check figure 2) OR JavaBasic/flatMap.avif 



/* terinary operation on stream*/
 // Accumulate names into a List
    List<String> list = new ArrayList<>();
    List<Person> people = new ArrayList<Person>();
    people.add(new Person("sky"));

//TO-DO: add more examle from https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
    //Accumulate names into a List
    list = people.stream().map(Person::getName).collect(Collectors.toList()); // call getName on each element of type People
    list = people.stream().map(x -> x.getName()).collect(Collectors.toList()); /* same as above */

    System.out.print("list: " + list);

}

public static void main(String args[])
{
    System.out.printf("Inside FunctionProgrammingUsingStreamAPI main\n");

    FunctionProgrammingUsingStreamAPI obj  = new FunctionProgrammingUsingStreamAPI();

    obj.streamAPIExample();

}


}