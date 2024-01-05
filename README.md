api 정리 : https://docs.google.com/spreadsheets/d/1IWMzJh89v_LpKw273MpVAHRYexMn_mpybptselW3sUI/edit#gid=753593791


# gogoCard API

gogoCard API는 할 일 카드를 생성, 조회, 수정, 삭제할 수 있는 RESTful API입니다.

## API 명세

### 카드 생성

- Method: POST
- URL: http://{server_url}/api/gogocards
- Request:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| title | String | 필수 | 제목 |
| content | String | 필수 | 내용 |
| date | String | 필수 | 작성일 |
| author | String | 필수 | 작성자 |

- Response:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| id | Long | 필수 | 고유 id |

### 카드 조회

- Method: GET
- URL: http://{server_url}/api/gogocards/{id}
- Request:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| id | Long | 필수 | 고유 id |

- Response:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| title | String | 필수 | 제목 |
| content | String | 필수 | 내용 |
| date | String | 필수 | 작성일 |
| author | String | 필수 | 작성자 |

### 카드 목록 조회

- Method: GET
- URL: http://{server_url}/api/gogocards
- Request:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| author | String | 필수 | 작성자 |

- Response:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| title | String | 필수 | 제목 |
| id | Long | 필수 | 고유 id |

### 카드 수정

- Method: PUT
- URL: http://{server_url}/api/gogocards/{id}
- Request:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| id | Long | 필수 | 고유 id |
| title | String | 필수 | 제목 |
| content | String | 필수 | 내용 |
| author | String | 필수 | 작성자 |

- Response:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| isSuccess | Boolean | 필수 | 수정 성공 여부 |
| id | Long | 필수 | 수정한 카드 id |
| message | String | 필수 | 수정 성공 메세지 |
| code | Int | 필수 | 응답 코드 |

### 카드 삭제

- Method: DELETE
- URL: http://{server_url}/api/gogocards/{id}
- Request:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| id | Long | 필수 | 고유 id |

- Response:

| 파라미터 | 타입 | 필수여부 | 설명 |
| --- | --- | --- | --- |
| success | Boolean | 필수 | 삭제 성공 여부 |
| message | String | 필수 | 삭제 완료 메세지 |
| error | String | 필수 | 에러 내용 반환 |

## Result Code

| Code | message |
| --- | --- |
| 200 | 카드를 생성/조회/수정/삭제했습니다. |
| 400 | 유효하지 않은 데이터입니다. |
| 400 | 카드 제목을 입력해 주세요. |
| 400 | 카드 내용을 입력해 주세요. |
| 400 | 작성자를 입력해 주세요. |
