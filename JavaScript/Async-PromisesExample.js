//const container  = document.getElementById('container'); //get div in HTML where we will display dynamic HTML
//Promise Use Steps
/* 
(a) Build Promise
(b) Consume Promise
*/

//Promise LifeCycle
/*                
            Fufilled
            /
Promise ---
            \
            Rejected
*/

const insertData=(data)=>{
    const html = `<pre>Country: ${data.name.common} \nCapital: ${data.capital} \nPopulation:${data.population}</pre>`

    container.insertAdjacentHTML('beforeend',html);
}
//Consume Promise Example
const getCountryData = (country)=>{
    fetch(`https://restcountries.com/v3.1/name/${country}`)
        .then(response =>{
            return response.json(); //.json() is also async ==> Just return this Promise so that it can be chained using .then in next code (it will result in cleaner code)
            //we could have also handled it here, but it would not be a clean code
        })
        .then(data=>{
            console.log(data);
            insertData(data[0]);
        })
        .catch(error=>{ 
            //IMP: this will catch any error from above async task
            console.log("ERROR! Bhago!");
            return error; //IMP: we return the error, so that the caller function take case as what shold the application do in this case
        })

    //Note: fetch return a promise
}


const getNeighbouringCountry=(country)=>{
    fetch(`https://restcountries.com/v3.1/name/${country}`)
        .then(resposne => resposne.json())
        .then(data=>{
            const neighbours = data[0]?.borders;
            
            //IMP: Reject promise by throwing error!
            if(neighbours===undefined)
                return new Error(`Neighbours Not Found`);

            const nc1 = neighbours[0];
            
            //IMP: Please do not handle this promise here, as it will un-necessary create extra depth at this level(& result in bad code)
            return fetch(`https://restcountries.com/v3.1/alpha/${nc1}`); //get any-one neighbour for current example
        })
        .then(response => response.json()) //IMP: Instead of going in depth, now we are going in height. (Which is somewhat imporved code, as its easy to follow the flow)
        .then(neighbourData =>{
            insertData(neighbourData[0]);
        })
        .catch(error=>{
            console.log("Some Error Occurred");
            console.log(error);
            return error;
        })
}

const buildPromises=()=>{

    const lottery = new Promise((resolve,reject)=>{

        //do Work (a) In success case call resolve (b) In Failure case call (b)
        //all work here are SYNCHRONOUS
        setTimeout(()=>{
            //do work

            if(true)
                resolve("Success");
            else
                reject("Failure")
        },1000)
    }) // lootery promise end here


    lottery
        .then(response => console.log(response))
        .catch(error=> console.log(error));

    //We build promises, to wrap old callback bases functions.
    //example blow

    /*We'd rather use promises, and sometimes in order to do that(for other library that is not yet moved to Promises),
     we need to turn callback-based functionality into promise-based by ourselves*/
     
     /*Because in modern code, most developers have moved to using promises and async/await 
     (which will be taught in a few lectures from now) rather than callbacks.
     Hence, we will need to promisify a little*/
    const lotteryPromisified = (seconds)=>{
        //do data transformatio (no asycn here )

        return new Promise((resolve,reject)=>{
            
            setTimeout(()=>{
                //do late work

                if(true)
                    resolve("Success!")
                else
                    reject("Failure!");
            },seconds*1000)
        })
    }; //lotteryPromisifed end

    lotteryPromisified(2)
        .then(response => console.log(response))
        .catch(error=>console.log(error));
}

const main=()=>{
    //getCountryData('India');

    //getNeighbouringCountry('india');

    buildPromises();
}

main();