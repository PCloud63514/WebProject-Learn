# CSS 해상도 단위



### em

---

css에서 body 태그의 font-size를 14px 라고 지정했을 경우 자식요소의 태그에 font-size 14px가 적용 됩니다.

이 때 자식요소에서 em 단위를 쓰면 배수를 적용할 수 있습니다.

em 단위를 쓸 경우 부모 스타일 속성의 font-size 14px * 자식 요소 em 이 계산됩니다.

**font-size: 14px * em:1.2 = 16.8px**

#### 예시

```
<body>
	<div class="test"> Test </div>
</body>

#######CSS
body {
	font-size: 14px;
}
div {
	font-size: 1.2em;
}
```



em은 부모 요소의 속성에 맞춰 크기를 적용하므로 상속이 반복적으로 이루어지면 font-size 또한 점점 커지게 됩니다.

이때 자식 요소 더라도 동일한 사이즈를 맞추고 싶다면 **rem** 단위를 사용할 수 있습니다.



### rem (Root em)

---

**em**의 문제는 부모 요소의 중첩으로 인해 폰트 사이즈가 점점 커지는 것이였습니다.

이를 해결하는 것이 **rem** 으로 단순히 부모의 font-size를 상속받는 것이 아닌 **Root** 의 속성을 상속받는 것입니다.

사용 방법은 단위만 **rem**으로 적용하면 됩니다.

font-size를 넘어서 rem은 Grid System에도 유용합니다. 

width, height 등 동일한 UI Style을 작성해야할 때 사용할 수 있습니다.

 

### vh & vw (vertical height & vertical width)

---

```
vh = Viewport 너비값에 상대적 단위 (1vh = viewport 너비의 1% 길이)
vm = viewport 높이값에 상대적 단위 (1vm = viewport 높이의 1% 길이)
```

반응형 웹이나 앱 디자인을 위해선 다양한 해상도에 대응하기 위해 flex, 나 퍼센트 등의 값에 의존하게 됩니다.

하지만 css의 경우 퍼센트 적용 방식이 가장 가까운 부모요소(즉 Stack 바로 위 부모)에 상대적인 영향을 받습니다.

이는 디자인에 요소가 많아질 수록 원하는 모습이 나오지 않거나 복잡성이 증가하게 됩니다.

가까운 부모요소의 상대적 영향을 지우기 위해 **em**에서 **rem**으로 변경한 것 처럼 최상단 요소에 상대적인 값을 적용하면 어떨까요?

이 때 **View port**의 너비 값과 높이 값을 사용할 수 있습니다.

예시로 Viewport의 높이가 900px일 때 1vh는 9px 입니다. 즉 1% (100분의 1 단위)

마찬가지로 Viewport의 너비 값이 750px이면 1vw는 7.5px이 됩니다.

- viewport의 크기에 상대적이기 때문에 기본 margin을 적용하기 쉽고 Viewport가 변경되면 반응하여 변경된다.



## vmin & vmax

```
vmin = viewport의 높이 및 너비 중 최소 값
vmax = viewport의 높이 및 너비 중 최대 값
```

**vmin** **vmax** 또한 Viewport에 상대적입니다. 다만 출력값의 차이가 존재합니다.

예로 Viewport의 높이가 700px 너비가 1100px일 경우 **1vmin**은 7px가 되고, **1max**는 11px가 됩니다.

즉 높이와 너비 둘 중 더 큰 값이 vmax, 더 작은 값이 vmin에 적용되는 것입니다.

- 터치스크린 같은 화면 영역을 메워야하는 요소에 적용하기 좋습니다.


