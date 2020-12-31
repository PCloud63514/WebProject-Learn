# ElasticSearch 추상화 개념

Elastic Search의 가장 큰 특징이라면 RDBMS와 다르게 Document 지향적이며, 이를 일급 객체로 취급하는 것입니다.

당연하게도 RDMBS의 스키마 처럼 DB 구조 및 제약조건 명세에 대한 개념이 Elastic Search엔 없습니다.

마찬가지로 정해진 규격의 Col, Row, Table 또한 없습니다. 물론 Elastic Search에도 값들을 정의하는 추상화 개념이 존재합니다.



## Index

**Index**는 Elastic Search에서 단일 타입의 Document를 저장하고 관리하는 일종의 컨테이너 입니다.

<img src="https://user-images.githubusercontent.com/22608825/103193424-c69e2980-491f-11eb-9cf2-3f387a2f0eb6.png" alt="111" style="zoom:50%;" />



그림처럼 Index는 단일 Type의 Document를 갖을 수 있습니다.

```
이 페이지에선 Elastic Search 7.10.1을 기준으로 진행하고 있습니다.
```

  

## Type (Document Type)

**Type**은 Elastic Search에서 논리적으로 Index 내에 같은 Document 종류를 그룹화 하고 구성하는 것을 의미합니다.

Elastic Search 7.x  Typeless를 지향하며 Type 구조가 삭제되었습니다.

Type 구조가 사라지며 기존 자리는 _doc으로 대체되었지만 이는 진행하며 다른 페이지에서 설명할 것 입니다.



## Document

**Document**는 Elastic Search에서 정보의 기본 단위를 의미합니다.

맨 위의 사진처럼 Index와 Type 안에 포함되어 있으며, 다중 필드(속성)를 포함하고 있습니다.

사용자가 정의한 필드 외에도 Document에 메타 필드가 포함되어 있습니다.

 

- _id: DB Table의 기본키 처럼 Type 내 Document의 고유 식별자. 자동생성 및 사용자 정의가 가능합니다.
- _type: Document의 Type을 갖고 있습니다.
- _index: Document의 Index 이름을 갖고 있습니다.



## 중간정리

Index, Document 의 내용을 정리하기 위해 아래의 데이터 색인 예시를 확인하겠습니다.

- 색인: Elastic Search 용어에서 Index 내에서 Document를 추가하거나 생성하는 작업을 색인 연산(Indexing Operation)이라 합니다.

#### 예시

```json
PUT /message/_doc/1
{
  "name" : "PCloud",
  "message" : "Hello!",
  "date" : "2020-12-26T17:17:25"
}
```

#### 예시 결과 (GET)

```json
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "_seq_no" : 0,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "PCloud",
    "message" : "Hello!",
    "date" : "2020-12-26T17:17:25"
  }
}
```

JSON 으로작성하지 않은 _(언더바) 가 붙은 Field를 확인할 수 있습니다. 이를 Meta Field, 직접 정의한 Field를 사용자 정의 Field라 합니다.



## Node

Elastic Search 에서 **Node**는 하나의 단위 프로세스입니다. 가장 낮은 Level에서 Elastic Search Process의 Single Instance로 데이터 공유를 담당합니다.

**분산 시스템**인 Elastic Search는 다른 Process와 통신하는 **Multi Process**로 구성되어 있으며,  처음 Elastic Search를 설치 및 실행했을 때,

이를 **Single Node** 또는 **Single Node Elastic Search Cluster**라고 합니다.

다수의 Shard로 구성되어 있으며, 역할에 따라 Master-eligible, Data, Ingest, Trilbe Node로 구분합니다.

 

- 모든 Elastic Search Node는 시작할 때 고유 ID와 이름이 지정됩니다. 
- config/elasticsearch.yml 환경설정 파일에서 node.name 변수를 사용해 고정된 이름을 지정할 수 있습니다.
- 이름 뿐만이 아닌 포트, Cluster Name 등 기본값을 변경할 수 있습니다.



## Cluster

Elastic Search에서 **Cluster**는 System의 가장 큰 단위입니다.  단일 및 다중 Index를 호스팅하며 검색, 색인, 집계와 같은 연산을 제공합니다.

하나의 Cluster는 하나 이상의 Node로 구성되며, 모든 Elastic Search Node는 항상 Cluster의 부분 집합입니다.

- 모든 Elastic Search Node는 elasticsearch 라는 이름으로 Cluster에 참여하려고 시도합니다.
- config/elasticsearch.yml 파일의 cluster.name 속성을 변경하지 않고 같은 네트워크에서 여러 노트를 시작하면 Cluster가 자동으로 구성됩니다.
- 마찬가지로 같은 네트워크에서 Node가 다른 Cluster에 강제로 참여하는 것을 방지하기 위해 cluster.name 속성을 지정할 수 있습니다.

   

## Shard

Elastic Search에서 **Shard**는 Cluster에서 Index를 분배하고 Single Index의 Document를 여러 Node로 분할하는데 사용됩니다.

이때까지의 내용에서 설명한 Index는 1개 이상의 Type(단일 Type 지향) 을 지정하고, 여러 개의 Document를 포함할 수 있습니다.

하지만 Single Node에 저장할 수 있는 데이터양은 제한이 있으며, 그 한계는 노드의 저장소, 메모리, 처리 용량에 따라 결정됩니다.

이에 따라 **Shard**를 활용하여 Cluster에서 Single Index 데이터를 분할해 Cluster의 저장소, 메모리, 처리 용량을 활용할 수 있게됩니다.

  

Shard에 위치한 데이터를 분할하는 과정을 **Sharding**이라 하며, Elastic Search에 내장된 고유한 기능으로 아래와 같습니다.

- Cluster에 위치한 Multi Node의 **저장소 활용**을 돕습니다. (확장 용이성)
- Cluster에 위치한 Multi Node의 **처리 능력 활용**을 돕습니다. (병렬화 기능)



모든 Index는 ElasticSearch에서 5개의 Shard를 갖도록 구성됩니다. 또한 Index 생성 시점에 Index의 데이터를 나눌 Shard의 개수를 지정할 수 있습니다.

물론 Index를 생성 후엔 Shard의 개수를 변경할 수 없습니다.



이를 그림으로 표현하면 아래와 같습니다.

![image](https://user-images.githubusercontent.com/22608825/103168007-402c0e00-4873-11eb-9eab-d6cff9704de5.png)

그림은 3개의 노드로 구성된 Cluster에서 5개의 Shard를 가진 Index를 표현한 것입니다.

각 Shard(P1 ~ P5)는 전체 데이터의 약 1/5 를 포함하고 있습니다.



##  Replica (Replica Shard)

이름 그대로 Shard의 복제본을 의미합니다. 예를 들기 위해 위 그림을 예제로 해봅시다.

Clusher에서 Node1에게 장애가 발생했을 경우 Node1에 위치한 Shard P1과 P2에 저장된 데이터 조각이 손실될 것입니다.

하드웨어 장애 상황에도 문제가 없어야 하므로, 이를 해결하는 것이 Replica 입니다.

각 Shard는 0개 이상의 Replica를 갖을 수 있습니다. Replica는 원본 Shard와 똑같은 데이터를 갖고 있으며, 각 Shard가 Replica를 하나씩 만들 경우

5개의 주 Shard(primary Shard)와 Replica가 각 Node에 위치하게 됩니다.

  

- Replica를 그림으로 표현하면 아래와 같습니다.

![image](https://user-images.githubusercontent.com/22608825/103168526-4cb26580-4877-11eb-8f48-8566d8d518c3.png)



노드에 장애가 발생하더라도 복구할 수 있도록 각 Shard의 Replica는 다른 Node에 위치하게 됩니다.

만약 Node1에 장애가 발생하더라도, Node2와 Node3에 Replica를 포함하여 모든 Shard를 갖고 있을 수 있습니다.
또한 Replica는 해당 Shard를 대신하여 주 Shard로 승격될 수 있습니다.





## 중간정리

![image](https://user-images.githubusercontent.com/22608825/103167685-9e0b2680-4870-11eb-8506-6437541f0348.png)



Cluster는 여러 Node로 구성될 수 있으며, 각 Node는공유 데이터를 저장하고 관리합니다.

단일  Cluster는 하나 이상의 Index를 호스팅할 수 있으며, Index는 Document의 연관 Type에 대한 논리적 그룹입니다.
