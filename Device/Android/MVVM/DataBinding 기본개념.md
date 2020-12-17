# DataBinding 기본개념

Android의 XML 파일에 Data를 연결(Binding)하여 UI 갱신 코드를 줄여주는 방법입니다. Android JetPack Library에 포함된 기능입니다.

DataBinding의 목적은 Application Logic의 데이터와 Layout의 Widget을 직접적으로 Binding 하여 **글루코드** 를 최소화 하는 것입니다.

- 글루코드: 프로그램 요구사항에 들어가지 않지만, 호환성이 없는 부분끼리 결합하기 위한 코드.  예시) findViewById



## 구현



### build.gradle(Module:app) 수정

```
android {
...
    dataBinding {
            enabled = true
    }
}
```

> 단  gradle 버전이 4.0 이상이라면 아래의 방법을 적용해주세요.  안될 경우 DSL 에러가 발생합니다.
>
> ex) classpath "com.android.tools.build:gradle:4.0.1"
>
> - DSL element 'android.dataBinding.enabled' is obsolete and has been replaced with 'android.buildFeatures.dataBinding'.
>
> ```
> android {
> ...
>     buildFeatures{
>          dataBinding = true
>     }
> }
> ```



### Layout 수정

> DataBinding을 적용할 Activity를 수정합니다.  적용하기 위해선 xml의 root tag를 <layout> 으로 교체하면 됩니다.
> 적용 시 xml 파일의 이름을 이용해 파스칼 표기법 기반의 DataBindng 클래스를 자동생성합니다.
>
> - activity_main.xml -> activityMainBinding.class

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



#### Data & variable

> activity.xml 파일에 <layout> 태그를 넣었다면 databinding Class가 완성되었을 것입니다.
>
> 이젠 생성된 databinding 을 이용해 직접적으로 데이터와 View를 결합해봅시다.
>
> 필요한 요소는 <data>와 <variable> 입니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout  xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".ui.sample.SampleActivity">
    <data>
        <variable
            name="sample"
            type="com.pcloud.mvvm_sample.Sample" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="@{sample.message}"
            />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



#### Sample.kt

````
data class Sample(val message:String)
````



#### SampleActivity.kt

```kotlin
class SampleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)

        binding.sample = Sample("Hello DataBinding!!")
    }
}
```





