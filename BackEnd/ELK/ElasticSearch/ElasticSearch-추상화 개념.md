# ElasticSearch 추상화 개념

Elastic Search의 가장 큰 특징이라면 RDBMS와 다르게 Document 지향적이며, 이를 일급 객체로 취급하는 것입니다.

당연하게도 RDMBS의 스키마 처럼 DB 구조 및 제약조건 명세에 대한 개념이 Elastic Search엔 없습니다.

마찬가지로 정해진 규격의 Col, Row, Table 또한 없습니다. 물론 Elastic Search에도 값들을 정의하는 추상화 개념이 존재합니다.



## Index

**Index**는 Elastic Search에서 단일 타입의 Document를 저장하고 관리하는 일종의 컨테이너 입니다.

RDBMS에서 비교한다면 **Database**(스키마에 보다 가까움)의 개념입니다.

<img src="https://user-images.githubusercontent.com/22608825/103151221-6b552580-47bf-11eb-8a05-6e1b6f1fdf69.png" alt="111" style="zoom:67%;" />



그림처럼 Index는 단일 Type의 Document를 갖을 수 있습니다.

```
이 페이지에선 Elastic Search 7.10.1을 기준으로 진행하고 있습니다.
Elastic Search 6.0 미만의 버전에서는 하나의 Index에서 여러 Type을 포함할 수 있었습니다.
하지만 6.0 버전부터 Index는 단일 Type만 갖을 수 있도록 변경되었습니다. 
* 6.0이전 버전에서 6.0 이후 업데이트에도 문제는 없습니다.
```

  

## Type

**Type**은 Elastic Search에서 논리적으로 Index 내에 같은 Document 종류를 그룹화 하고 구성하는 것을 의미합니다.

RDBMS에서 Table의 역할과 유사합니다. 



## Document

**Document**는 Elastic Search에서 정보의 기본 단위를 의미합니다.  RDBMS에서 Row의 역할과 유사합니다.

맨 위의 사진처럼 Index와 Type 안에 포함되어 있으며, 다중 필드(속성)를 포함하고 있습니다.





## 중간정리

Index, Type, Document 의 내용을 정리하기 위해 아래의 예시를 확인하겠습니다.

```json
PUT /catalog/product/1
{
  "sku": "SP000001",
  "title": "Elasticsearch for Hadoop",
  "description": "Elasticsearch for Hadoop Description",
  "author": "Vlshal Shukla",
  "ISBN": "1785288997",
  "price": 26.99
}
```



Elastic Search에 JSON Document를 색인했을 때의 예시입니다.

- catalog는 Index
- product는 Type
- 그 외의 내용 (sku, title 등)은 Document를 의미합니다.