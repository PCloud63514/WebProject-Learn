# CSS 기본개념

CSS(Cascading Style Sheets)는 HTML 요소들이 각 Device에서 어떻게 보이는가를 정의하는 Style Sheets Language 입니다.

HTML4 부터 CSS와 같은 서식 설정을 HTML 문서에서 분리하여 작성하는 것이 가능해졌습니다.

   

- CSS를 사용할 경우 HTML 요소의 세부 스타일을 공통적으로 설정할 수 있다. (즉 유지보수가 용이하다.)

  

## 문법

CSS 작성을 위해 문법에 대한 설명을 진행하겠습니다.

CSS의 문법 구성은 다음과 같습니다.

- Style을 적용하고자 하는 HTML 요소(Element)를 지정하는 **선택자(Selector)**
- HTML 요소(Element)의 속성의 명(Property)과 값(Value)을  지정할 **선언부(Declaratives)**

  

**예시**

```css
a { 
    background-color: yellow;
    font-size: 16px;
}
```



### Selector

Selector를 이용해 HTML 요소를 가르키는 방식은 다음과 같습니다.

- **HTML Element** Selector
- **Id** Selector
- **Class** Selector
- **Group** Selector



> 각 방법에 대한 예제는 다음과 같습니다.



#### HTML Element Selector

```css
<style>
h2 { 
    color:teal;
    text-decoration: underline;
}
</style>
/*---------*/
<h2> 이 요소에 Style을 적용합니다. </h2>
```

   

#### Id Selector

```css
<style>
#heading {
    color:teal;
	text-decoration:line-through;
}
</style>
/*---------*/
<h2 id="heading"> 이 id에 Style을 적용합니다.</h2>
```

​       

#### Class Selector

```css
<style>
	.headings {
		color:lime;
        text-decoration:overline;
	}
</style>
/*---------*/
<h2 class="headings"> 이 Class에 Style을 적용합니다. </h2>
<p> Class 선택자를 이용하여 Style을 적용할 HTML 요소들을 한번에 선택할 수 있습니다. </p>
<h3 class="Headings"> 이 요소 또한 Style을 적용합니다. </h3>
```

​    

#### Group Selector

```css
<style>
h1 {color:navy;}
h1, h2 {text-align: center;}
h1,h2,p {background-color: lightgray;}
</style>
```

### 

## CSS 적용 방법

CSS 파일을 별도로 작성하여 링크를 통해 HTML에 적용하는 방법 외에도 다양한 방법을 통해 적용할 수 있습니다.

- 인라인 스타일 (Inline Style)
- 내부 스타일 시트 (Internal Style Sheet)
- 외부 스타일 시트 (External Style Sheet)

만약 위에 언급한 다양한 방법을 전부 프로젝트에 적용한다면 적용 우선순위에 대해 이해해야합니다.

우선순위에 따라 Style 적용이 변합니다. 순서는 다음과 같습니다.

- 1 순위 - inline Style
- 2 순위 - Internal / External Style Sheet
- 3 순위 - Web Browser Default Style

​     

### Inline Style

> HTML 요소 내부에 직접 Style 속성을 작성하는 방법을 **Inline Style** 이라 합니다.
>
> - 해당 요소만 Style이 적용됩니다.

```html
<body>
	<h2 style="color:green; text-decoration:underline"> 인라인 스타일 적용 </h2>
</body>
```

   

### Internal Style Sheet

>HTML 내부의 <head> 태그에 Style을 작성하는 방법을 **Internal Style Sheet** 라합니다.
>
>- 해당 HTML 문서만 Style이 적용됩니다. 

```html
<head>
	<style>
        body { background-color: lightyellow; }
        h2 { color: red; text-decoration: underline; }
    </style>
</head>
```

   

### External Style Sheet

> Style을 .css 확장자인 스타일 시트 파일로 작성하는 방법을 **External Style Sheet** 라 합니다.
>
> - Web Site 전체의 스타일을 하나의 파일에서 변경할 수 있습니다.
> - <link> 태그를 사용하여 외부 스타일 시트 파일을 불러와야합니다.

```html
<head>
    <link rel="stylesheet" href="/example/media/expand_style.css">
</head>
```

#### expand_style.css

```css
body { background-color:lightyellow; }
p { color:red; text-decoration:underline; }
```



## 참고문헌

[코딩의 시작, TCP School](http://www.tcpschool.com/css/css_intro_syntax)