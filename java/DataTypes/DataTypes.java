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

    void primitiveTypes()
    {
        System.out.printf("Testing Primitinve Data Types\n");

        int a=90,b=34_56;
        System.out.printf("a=%d,b=%d\n",a,b);

        String str = "I am an Immutable String";
        System.out.printf("str = %s\n",str);
    }



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



    public static void main(String args[])
    {
        System.out.printf("Starting Main\n");

         // Create an instance of the DataTypes class
        DataTypes object = new DataTypes();

        // Call the primitiveTypes method on the instance
        // object.primitiveTypes();

        // object.testArrayList();

        object.arraySorting();

        System.out.printf("\n");
    }







}