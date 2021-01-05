# Java Script 기초

HTML5 도입 이후 플러그인에 의존하던 구축방식을 대체한 Script 언어.



## 개발 언어 차이점



## Protorype - base

> Java Script는 C 및 Java 등과 같은 **Class 기반 OOP(Object Oriented Programming)*Language**와 차이점이 존재한다.
>
> Java Script는**ProtoType 기반 객체지향 언어**.
>
>  
>
> 단순하게 Class-less 방식으로 객체를 생성할 수 있다는 것이다.
>
> * ECMAScript 6에서 Class가 추가되었다.



#### 예시

```js
var user = {
	id: 1,
	name: 'Kwon',
	age: 26
};

console.log(user);
console.log(user.hasOwnProperty('name'));
```

#### 결과

```
{id: 1, name: "Kwon", age: 26}
age: 26
id: 1
name: "Kwon"
__proto__:
constructor: ƒ Object()
hasOwnProperty: ƒ hasOwnProperty()
isPrototypeOf: ƒ isPrototypeOf()
propertyIsEnumerable: ƒ propertyIsEnumerable()
toLocaleString: ƒ toLocaleString()
toString: ƒ toString()
valueOf: ƒ valueOf()
__defineGetter__: ƒ __defineGetter__()
__defineSetter__: ƒ __defineSetter__()
__lookupGetter__: ƒ __lookupGetter__()
__lookupSetter__: ƒ __lookupSetter__()
get __proto__: ƒ __proto__()
set __proto__: ƒ __proto__()

true
```



Java Script의 모든 객체는 **[[Prototype]]** 이라는 **Internal Slot**을 가진다.

```
Internal Slot: 내부 속성(은닉 속성)
get 접근은 가능하며, set 접근은 불가능.
```



### [[Prototype]] 및 Prototype 차이점

| [[Prototype]]                                                | Prototype                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| - 함수를포한한 모든 객체가 갖고 있는 Internal Slot<br />- 객체의 입장에서 자신의 부모 역할을 하는 프로토타입 객체를 가리킨다.<br />- 함수 객체의 경우 Function.prototype을 가리킨다. | - 함수 객체만 갖고 있는 Property<br />- 함수 객체가 생성자로 사용될 때 이 함수를 통해 생성될 객체의 부모 역할을 하는 프로토타입 객체를 가리킨다. |



### Constructor Property

생성자 프로퍼티. 타 언어와 목표는 같으며, Prototype 객체는 Constructor Property를 갖고 있다.

이 Constructor Property는 객체의 입장에서 자신을 생성한 객체를 가리킨다.



### Prototype Chain

객체의 property를 참조하는 과정에 해당 객체에 property가 없을 경우 동적으로 추가시킨다. 있을 경우 재할당한다.