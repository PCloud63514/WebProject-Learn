# AAC(Android Architecture Componets) 기본개념

Android는 동작 시 다양한 Component(Activity, Fragment, Service, BroadcastReceiver, Provider) 가 각자의 Life Cycle을 갖고 활동합니다.

각 Component의 Life Cycle로 생기는 문제를 줄이기 위해 매번 어느 영역에서 메모리를 해지하고 순서대로 종료해야 안전할지 고민해야했습니다.

이를 개선하고 해결하기 위해 만들어진 라이브러리가 바로 **AAC** 입니다.

  

## Library 소개

AAC는 총 5개의 Library로 구성되었습니다.

### 1. Life Cycles (Easy handling lifecycles)

> 생명주기 모니터링 기능을 갖고 있는 Library입니다.



#### Lifecycle Owner

> Lifecycle Owner는 Activity, Fragment 의 생명주기를 분리하여 Lifecycle 객체에 담은 것을 의미합니다.
>
> Lifecycle 객체를 통해 해당 Component의 생명주기를 모니터링할 수 있습니다.
>
> - 모니터링과 별개로 화면 내에서만 생명주기에 따른 동작을 정의할 수 있습니다.

```

```

#### Lifecycle Observer

> **Lifecycle Owner**가 단순히 Listener를 통해 모니터링하는 것이라면 **Lifecycle Observer**는 화면 밖에서도 생명주기에 따른 동작을 정의할 수 있습니다.
>
> 이를 위해 원하는 Component에 LifecycleObserver 인터페이스를 구현하고, 넘겨받은 Lifecycle Owner 객체에 구현한 LifecycleObserver를 등록해야합니다.

```

```

   

### LiveData (Lifecycle aware observable)

> Data를 Obserable 로 Wrapping하여 Lifecycle 및 데이터 변화를 감지합니다.
>
> 핵심은 Component의 생명주기가 active 상태에서 data의 update를 진행하기 때문에 UI와 데이터 상태를 일치시킬 수 있다는 점입니다.
>
> - UI가 데이터 상태와 일치시키는 것을 보장한다.
>   - LiveData는 Observer 패턴을 따르며, Component의 생명주기에 변경이 일어날 때마다 Observer 객체에 알립니다.
>   - Observer 객체를 사용하면 UI를 변경해야하는 플루코드를 통합하여 관리할 수 있습니다.
>
> - 메모리 누수에 효과적이다.
>   - Component의 Lifecycle 객체와 결합되어 있기 때문에 Component의 Destroy와 함께 메모리 해지가 발생합니다.
> - Lifecycle 의 stop 상태의 activity 와 Crash가 발생하지 않습니다.
>   - Observer의 생명주기가 inactive일 경우 Observer는 LiveData의 이벤트를 수신하지 않습니다.
> - 항상 최신의 데이터를 유지합니다.
> - 화면 구성이 변경되어도 데이터를 유지합니다.
>   - 화면 회전같은 activity가 새로 그려지는 이벤트도 LiveData는 회전하기 전의 최신 상태를 즉시 가져옵니다.
> - Resource를 공유할 수 있습니다.
>   - LiveData를 확장하는 클래스를 작성하여 싱글톤 패턴으로 관리하면 글로벌하게 사용할 수 있습니다.

##### Override Method

- onActive : 1개 이상의 active observer
- onInactive :  0개의 active observer

```

```

##### Call Method

- setValue :  Observer의 Callback을 호출하는 함수입니다.  UI Thread에서 동작합니다.

- postValue: Observer의 Callback을 호출하는 함수입니다.  Background Thread에서 동작합니다.



### 2. ViewModel (Managing data in a lifecycle)

> MVVM Pattern 에 등장하는 ViewModel 입니다. 동일하게 View와 Repository를 이어주고 데이터를 갱신 및 임시보관하는 역할입니다.
>
> 단 Singleton Pattern 등의 독립 인스턴스 처리를 하지 않는다면 ViewModel은 View에 종속되어 View의 Lifecycle에 직접적인 영향을 받을 것이며 소실까지 고려할 수 있습니다.
>
> - koin, Dagger2 등을 이용해 single 연산자로 문제를 피할 수 있습니다.
>
> 하지만 위의 경우는 View(Activity, Fragment 등) 에 직접 viewModel을 선언하고 생성했을 경우이며, AAC의 ViewModel을 상속할 경우 달라지게 됩니다.
>
> AAC의 ViewModel을 상속받을 경우 ViewModelProviders로 Scope(범위)를 관리할 수 있습니다. 
> 해당 Scope(범위) 내에서는 하나의 Instance(Singleton) 만 유지하며 작업의 중복 및 데이터 소실을 방지합니다.

#### 4. Room (Object Mapping for SQLite)

#### 5. Paging (Gradually loading information)



## 참고문헌

[[Android\] AAC ViewModel과 찰떡 궁합! LiveData 이해하기 :: 준비된 개발자 (tistory.com)](https://readystory.tistory.com/101)