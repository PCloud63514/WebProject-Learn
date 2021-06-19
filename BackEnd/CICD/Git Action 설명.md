# Git Action 설명

Git Hub에서 제공하는 CI 서비스.



Repository를 구성하면 Action 탭을 확인할 수 있으며, 이를 사용하여 Git Action을 구성할 수 있다.



역시 주요목적은 CI이며, 이를 위해 Git Action은 다양한 기능을 제공한다.

우선 OS를 직접 선택하여 다양한 테스트 환경을 구성할 수 있다.

또한 특정 이벤트를 인식하여 이벤트를 동작시킬 수 있다.



예시로 특정 Branch에서 Push나 Pull Request 이벤트가 발생하였다면 Git Action이 동작하도록 구성할 수 있다.

Git 이력의 이벤트만 체크할 수 있는건 아니다.

특정 코드나 명령어를 이용할 수도 있으므로 사용 방법이 무궁무진해진다.



나름 가장 사용하기 쉬운 CI라고 생각하며, 예전과 다르게 Repository를 Private로 구성하는데 별도의 비용도 소모되지 않아 Git Hub를 사용 중이라면 Git Action을 사용하는 것도 좋은 선택이라 생각한다.

물론 Private Repository에서 Git Action을 사용하면 월 기준 회수 제약이 걸리지만 어지간해서는 초과할 일이 없다고 봐도 무방하다.



만약 Git Action을 사용하고자 한다면 Git Flow 도 검색하여 프로젝트에 함께 적용하는 것을 권장한다.