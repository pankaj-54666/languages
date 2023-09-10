import static java.lang.System.out;
import java.util.function.Consumer;


class HelloWorld{

    public static void main(String args[])
    {

        //Print Way1
        System.out.printf("Starting Main\n");

        //Print Way2
        out.printf("This make it c++ style printing\n");

        //Print Way3
        Consumer<String> printfFunction = System.out::printf; //Consumer is  a functional interface 

        printfFunction.accept("Hello\n");

        //Print Way4
        /*  
            VS Code -> Command Platter -> User Snippets -> select language as Java -> input below lines
        
        "Print using System.out.printf": {
        "prefix": "sout",
        "body": ["System.out.printf(\"$1\")"],
        "description": "System.out.printf statement"}

        then sout + tab in .java file to replace sout.
        */
       System.out.printf("After Setting on settings.json or java.json\n");

    }
}