# Viewport & CSS Media queries

반응형 웹(및 앱 등등) 을 제작할 때 꼭 함께 등장하는 단어인 Viewport.

그리고 css 작성 시 나오는 Media query 에 대해 정리하는 페이지 입니다.



## 반응형 웹?

```
반응형 웹은 Display의 종류에 따라 화면의 크기(구성 크기) 가 자동으로 변하도록 만들어진 웹 페이지를 의미합니다.
```

데스크탑의 PC, 스마트폰, 아이폰, 태블릿 PC 등 다양한 Device에서 웹 또는 앱에 접근할 수 있습니다.

문제는 개발 당시에 사용한 Device의 Display하나에 맞춰 고정적인 크기로 개발을 진행하면, 다른 Device의 Display에 대응할 수 없게되며 원치않는 UI를 확인하게 될 것 입니다.



## Viewport

```
Viewport 는 사용자에게 보여지는 영역을 의미합니다.
```

Mobile 웹(앱) 을 작성하다보면 **Viewport** 라는 단어를 확인할 수 있습니다. (필자는 OpenGLES 에서 처음 확인했습니다.)

웹 개발을 하던 Graphics 개발을 하던 작성된 UI 내용이 사용자에게 보여지게 되는데, 이 때 보여지는 범위를 의미합니다.

Mobile 외에도 데스크탑 PC의 브라우저에서 창의 크기를 줄였을 때 보여지는 웹 페이지 또한 Viewport의 크기가 변경된 것을 의미합니다.

  

## CSS3 Media queries

```
Media queries 는 Media Type(all, screen, print, speech) 에 맞춰 css 를 적용할 수 있는 방법 입니다.
```

**반응형 웹** 을 검색 시 쉽게 찾을 수 있는 내용이 **Media queries** 입니다.

Media Type외에 특성을 부여해 상태에 따라 다른 Style Sheet를 적용할 수도 있습니다.

- Viewport의 크기에 따라 다른 Style Sheet를 적용
- Mouse가 아닌 Touch Screen을 사용하는지 유무 (사이트 환경 내용 탐지)

등 특성에 따라 반응하는 **반응형 웹** 의 **핵심** 입니다.



#### Media-Type

- all : 특정 Media-Type을 지정하지 않을 경우 선택되는 기본 속성.
- screen
- print
- speech

### Media queries 작성법

크게 두 가지 방법이 있습니다.



#### 속성 방식

```html
<link rel="stylesheet" media="screen and (max-width: 768px)" href="mystyle.css"/>
```

media type이 Screen이고, 화면의 최대 너비가 768px 이하 일 때 적용한다. 라는 조건입니다.

이 조건이 맞을 때 <link> 요소를 통해 css 파일을 불러올 수 있습니다.

 

#### 어노테이션 방식

```css
@media media-type and (media-feature-rule) {
	/* CSS rules go here */
}
```

Media queries 구문을 가장 잘 표현하는 방식 입니다.

media-type을 제외할 경우 기본적으로 all이 사용됩니다.



#### 예제

```css
/* media-type 이 screen이고, width 크기가 600px일 경우 css 적용 */
@media screen and (width: 600px) {
    body {
        color: red;
    }
}
/* 조건을 논리곱 형태로 적용 */
@media screen and(min-width: 400px) and (orientation:landscape) {
    body {
        color: blue;
    }
}
/* 조건을 논리합 형태로 적용 */
@media screen and(min-width:400px), screen and (orientation: landscape) {
    body {
        color: blue;
    }
}
/* 조건을 not 연상 형태로 적용 */
@media not all and (orientation: landscape) {
    body {
        color:blue;
    }
}
```

#### media-type을 제거했을 때

```css
/* 가로모드일 경우 동작하는 조건식 */
@media (orientation: landscape) {
	body {
		color: rebccapurple;
	}
}
```







## 참고문헌

#### [반응형 웹 - 뷰포트(viewport) 사용법 (tistory.com)](https://offbyone.tistory.com/110)

#### [미디어 쿼리 초보자 안내서 - Web 개발 학습하기 | MDN (mozilla.org)](https://developer.mozilla.org/ko/docs/Learn/CSS/CSS_layout/미디어_쿼리_초보자_안내서)

#### [반응형 웹을 위한 미디어 쿼리 사용법(CSS media queries) (tistory.com)](https://offbyone.tistory.com/121)