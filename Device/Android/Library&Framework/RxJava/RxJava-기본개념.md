# RxJava-기본개념(부제:RxAndroid-기본개념)

RxJava(ReactiveX Java)를 이해하기 위해선 정리해야하는 지식들이 많은 편입니다.

- 동기 & 비동기
- Observable & Observer

구현코드보단 추상적인 개념을 학습한다 생각해주세요.  이를 설명하고 진행하겠습니다. 

이해하고 계신다면 바로 아래 ""로 이동해주세요.



### 동기(Synchronous) & 비동기(Asynchronous)

>**동기**를 직역하면 **동시에 일어나는** 입니다. 요청(Request)이 발생하면 결과(Result)가 동시에 발생하는 것을 동기라 합니다.
>
>**발생** 을 이해하기 어렵다면, 요청을 했다면 결과가 반영되기 전까지 기다리는 것을 의미합니다. (이를 **블록 상태** 라 합니다.)
>
>* (원자성(Atomicity)느낌을 떠올리면 될듯 싶습니다. 요청 발생 시 무조건 결과도 함께해야한다. 의 느낌입니다.)
>
>특징은 요청에 대한 결과처리가 지연될 경우 어떻게 대처할지 생각해야한다는 점입니다. (결과를 전달 받지 못한다면 끝나지 않기 때문이죠.)
>
> 
>
>**비동기**를 직역하면 **동시에 일어나지 않는** 입니다. 요청(Request)이 발생하더라도 결과(Result)가 동시에 발생하지 않는 것을 비동기라 합니다.
>
>동기와 반대로, 요청 후 결과를 기다리지 않고 다른 작업을 할 수 있음을 의미합니다. (이를 **논블록 상태** 라 합니다.)
>
>특징은 멀티 스레드 환경에서 동기화 및 데드락 문제가 발생할 수 있습니다.

   

이를 설명한 이유는 RxJava는 다수의 비동기를 사용해 동시성을 목표로한 언어이기 때문입니다.

  

### Observable & Ovserver

> Observable을 직역하면 **관측 가능** 입니다. Observer 패턴을 알고 있다면 이해가 편할 것 입니다.
>
> Observable은 DataStream 즉 데이터 생성이 이루어지는 객체를 의미합니다. 
>
> Observer는 Observable의 DataStream 동작에 반응하는 객체를 의미합니다.
>
>  
>
> Observable는 자신을 구독(관측)하는 Observer에게 순차적으로 변경된 Data를 전달합니다.



## 생성 연산자

> [ReactiveX - Operators](http://reactivex.io/documentation/ko/operators.html) 사이트는 한글로 작성된 Docs가 존재합니다.  아래 작성된 내용은 반복되는 내용이니 사이트를 참고하고 복습 겸 읽어주세요.
>
> Observable Instance를 생성할 수 있는 팩토리 함수를 기준으로 설명하겠습니다.

  

#### create

>  Observable Class의 팩토리 함수. 기본적인 Observable Instance 생성 연산자입니다.

- onNext: Observable이 데이터 발행을 알립니다.
- onComplete : 데이터 발생이 모두 완료되었다 알립니다. (단 한번만 발생하는 이벤트이므로 onNext 같은 데이터 발생 이벤트가 발생해서는 안됩니다.)
- onError: 에러 발생을 알립니다. 이벤트 발생 후 Observable Instance의 실행이 종료되므로, onNext, onComplete 이벤트는 발생하지 못합니다.
- subscribe():  Observable에서 정의한 데이터 흐름을 실행시키는 함수 입니다. 결과적으로 Disposable Interface Instance를 반환합니다.

```kotlin
val observable:Observable<String> = Observable.create<String> {
    it.onNext("One")
    it.onNext("Two")
    it.onNext("Three")
    it.onComplete()
}

val observer = object: Observer<String> {
    override fun onSubscribe(d: Disposable?) {
        // Observable이 데이터 발행 준비가 되었을 때 호출됩니다.
        // 발행 작업을 취소할 수 있는 Disposable Reference를 매개변수로 받습니다.
        println("Start Subscribe")
    }

    override fun onNext(t: String?) {
        // Observable이 데이터를 발행 시 호출됩니다.
        println("onNext $t")
    }

    override fun onError(e: Throwable?) {
        // Observable에 에러가 호출될 경우 호출됩니다.
        println("onError")
    }

    override fun onComplete() {
        // Observable이 완료되었을 경우 호출됩니다.
        println("onComplete")
    }
}
observable.subscribe(observer)
================================================
Start Subscribe
onNext One
onNext Two
onNext Three
onComplete
```

  

#### Disposable 객체

> subscribe() 함수 호출의 결과로 반환되는 Instance입니다. 호출할 수 있는 함수는 dispose(), isDisposed() 입니다.
>
> ##### dispose()
>
> > Observable 에게 더 이상 데이터를 발행하지 않도록 구독을 해지하는 함수
> >
> > Observable이 onComplete 를 호출했다면, 자동으로 dispose() 가 호출되며 Observable의 구독이 해지됩니다.
>
> ##### isDisposed()
>
> > Observable의 구독을 해지했는지 확인하는 함수. 구독이 해지되었다면 true를 반환합니다.

​     

#### just()

> Parameter로 제공한 데이터를 item 형식으로 정의하는 팩토리 함수입니다. 
> 최대 10개의 item을 생성 가능하며, subscribe() 함수를 호출해야 실제로 데이터를 발생합니다.
> create와 다르게 내부적으로 onNext, onCompleted() 함수가 동작하여 별도로 작성할 필요가 없습니다. 
>
> - 예제의 subscribe 는 consumer 방식을 사용했습니다.
> - consumer는 Method 형태의 자바 인터페이스이며 SAM을 통해 람다로 표현할 수 있습니다.
> - 무거운 로직을 사용 시 효율이 떨어지는 단점이 존재합니다.

```kotlin
val observable: Observable<Int> = Observable.just(1,2,3,4,5)

println("subscribe Start")

observable.subscribe(
	{ println("onNext $it") },
	{ println("onError") },
	{ println("onComplete") }
)
=================
subscribe Start
onNext 1
onNext 2
onNext 3
onNext 4
onNext 5
onComplete
```

​    

#### Single

> 오직 하나의 데이터만 발행하는 Observable Instance 입니다.
>
> Observer 방식으로 consumer 등록 시 SingleObserver를 구현 및 전달합니다.

```kotlin
Single.just("Single Test")
            .subscribe({println(it)}, {println("onError")})
===============
Single Test
```

   

#### range

> range(start:Int, cnt:Int) 형태로 start 부터 1씩 증가된 값을 cnt 횟수만큼 발행하는 Observable 생성 함수입니다.
>
> 단 map 같은 형변환 Operater를 사용하지 않으면 Observer에서 Integer의 형태로 값을 받게됩니다.

```kotlin
Observable.range(0, 3).subscribe(::println)
==============
0
1
2
```

​     

#### defer

> ObservableSource를 생성하는 연산자입니다. 
> Lazy initalize 형태로, Observer가 subscribe 시 Observable Instance가 생성됩니다.( create와 Observable 생성 시기가 다른 것 입니다.)
> Observable에서 데이터를 발행할 때 각각의 Observer에게 항시 새로운 Observable을 생성합니다.
>
> - 즉 create, just는 연산자 선언 순간 메모리가 할당되지만, defer는 Ovserver의 subscrive 전 까진 할당되지 않습니다.

```

```

   

#### from(fromArray, fromIterable, fromCallable)

> 이미 생성되어 있는 (Array, Iterable, Callable)의 값을 Observable 형식으로 바꿔주는 함수입니다.
>
> defer와 마찬가지로 Data Stream 생성을 지연합니다. 
> Observable에서 데이터를 발행할 때 추가로 Observable을 생성하지 않고 바로 데이터를 전달할 수 있습니다.
>
> - 효율 상 다른 팩토리 연산자보다 from이 우수하며, Create의 구독취소와 같은 에러 발생 가능성도 적습니다.

```kotlin
val items = arrayOf("One", "Two", "Three")
Observable.fromArray(*items).subscribe(::println)
===========
One
Two
Three
```

   

#### timer

> 정해진 시간 후 0을 전달하는 Observable을 생성하는 함수입니다.

```kotlin
Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe({println(it)})
Thread.sleep(2000)
```



- empty
- never
- throw
- Interval

   

## 변환 연산자

#### buffer

> buffer(count:Int, skip:Int) 형태로  count만큼 데이터가 쌓이면 한번에 발행하는 연산자입니다.
>
> count 까지 포함하여 skip만큼의 데이터는 제외합니다.
>
> 마지막 남은 데이터는 count 랑 상관없이 발행합니다.

```kotlin
Observable.fromIterable(0..8).buffer(2,4).subscribe(::println)
======================
[0, 1]
[4, 5]
[8]
```



#### map

> 발행할 데이터를 원하는 형식으로 변환하는 연산자입니다.

```kotlin
Observable.fromIterable(0..2).map { it * 1.1f }.subscribe(::println)
===============================
0.0
1.1
2.2
```



#### (flat, concat, switch)Map

> 전달받은 Observable을 이용해 새로운 Observable을 만드는 연산자입니다.
>
> - flatMap: 데이터를 병렬처리
> - concatMap: 데이터를 직렬처리
> - switchMap: 중간에 데이터가 들어오면 무시

```kotlin
Observable.fromIterable(1..6)
            .XXXMap {
                Observable.just("$it,").delay(100, TimeUnit.MILLISECONDS)
            }
            .subscribe(::print)
        Thread.sleep(2000)
================================
flat 기준
3,1,2,4,5,6,
concat 기준
1,2,3,4,5,6,
switch 기준 (1~5 까지는 중간에 들어온 데이터라 무시)
6,
```



#### scan

> 이전 데이터와 현재 데이터를 조합하여 하나의 데이터를 발행하는 연산자입니다.
>
> 첫 번째 데이터는 그대로 전달됩니다.

```kotlin
Observable.fromIterable(listOf("A", "B", "C", "D", "E")).scan { t1, t2 -> "${t1 + t2}"}.subscribe(::println)
=====================================
A
AB
ABC
ABCD
ABCDE
```



## 필터링 연산자

>





## 그외

#### CompositeDisposable

> 다수의 Disposable을 쉽게 관리할 수 있는 클래스입니다.
>
> 각 컴포넌트(Activity, Fragment, ViewModel)의 LifeCycle에 맞춰 dispose 시켜야 메모리 누수에 안전합니다.
>
> dispose와 clear 함수를 호출할 수 있습니다.
>
> - dispose: isDisposed를 true로 변경합니다. (즉 재사용 불가)
> - clear: dispose를 해제 후에 다시 사용 가능합니다.



# 참고문헌

[[RxJava\] create, just, defer와 fromCallable 차이점 & 샘플코드 (tistory.com)](https://softwaree.tistory.com/36)

[RxJava 입문하기 (Kotlin) - 기본2 (velog.io)](https://velog.io/@cmplxn/RxJava-입문하기-Kotlin-x389aezg)