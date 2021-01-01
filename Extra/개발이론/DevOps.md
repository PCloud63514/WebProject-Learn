# DevOps

**DevOps** 는 개발(Development)과 운영(Operations)의 합성어로 IT 조직과 비즈니스 조직의  협업 및 통합을 강조하는 개발환경(문화)을 뜻합니다.

이론 상 의사소통이 가능한 구조를 만들어 조직 간 커뮤니케이션이 유연하도록 하자 라고 하지만 와닿지는 않는 표현입니다.

개발자 입장에서 공감하고 필요하구나 느끼기 위한 비교는 하나의 애플리케이션을 개발할 때 여러 팀이 협업하는 경우일 듯 싶습니다.

애플리케이션 개발 시 각 서비스(및 기능)을 팀 또는 개발자 단위로 나누어 작업을 하게 될 것입니다.

 





**DevOps** 라는 이름에서 개발과 운영이 등장하지만, 

 





**DevOps**를 검색하면 같이 따라오는 단어 두개가 있습니다.

![image](https://user-images.githubusercontent.com/22608825/103435010-46900080-4c4c-11eb-9f6e-4a1201674309.png)

### CI(Continuous Integration)

> 개발이 끝난 후 코드 품질을 관리하는 기존 방식에서 벗어나, 개발 중 지속적으로 코드 통합과 품질유지를 목표하는 것을 의미합니다.
>
> 버전관리를 이용해 프로젝트를 진행 중 개발인원 들이 커밋을 재때 하지 않아 한 참 뒤에 한번에 통합할 때나, 많은 양의 코드를 일일이 테스트 하는 비용을 줄이기 위함을 의미합니다.

- 개발 중 지속적(Continuous)으로 코드에 대한 통합(Integration)을 진행하여, 품질을 유지하는 것

- 개발자간의 코드 충돌을 방지하기 위한 자동화 프로세스

- 정기적인 빌드 및 테스트를 거쳐 공유 레포지토리에 병합

- 기존 코드와 신규 코드 간의 충돌이 발견 시 버그 수정 가능

  

### CD(Continuous Delivery)

- CI 프로세스를 통해 개발 중 지속적트오 테스트를 진행하고, 이를 통과한 코드에 대해 서버(테스트, 운영)에 자동으로 배포
- 애플리케이션을 프로덕션으로 배포 가능
- 프로덕션 환경으로 배포할 준비가 되어 있는 코드 베이스를 확보하는 것이 목표



### CD(Continuous Deployment)

- 프로덕션 준비가 완료된 빌드를 코드 리포지토리에 자동으로 배포하는 형태
- 애플리케이션을 프로덕션으로 릴리스 하는 작업을 자동화



### CD 정리

소프트웨어가 항시 신뢰 가능한 수준에서 배포될 수 있도록 지속적으로 관리하는 것이 목표이다.

CI가 선행되어야 가능하며, CI에서 통과한 코드에 대해 서버에 배포하는 것이다.

CI/CD 파이프 라인의 단계는 각기 다른 태스크 하위 집합으로 이루어져 있으며, 이를 파이프 라인 단계(pipeline state)라고 합니다.

- 빌드(Build) - 애플리케이션을 컴파일하는 단계
- 테스트(Test) - 코드를 테스트하는 단계. (위에 설명한대로 테스트 단계를 자동화 한다.)
- 릴리스(Release) - 애플리케이션을 리포지토리에 제공하는 단계
- 배포(Deploy) - 코드를 프로덕션에 배포하는 단계
- 검증 및 컴플라이언스(Validation & compliance) - 빌드 검증 단계는 해당 조직의 필요에 따라 결정.



![image](https://user-images.githubusercontent.com/22608825/103437855-dbf0bc00-4c6f-11eb-98db-d84d366b60de.png)



### 솔루션

#### CircleCI

> Cloud 에서 구동하며, AWS의 ECS, S3, EKS 등 다양한 서비스를 지원합니다.
>
> 가격은 제한 내에 무료입니다.

  

#### Github Action

>Github 저장소를 기반으로 소프트웨어 개발 Workflow를 자동화 할 수 있는 도구입니다.
>
>Github에서 직접 제공하는 CI/CD 도구이며 공개 저장소는 무료이며, 비공개 저장소는 계정에 부여된 무료 사용량 이후 유료로 전환됩니다.
>
>또한 AWS의 ECS를 지원합니다.
>
>[Github Action 빠르게 시작하기 :: 조은우 개발 블로그 (jonnung.dev)](https://jonnung.dev/devops/2020/01/31/github_action_getting_started/)
>
>[[Github action\] 을 이용해서 AWS ECS 배포 자동화 (velog.io)](https://velog.io/@q00/Github-action-aws-ecs-Github-CICD-55k38sf8ik)



## 개인적

최근 추세는  CI CD는 개발자가 구현하고 DevOps는 환경보단 포지션의 느낌이라고 한다.

리소스에 대한 권한을 각 개발자에게 부여하고, 스키마 설계 등 DBA의 느낌? 관리자의 느낌이 강하다고 한다.



CI CD를 DevOps에 연결하지말고 서로 독립적으로 이해하는 편이 좋을 것 같다.



## 참고문헌

#### [DevOps란 무엇인가. 그동안 DevOps란 말을 들어만 봤지 정확히 이게 어떤 의미인지를… | by 심재철 | Medium](https://simsimjae.medium.com/devops란-무엇인가-c50f4d86666b)

#### [DevOps\]CI/CD (지속적 통합/지속적 제공) 개념과 과정/ 툴 별 장단점 비교 (tistory.com)](https://devuna.tistory.com/56)

#### [QA\] CI/CD 란? | 코딩장이 (itholic.github.io)](https://itholic.github.io/qa-cicd/)

#### [CI/CD 파이프라인이란? (redhat.com)](https://www.redhat.com/ko/topics/devops/what-cicd-pipeline)