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

<img width="1051" height="870" alt="플래서v2ERD" src="https://github.com/user-attachments/assets/8f66b2ab-943e-4247-bb9b-7b78c0605994" />



## API 명세서
| 기능               | Method   | URL                      | 요청(Request)                                                                                  | 응답(Response)                                                                                                                   | 상태 코드 / 예외 상황                                                                          |
| ---------------- | -------- | ------------------------ | --------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| **회원가입**        | `POST`   | `/user`                  | ```json { "name": "정순관", "email": "jung@test.com", "password": "Passw0rd!" }```            | ```json { "id": 1, "name": "정순관", "email": "jung@test.com", "createdAt": "...", "modifiedAt": "..." }```                      | ✅ `201 Created` <br> ❌ 이메일 중복 시 `400 Bad Request`                                     |
| **로그인**          | `POST`   | `/user/signIn`           | ```json { "email": "jung@test.com", "password": "Passw0rd!" }```                              | `200 OK` (세션 쿠키 발급: `JSESSIONID`)                                                                                         | ❌ 인증 실패 시 `401 Unauthorized`                                                           |
| **로그아웃**        | `GET`    | `/user/signOut`          | (없음)                                                                                          | `200 OK`                                                                                                                        |                                                                                               |
| **유저 조회(단 건)** | `GET`    | `/user/{id}`             | (없음)                                                                                          | ```json { "id": 1, "name": "정순관", "email": "jung@test.com", "createdAt": "...", "modifiedAt": "..." }```                    | ❌ 존재하지 않으면 `404 Not Found`                                                           |
| **유저 조회(전체)** | `GET`    | `/user`                  | (없음)                                                                                          | ```json [ { "id": 1, "name": "정순관", "email": "jung@test.com", "createdAt": "...", "modifiedAt": "..." } ]```                  |                                                                                               |
| **유저 수정**        | `PATCH`  | `/user/{id}`             | ```json { "name": "정순관" }```                                                                | ```json { "name": "정순관", "modifiedAt": "..." }```                                                                           | ❌ 존재하지 않으면 `404 Not Found`                                                           |
| **유저 삭제**        | `DELETE` | `/user/{id}`             | (없음)                                                                                          | `204 No Content`                                                                                                                | ❌ 존재하지 않으면 `404 Not Found`                                                           |
| **일정 생성**        | `POST`   | `/todo`                  | ```json { "title": "스터디", "content": "백엔드 공부" }```                                      | ```json { "id": 1, "userId": 1, "title": "스터디", "content": "백엔드 공부", "createdAt": "...", "modifiedAt": "..." }```       | ✅ `201 Created` <br> ❌ 로그인 필요 `403 Forbidden`                                         |
| **전체 일정 조회**     | `GET`    | `/todo`                  | (없음)                                                                                          | ```json [ { "id": 1, "userId": 1, "title": "스터디", "content": "백엔드 공부", "createdAt": "...", "modifiedAt": "..." } ]```    | ✅ `200 OK`                                                                                  |
| **단건 일정 조회**     | `GET`    | `/todo/{id}`             | (없음)                                                                                          | ```json { "id": 1, "userId": 1, "title": "스터디", "content": "백엔드 공부", "createdAt": "...", "modifiedAt": "..." }```      | ✅ `200 OK` <br> ❌ 존재하지 않으면 `404 Not Found`                                         |
| **일정 수정**        | `PATCH`  | `/todo/{id}`             | ```json { "title": "수정된 제목", "content": "수정된 내용" }```                                 | ```json { "title": "수정된 제목", "content": "수정된 내용", "modifiedAt": "..." }```                                           | ✅ `200 OK` <br> ❌ 본인이 아니면 `403 Forbidden` <br> ❌ 일정 없으면 `404 Not Found`       |
| **일정 삭제**        | `DELETE` | `/todo/{id}`             | (없음)                                                                                          | `204 No Content`                                                                                                                | ✅ `204 No Content` <br> ❌ 본인이 아니면 `403 Forbidden` <br> ❌ 일정 없으면 `404 Not Found` |



##  주요 기능


### 1. 회원 관리
- 회원가입 기능
- 로그인, 로그아웃 기능
- 회원 조회, 수정, 삭제 가능

### 2. 일정 생성
- 사용자는 제목과 내용을 입력해 새로운 일정을 생성
- 로그인 세션 필요

### 3. 일정 조회
- 전체 일정 조회: 모든 일정 최신 수정일 기준 조회
- 단 건 조회: 특정 ID 일정 조회

### 4. 일정 수정
- 선택한 일정의 제목과 내용을 수정
- 본인 일정만 가능
- 로그인 세션 필요

### 5. 일정 삭제
- 선택한 일정 삭제
- 본인 일정만 가능
- 로그인 세션 필요
---
미구현
### 6. 댓글 관리
- 특정 일정에 댓글 작성 가능
- 댓글 조회 가능
- 댓글은 최대 10개 제한
- 일정이 없으면 댓글 작성/조회 불가

```
