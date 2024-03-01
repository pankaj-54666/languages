package com.example;
import java.util.function.Function;
import java.util.function.BiConsumer;


interface MyFunction{
    double getDouble();
}


//javac -d . LambdaExpression.java && java com/example/LambdaExpression
/* Summerization made from The Complete Java Reference Book */
class LambdaExpression{

    public static String customReverse(Function<String,String> reverseLogic, String str)
    {
        return reverseLogic.apply(str);
    }

    public static void main(String args[]){
        
        System.out.print("Inside LambdaExpression main\n");


    /* # Simple Lambda Demonstration */
    MyFunction obj1;
    obj1 = () -> 3456.345;

    // int a = obj1();      //will not work
    /* Why above will not work.
        OBSERVE obj1() will not work, as above statement is misleading. What "obj1 = () -> 346.345" does is that accept the getDouble's implementation from user. As MyFunction class has single abstract methode, java compiler now know that it need to replace the virtual methode with provided methode in the line.
        Once methode defination to getDouble is provided, now you can assume obj1 as a class object & can call its class variable the way you call normal class*/

    double a = obj1.getDouble(); //will work

    System.out.printf("a: %f\n",a);

    /*# type mismatch */
    // obj1 = () -> "Hello";  //explain why it will not work? Hint: consider that the statment will be made as function declration of double getDouble().

    /* generic lambda expression*/

    interface SomeFunc<T> {
        T apply(T t);
    } 

    SomeFunc<String> reverse = (str) -> "reversed: " + str;
    System.out.printf("reversed string: %s\n",reverse.apply("Hello"));


    /*# Passing lambda as a argument */

    //instead of defining our own lambda expression taking single input & returning single output. We have used provided Function lambda by java.util.function.Function
    //if you want to accept more arguments, you can either chain these or create new lambda interface accepting muliple values (like MyFunction, but getDouble accepting multiple parametes this time )
    Function<String,String> reverseLogicOne = (str) -> "reverse1: " + str;
    Function<String,String> reverseLogicTwo = (str) -> "reverse2: " + str;

    String s2 = customReverse(reverseLogicOne,"abcd");
    String s3 = customReverse((str)-> "reverse3: " + str, "abcd"); /* very important pattern: create and pass the function in-place */

    System.out.printf("s2: %s, s3=%s\n",s2,s3);

    /* throwing exceptions from lambda*/
    /* unchecked expection can be thrown from lambda without any issue. But to throw a checked exception , the exception MUST be compatible with the exceptions(s) isted in the throw clause of the abstract methode in the functional interface */

    class EmptyArrayException extends Exception{
        EmptyArrayException(){ super("Array Emtpty");};
    }

    interface ArrayFunc{
        int apply(int [] arr)  throws EmptyArrayException;
    }

 

    ArrayFunc afun1 = arr -> {
        if(arr.length == 0) throw new  EmptyArrayException();
        return arr[0];
    }; 


    try{
        System.out.printf("afun1.apply(): %d\n",afun1.apply(new int[0]));
    }catch(EmptyArrayException e){
        System.out.printf("Exception  Caught" + e + "\n");
    }


    /*# capturing local variable ,and this in lambda*/
    /* lambda expression can use and modify an instance
variable from its invoking class. It just can’t use a local variable of its enclosing scope unless
that variable is effectively final(=whose value does not get verifed anywhere in program after first assignment)*/

    int p =10;
    Function<Integer,Integer> fun2 = (num) ->{
        if(p == 10)
        {
            System.out.println("Logic1");
        }else
        {
            System.out.println("Logic2");
        }
        return num+p;
    };

    fun2.apply(12);


    /*# Methode Reference and Lambda Expression*/
    /* Methode reference provides a way to refer to a methode without exectuing it*/

    BiConsumer<String,Object[]> cprintf = System.out::printf;
    cprintf.accept("Hello There");

    /*
        Methode Refernce to static Methodes     => className::methodeName
        Methode Reference to instance methodes  => objRef::methodeName
        Methode Reference to class methodes     => className::instanceMethodeName
            ↑ passes methode can be used on any object of the class ↑

        Methodes Reference to generic methode   => MyArrayOps::<Interger>countMatching
          ↑ check page 401 fo  more details ↑ 

        Constructor refernce                    => className::new
        ↑   MyFunc myClassCons = MyCalss::new;
            MyClass mc = myClassCons.apply(100); //create object using constructor reference
        ↑ 
    */

   /*# predefined functional intference*/
   /* as we have seen we need a lot of class boilploter code, even only if we want to use the functional interface feature. So remove this boilploter code for most comman case, java.util.function package provides some predefined functional interface*/

    /*
        //Built-In functionl interface accepting single argument
        Interface           Abstract Methode            Description
        ▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔
        UnarayOperator<T>       T apply(T)              Accept T type & return same type
        Function<T,R>           R apply(T)              Accept T type data & return R type data
        Consumer<T>             void accept(T)          Consumer T type data
        Supplier<T>             T get()                 Produce T type data
        Predicate<T>            boolean test(T)         Convert T to bollean upon some condition

        //Built-in Fucntional interface accepting two argument
        BinaryOperator<T,U>     T accept(T,U)
        BiFunction<T,U,R>       R accept(T,U)
        BiConsumer<T,U>         void accept(T,U)
            NA
        BiPredicate<T,.U>       boolean test(T,U)

        //specilization
        BooleanSupplier         getAsBoolena()
        DobuleConsumer          void accept(double val)
        OtherNotSuchImportant
    */   








    }
}