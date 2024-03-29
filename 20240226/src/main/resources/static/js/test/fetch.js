
console.log('시작');
//프라미스객체 : 1.상태정보(pending, fulfilled, rejected) 2. 처리결과정보(성공, 실패)
const promise = new Promise((resolve,reject)=>{

  //비동기로직 위치하는곳
  
  // 비동기 로직 성공하면
  if(true){
    resolve('처리결과');
  }else{
    reject('실패결과')
  }
});

promise.then(res=>{console.log(res); return 1;})
.finally(() => console.log('실패유무와 상관없이 실행 1'))
.then(res=>{console.log(res); return new Error('오류1')})
.then(res=>{console.log(res); return 3;})
.finally(() => console.log('실패유무와 상관없이 실행 2'))
.then(res=>{console.log(res); })
.catch(err=>console.log(err));

console.log('끝');