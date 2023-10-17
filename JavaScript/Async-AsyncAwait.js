const container  = document.getElementById('container'); //get div in HTML where we will display dynamic HTML
const insertData=(data)=>{
    const html = `<pre>Country: ${data.name.common} \nCapital: ${data.capital} \nPopulation:${data.population}</pre>`

    container.insertAdjacentHTML('beforeend',html);
}

//Build upon Promises
//Is just a synthetic suger over Promises

//IMP: async function always return a promise
const getCountryData=async (country)=>{
    //IMP: when handing via async await, we must catch error using catch blow
    //Please note that, it is same catch, we was beign handled at the end of several .then statement!!
    try{
        const response = await fetch(`https://restcountries.com/v3.1/name/${country}`);
        const [data] = await response.json();
        return data; //IMP: this will be the fufillied result of async promise
    }catch(error){
        console.log("Some Error Happened during aysnc task\n");
        return error; //Promise Rejected
    }
}

const getNeighbouringCountry = async (country)=>{
    try{
        const response = await fetch(`https://restcountries.com/v3.1/name/${country}`);
        const [data] = await response.json();

        const neighbours = data.borders;
        const nc1 = neighbours[0];
        if(nc1 === undefined)
            return new Error("No Negihbours Exist"); //IMP: You can reject from anywher in async, by throwing a Error object

        const nresponse = await fetch(`https://restcountries.com/v3.1/alpha/${nc1}`);
        const [ndata] = await nresponse.json();

        return ndata;      
    }catch(error){
        console.log("Some Error Happend during async task\n");
        return error;
    }
}

const main=()=>{
    getCountryData('india')
        .then(data=>{
            console.log("Success response: ",data)
            insertData(data);
        })
        .catch(error=>console.log("Failure Message: ",error));
    

    getNeighbouringCountry('india')
        .then(ndata=>{
            console.log("Success response: ",ndata);
            insertData(ndata);
        })
        .catch(error=>console.log("Failure Message: ",error));
}

main();