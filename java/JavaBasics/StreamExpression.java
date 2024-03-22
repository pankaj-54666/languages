package com.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

// javac -d . StreamExpression.java && java com/example/StreamExpression
class StreamExpression{
    public static void main(String args[])
    {
        System.out.printf("Inside StreamExpression::main\n");

        /* #Intro*/
        /*
            Stream API is designed with lambda expressions in mind.
            The stream API provides some of the most significant demonstrations of the power that lambdas bring to Java.

            What is a stream in java?
                A stream is a conduit for data. A stream, itself, never provides storage for the data. It simply moves data, possibly filtering, sorting, or otherwise operating on that data in the process
        */

       /*
        Base Stream Inteface
            interface BaseStream<T, S extends BaseStream<T, S>>
            Interface Methodes:
                void close()
                boolean isParallel()
                S sequential()
                S parallel()
                Others...
            
        Stream Interface (one of most used derive class of BaseStream)
            interface Stream<T>
            Interface Methodes:
                                                               countt of elements inthe stream
                Stream<T> filter(Predicate<? super T> pred)                     filter on predicate
                void forEach(Consumer<? super T> action)                        
                <R> Stream<R> map(Function<? super T, ? extends R> mapFunc)

                DoubleStream mapToDouble(ToDoubleFunction<? super T> mapFunc)   a specifilization

                Optional<T> max(Comparator<? super T> comp)                     find the maximum
                Optional<T> min(Comparator<? super T> comp)

                T reduce(T identityVal, BinaryOperator<T> accumulator)

                <R,A> R collect(Collector<? super T,A,R> collectorFunc)         v1:collects elements into the container.
                long count()   
                <R> R collect(Supplier<R> target, BiConsumer<, ? super T> accumulaator, BiConsumer<R,R,> combiner)

                Stream<T> sorted()
                Object[] toArray()                      
       */


      /* #Obtain a stream */

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3,4,6,8,-9,-4,-78));

        Stream<Integer> stream1 = list.stream();

        Optional<Integer> mn = stream1.min(Integer::compare);
        if(mn.isPresent())
            System.out.printf("min val: %d\n",mn.get());


        /* # filter,map,peek, and reduce(Terinary operation)*/
        list.stream()
                    .filter(x -> x<0)
                    .map(x -> x*2)
                    .forEach(x -> System.out.printf("%d ",x));

        System.out.println();

        var negativeSum = list.stream()
            .filter(x -> x<0)
            .map(x -> x*2)
            .peek(x -> System.out.printf("%d ",x))
            .reduce(0, (res,x) -> res + x);

        System.out.printf("negativeSum = %d\n",negativeSum);
        


        
        /* # collect in details */
        List<Integer> negativeNumber = list.stream()
                                .filter(x -> x<0)
                                .collect(Collectors.toList()); //.toList and .toSet are two implementaion provided by java.util.stream
        
        Set<Integer> negativeNumber1 = list.stream()
                                .filter(x -> x<0)
                                .collect(Collectors.toSet());

        LinkedList<Integer> negativeNumber2 = list.stream()
                                                .filter(x -> x<0)
                                                .collect(
                                                    ()-> new LinkedList<>(),
                                                    (lst,x) -> lst.add(x),
                                                    (lstA,lstB) -> lstA.addAll(lstB));

        LinkedList<Integer> negativeNumber3 = list.stream()
                                                .filter(x -> x<0)
                                                .collect(
                                                    LinkedList::new,
                                                    LinkedList::add,
                                                    LinkedList::addAll);


        System.out.printf("negativeNumber array is(v1 collect used): " + negativeNumber + "\n");
        System.out.printf("negativeNumber LinkedList(v2 collect used) is: " + negativeNumber2 + "\n");
        System.out.printf("negativeNumber LinkedList(v2 collect used + pass methode reference) is: " + negativeNumber3 + "\n");
        
        /* # skipped*.
        /*
            Iterators and streams.
            Spliterator
            .allMatch()
            .anyMatch()
            .noneMatch()
            .distinct()
            .of()
            .flatMap
            OtherAt https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
        */



                    

    }
}