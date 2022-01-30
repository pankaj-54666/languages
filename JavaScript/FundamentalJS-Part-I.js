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

function main()
{
    //variableNamingConvention();
    dataTypes();
}

main()
