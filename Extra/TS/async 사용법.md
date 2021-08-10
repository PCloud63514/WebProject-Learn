# async 사용법

네트워크 통신을 위한 비동기 로직을 작성할 때 async 작성을 피하기 힘듭니다.



기본적으로는 **then, catch 함수**를 사용해서 비동기 로직에 대비할 수 있지만 이는 무수히 많은 콜백 체인이 생성될 수 있습니다.

```
function callbackSample() {
	call().when(()=> {
		call2().when(()=> {
			call3().when(()=> {
			....
			})		
		})
	})
	.catch(()=> {
	
	})
}
```

위와 같은 문제를 Promise 패턴이 해결했습니다.

Promise 는 ES6에 등장한 비동기 처리를 효과적으로 할 수 있는  패턴으로, C#을 사용했던 개발자라면 익숙한 await과 async를 사용하는 방식입니다.

Promise는 대기(pending), 이행(fulfilled), 거부(rejected) 3가지의 상태를 통해 비동기를 구현합니다.

[설명 링크](https://github.com/PCloud63514/WebProject-Learn/blob/757739a467caa864acf639537645cac0edaa01bc/Front/React/3.React%20%EC%8B%9C%EC%9E%91.md#promise)



간단히 코드로 표현해봅시다.

```
async function getUser(Long id):Promise<User> {
	var response = await rest.request("URL",...);
	return User.parse(response.data);
}
```

예외처리라고는 하나도 되어 있지 않는 코드라 async, Promise await 만 주목해주세요.

async 키워드를 붙임으로 써 비동기 메서드임을 명시하였고, 반환타입에 Promise를 붙여 비동기 로직에 대한 결과를 반환하는 것을 명시했습니다.

이 때 async 키워드를 붙혔다면 반환타입은 Promise가 되어야합니다.

또한 비동기 로직을 호출할 때 await 키워드를 작성하여 콜백에 대한 대기를 이행할 수 있습니다.