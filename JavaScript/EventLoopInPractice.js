// The Event Loop in Practice
//Reference Image(MUST to understand code): JSEventLoop.png


console.log('(1) Test start'); //Printed First, as all task in CallStack get executed

setTimeout(() => console.log('(5) 0 sec timer'), 0); //scheduled in CallbackQueue

Promise.resolve('(3) Resolved promise 1').then(res => console.log(res)); //scheduled in MicroTaskQueue

Promise.resolve('(4) Resolved promise 2').then(res => { //Scheduled in MicroTask Queue (comes after promise1 in queue)
  for (let i = 0; i < 1000000000; i++) {}
  console.log(res);
});

console.log('(2) Test end'); //In Current Callback => will get executed 2nd