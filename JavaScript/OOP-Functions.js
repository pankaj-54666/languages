
/*
When below function is called with `new` keyword, below are the steps which happen in sequence.
(1) New object {} is created
(2) function is called with this set to {}
(3) linked to prototype
(4) function automatically returns the {}

*/
function Person(name,age){
    this.name = name;
    this.age = age;

    //IMP: do not create function inside construction function. As it will create a copy of function to every instance
    //which is waste of memory

    /*
    this.calAge=function(){
        return 2037-age;
    }
    */
}

// attributes & methode of Personal Construction Function

//IMP: ES6 arrow function cannot have 'this' keyword, hence we need to use normal function here
Person.prototype.calAge = function(){
    console.log(2037-this.age);
}
Person.prototype.species = "Home Sapiens"

// Static Methode (accessible via Person.hey(), but not via user.hey())
Person.hey = function(){
    console.log("hello");
}

//Tesiting Above Code
const user = new Person("Ramesh",2022); //Creat New user
console.log(user);

console.log("user.hasOwnProperty('name'): ",user.hasOwnProperty('name')); //user owns this property
console.log("user.hasOwnProperty('species'):",user.hasOwnProperty('species')) //user do NOT owns this (as this is prototype attribute)

user.calAge();  //we can call own + prototype's methode + attribute on user object

//Check Instance
console.log("is user instance of Person:",user instanceof Person);

//Check if user & Person Prototype are same (you can use any of them)
console.log("prototype of user & Person: ",user.__proto__ === Person.prototype);
console.log("prototype of user & Person: ",Person.prototype.isPrototypeOf(user));

//Checking static
// user.hey(); //Runtime error, as static methode cannot be called on instance
Person.hey(); //okay


//=== INHERITCANE ==

const Student = function(name,age,course){
    Person.call(this,name,age); //IMP:Call Parent class Function
    this.course = course; //Student specific property
}

//Linking Prototype
Student.prototype = Object.create(Person.prototype); //VERY IMP: make Student prootoype same as person

Student.prototype.introduce = function(){
    console.log(`My name is ${this.name}`);
}

const student = new Student('Ramesh',23,'ECE');
student.introduce();

student.calAge(); //IMP: see PrototyChain.png image
//the function lookup will start from botton of prototyep chain

console.log(student instanceof Student); //true
console.log(student instanceof Person); //true
