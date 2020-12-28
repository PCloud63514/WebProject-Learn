# Elastic Search Data Type

RDBMS와 Elastic Search를 비교한 내용을 기억한다면 Elastic Search엔 Schema가 존재하지 않는 것을 알 것입니다. 
Elastic Search는 field와 field type 없이도 Document를 저장할 수 있습니다. 
하지만 실제 운영 환경에서 사용하는 데이터는 Schema가 존재하며 체계화된 구조를 갖고 있습니다.

 

예로 Elastic Search처럼 Schema Less에 Document-Oriented의 엔진은 특정 Type이 지정된 Document에 항상 공통 Field 집합을 갖게 됩니다.

실제 Index Type은 공통 Field를 기반으로 생성하며, 일반적으로 Index에서 하나의 Type을 가진 Document는 몇 가지 공통 Field를 공유하게 됩니다.

 

하지만 RDBMS는 데이터에 대해 엄격하고 체계화된 구조를 기준으로 진행됩니다. 

RDBMS에서 Table 생성 시점에 각 열 이름과 Type을 지정하는 Table 구조를 정의해야하며, 실행 중에는 새로운 이름을 갖거나 다른 데이터 Type을 갖은 열을 레코드에 저장할 수 없습니다.

위의 예시인 **Elastic Search와 RDBMS의 차이를 이해하고 Elastic Search에서 지원하는 데이터 타입을 이해하는 것이 이 페이지의 목표입니다.**



## 데이터 타입

Elastic Search의 데이터 타입은 매우 다양합니다. 

- 텍스트  |  숫자
- 참 거짓 | 이진 객체
- 배열, 객체  |  중첩 타입
- 지리 및 지형 정보
- IPv4 & IPv6주소



특수한 데이터 타입을 저장하기 위해 위의 다양한 데이터 타입을 지원합니다. Document의 각 Field는 데이터 타입을 갖고 있으며, 위의 내용을 포함에 정리하면

아래와 같습니다.



### 문자열(String) 데이터 타입

> Elastic Search 6.0 이상 부터 string type은 deprecated 되어 text와 keyword만 남게 되었습니다.

#### text

> <span style="color:black">설명이나 길이가 긴 텍스트 값을 저장하는 데이터 타입입니다. </span>
> <span style="color:black">전문 텍스트 검색에 유용하며, **텍스트 타입을 가진 Field는 색인 전에 분석을 거쳐 전문 텍스트 검색에 활용됩니다.**</span>

 

#### keyword

> <span style="color:black">문자열 Field 분석이 가능한 데이터 타입 입니다.  정렬, 필터링, 집계 기능을 지원합니다.</span>

  

### 숫자(Numeric) 데이터 타입

#### byte(8bit), short(16bit), integer(32bit), long(64bit)

> <span style="color:black">정밀도를 가진 부호 있는 정숫값.</span>

 

#### float, double

> <span style="color:black">**단일 정밀도(Single precision)** 32bit와 **이중 정밀도(double precision)** 64bit 표현이 가능한 IEEE 754 부동 소수점 수.</span>

 

#### half_float

><span style="color:black">**반 정밀도(half precision) 16bit**를 표현하듣 IEEE 754 부동 소수점 수.</span>

 

#### scaled_float

> <span style="color:black">길고 고정된 비율을 기반으로 한 부동 소수점 수.</span>

 

### 날짜(Date) 데이터 타입

#### date

> <span style="color:black">밀리초 단위로 정밀한 time stamp 를 저장할 수 있는 데이터 타입.</span>

 

### 논리(Boolean) 데이터 타입

#### boolean

> <span style="color:black">논리 데이터 타입.  (True / False)</span>

 

### 이진(Binary) 데이터 타입

#### binary

> <span style="color:black">Base64 인코딩을 수행한 후, 임의의 이진값을 저장할 수 있는 데이터 타입.</span>

 

### 범위(Range) 데이터 타입

### _range

> <span style="color:black">integer, float, long, double, date 등 다양한 범위를 정의하는 데이터 타입.</span>
>
> <span style="color:black">예시) integer_range</span>

 

### 복합 데이터 타입

#### 배열 데이터 타입

> <span style="color:black">위에 설명된 데이터 타입(문자열, 정수, 날짜 등)을 가진 인스턴스 배열 입니다.</span>
>
> <span style="color:black">배열에서 데이터 타입을 혼합해서 사용할 수 없습니다.</span>

 

#### 객체 데이터 타입

> <span style="color:black">JSON Document 안에 내부 객체.</span>

 

#### 중첩 데이터 타입

> <span style="color:black">내부 객체의 배열을 지원하는데 사용됩니다. 각 내부 객체는 독립적으로 쿼리할 수 있어야합니다.</span>

 

### 그 외 데이터 타입

#### 지리(Geo-point) 데이터 타입

> <span style="color:black">경도와 위도를 저장하는 데이터타입 입니다. </span>
> <span style="color:black">지리 데이터 타입을 사용하면 특정 지점에서 2km 내에 위치한 모든 ATM을 검색하는 등 지리를 이용한 쿼리를 사용할 수 있습니다.</span>

 

#### 지형(Geo-shape) 데이터 타입

> <span style="color:black">다각형, 지도 등 기하학 지형을 저장하는데 사용됩니다.</span>
>
> <span style="color:black">지형 데이터 타입을 사용하면 특정 지형 내에 모든 항목을 검색할 수 있는 쿼리를 사용할 수 있습니다.</span>

 

#### IP 데이터 타입

> <span style="color:black">IPv4, IPv6 주소를 저장하는 데이터 타입입니다.</span>



## 정리

Elastic Search에서 지원하는 다양한 데이터 타입에 대해 정리해보았습니다.

문자열, 숫자, 날짜 등은 기존 개발에도 사용되었으므로 이해가 쉬웠을 것입니다.

다음은 주제인 Mapping에 대해 설명하겠습니다.

- 다음 설명은 [ElasticSearch-추상화 개념](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-%EC%B6%94%EC%83%81%ED%99%94%20%EA%B0%9C%EB%85%90.md)을 진행한 것을 기준으로 진행하겠습니다.