
//const print = process.stdout.bind.write;

const sum = (a, b=10)=>{
    console.log(`a = ${a}, b = ${b}\n`);

}

const mySum = sum;

function main()
{
    console.log("Hello Main\n");

    let arr = ["pankaj","Kumar",90,true]
    //console.log(arr)
    arr.forEach(e=>{
        console.log(e)
    })

    console.log("Before sorting\n");
    console.log(arr);
    arr.sort();
    console.log("After sorting\n")
    console.log(arr);

    //print("Without New Line")

    mySum(20,50);
    sum(20)
}


main();