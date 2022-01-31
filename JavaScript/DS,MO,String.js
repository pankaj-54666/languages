
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

    

}


function main()
{
    objectDestructure()
}

main()