## AWS 접속하기

- 키를 이용해 ssh 접속
- ssh -i "amazon_key.pem" [ubuntu@ec2-15-165-159-31.ap-northeast-2.compute.amazonaws.com](mailto:ubuntu@ec2-15-165-159-31.ap-northeast-2.compute.amazonaws.com)

## Docker 설치하기

- 자동설치 스크립트를 이용하기

- > curl -fsSL https://get.docker.com/ | sudo sh

- 도커는 기본적으로 root 권한이 필요하기 사용자를 docker 그룹에 추가해준다.

- > sudo usermod -aG docker 'user'

## Docker image

- Docker image 이름은 <name_space>/<image_name>: 형식으로 구성.

- 설치된 이미지들 확인하기

- > docker images

- 받기

- > docker pull

- 삭제

- > docker rmi [OPTIONS]

| 옵션 | 설명               |
| ---- | ------------------ |
| -f   | 컨테이너 강제 삭제 |

## 컨테이너 실행하기

- Docker 명령어

- > docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG....]

| 옵션     | 설명                                                         |
| -------- | ------------------------------------------------------------ |
| -d       | detached mode 흔히 말하는 백그라운드 모드                    |
| -p       | 호스트와 컨테이너의 포트를 연결 (포워딩)                     |
| -v       | 호스트와 컨테이너의 디렉토리를 연결 (마운트)                 |
| -e       | 컨테이너 내에서 사용할 환경변수 설정                         |
| –name    | 컨테이너 이름 설정                                           |
| –-rm      | 프로세스 종료시 컨테이너 자동 제거                           |
| -i       | 표준입력(stdin)을 활성화 및 컨테이너 연결 없이도 표준입력 유지 |
| -t       | Bash 사용을 위한 명령어 셀 표시를 위해서 사용함.             |
| -it      | -i와 -t를 동시에 사용한 것으로 터미널 입력을 위한 옵션       |
| –link    | 컨테이너 연결 [컨테이너명:별칭]                              |
| –cap-add | cgroups얻는 것                                               |

- Docker 시작 및 재시작

- > docker start

- > docker restart

- 실행 중인 컨테이너 접속

- > docker attach

- 컨테이너 조회

- > docker ps [OPTIONS]

| 옵션 | 설명                       |
| ---- | -------------------------- |
| -a   | 중지 된 컨테이너 조회 가능 |

- 실행 중인 컨테이너 중지

- > docker stop

- 컨테이너 삭제

- > docker rm

- 컨테이너 저장

- > docker commit -p

- 컨테이너 백업

- > docker save -o [SAVE_NAME].tar [IMAGE_NAME]

- 컨테이너 복원

- > docker load < [BACKUP_FIME_NAME].tar

## 호스트 컨테이너 File(Directory) 복사

- 호스트 -> 컨테이너

- > docker cp [HOST_FILE_PATH] [CONTAINER_NAME]:[CONTAINER_LOCAL_PATH]

- 컨테이너 -> 호스트

- > docker cp [CONTAINER_NAME]:[CONTAINER_LOCAL_PATH] [HOST_FILE_PATH]

## Docker Tomcat 추가하기

- > docker pull tomcat

## 실제 사용 예시

- 시나리오: AWS 서버 포트는 8080 docker container port 는 80으로 설정

- image 이름은 tomcat tag는 9.0

- 컨테이너 내부 LC_ALL은 ko_KR.UTF-8 설정 환경변수로 써 추가

- admin 권한 갖고 시작

- > docker run -it -e LC_ALL=ko_KR.UTF-8 -p 8080:80 --cap-add=SYS_ADMIN tomcat:9.0
