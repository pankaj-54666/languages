'use strict'

//const testFunctionOne = testFunction(); //Forward Function Declaration

//Function Defination
function testFunction(name,age,hoobies)
{
    console.log("userName = %s, age = %d",name,age) //C style Printing
    
    let userInfo = `userName = ${name}, age = ${age}\n` //Or we can use template prining
    console.log(userInfo)

    console.log("Hobbies of user is\n")

    //for loop of array
    hoobies.forEach(ele => {
        console.log(ele)
    });
}

//Function Expression
let testFunctionTwo  = function(name)
{
    console.log("testFunctionTwo userName = ",name)
}

//Arrow Function 
const testFunctionThree = (name)=>{
    console.log("testFunctionThree userName = ",name);
}

//Shorted form of arrow function, 2037-birthYear will be returned
const testFunctionFour = (birthYear)  => 2037 - birthYear

function functionTest()
{
    testFunction("Pankaj",56,["chess","VitaminD"]);

    testFunctionTwo("Tree");
    testFunctionThree("Four");

    console.log(testFunctionTwo)

    testFunctionTwo = 56; /*Allowed type are associated with value */

    console.log(testFunctionTwo)
}

function arrayTest()
{
    const hobbies  = ["Chess","VitaminD", 67,89,true] /*Way1 Array Declaration */
    hobbies.forEach(elem =>{
        console.log(elem + " " )
    })

    let peoples = new Array("Sam","Tommy", "Kutta!", 68,90,false) /*Way2 Array Declaration */
    console.log("Printing peoples\n");
    peoples.forEach(elem =>{
        console.log(elem)
    })

    //Adding element to array
    peoples[0] = "Tina" //updating value 
    peoples.push("890") //inserting elements
    console.log(peoples)

    //Methodes on array elements


}


function main()
{
    //functionTest();
    arrayTest();
   
}

main();