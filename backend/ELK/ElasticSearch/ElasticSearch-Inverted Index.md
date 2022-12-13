# ElasticSearch Inverted Index

Elastic Search의 검색이 빠른 이유는 무엇일까요?

여러가지 이유가 있겠지만 그 중 전문 텍스트 검색을 지원하는 시스템에서 핵심 데이터 구조인 역색인(Inverted Index)을 꼽을 수 있습니다.

**Inverted Index**는 마치 책 마지막에 정리된 색인 목록과 유사한 성격을 갖고 있습니다.

- 진행할 예제는 [일래스틱 스택 6 입문 서적]([일래스틱 스택 6 입문 - YES24](http://www.yes24.com/Product/Goods/61155479)) 를 참고하였습니다.

----

예로 아래의 Document가 삽입되어 있다 가정합니다.

| Document ID | Document                            |
| ----------- | ----------------------------------- |
| 1           | It is Sunday tomorrow.              |
| 2           | Sunday is the last day of the week. |
| 3           | The choice is yours.                |

 

Elastic Search는 색인된 3개의 Document에 대해 아래의 데이터 구조를 생성합니다. 이를 역색인(Inverted Index) 라 합니다.

| 용어      | 빈도 | Document(ID) |
| --------- | ---- | ------------ |
| Choice    | 1    | 3            |
| day       | 1    | 2            |
| is        | 3    | 1, 2, 3      |
| it        | 1    | 1            |
| last      | 1    | 2            |
| of        | 1    | 2            |
| sunday    | 2    | 1, 2         |
| tormorrow | 1    | 1            |
| week      | 1    | 2            |
| yours     | 1    | 3            |

  

정리하면 다음과 같습니다.

- 용어는 Document에서 구두점을 제거하고 소문자로 치환한 후 분리된 글자를 나타냅니다.
- 용어는 알파벳 순으로 정렬됩니다.
- 빈도 열은 전체 Document에 용어가 얼마나 많이 나타났는지 나타냅니다.
- Document(ID)는 용어가 속한 Document를 나타냅니다.

 

결국 Elastic Search의 전문 텍스트 검색에서 빠른 속도를 보일 수 있는 것은, 마치 사전처럼 정리된 Inverted Index가 존재하기 때문입니다.

사용자가 특정 용어를 검색 시 이미 정렬되었기 때문에 정렬된 용어에서 해당하는 열만 찾으면 됩니다.

물론 Inverted Index는 기본적인 기능이며 이 외에도 다양한 기능을 Elastic Search는 지원합니다.





## 정리하며

Tensorflow 의 임베딩 층을 학습했던 것이 떠올랐습니다.  학습과정에서 단어의 빈도 수를 기준으로 새로운 텍스트를 만든다던지...

Inverted Index 같은 구조는 Elastic Search가 아니라도 암호화 알고리즘이나 인공지능에도 보이는 것 같습니다.