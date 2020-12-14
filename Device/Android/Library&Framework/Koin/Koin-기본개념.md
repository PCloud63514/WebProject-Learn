# Koin-기본개념

Koin은 Kotlin의 [DI]() 를 위한 Library 입니다. (Java의 Dagger )

[insert-koin.io · a smart Kotlin dependency injection framework (insert-koin.io)](https://insert-koin.io/)

- 진행 전 [DI]()에 대해 이해를 해야합니다.



>사용에 대한 이유는 결국 DI를 해결하기 위함입니다.
>
>장점은 DI의 장점과 일치합니다.

  

## DSL (Domain-Specific Languages)

Koin을 사용하기 위해선 DSL 이라는 언어에 대해 이해해야합니다. 내부적으로 DSL의 다양한 키워드를 사용하며 이를 숙지해야합니다.

도메인 특화 언어(DSL) 를 해석하면, 도메인 - (산업, 특정 영역, 서비스 영역)  에 대해 특화된 언어 입니다.

여기서 주목해야할 것은**특화**입니다. C, Java, C# 과 같은 범용적 개발생산이 가능한 언어를 뜻하는 것이 아니라 오로지 한 가지 일만 할 수 있는 것을 의미합니다.



- SQL의 query는 DB 데이터 참조에 **특화**된 문법
- HTML의 CSS는 Style 작성에 **특화**된 문법
- Java의 ANT, Maven 등 패키지 관리에 **특화**된 문법
- 각 언어의 Regular expression(정규식)은 언어 내부의 재사용가능한 공식 작성에 **특화**된 언어



더 많은 DSL이 있지만 공통적으로 한 가지 목적(도메인)에 특화된 언어를 의미합니다.

딱 이정도까지만 이해하면 Koin 학습 진행은 문제가 없을 것입니다. 

-  [도메인 특화 언어(DSL)에 관한 설명 | JetBrains MPS](https://www.jetbrains.com/ko-kr/mps/concepts/domain-specific-languages/) 에서 이해를 하시는 방법도 좋은 방법입니다.



### Koin DSL Keyword

> Koin을 사용 시 간단하게 구현이 가능한 DSL의 키워드 들은 아래와 같습니다.
>
> 사용법은 다음 Koin 적용 문서에서 작성됩니다.

   

#### Module

> Koin을 통해 제공할 객체의 명세입니다.

#### viewModel

> Activity 및 Fragment에 각 ViewModel 주입합니다.

#### factory

> DI(Inject / get) 시점마다 새로운 Instance를 매번 생성합니다. (AAC 방식 Dagger의 Provider 같은 개념)

#### single

> 해당 객체를 싱글톤으로 생성하여 DI하는 방식입니다. (App Life Cycle 동안 Single Instance)

#### scoped

> Scope 내에서 Single Instance 를 반환합니다.

#### bind

> 생성할 객체를 다른 Type으로 Binding(Class, Interface 상속 관계가 필요합니다.)

#### get()

> Component 내에서 알맞은 의존성을 주입합니다.

#### inject()

> by 키워드를 통해 의존성을 주입합니다. val 변수에만 사용할 수 있습니다.



# 참조문헌

[도메인 특화 언어(DSL)에 관한 설명 | JetBrains MPS](https://www.jetbrains.com/ko-kr/mps/concepts/domain-specific-languages/)

[랄라라 :: DSL(Domain Specific Language) 이해하기 (tistory.com)](https://unabated.tistory.com/entry/DSLDomain-Specific-Language-이해하기)



[DSL이란? (tistory.com)](https://lannstark.tistory.com/13)