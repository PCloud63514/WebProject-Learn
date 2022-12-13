# MySQL 시작하기

> - 이전페이지 [MySQL 설치 및 세팅하기](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/DB/MySQL%20%EC%84%A4%EC%B9%98.md)
>
> 이번 페이지는 설치한 MySQL을 접속 및 기본 셋팅을 진행하고 간단한 테이블 및 데이터를 삽입하는 작업을 진행하겠습니다.



## 1. MySQL 접속

> cmd 에서 아래의 커맨드를 입력해주세요.

```
mysql -u root -p
```

이후 password 입력 시 접속됩니다.

![image](https://user-images.githubusercontent.com/22608825/100523003-5604cf80-31f0-11eb-97a3-6df036b45ad8.png)



## 2. Character Set 확인하기

> character set 을 utf-8로 변경하려 합니다.
>
> status 커맨드를 입력하면 아래의 화면을 확인할 수 있습니다.

   

![image](https://user-images.githubusercontent.com/22608825/100523055-b6940c80-31f0-11eb-98d6-f216e1db0a17.png)



Client 와 Conn 의 characterset 이 euckr 인 것을 확인할 수 있습니다. 이를 utf8로 변경해야합니다. 

1.  우선 C:\ProgramData\MySQL\MySQL Server 8.0 경로로 이동해야합니다. ProgramFiles 가 아닌 ProgramData 입니다.

2.  my.ini 또는 my-default.ini 파일을 확인할 수 있습니다. my-default.ini 이면 파일 내용을 복사 후 my.ini 파일을 생성 및 붙여넣기 이후에 진행해주세요.
3. my.ini 의 권한을 수정하기 위해 속성 및 보안을 수정해주세요.

​     

<img src="https://user-images.githubusercontent.com/22608825/100523235-4c7c6700-31f2-11eb-9589-81b9e348a1f2.png" alt="image" style="zoom:67%;" />



4. 이후 my.ini 을 편집하여 아래의 내용을 맨 아래 추가시켜 주세요.

```ini
[client]
default-character-set=utf8

[mysqld]
character-set-client-handshake = FALSE
init_connect="SET collation_connection = utf8_general_ci"
init_connect="SET NAMES utf8"
character-set-server = utf8

[mysql]
default-character-set=utf8

[mysqldump]
default-character-set=utf8
```

5. 수정 후 MySQL을 재부팅 해주세요.

   1. Windows 검색에서 서비스를 검색하여 실행합니다.
   2. MySQL을 찾습니다. (MySQL80)
   3. 오른쪽 클릭 후 다시시작을 눌러줍니다.

   

6. 변경되었는지 확인하기 위해 다시 status를 입력해주세요.

   

![image](https://user-images.githubusercontent.com/22608825/100523309-ea703180-31f2-11eb-9b45-2dc7e78cc5da.png)



## 3. Query 사용하기

> 사용자 계정 생성 테이블 생성 및 튜플 삽입 등을 해볼 것입니다. 그 전에 DML, DDL, DCL 등을 모른다면 문서를 읽고 와주세요.

   

### User 생성하기

>앞으로 사용하게될 User를 생성하고 권한을 줍니다.

#### User 목록 확인

```
use mysql;
select host, user, from user;
```

#### User 생성 

```
create user 사용자 명@localhost identified by '비밀번호';
```

#### 추가사항

> 계정 권한으로 접근에 발생할 에러를 방지 하기 위해 추가합니다.

```
alter user '계정'@localhost identified with mysql_native_password by '비밀번호';
```



### 데이터베이스 생성하기

> 우선 데이터베이스를 만들어줍니다.

```cmd
create database [데이터베이스 이름]
ex) create database tinydb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```



#### 권한 추가  

> mysql의 db 접근을 위해 계정에 권한을 추가합니다.

```
grant all privileges ON DB명.* TO '계정'@'localhost';
```

 이후 생성된 데이터베이스에 접속합니다.

```
use tinydb;
```

​    

### Table 생성하기

> DB에 성공적으로 접속하였다면 테이블을 생성해보겠습니다.
>
> 다른 Tiny 예제에서 사용할 수 있도록 사용자의 정보를 기록하는 테이블을 만들어봅시다.

​     

#### Gender table 만들기

```
create table Gender(
	id int not null AUTO_INCREMENT primary key,
	text varchar(20) not null
);
```

​      

#### User table 만들기

```
create table User(
	id varchar(20) not null,
	passwd varchar(20) not null,
	name varchar(20) not null,
	genderId int,
	primary key(id),
	foreign key(genderId) references Gender(id)
);
```



#### Table 생성 확인하기

> 재대로 생성되었는지 확인해주세요.

```
show tables;
```



### 데이터 삽입하기

> 테이블 생성이 확인되었다면 이번엔 생성된 테이블에 데이터를 삽입하겠습니다.
>
> 우선 참조 테이블인 Gender 테이블에 데이터를 삽입해줍시다.

```
Insert into Gender(text) values('남자');
Insert into Gender SET text='여자';
Insert into Gender SET text='공개안함';
```

데이터를 넣으려는데 **ERROR 1366 (HY000): Incorrect string value: '\xB3\xB2\xC0\xDA' for column 'text' at row 1** 이런 에러를 발견하게 될 것 입니다.

해결하기 위해 검색해본 결과 우분투 기준의 내용이 대다수이며, 현재 이 페이지는 Windows10 을 기준으로 작성하기 때문에 db나 table의 character-set을 변경하는 행동은 소용 없을 것입니다. 

Windows 10의 cmd 자체가 mysql에게 보낼 때 데이터 형식이 이상한 것이 문제입니다. 추가로 cmd 65001 같은 경우도 소용 없습니다.

**show variables like 'c%';** 명령어를 입력하면 euckr 이 없다면 결국 문제는 cmd 이기 때문에 이젠 cmd 사용을 멈추고 **MySQL Workbench** 를 사용하겠습니다.



#### MySQL Workbench

<img src="https://user-images.githubusercontent.com/22608825/100538457-50040280-3273-11eb-9d7a-128ba1caf7cc.png" alt="image" style="zoom:67%;" />



이제 다시 1번 과정인 MySQL 접속 과정부터 시작하여 진행합시다.

```
use tinydb;
insert into Gender(text) values='남자';
Insert into Gender SET text='여자';
Insert into Gender SET text='공개안함';
select * from Gender;
```



![image](https://user-images.githubusercontent.com/22608825/100538590-0962d800-3274-11eb-9d72-ca4f0fe663d3.png)

​      

#### User 데이터 삽입하기

> 마지막으로 User Table에 데이터를 삽입하겠습니다.

```
insert into User(id, passwd, name, genderId) values('test01', 'test', 'Kim', 1);
Insert into User SET id='test02', passwd='test', name='Yun', genderId=2;
Insert into User SET id='test03', passwd='test', name='Song', genderId=3;
select * from User;
```



![image](https://user-images.githubusercontent.com/22608825/100539539-96a92b00-327a-11eb-8e64-365c1a1093d2.png)