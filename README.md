# 🗓️ Planner

##  프로젝트 소개
Spring Boot + JPA 기반 일정 관리 API
- 일정 생성, 조회, 수정, 삭제 및 회원가입, 로그인, 댓글 기능 제공

---

##  개발 환경
- **Language:** Java 17+
- **IDE:** IntelliJ IDEA
- **Spring Boot**
- **My SQL**
- **lombok**
- **Spring web**
- **Version Control:** Git & GitHub

---
##  ERD




## API 명세서
| 기능               | Method   | URL                      | 요청(Request)                                                                             | 응답(Response)                                                                                                                                    | 상태 코드 / 예외 상황                                                                            |
| ---------------- | -------- | ------------------------ | --------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| **일정 생성**        | `POST`   | `/todo`                  | `json { "title": "테스트 제목", "content": "스프링 공부", "userName": "순관", "password": "1234" }` | `json { "id": 1, "title": "테스트 제목", "content": "스프링 공부", "userName": "순관", "createdAt": "2025-11-06T13:45", "modifiedAt": "2025-11-06T13:45" }` | ✅ `201 Created` <br> 🔹 비밀번호는 응답에서 제외                                                    |
| **전체 일정 조회**     | `GET`    | `/todo`                  | (없음)                                                                                    | `json [ { "id": 1, "title": "테스트 제목", "content": "스프링 공부", "userName": "순관", "createdAt": "...", "modifiedAt": "..." } ]`                       | ✅ `200 OK`                                                                               |
| **작성자 기준 일정 조회** | `GET`    | `/todo?userName=순관`      | (없음)                                                                                    | `json [ { "id": 1, "title": "테스트 제목", "content": "스프링 공부", "userName": "순관", "createdAt": "...", "modifiedAt": "..." } ]`                       | ✅ `200 OK`                                                                               |
| **단건 일정 조회**     | `GET`    | `/todo/{id}`             | (없음)                                                                                    | `json { "id": 1, "title": "테스트 제목", "content": "스프링 공부", "userName": "순관", "createdAt": "...", "modifiedAt": "..." }`                           | ✅ `200 OK` <br> ❌ `404 Not Found` : 존재하지 않는 일정                                           |
| **일정 수정**        | `PATCH`  | `/todo/{id}`             | `json { "title": "수정된 제목", "userName": "변경된이름", "password": "1234" }`                   | `json { "title": "수정된 제목", "userName": "변경된이름", "modifiedAt": "..." }`                                                                          | ✅ `200 OK` <br> ❌ `404 Not Found` : 잘못된 비밀번호 또는 일정 없음                                    |
| **일정 삭제**        | `DELETE` | `/todo/{id}`             | `json { "password": "1234" }`                                                           | (204 No Content)                                                                                                                                | ✅ `204 No Content` <br> ❌ `404 Not Found` : 일정 없음 <br> ❌ `401 Unauthorized` : 비밀번호 불일치   |
| **댓글 생성**        | `POST`   | `/todo/{todoId}/comment` | `json { "content": "좋아요!", "userName": "정순관테스트", "password": "1111" }`                  | `json { "id": 1, "content": "좋아요!", "userName": "정순관테스트", "createdAt": "...", "modifiedAt": "..." }`                                            | ✅ `201 Created` <br> ❌ `404 Not Found` : 존재하지 않는 일정 <br> ❌ `400 Bad Request` : 댓글 10개 초과 |




##  주요 기능

### 1. 일정 생성
- 사용자는 제목, 내용, 작성자명, 비밀번호를 입력해 새로운 일정을 생성합니다.

### 2. 일정 조회
- 전체 일정 조회 : 모든 일정을 최신 수정일 기준으로 조회합니다.
- 작성자별 일정 조회 : 특정 작성자의 일정만 필터링해 조회합니다.
- 일정 단 건 조회(단일) : ID로 특정 일정을 조회합니다.

### 3. 일정 수정
- 선택한 일정을 비밀번호 입력, 검증 후 제목과 작성자명을 수정합니다.
-
- ### 4. 일정 삭제
- 선택한 일정을 비밀번호 입력, 검증 후 삭제합니다.

- ### 5. 댓글 작성
- 특정 일정에 댓글을 작성합니다.



```
