import java.util.*; //collection + other import

class DataTypes{
    /* aux functuins*/
    void printArray(int arr[])
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.printf("%d ",arr[i]);
        }
        System.out.println("");
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


    /**
    Goal:
    (a) Sort a primitive array
    (b) Sort it in revese order
    (c) Sort Generic Array
    (d) Sort using comperator
    (e) Comperator using lambda expression, function expression and classs comperator.
    */
    void arraySorting()
    {

        //# Sort primitive array Array: normal + reverse

        int arr[] = {5,3,4,1,6,8,34,4,2};
        
        System.out.println("\n\t::Before Sort\t");
        printArray(arr);

        Arrays.sort(arr);
        System.out.printf("After Sorting\n");
        printArray(arr);

        //Task: How to sort in revese? as comperator does not accept primite types.

        //# Sort ArrayList
        // ArrayList<int> arr2 = new ArrayList<>(); //Will not work, as collection require Generics and not works with primitive 

        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(3);
        arr3.add(2);
        arr3.add(5);

        ArrayList<Integer> arr4  = new ArrayList<>(Arrays.asList(3,2,5));

        Collections.sort(arr4);
        System.out.println("SAfter soritng collection: " + arr4);

        Collections.sort(arr4,Collections.reverseOrder());
        System.out.println("Soritng in revese Order" + arr4);

        // Lambda Comperator
        Comparator<Integer> rComperator1 = (a,b) -> b.compareTo(a);
        Comparator<Integer> rComperator2 = (a,b) -> {
            return b.compareTo(a);
       };


        // Function Comperator
        Comparator<Integer> rComperator3 = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a); // Reverses the natural order
            }
        };

        //TO-DO: add example for class comperator.

        Collections.sort(arr4,rComperator1);





    }

    void testArrayList()
    {
        System.out.printf("Testing Collection\n");

        ArrayList<String> arr = new ArrayList<String>(); //() calls the default constructor
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

    public static void main(String args[])
    {
        System.out.printf("Starting Main\n");

         // Create an instance of the DataTypes class
        DataTypes object = new DataTypes();

        // Call the primitiveTypes method on the instance
        // object.primitiveTypes();

        // object.testArrayList();

        // object.arraySorting();

        // object.testMap();

        // object.testPriorityQueue();

        // object.testQueue();
        // object.testStack();

        object.testSet();

        System.out.printf("\n");

        

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