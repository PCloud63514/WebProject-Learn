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
   1. State & Props
   2. Class Component
   3. Function Component
2. Rendering



## Component

React Native App의 구성 요소를 의미합니다.  Component의 내부를 살펴보면 **JSX.Element** 라는 것을 확인할 수 있습니다.

 View, Text, Image 등을 칭하기도 하고, 이 Component를 조합하여 하나의 Component를 만드는 등 다양한 작업을 하게 됩니다.

> Android와 IOS를 동시에 지원하는 Basic Components가 있는 반면, 각 플랫폼을 동시에 지원하지 않는 Components도 존재합니다. 
>
> [Core Components and APIs · React Native](https://reactnative.dev/docs/components-and-apis) 링크에서 개발에 필요한 Components가 각 플랫폼을 지원하는지 확인해주세요.







Component를 구현하는 다양한 방법이 존재합니다. 우선 기본적인 방법인 Class Component와 Function Component에 대해 설명하겠습니다.

> 두 방식은 구현 방식 외에도 다양한 차이가 존재합니다. 
>
> 구현 방식에 대해 소개 후, 두 방식이 어떤 점에서 차이가 나고  성능 면에서 어떤 차이를 나타내는지 확인하겠습니다.

### Class Component

이름 그대로 Class 방식으로 구현된 Component 입니다.

#### Code

```jsx
import React from 'react'

class MyComponent extends React.Component {
	constructor(props) {
		super(props)
	}
	
	shouldComponentUpdate(nextStates, nextProps) {
	
	}
	
	componentDidMount() {
	
	}
	
	componentDidUpdate() {
	
	}
	
	render() {
		return (
			<>
			</>
		)
	}
}
```



#### shoudComponentUpdate

#### componentDidMount

#### componentDidUpdate





---

### Function Component



[개발환경 구축]()

[React Native Setting up the development environment](https://reactnative.dev/docs/environment-setup)

