# React-Native Warning

RN을 이용한 프로젝트 개발 도중 발생하는 Warning에 대한 해결방법을 작성한 문서입니다.



## Warning: Each child in a list should have a unique "key" prop.

Child Component를 map 또는 Array와 같은 객체에서 관리할 때 발생하는 경고입니다.

에러 그대로 child Component에게 unique한 key를 주지 않아 발생하는 문제이기 떄문에 key를 주는 것으로 해결할 수 있습니다.

```jsx
import React from react
import { Text, View } from 'react-native'
data = {
	{ name:'A' age:25 },
	{ name:'B' age:26 },
	{ name:'C' age:27 },
}

const App = () => {
	# const addInfoTexts = () => (Object.entries(Screens).map((data, index)=> <Text}> {data.name} </Text>))
    const addInfoTexts = () => (Object.entries(Screens).map((data, index)=> <Text key={index}> {data.name} </Text>))
    
	return (
		<>
			{ addInfoTexts() }
		</>
	)
}
export default App
```

