const container  = document.getElementById('container'); //get div in HTML where we will display dynamic HTML
const insertData=(data)=>{
    const html = `<pre>Country: ${data.name.common} \nCapital: ${data.capital} \nPopulation:${data.population}</pre>`

    container.insertAdjacentHTML('beforeend',html);
}


//Async is Vanilla JS
const getCountryData=(country)=>{

    const request = new XMLHttpRequest();
    request.open('GET',`https://restcountries.com/v3.1/name/${country}`);// https://restcountries.com/#api-endpoints-v2-all//
    request.send(); //send the query over network

    //IMP: Register event lister, which will be called when data is loaded from network call!
    request.addEventListener('load',()=>{
        const [data]  = JSON.parse(request.responseText); //get the data!
        console.log(data); 

        //Make a HTML from received data
        const html = `<pre>Country: ${data.name.common} \nCapital: ${data.capital} \nPopulation:${data.population}</pre>`

       container.insertAdjacentHTML('beforeend',html);
    });

    /* Code Explanation
    XMLHttpRequest is created first -> then .send reqeust the data -> 
    -> then just as we listen to button click event, here we listen to 'load' event on XMLHttpRequest object
    ->Once data is loaded, browser will call the the event listner
    -> inside the event listern, we will write our logic as what to do with the data
*/
}

/*
    To get the neighbouring you need to do following operation IN SEQUENCE:
    (a) Get country details & Its neighbouring list using network call
    (b) For all neighbouring country, do networkCall again to get their details.

//IMP: Here we are going 2-depth, what if we need do 5-depth? -> The code will look very much nested. & that what is known as callback-hell

*/
const getNeighbouringCountry=(country)=>{

    const reqeust  = new XMLHttpRequest();
    reqeust.open('GET',`https://restcountries.com/v3.1/name/${country}`);
    reqeust.send();

    reqeust.addEventListener('load',()=>{
        const [data] = JSON.parse(reqeust.responseText);
        const neighbours = data.borders;
        console.log(`Neighbours of ${country} is/are: ${neighbours}`);
        
        neighbours.forEach(nc => {
            
            const reqeust2 = new XMLHttpRequest(); //IMP: 2nd Async Call
            reqeust2.open('GET',`https://restcountries.com/v3.1/alpha/${nc}`);
            reqeust2.send();

            reqeust2.addEventListener('load',()=>{
                const [data] = JSON.parse(reqeust2.responseText);
                console.log(`Info about ${data.name.common}: Capital: ${data.capital}`);
                insertData(data);
            })
        });
    })

}


const main=()=>{
    
    //Single Ajax Call Example
    //getCountryData('india'); //<= get data in async syle <= this is non-blocking
    //getCountryData('russia');


    getNeighbouringCountry('india');
}

main();