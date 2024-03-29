# 백엔드 트랙 기말과제 - 도서관리 시스템

다음 기능명세를 바탕으로 도서관리 시스템 API 서버를 개발한다.

## 개발환경
- Java 17+
- Spring Boot 3.2.2

## 기능명세

### 회원

- 관리자는 회원을 등록할 수 있다.
- 관리자는 등록된 회원 목록을 조회할 수 있다.
- 관리자는 회원 정보를 수정할 수 있다.
- 관리자는 회원 정보를 삭제할 수 있다.

### 도서

- 관리자는 도서를 등록할 수 있다.
- 관리자/회원은 도서 목록을 조회할 수 있다.
- 관리자는 도서 정보를 수정할 수 있다.
- 관리자는 도서 목록에서 특정 도서를 제거할 수 있다.

### 대출/반납

- 회원은 대출중이 아닌 도서를 대출할 수 있다.
    - 학생은 최대 10권, 권당 10일 대출
    - 교직원은 최대 20권, 권당 30일 대출
    - 관리자는 최대 100권, 권당 110813일 대출
- 회원은 대출한 도서 목록을 조회할 수 있다.
- 회원은 대출중인 도서를 반납할 수 있다.
- 회원은 대출한 도서의 반납 기한을 연장할 수 있다.
    - 연장은 1회에 한해 허용되며, 반납 예정일 당일에만 가능하다.
    - 연장시 반납 예정일은 5일 추가된다.

### 구입신청 (선택구현)

- 회원은 희망도서를 신청할 수 있다.
    - 신청할 도서는 기존에 등록된 도서 목록에 없어야 한다.
- 회원은 자신이 신청한 희망도서 목록을 조회할 수 있다.
    - 신청상태: 신청, 등록, 취소
- 관리자는 희망도서 목록에서 도서를 등록할 수 있다.
    - 관리자는 신청된 희망도서를 취소할 수 있다.
    - 등록/취소한 경우 처리일이 기록된다.
- 관리자는 전체 희망도서 목록을 조회할 수 있다.
- 회원은 자신이 신청한 희망도서를 취소할 수 있다.

## API 명세

다음 링크의 API 명세를 준수한다.

https://documenter.getpostman.com/view/32570206/2s9YypFP22

## 과제 진행방식

- 과제는 이 저장소를 Fork & Clone 해 시작한다
- [멋사 컨벤션](https://github.com/LikeLionHGU)을 준수하여 버전관리를 한다.
- 구현이 완료되면 이 저장소에 Pull Request 를 보내 과제를 제출한다.
  - PR 제목은 `[12기] $이름 기말과제 제출합니다` 형식으로 작성한다.
  - PR 본문에는 자유 형식으로 과제에 대한 소감(회고)을 작성한다.
- 과제 진행중에 궁금한 점은 멋사 디스코드 채널을 통해 질문한다.

## 유의사항

- `src/main/resources/` 디렉토리에 `application.yml` 파일을 추가하여 DB 접속 정보 등 필요한 설정을 추가한다.
- `build.gradle`에 라이브러리를 따로 추가하지 않는다.
- API 명세에 정의된 API 외에는 추가로 정의하지 않는다.
- 패키지를 통해 클래스를 적절히 분리한다.
- 예외 처리는 각자 판단하여 구현한다.
- 포스트맨을 통해 API 요청에 대한 응답이 정상적으로 이루어지는지 확인한다.
