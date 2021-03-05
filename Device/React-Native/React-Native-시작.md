# React-Native 시작

React를 기반으로, Android, IOS의 네이티브 앱 개발을 하나의 프로젝트로 개발하여 생산성을 높히기 위한 프레임워크.

> 이 페이지는 React를 처음 학습하시는 분들을 기준으로 작성된 페이지입니다.
>
> 이번 내용은 React와 내용이 공유되는 점이 많습니다. 즉 [React Docs](https://ko.reactjs.org/docs/react-component.html) 를 참고해도 전혀 문제가 없습니다.



준비사항

- Android 개발 시 - Android Studio 및 SDK 설치  (adb만 설치해도 문제는 없습니다. 단 CPU, Memory NetWork Profiling을 위해서 설치해주세요.)
- IOS 개발 시 - Xcode (mac 제한)



## 목차

1. Component
   1. Life Cycle - State & Props
      1. State & Props
      2. Life Cycle
   2. 구현
      1. Class Component
      2. Function Component
2. Rendering



## Component

React Native App의 구성 요소를 의미합니다.  Component의 내부를 살펴보면 **JSX.Element** 라는 것을 확인할 수 있습니다.

 View, Text, Image 등을 칭하기도 하고, 이 Component를 조합하여 하나의 Component를 만드는 등 다양한 작업을 하게 됩니다.

> Android와 IOS를 동시에 지원하는 Basic Components가 있는 반면, 각 플랫폼을 동시에 지원하지 않는 Components도 존재합니다. 
>
> [Core Components and APIs · React Native](https://reactnative.dev/docs/components-and-apis) 링크에서 개발에 필요한 Components가 각 플랫폼을 지원하는지 확인해주세요.



### Life Cycle - State & Props

Component 는 내부적으로 특정 주기를 갖고 변화하며, 고유한 속성을 상속 및 생성하여 행동할 수 있습니다.

#### State & Props

Component를 설명하는 과정에서 이 두 가지를 제외하고 설명하는 것이 불가능합니다.

필수적으로 인지를 하고 넘어가야 하므로 간단하게 설명하겠습니다.

##### State

Component가 갖는 속성입니다. 

```jsx
...
class MyComponent extends React.Component {
	state = {
		name:'PCloud',
		year:2021
	}
}
```

예제만 보면 Member Field와 무슨 차이가 있는가?  라는 질문이 생길 수 있습니다.

State 자체가 Member Field에 속하는건 맞지만, 좀 더 상세히는 Rendering에 밀접한 관련이 있습니다.

일반적으로 State의 값이 변경 시 Component에게 Rendering 이 발생합니다.

단 Observable 한 형식의 호출은 아닙니다. (RX 의 Live Data와 다른 것을 의미합니다.)



##### Props

상위 Component로 부터 전달받은 속성입니다.

State와 뚜렷한 차이점은 **수정**이 불가능하다는 점입니다. 

당초 React의 경우 단방향 방식의 MVC 모델을 표현한 모습이기 때문에 하위 컴포넌트가 상위 컴포넌트의 값을 수정하는 등의 행동이 맞지 않습니다.

물론 수정하는 방법이 존재하기는 합니다. 직접적으로 속성에 값을 삽입할 수 없지만 상위 컴포넌트에서 속성을 수정할 수 있는 Function을 bind 하여 전달하는 방법이 있습니다. (이는 추후 설명합니다.)



#### Life Cycle 

**Constructor(props)** (생성자) 함수 호출로 Component는 시작됩니다.

> 이 단계에서 상위 Component가 전달한 **Props**를 확인할 수 있고, 초기 State를 설정할 수 있습니다.
>
> 단 사용자가 직접 new 키워드를 사용해서 생성하는 것이 아닌 React.DOM에 Mount 전 호출됩니다.



**componentDidMount** React.DOM에 Mount 되었을 때 호출됩니다. 즉 첫 Rendering 과정 입니다.

> Component가 동작하기 위한 리소스를 불러오는 곳으로 쓰입니다. 
>
> 주로 Network 와 관련된 로직을 호출합니다.
>
> Constructor 와 차이는 React.DOM에 Mount 되어 있는지 유무이며, 보통 화면 크기를 필요로 하는 경우에 React.DOM Mount 유무를 확인합니다.
>
> - state를 변경하여 rendering을 호출할 수 는 있습니다. 단 중복으로 rendering 과정이 생기며 시각적인 확인은 불가능합니다.



**componentDidUpdate(preProps)**

>Component가 Rendering 과정 이후 호출되는 함수 입니다.
>
>Rendering 이전의 Props를 전달받을 수 있으며, 이 함수에서도 State 변경을 통한 Rendering 과정을 유도할 수 있습니다.
>
>- 이럴 경우 무한히 Rendering이 발생할 수 있으므로 조건을 세워 방지해야합니다.



**shouldComponentUpdate(nextState, nextProps) return boolean**

>**State** 및 **Props**의 내용에 변화가 생겼을 경우 호출됩니다.
>
>이 함수의 목표는 변경된 내용을 확인하여 Rendering을 할지에 대해 결정하는 것입니다.
>
>보통 **Current State&Props** 와 **nextState&Props** 를 얕은 값 비교를 진행하여 서로 다를 경우 Rendering을 하도록 유도합니다.
>
>- true를 반환 시 Rendering을 진행합니다.



### 구현

Component를 구현하는 다양한 방법이 존재합니다. 

기본적인 방법인 Class Component와 Function Component에 대해 설명하겠습니다.

> 두 방식은 구현 방식 외에도 다양한 차이가 존재합니다.  (물론 두 방식 외에도 다른 구현 방식이 존재합니다.)
>
> 구현 방식에 대해 소개 후, 두 방식이 어떤 점에서 차이가 나고  성능 면에서 어떤 차이를 나타내는지 확인하겠습니다.



#### Class Component

이름 그대로 Class 방식으로 구현된 Component 입니다.

##### Code

```jsx
import React from 'react'
import { Text, View } from 'react-native'

export default class MyComponent extends React.Component {
    state = {
        name:"PCloud"
    }
	constructor({props}) {
		super(props)
	}
	
	shouldComponentUpdate(nextStates, nextProps) {
		if(this.state !== nextState || this.props !== nextProps) {
            return true
        }
        return false
	}
	
	componentDidMount() {
		this.setState({name:"User"})
	}
	
	componentDidUpdate(preProps) {
	
	}
	
	render() {
        const { title } = this.props
        const { name } = this.state
		return (
			<View style={{flex:1}}>
                <Text>{title}</Text>
                <Text>{name}</Text>
			</View>
		)
	}
}
```



#### Function Component

Function 방식으로 구현된 Component 입니다.

##### Code

```jsx
import React, { useState, useEffect } from 'react'
import { Text, View } from 'react-native' 
const MyComponent = ({title}) => {
	const [name, setName] = useState('PCloud')
    
	useEffect(()=> {
        this.setName('User')
    })
	
	return (
		<View>
            <Text>{this.title}</Text>
            <Text>{this.name}</Text>
		</View>
	)
}
export default React.Component(MyComponent)
```



#### 두 가지 구현 방식에 대한 차이점

우선 눈에 띄는 차이는 State를 선언하는 방법입니다.

**Class Component** 의 경우 state의 body를 직접 작성 후 수정을 요청 시 setState 함수 호출을 통해 변경하게 됩니다.

**Function Component**의 경우 **Hook 함수** 라고 불리는 **useState** 를 통해 state member 생성 및 set 함수를 전달받습니다.

이를 별개로 나누어 설명한 이유는 **Class Component** 에서는 **Hook 함수**를 호출할 수 없기 때문입니다.

이로 인해 만약 하위 Component에게 state를 변경하게 하기 위해선 **Class Component**는 직접 수정을 위한 Function을 생성 후 .**bind**하여 전달해야합니다.

하지만 **Function Component**의 경우 번거로운 작업 없이 생성 시 전달받은 set Function을 전달하면 해결됩니다.



Life Cycle Function 도 차이가 존재합니다.

**Class Component** 는 앞서 설명한 didMount 나 didUpdate 가 작성된 것을 확인했을 것 입니다.

하지만 **Function Component**는 **useEffect** 라는 Hook 함수가 그 자리를 대신하고 있습니다.

**useEffect** 는 **ComponentDidMount** 와 **ComponentDidUpdate**가 합쳐진 방식입니다.

즉 React.DOM에 삽입 직후 최초 Rendering과 Rendering 직후 호출됩니다. (업데이트 보장)



물론 최초 Rendering 만 하고 싶거나 특정 State 및 Props의 변화에만 반응하고 싶을 수 있습니다.

그럴 경우 다음과 같이 구현할 수 있습니다.

```jsx
useEffect(()=> {
   this.setName('User')
},
[this.title]) <--- 핵심
```

이는 Rendering 직후 호출될 때 title이 변경되었는지 확인 후 변경되었다면 호출되는 방식입니다.

만약 Rendering 직후 useEffect의 호출을 원하지 않는다면 이를 [] 이렇게 비워놓아 호출을 방지할 수 있습니다.

---



## Rendering

번외의 느낌으로 shoudComponentUpdate를 직접 작성하는 것이 아닌 자동으로 방지하는 방법에 대해 작성했습니다.

#### Class Component

PureComponent는 자동으로 Current State & Props 와 next State Props를 얕은 값 비교합니다.

```jsx
import React from 'react'

export default class MyComponent extends React.PureComponent {
	...
}
```



#### Function Component

memo 또한 동일합니다.

```jsx
import React from 'react'

const MyComponent = (props) => {
	...
}
export default React.memo(MyComponent)
```





[개발환경 구축]()

[React Native Setting up the development environment](https://reactnative.dev/docs/environment-setup)

