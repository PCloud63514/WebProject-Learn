# ElasticSearch

**ElasticSearch**는 Apache Lucene 기반으로 개발한 **실시간 분산 검색 및 분석 엔진**입니다. 

중앙에 대이터를 저장하고, 이를 통해 다양한 문제(예상 항목 검색, 예상하지 못한 항목 유추)를 해결할 수 있고, 정형, 비정형, 위치정보, 메트릭 등  원하는 방법으로

다양한 유형의 검색을 수행하고 결합할 수 있습니다.

**ElasticSearch**는 Elastic Stack의 중심에서 검색과 분석 엔진이라는 가장 중요한 역할을 담당합니다.



**주요 이점**

- Schema Less 및 Document-Oriented(지향)
- 검색 및 분석
- Near real time (거의 실시간)  /  신속성  / Fault tolerant (결함 허용성)
- 운영 및 확장 용이
- 풍부한 클라이언트 라이브러리 및 REST API 지원



## Install

- [Download Elasticsearch Free | Get Started Now | Elastic | Elastic](https://www.elastic.co/kr/downloads/elasticsearch)

환경에 맞춰 설치하면 됩니다. ElasticSearch와 Kibana를 함께 설치할 것이므로, [Kinana]() 문서도 참고해주시기 바랍니다.



Windows 기준

- 설치 후 bin/elasthcsearch.bat 관리자 권한으로 실행.

- localhost:9200 접속

- 아래의 출력 메시지와 비슷한 것이 나오면 성공입니다. (name, uuid, hash는 가렸습니다.)

![image](https://user-images.githubusercontent.com/22608825/103148447-fe339700-47a2-11eb-905b-681b893afa0c.png)



## 목차

### 1. [Elastic Search 추상화 개념](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-%EC%B6%94%EC%83%81%ED%99%94%20%EA%B0%9C%EB%85%90.md)

### 2. [Elastic Search 버전 별 차이점](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-%EB%B2%84%EC%A0%84%EB%B3%84%EC%B0%A8%EC%9D%B4%EC%A0%90.md)

### 3. [Elastic Search Data Type](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-Data%20Type.md)

### 4. [Elastic Search Mapping Type](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-Mapping%20Type.md)







# 참고문헌

#### [일래스틱 스택 6 입문 서적]([일래스틱 스택 6 입문 - YES24](http://www.yes24.com/Product/Goods/61155479))

> 용어에 어려움이 없다면 학습에 최적인 서적.