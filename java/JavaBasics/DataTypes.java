package com.datatypes;
import java.util.*; //collection + other import

//javac -d . DataTypes.java && java com.datatypes.DataTypes
class DataTypes{
    /* aux functuins*/
    void printArray(int arr[])
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.printf("%d ",arr[i]);
        }
        System.out.println("");

        /* alternative is */
        for(int x:arr)
        {

        }
    }
        
    /**
    Goal:
        (a) Java Primitive: int,String
        (b) int vs Integer
        (c) int [] vs Integer[]
        (d) Functions avaiable on int vs Integer
    */
    void primitiveTypes()
    {

        System.out.printf("Testing Primitinve Data Types\n");

        int a=90,b=34_56;
        System.out.printf("a=%d,b=%d\n",a,b);

        String str = "I am an Immutable String";
        System.out.printf("str = %s\n",str);
    }

    void testArray()
    {
        //# 1D array Declaration
        int arr1[] = new int[10]; //this is similary to c++ heap declaration
        Arrays.fill(arr1,-1);

        int arr2[]  = {1,2,3,4,5,6}; //shorthand notation

        System.out.printf("Traversa array using for loop\t");
        //traverse array : way1
        for(int i=0;i<arr1.length;i++)
        {
            System.out.printf("%d ",arr1[i]);
        }
        System.out.printf("\n");

        System.out.printf("Traversa array using auto similary\t");
        //traverse array : way2 (Preferred)
        for(int x:arr2)
        {
            System.out.printf("%d ",x);
        }
        System.out.printf("\n");       

        //# 2D array 
        
        int mem[][] = { {1,2,3}, {2,4,6},{1,3,5}};
        int mem2[][] = new int[10][101]; //2D
        
        //way1 print: preferred
        System.out.printf("Printing 2D arrays\n");
        for(int i=0;i<mem.length;i++)
        {
            for(int j=0;j<mem[i].length;j++)
            {
                System.out.printf("%d ",mem[i][j]);;
            }
            System.out.println();
        }

        //not preferred way
        System.out.printf("\nPrinting 2D arrays\n");   
        for(int x[]:mem)
        {
            for(int y:x)
            {
             System.out.printf("%d ",y);;
            }
            System.out.println();
        }




        String sarr[] = new String[3];
    }


    /**
    Goal:
    (a) Sort a primitive array
    (b) Sort it in revese order
    (c) Sort Generic Array
    (d) Sort using comperator
    (e) Comperator using lambda expression, function expression and classs comperator.
        Concolustion: we are using function defined on class Arrays.
    */
    void arraySorting()
    {

        //# Sort primitive array Array: normal + reverse

        int arr[] = {5,3,4,1,6,8,34,4,2};
        int ara[] = new int[arr.length];
        Arrays.fill(ara,-1); //fill all entry with -1
        
        System.out.println("\n\t::Before Sort\t");
        // printArray(arr);
        for(int x:arr)
            System.out.printf("%d->",x);
        System.out.printf("\n");
        

        Arrays.sort(arr);
        System.out.printf("\n\t::After Sorting\n");
        printArray(arr);

        //Task: How to sort in revese? as comperator does not accept primite types.

        //# Sort ArrayList
        // ArrayList<int> arr2 = new ArrayList<>(); //Will not work, as collection require Generics and not works with primitive 

        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        arr3.add(3);
        arr3.add(2);
        arr3.add(5);

        ArrayList<Integer> arr4  = new ArrayList<Integer>(Arrays.asList(3,2,5));

        Collections.sort(arr4);
        System.out.println("SAfter soritng collection: " + arr4);

        Collections.sort(arr4,Collections.reverseOrder());
        System.out.println("Soritng in revese Order" + arr4);

        // task; Lambda Comperator
        Comparator<Integer> rComperator1 = (a,b) -> b.compareTo(a);
        Comparator<Integer> rComperator2 = (a,b) -> {
            return b.compareTo(a);
       };

        // task: Function Comperator (via anonymous inner class)
        Comparator<Integer> rComperator3 = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a); // Reverses the natural order
            }
        };

    //NOTE: in C++ we return true/false, but in java/javascript we return +ve,-ve and 0 value
    /* task: Preferred */
    Comparator<Integer> rComperator4 = (a,b) -> {
            if(a<b) return -1;
            if(b>a) return 1;
            return 0;
       };

        //TO-DO: add example for class comperator.

    //task: class comperator with seperate class
    class SortByName implements Comparator<Integer>{
        public int compare(Integer lhs,Integer rhs){
            return lhs - rhs;
        }
    };
    SortByName rComperator5 = new SortByName();

    //task: class comperator with comparision logic inside data class itself
    class MyInteger implements Comparable<MyInteger>{
        Integer x;

        MyInteger(Integer x){
            this.x = x;
        }

        public int compareTo(MyInteger rhs){
            return this.x - rhs.x;
        }

        @Override
        public String toString(){
            return Integer.toString(x);
        }
    };

    PriorityQueue<MyInteger> pq = new PriorityQueue<MyInteger>();
    pq.add(new MyInteger(12));
    pq.add(new MyInteger(14));
    pq.add(new MyInteger(11));
    pq.add(new MyInteger(30));
    System.out.println("Contents of pq with Comparable : " + pq);


     // Collections.sort(arr4,rComperator5);

        

    }

    void testArrayList()
    {
        System.out.printf("Testing Collection\n");

        ArrayList<String> arr = new ArrayList<String>(); //() calls the default constructor

        // List<String> list = new ArrayList<String>() | new LinkedList<>() ; alternative syntax using parent class List

        arr.add("Hello");
        arr.add("Kumar");
        arr.add(1,"Middle"); //add element at index 1

        arr.remove(1); //remove element at index 1
        arr.remove("helo"); //No Removal + No Error thrown

        System.out.printf("\n\t::Content of arr is: " + arr);

        System.out.printf("\n\t::Using for loop to print array:\t");
        for(int i=0;i<arr.size();i++)
        {
            // System.out.printf("%s->",arr[i]); //NOTE: will work with array only
            System.out.printf("%s->",arr.get(i)); //there is no operator overload conecpt in java, hence as ArrayList is object, you need to use some getter function to get its value.
        }
        System.out.println(); 

        System.out.printf("\n\t::Pring using int i:arr formated\n");
        for(String s:arr)
        {
            System.out.printf("%s->",s);
        }

        System.out.printf("\n\t::Print Using .forEach\t");
        arr.forEach(element -> System.out.printf("%s->",element));

        System.out.printf("\n\t::Print Using iterator\t");
        Iterator<String> iterator = arr.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.printf("%s->",element);
        }


        // # Converting to array
        String arr2[] = new String[arr.size()]; //array declaration
        arr2 = arr.toArray(arr2); //<T> T[ ] toArray(T array[ ])

        Object arr3[] = arr.toArray(); 

        System.out.printf("\n\t::Conversion form ArrayList to array\t");
        for(int i=0;i<arr2.length;i++)
        {
            System.out.printf("%s->",arr2[i]);
        }
        
    }

    /**
     *  Map Functions:      https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#method.summary
     *  HashSet Functions:   https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html#method.summary*/
    void testMap()
    {
        // Map<Integer,String> mMap = new Map<>(); //it will not work, as Map is a interface.

        Map<Integer,String> mMap = new HashMap<>();
        //we can replace Hashmap with any implementation of Map interface like: HashMap,HashTabel,LinkedHashmap,TreeMap,etc (https://quickref.me/java.html)

        mMap.put(1,"One");
        mMap.put(2,"Two");
        mMap.put(-1,"NergativeOne");

        //way1 print
        System.out.println("\n\t::Print Using Map.Entry:mMap");
        for(Map.Entry<Integer,String> entry: mMap.entrySet()){
            Integer key = entry.getKey();
            String value = entry.getValue();

            System.out.printf("%d:%s\n",key,value);
        }

        //way2 (Preferred)
        System.out.println("\n\t::Print using mMap.forEach");
        mMap.forEach((key,value)-> System.out.printf("%d:%s\n",key,value));

        //way3
        System.out.printf("\n\t::Print using .entrySet().stream().forEach\n");
        mMap.entrySet().stream()
            .forEach(entry -> System.out.printf("%d:%s\n",entry.getKey(),entry.getValue()));

        //way4
        System.out.printf("\n\t::Using Iterator\n");
        Iterator<Map.Entry<Integer, String>> iterator = mMap.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<Integer, String> entry = iterator.next();
             System.out.printf("%d:%s\n",entry.getKey(),entry.getValue());
        }

        //Check key existance
        System.out.printf("\n\t:: check key existance: " + mMap.containsKey(1));

        //Map with custom comperator

        class MyListNode{
            int x,y;
        };

        class CustomComparator implements Comparator<MyListNode> {
            @Override
            public int compare(MyListNode node1, MyListNode node2) {
                return Integer.compare(node1.x, node2.x);
            }
        }

        Map<MyListNode,String> mMap2 = new TreeMap<>(new CustomComparator()); //Hashmap is unordered, so if you want ordering you need to use TreeMap.
        mMap2.put(new MyListNode(),"One");

    }

/**
 * GOAL: 
 * (a) Priority Queue with Integer
 * (b) Priority Queue with custom Object
 * (c) Function comperator when the object has more than one attribute to sort upon
 */
    void testPriorityQueue()
    {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        //PriorityQueeu<Integer> pq = new PriorityQueue<>(); //auto type inferred

        pq.add(3);
        pq.add(1);
        pq.add(-100);
        pq.add(89);

        System.out.printf("\n\t::Access pq elements in order size(%d)\n",pq.size());
        while(!pq.isEmpty())
        {
            Integer top = pq.peek();
            pq.remove();
            //peek + remove  = poll

            System.out.printf("%d,",top);
        }
        System.out.println("");

        // # Priority Queue with comperator
        class Person {
            String name;
            int age;
            int height;
            int distance;

            public Person(String name, int age, int height, int distance) {
                this.name = name;
                this.age = age;
                this.height = height;
                this.distance = distance;
            }
        }

        Comparator<Person> personComperator = (Person a,Person b)->{
            // Compare by age in descending order (higher age first)
            int ageComparison = Integer.compare(b.age, a.age);
            if (ageComparison != 0) {
                return ageComparison;
            }

            // Compare by height in ascending order (lower height first)
            int heightComparison = Integer.compare(a.height, b.height);
            if (heightComparison != 0) {
                return heightComparison;
            }

            // Compare by distance in descending order (higher distance first)
            return Integer.compare(b.distance, a.distance);
        };
        PriorityQueue<Person> pq2 = new PriorityQueue<Person>(personComperator);

    }

    void testQueue()
    {
        // Queue<Integer> q = new Queue<Integer>(); //ISSUE: Queue is an abstract class
        //LinkedList, ArrayDeque or PriorityQueue are their concrete implementation.

        ArrayDeque<Integer> q = new ArrayDeque<Integer>();


        q.add(20);
        q.add(5);

        while(!q.isEmpty())
        {
            System.out.printf("%d,",q.peek());
            q.remove();
        }

        //Methodes Available on queue: .peek, .peekFirst, .peekLast, .poll, .pollFirst, .pollLast, .pop(when using as stack). push(when using as stack), .remove etc
        //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html#method.summary

        // # Alternative Decraration
        Queue<Integer> q2 = new ArrayDeque<Integer>(); //works because parent class can point to any object of child class
    }

    void testStack()
    {
        Stack<Integer> st = new Stack<Integer>();

        st.push(34);
        st.push(19);

        while(!st.isEmpty())
        {
            System.out.printf("%d,",st.peek());
            st.pop(); 
            // st.remove(); //WARNING: does not exist
        }

        // Others methode on stack: .pop(), .peek(), .push()
    }

    void testSet()
    {
        Set<String> s = new HashSet<String>();

        s.add("Apple");
        s.add("banana");
        s.add("hurray");

        
        System.out.printf("does Apples exist? %b\n",s.contains("Apple"));

        s.remove("hurray");

        System.out.printf("Updated set entry is: " + s);
    }

    void testString()
    {
        String s1 = "I am a immutable string, i cannot be edited";
        String s2 = new String("Alternatie and more streamline with java decralartion");

        StringBuffer s3 = new StringBuffer("I am editable string");

        //Note on builder: not thread-safe, but fast on single-thread scenerio
        StringBuilder s4 = new StringBuilder("String using string builder"); 

        System.out.println("s1: "+ s1 + "\ns2: " + s2 + "\ns3: " + s3 +"\ns4: "+s4);

        // s3 = s3 + "additiong"; //NOTE: this style will not work as s3 and "addition" are of different type
        s3.append("addition");

        String s5 = "Hello" + "There";

        System.out.println("s1: "+ s1 + "\ns2: " + s2 + "\ns3: " + s3 +"\ns4: "+s4 + "\ns5: " +s5);


        /*
            .concat : for immutatble string(return new string)
            .append : for mutalbe string(modify original string)
        */
        

        // # Strign Construction ways 
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#constructor.summary

        String a1 = "Java Program";
        String a2 = new String("Java");

        char c[] = {'H','e','l','l','0'};
        String a3 = new String(c);

        byte b[] = {65,66,67,68}; //ascii code (ABCD)
        String a4 = new String(b);

        //"Java" != new String("Java") => Reason why

        // # String Methodes

        String s = new String("Test String");
        s.length();
        s.charAt(3);
        s.equals("Test String");
        s.equalsIgnoreCase("test");
        s.compareTo("test");
        s.trim();

        s.substring(1);
        s.substring(4,5);
        s.toLowerCase();
        s.toUpperCase();
        s.toLowerCase();

        s.trim();
        s.startsWith("Tes");
        s.endsWith("ing");

        String regexExpression  = "[a-z]*";
        s.matches(regexExpression);
        //other less used at: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#method.summary
        



    }
    public static void main(String args[])
    {
        System.out.printf("Starting Main\n");

         // Create an instance of the DataTypes class
        DataTypes object = new DataTypes();

        // Call the primitiveTypes method on the instance
        object.primitiveTypes();

        object.testArray();

        object.testArrayList();

        object.arraySorting(); /* comperator example*/

        object.testMap();

        object.testPriorityQueue();

        object.testQueue();
        object.testStack();

        object.testSet();

        object.testString();

        System.out.printf("\n");

        /*#1 Array in java */

        /*
            TO-DO: 
            (a) Lambda Expression
            (b) Linked List
            (c) Map, Set : browser,add, remove etc function
            (d) Queue, Stack: supported fuctions.

            (e) class, inheritacne, dervied class, methode overriding (just implement one design pattern, it will cover all aspects of it.)
        */		
    }

}