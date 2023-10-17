//Manually set the prototype of newly created object


const Person ={
    //Construction Replacement
    init(name,age){
        this.name = name;
        this.age = age;
    },

    //Instance Methodes
    calcAge(){
        console.log(2037-this.age);
    }
}

//Static Methode
//??

const user = Object.create(Person);
user.init('Ramessh',2022);

user.calcAge();

//user.hey();

//==INHERITANCE==

const Student = Object.create(Person);
Student.init = function(name,age,course){
    Person.init.call(this,name,age);
    this.course = course;
}

const student = Object.create(Student);
student.init()
