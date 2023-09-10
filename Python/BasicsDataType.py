#Source: Learning Python, 5th Edition
#Chapter4: Introducing Python Object Types
'''List Comprehension internal

    squares = [x ** 2 for x in [1, 2, 3, 4, 5]]
    squares
    [1, 4, 9, 16, 25]

can always be coded as an equivalent for loop that builds the result list manually by
appending as it goes:

    squares = []
    for x in [1, 2, 3, 4, 5]: # This is what a list comprehension does
    squares.append(x ** 2) # Both run the iteration protocol internally

    [1, 4, 9, 16, 25]
'''

def setTest():
    #set are collection of unqiue iteams
    Y = {'h','a','m','h'} #duplicated h will be omittted
    X = set("spam")
    
    print(X,Y)
    
    print("Intersection ", X & Y)
    print("Union ",X|Y)
    print("Difference ",X-Y,Y-X) #Observe both operation result in two differnet set
    
    print("isSuperSet? ",X>Y,Y>X)
    
    s2 = {n**2 for n in [1,2,3,4]} #Set Comprehension
    print(s2)
    
    #Membership test
    print("isExist? ",1 in s2)
    
    
    

def tupleTest():
    #Tumple is just like list but immutable
    #tuple only have two methodes 
    t = (1,2,3,4,5,1,2,3)
    print(t.count(2)) #Count how many times 2 occurrs
    print(t.index(2)) #Count 1st index of 2 in tuple
    
    t2 = (12,13) + t[2:-2]
    print(t2)

def dicTest():
    D = {"Pankaj":25,"Pritty":24}
    D["Alien Bob"] = 1000
    
    D2 = dict(Alice="10000")
    D3 = dict(zip(["Alien Alice","Alien Bob"],[1000,1000+1]))
    print(D)
    print(D2)
    
    print("\nD3 is");
    print(D3)
    
    print("Pankaj age = ",D["Pankaj"])
    
    #Dictionary Nesting
    rec = {'name': {'first': 'Bob', 'last': 'Smith'},
           'jobs': ['dev', 'mgr'],
           'age': 40.5}
    print("Nested dic\n")
    print(rec)
    
    print("Is Key exist test ","Pankaj" in D)
    value1 = D.get("Pankaj",0) #get value of key, if not exist then take defaut value as 0
    value2 = D["Pankaj"] if "Pankaj" in D else 0
    
    print(value1,value2)
    
    kL = list(D.keys())
    kL.sort()
    print(kL)

def listTest():
    l1 = [1,2,3,4,5]
    l1 = [x for x in range(1,10,2)] #range(start,stop,step)
    print("list1 = ",l1)
    #list comprehension
    l2 = [2*x for x in  l1 if x>3] #for in loop
    print("Printing the list\n");
    print(l2)
    
    #List Operations
    l1.append("me")
    print(l1)
    l1.pop()
    l1.sort()
    l1.reverse()
#    l1[100] = 10 #IndexError , a type of out of bound run-time erro

    #List nested and n-d array in python
    mat = [[1,2,3],
           [4,5,6],
           [7,8,9]
           ]
    print("matrix is \n",mat)
    print("mat[1][2] = {}".format(mat[1][2]))
    
    col2 = [row[1]*2 for row in mat] #row is [row[0],row[1],row[2]]
    print("Col2 = \n",col2)
    
    diag = [mat[i][i] for i in [0,1,2]]
    print("diag\n",diag)
    
    l3 = [[x**2,x**3] for x in range(-6,4,1) if x>0]
    print(l3)
    
    l4 = [0]*10 #initilize a list of 10 size with all value as 0
    print(l4)

def testString():
    s = "Hello i am a simple string" 
    print("Simple string = ",s)
    
    #Lenghth, slicing, forward and backward indexing 
    
#    s[0] = 'P' #Python string are immutable!
#number,string,tuple are immutable.  list,dictionary and set are not
    print("First Char = ", s[0], "\nLast Char = ", s[-1], "\nString Lenght ",len(s),"convert to list",list(s))
    
    L = list(s)
    newS = '_'.join(L) #Combine list iteam to string using _ as seperator
    print(".join can combine list iteam to string\n",newS)
    
    print(s[:3], s[6:], s[3:6],sep = " ")
    
    #String class methodes
    print("Checking String class methodes");
    print(s.find('am'))  #find first occurance of provided string
    print(s.replace('am',"AM "))
    
    print(s.split(' ')) #Split the string in accordance to provided delimeter
    print(s.upper(),s.isalnum(),s.lstrip(),sep=" ")

#    printdir(s)) #Print List of methodes avaialble on current object type
#    print(help(s.replace)) #Print the help doc of replace
    
    #Mutiline string assignment without \n
    s2 = """ Hello
    I 
    am
    a
    multiline 
    string
    """
    print(s2)

def variableCheck():
#  int age = 68 //Invalid
    age = 25
    s = 25
    s = "Pankaj" #Allowed

    print("{}, your name is {}".format(s,age))
    
    

def main():
    #variableCheck()
    #listTest()
    #testString()
    #dicTest()  
    #tupleTest()
    setTest()
    
    #if type(obj)==list
    
    
    

main()