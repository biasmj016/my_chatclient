# my_saju_interpret

chatclient 찍먹해보기위한 미니 사주풀이 프로젝트

## 기술스택

- Java 21
- Spring Boot 3.4.4
- Redis
- Docker

## 프로젝트 목적 및 기능

이 프로젝트의 목적은 사주명리학을 기반으로 한 사주풀이를 제공하는 것입니다. 주요 기능은 다음과 같습니다:
- 사용자가 입력한 생년월일, 성별, 양력 여부, 윤달 여부, 이름, 출생지 등의 정보를 바탕으로 사주풀이를 제공합니다.
- Redis를 사용하여 캐싱 기능을 제공합니다.
- OpenAI API를 사용하여 사주풀이를 생성합니다.

## 테스트 방법

   ```http
   POST http://localhost:8889/api/saju/interpret
   Content-Type: application/json

   {
     "year": 1990,
     "month": 1,
     "day": 1,
     "hour": 0,
     "minute": 0,
     "gender": "male",
     "isSolar": true,
     "leapMonth": false,
     "name": "홍길동",
     "birthplace": "서울"
   }
   ```

