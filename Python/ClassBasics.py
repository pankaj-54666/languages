#Class basics

#import abstractmethod from abc
from abc import abstractmethod #import is must

class Animal:
    def __init__(self,planet="Earth"):
        self.planet = planet
        self.cellCount = None
    
    def __repr__(self):
        return self.planet + " " +  str(self.cellCount)
    
    def changePlanet(self,nPlanet):
        self.planet = nPlanet
        
    @abstractmethod  #Using abstract decorator
    def isAlive(self): 
        pass
    
    

class Xerox(Animal):
    def __init__(self,planet,age):
        super().__init__(planet) #methode to call supercall constructor
        
        self.name = "Xerox"
        self.age = age
        
    def changePlanet(self,nPlanet): #Methode Overriding
        uPlanet = self.planet + "->" + nPlanet
        Animal.changePlanet(self,uPlanet ) #Good: used alrady defined function
       
        


def main():
    a = Animal("Earth");
    a.cellCount = 90 #creating variable outside class!

    print(a)
    
    #a Animak on Mars
    x = Xerox("Mars", 100)
    print(x)
    
    x.changePlanet("Saturn")
    x.changePlanet("Sun!")
    print(x)
    
    print("isAlive? ",x.isAlive())
    
    #instance of subclass
    
main()