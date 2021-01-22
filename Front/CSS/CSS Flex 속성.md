# CSS Flex 속성

Flex는 Layout 내의 요소에게 방향과 크기를 지정해 배치하는 속성입니다.



임의로 Flex Container 와 Flex Item 로 크게 나누어 설명하겠습니다.



## Flex Container( 부모 / Layout )

flex 속성을 Container에 지정하는 것을 말합니다. 

### 예시

```css
container {
    display:flex;
	flex-direction:row;
    flex-wrap:wrap;
}
```



| 속성           | 설명                                                         |                                                              |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| display        | 서식 환경을 만드는 속성. <br />부모요소에 설정할 경우 자식요소에도 영향이 생깁니다.<br />display 속성에 flex 값을 주어 부모요소를 flex container로 지정합니다. | display:flex                                                 |
| flex-direction | 방향, 흐름, 시각적인 순서처리를 지정하는 속성                | row:기본 값. 항목을 왼쪽에서 오른쪽 나열<br />row-reverse: row 를 뒤집은 형태<br />column:항목을 수직으로 위에서 아래로 나열<br />column-reverse: column을 뒤집은 형태 |
| flex-wrap      | flex item들이 열을 바꾸는 방법을 지정하는 속성.<br />wrap은  flex item이 container 밖으로 나가게 됬을 때 열을 바꾸어 container에 포함시키는 속성입니다. | nowrap: 기본값. <br />wrap: flex item들이 container에 포함되도록 열을 변경하는 속성.<br />wrap-reverse: 주축은 그대로 유지하면서 교차축 기준으로 뒤집히는 속성.<br />initial: 기본값.<br />inherit: 부모요소로부터 값을 상속받는 속성. |



## 참고문헌

### [Web Club :: 모던 레이아웃 - 플렉스박스(Flexbox) (tistory.com)](https://webclub.tistory.com/628?category=541913)