# Type vs Interface

TypeScript 적응 도중 Type과 Interface 차이에 대해 정확히 알고 넘어가고자 작성하였습니다.

선언 방식도 비슷하고 사용하는 방법도 비슷한데 대체 어느 부분에서 차이가 생겨 두 가지로 구분된 것일까요?



## 한번에 해결될 사진

문서를 작성하던 도중 발견했습니다. 매우 깔끔하게 정리되어 있어서 굳이 추가적으로 문서작성을 필요로하진 않지만 혹시 이해가 안된다면 아래의 내용과 함께 보면 됩니다.

![image](https://user-images.githubusercontent.com/22608825/105665056-7c599980-5f19-11eb-93a4-65944832401c.png)





## 엇 비슷한 공통점

완벽히 공통적이진 않더라도, 아 비슷하네.. 정도의 공통적인 사항이 존재합니다.

### 선언 및 적용 방법

```tsx
type T1 = {
	key:String,
	value:String
}

interface I1 {
	key:String,
	value:String
}


const typeTest:T1 = {
    key: 'k1',
    value: 'value1'
}

const typeTest2:I1 = {
    key:'key2',
    value:'value2'
}
```



선언에선 **type** 인지 **interace** 인지의 **키워드** 차이가 있을 뿐 적용하는 방법은 동일합니다.



### extends & implements

```tsx
type T1 = {
    key:String,
    valaue:String
}

type T2 = {
    ket2:String,
    value:String
}

type T3 = T1&T2& {
    //내부적으로 key, key2, value 가 병합되어 있다. 곱 연산
}

type T4 = T1|T2| {
    //내부적으로 key, key2, value 가 병합되어 있다. 합 연산 (당연히 곱, 합연산은 섞어 사용할 수 있다.)
}

interface I1 {
    key:String,
    value:String
}
//...

interface I2 extends T1 {
    // 내부적으로 key, value 병합되어 적용. 
}



class C1 implements I1 {
    constructor(public key:String, public value:String) {
        
    }
}



```



### 차이점

작성법도 크게 차이가 없고 내부적으로 동작도 차이가 보이지 않습니다. (공식문서를 보니 동작방법도 차이가 없다고 합니다.)



#### 선언 병합

컴파일 단계에서 interface의 이름이 같을 경우 병합한다 하여, 선언병합이라 합니다.

저도, 조사 과정에서 처음 듣는 단어였지만 나름 명확한 단어라 이해가 어렵진 않았습니다. 

```tsx
interface A {
	key:String
}
interface A {
	value:String
}

//..... Compile 후 내부적으로
interface A {
	key:String,
	value:String
}
```



TypeScript Team은 **개방-폐쇄 원칙** 을 지키기 위해 JavaScript 객채의 동작 방식과 비슷하게 연결할 수 있도록 interface를 설계했습니다.

이를 바탕으로 TypeScript Team은 개발 과정에 **interface**를 우선적으로 사용하고, union(합 타입), tuple을 피할 수 없는 상황에 **type** 을 사용하도록 권장하고 있습니다.



## 마치며

그렇다할 큰 차이점은 못느끼겠고, type과 interface가 왜 따로 존재하는지 궁금하지 않으면 모르고 지나가도 개발에 문제가 없을 듯 합니다.





## 참고문헌

#### [Type vs Interface, 언제 어떻게?. TypeScript에서 Type Alias와 Interface의 차이를… | by 남현욱 | 휴먼스케이프 기술 블로그 | Medium](https://medium.com/humanscape-tech/type-vs-interface-언제-어떻게-f36499b0de50)