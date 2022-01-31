
const objectDestructure = ()=>{
    console.log("Object Destructuring");

    const userData = {
        name: "Pankaj",
        age:27,
        info:{
            email:"xyz@gmail.com",
            linkedIn: "xyz.LinkedIn"
        }
    };

    const {name} = userData; 
    const {age, info:{email:userEmail,linkedin}} = userData;

    console.log(`Name = ${name} Age = ${age}, email = ${userEmail}`)

    //Function Parameter Destrucring
    function printName({name:userName}){
        console.log("printName " + userName)
    }

    printName(userData) //Passing Whole Object

}

function spreadOperator()
{
    console.log("Soread operator");
    const userData = {
        name: "Pankaj",
        age:27,
        info:{
            email:"xyz@gmail.com",
            linkedIn: "xyz.LinkedIn"
        }
    };

    const userExtraData = {bestFriend:"Tom", ...userData}
    console.log(userData);
    console.log(userExtraData);

    //working with array
    console.log("\nArray Spread");
    const hobbies = ["Chess","VitaminD",89];
    const updateHobbies = [false,true, ...hobbies]; //sprea via shallow
    console.log(hobbies);
    console.log(updateHobbies);

    //working with string
    console.log("\nString spread")
    const str = "Pankaj";
    const letters = [...str];
    console.log(str)
    console.log(letters)
}

function main()
{
    //objectDestructure()
    spreadOperator();
}

main()