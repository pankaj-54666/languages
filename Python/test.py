#from abc import abstractclass

print("hello Worlds")

class Animal:
    def __init__(self,a,b):
        self.name = a
        self.age = b
    def __repr__(self):
        return "Name = {}, Age = {}".format(self.name,self.age)

    def test(self):
        raise NotImplementedError("Function Not Implemented\n")
        #pass
    

def main():
    a = Animal("Dog",56)
    print("Printing dog\n")
    print(a)

    print("Call test")
    try:
        a.test()
    except NameError:
        print("Caught NameError")
    except:
        print("Raise some exceptim")
    else:
        print("No Exception Raise")
    finally:
        print("This will be printing in both case")



main()