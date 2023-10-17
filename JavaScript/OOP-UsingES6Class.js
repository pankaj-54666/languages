
//class expression
/*const Person = class{};*/

//class declaration

class Person{
    constructor(name,age){
        this.name = name;
        this.age= age;
    }

    //Instance Methodes: will be attached to Person prototype (access via instance.calAge())
    calAge(){
        console.log("age: ",2037-age);
    }

    //Static Methodes: Cannot be accesses via object instace. Can be accessied on Class Directly (ex: Person.hey())
    static hey(){
        console.log("I cannot be called on object instance");
    }

    //JS related functions
    //IMP: `get` and `set` suffix are MUST
    //access like user.dateOfBirth
    get dateOfBirth(){
        return this.age-2000;
    }
    set dateOfBirth(dob){
        age = dob + 2000;
    }
}

const user = new Person('Ramesh',2024);
console.log(user);

//IMP: using getter, we can read them as property, without specfying the brackets
console.log(user.dateOfBirth);

//==INHERITANCE==

class Student extends Person{
    constructor(name,age,course){
        super(name,age); //call parent class construction
        this.course = course;
    }

    //instance methodes
    introducde(){
        console.log(`My Name is ${this.name}`);
    }

    //Override parent class methodes
    calAge(){
        return 2050-this.age;
    }
}

//== Private Properties & Proteched Methodes ==

class Country{
    construction(name,money){
        this.name=  name;
        this._money = money; //IMP: convention to use like _ to denote the developer that this is private member & should not be edited from outside

        //this.#money = money; //Experimental JS feature(for primate attribute)
    }

    _fireMissile(){ //Private Methode Convention
        console.log("Missile Fires");
    }
}

