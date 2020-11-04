# IoC(Inversion of Control)?

사전적 의미는 제어의 역전 입니다.  IoC는 Spring 외에도 Container라는 개념에 함께 붙는 개념입니다.

일반적으로 프로그래밍에서 Container는 개발자를 대신하여, 직접 객체를 생성하고 관리하고 제거하는 역할을 담당합니다.

이를 IoC Container라고 하고, 개발자를 대신한다 하여 이를 제어권의 흐름이 역전되었다 표현하여 IoC라 하는 것입니다.

즉 IoC는 객체의 생명 관리, 흐름제어를  개발자가 아닌 제 3자에게 위임하는 프로그래밍 모델 입니다.



## 호출 방식

>기존 인스턴스를 생성하는 방식 들과 IoC의 차이를 확인할 것입니다.



### Class 호출방식

> Class 내에 선언과 구현이 함께 하기 때문에 종속적입니다.

![image](https://user-images.githubusercontent.com/22608825/98094381-8d6fad00-1ecc-11eb-96b9-dd0791db8fbf.png)

​																																																					이미지 출처:[Spring -IoC & DI IoC란?](https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/)

### Interface 호출 방식

> 인터페이스를 상속받아 구현하도록 변경된 모습입니다. 
>
> 아직까진 구현클래스를 변경할 경우 호출 클래스 코드에서 수정이 필요합니다.

![image](https://user-images.githubusercontent.com/22608825/98094496-ba23c480-1ecc-11eb-90e2-95dba710cd00.png)

​																																																					이미지 출처:[Spring -IoC & DI IoC란?](https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/)

### Factory 호출 방식

> IoC를 들으며 가장 생각나는 것은 Factory 패턴이였을 것입니다. 호출클래스는 단지 Factory를 호출하는 것만으로, 
>
> Factory에서 구현 클래스를 생성하기 때문에 호출클래스와 구현클래스의 종속성이 사라집니다.
>
> 하지만 결국 호출 클래스에서 Factory를 호출하기 떄문에 Factory에 의존성이 생깁니다.

![image](https://user-images.githubusercontent.com/22608825/98094629-e3445500-1ecc-11eb-90b9-7d6ecafa1c2e.png)

​																																																					이미지 출처:[Spring -IoC & DI IoC란?](https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/)

### IoC 호출 방식

> Factory 패턴의 특성을 그대로 사용하여, 의존성을 제거한 모습입니다. 서버가 실행되었을 시점에 클래스간의 관계가 형성됩니다.
>
> 여기에 나온 의존성이 삽입된다는 의미로 IoC를 DI(Dependency Injection)라고 표현합니다.

![image](https://user-images.githubusercontent.com/22608825/98094651-e93a3600-1ecc-11eb-809a-cf0e487c42fb.png)

​																																																					이미지 출처:[Spring -IoC & DI IoC란?](https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/)

###### 참고문헌

[Spring -IoC & DI IoC란?](https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/)

[백문이불여일타 IoC, DI란 무엇일까](https://biggwang.github.io/2019/08/31/Spring/IoC,%20DI%EB%9E%80%20%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C/)

