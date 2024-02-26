{
 console.log('시작');
console.log('실행중');

  let sum = 0;
  for(let i=0; i<9999999; i++) sum+=i;
console.log(sum);
console.log('끝');
}
 console.log('----------------------')
// {

//   console.log('시작');
//   console.log('실행중');
  
//   async function method1(){  
//     const result = await new Promise((resolve,reject) =>{
//       let sum = 0;
//       for(let i=0; i<9999999; i++) sum+=i;
      
//       resolve(sum);
//     })
//   }
//     console.log('끝');
// }

{

  console.log('시작');
  console.log('실행중');
  
  async function method1(){  
    
      let sum = 0;
      for(let i=0; i<9999999; i++) sum+=i;
          
    return sum;
}

method1()
.then(res => console.log(res));
console.log('끝');



}
  
