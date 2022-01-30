//Code Examples of Chapters

//Practice Code

//Question Chapter wise in excel


function variableNamingConvention()
{
    console.log("ENTER variableNamingConvention\n")
    let userName = "Pankaj"
    console.log(userName)
}

function dataTypes()
{
    console.log("dataTypes()")
    let userData  = {
        name:"Pankaj",
        age:27,
        isLoggedIn:true,
    }
    console.log(userData)

    console.log("userData type is ", typeof(userData.name), " || ", typeof userData.name)

    userData.name = 9800; //JS is dynamcially, and type are associated with value not variable
    console.log(userData.name)

    //let,const and var
    let book = "JS2020" //ok,book is mutable
    const person = "Pankaj" //Immutable variable
    book = "343" /*allowed*/
    book = 90 /*allowed*/

    //person = "Hello" /*NOT allowed*/
    //const l;  //const must be initilized

    planet = "Earth" //A Global Object!! <= Should be avoided if not the need of global

}

function stringData()
{
    let s1 = "Pankaj \n Kumar \n Hello" // 
    let s2 = "Pankaj \n\ Kumar \n\ Hello" //Before ES6
    let s3 = `Pankaj
     Kumar
    Hello`;

    //S3 is an example of Template String

    console.log("s1 = ",s1)
    //console.log(s1,s2,s3)

    console.log("s2 = ",s2)

    console.log("s3 = ",s3)

    let userData  = {
        name:"Pankaj",
        age:27,
        isLoggedIn:true,
    }

    const userStr = `name = ${userData.name}, age = ${userData.age}` //using string template!
    console.log("userData object is\n",userStr) 

}

function main()
{
    //variableNamingConvention();
    //dataTypes();

    stringData()
}

main()
