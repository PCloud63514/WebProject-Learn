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

> **Lifecycle Owner**가 단순히 Listener를 통해 모니터링하는 것이라면 **Lifecycle Observer**sms 



#### 2. LiveData (Lifecycle aware observable)

#### 3. ViewModel (Managing data in a lifecycle)

#### 4. Room (Object Mapping for SQLite)

#### 5. Paging (Gradually loading information)

