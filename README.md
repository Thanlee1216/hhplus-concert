### Milestone
 * Github의 Milestone 기능 활용([Milestone 링크](https://github.com/Thanlee1216/hhplus-concert/milestone/1))
 * 이슈를 활용하여 Roadmap에 Task 생성([Roadmap 링크](https://github.com/users/Thanlee1216/projects/3/views/4))
   > ![Road Map](https://github.com/Thanlee1216/hhplus-concert/assets/97030142/ce4120b4-22f5-4d14-91a1-f855bdd82511)
 
### Sequence Diagram
 * 전체 구조
   > <img width="400" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/99cbf5eb-c2a6-466a-a0cf-2f702aa616aa">
 * 기능별 구조
   > <img width="200" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/5856fbcc-20cb-47d7-a522-16284ca59112">
   > <img width="340" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/059c38dd-c63e-47a6-866d-e6e843f049e4">
   > <img width="215" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/74d4efd4-7671-484a-8bd4-5b62724b7166">
   > <img width="160" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/395a3dab-6679-4936-b475-a0087871e8fb">
   > <img width="160" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/0f503d40-aabb-4b25-93b0-7bc7f1d1e7a3">
   
### ERD
   > <img width="800" alt="image" src="https://github.com/Thanlee1216/hhplus-concert/assets/97030142/61f06366-ad89-4138-8370-62b8de639ff4">

## Index 테스트
### Index 테스트 개요
  * 대상 : 콘서트 Concert Option 테이블
  * 사유
    * 예약 가능한 콘서트의 목록을 불러올 때 전체 데이터 중 오늘 날짜 이후의 데이터를 조회해옴
    * pk가 아닌 날짜를 조건으로 사용하기 때문에 index를 사용하지 않음
    * 날짜는 시간이 지날수록 카디널리티가 높아짐
### 테스트 방식
  1. 10만건의 콘서트 옵션 데이터 insert
  2. 날짜 조건으로 조회
  3. index 생성(날짜)
  4. 동일한 쿼리 조회 후 비교
### Test Case
#### case1 : concert_date 단일 조건 + 35만건 조회
  * index before
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/f8c0a372-c3cd-4d9e-948e-a7f1ea619274">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/f3d97ea5-33a3-4f88-9e76-60c93cc53e99">
  * index after
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/6216fdb9-2436-4717-9e24-ee7d0b26e8c8">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/a74f949f-f16b-4778-8ea2-9862bce804f6">
#### case2 : concert_date 단일 조건 + 1000건 조회
  * index before
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/e78e1161-b3bf-4247-b753-a1be69322d2b">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/d29cd481-5eba-4b0e-9a61-cf4fd36c31ca">
  * index after
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/b76bdfdc-3f51-42a2-85a3-0224b741fdeb">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/27be3b91-3a87-4dc7-b88c-7fb2fd892f09">
#### case3 : concert_id(fk) and concert_date 복합 조건 + 20만건 조회
  * index before
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/0d6243ff-071d-4d64-9258-1281b11a2e03">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/3d5a0f73-109f-49da-8dc1-563c663a6d54">
  * index after
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/b30d6505-c35d-4030-93c8-0a8c0495d1fa">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/43e1e7d5-3d52-4202-aed7-025f8f4038ee">
#### case4 : concert_id(fk) and concert_date 복합 조건 + 1000건 조회
  * index before
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/828a1067-bf23-4c4f-8fce-92fac97654f4">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/ebb9ac06-f3a4-4ff1-883f-795b3a776691">
  * index after
  > <img width="300" alt="image" src="https://github.com/user-attachments/assets/deadbe92-2765-44a9-b1e8-7293fb5cbddf">
  > <img width="1200" alt="image" src="https://github.com/user-attachments/assets/195a8fd1-1558-45b2-a8ea-d2cbabf99ede">
### 테스트 결과 정리
  #### case1 : concert_date 단일 조건 + 35만건 조회
  * 평균 값 : index 적용 전(0.1804), 후(0.1613) <U>**의미 없는 차이**</U>
  * explain : <U>**둘 다 풀스캔**</U>으로 48만건의 row를 검색
  #### case2 : concert_date 단일 조건 + 1000건 조회
  * 평균 값 : index 적용 전(0.0727), 후(0.0015) <U>**약 48배 차이**</U>
  * explain : index 적용 후 <U>**48만건 -> 1천건으로 검색 범위 좁혀짐**</U> (type range)
  #### case3 : concert_id(fk) and concert_date 복합 조건 + 20만건 조회
  * 평균 값 : index 적용 전(0.1346), 후(0.1351) <U>**의미 없는 차이**</U>
  * explain : <U>**둘 다 풀스캔**</U>으로 48만건의 row를 검색
  #### case4 : concert_id(fk) and concert_date 복합 조건 + 1000건 조회
  * 평균 값 : index 적용 전(0.0783), 후(0.0013) <U>**약 60배 차이**</U>
  * explain : index 적용 후 <U>**48만건 -> 1천건으로 검색 범위 좁혀짐**</U> (type range)
### 결론
  * index를 생성해도 전체 데이터 대비 대량의 데이터를 조회하는 경우 결국 전체 탐색
  * 조회 데이터의 양에 따라 index를 잘 나누고 쿼리의 조건을 통해 조회 row의 수를 줄여야 할 것으로 보임

## MSA 서비스 분리 보고서
### 서비스 분리 목록
#### 1. Users API Module
* 정의 : 유저 관련 API
* 책임 : 로그인, 로그아웃 등 세션 관리 / 대기열 관리
#### 2. Concert API Module
* 정의 : 콘서트 관련 API
* 책임 : 콘서트 정보 조회 / 콘서트 정보 등록 / 콘서트 좌석 예약(선점)
#### 3. Payment API Module
* 정의 : 결제 관련 API (콘서트 외의 판매 항목 추가 고려)
* 책임 : 충전 / 결제
#### 4. Batch Module
* 정의 : Scheduler 및 대량의 데이터 처리 모듈
* 책임 : 데이터 백업 / 데이터 정리 등

### 문제점과 해결 방안
#### 1. Service File
 * 분리된 각 모듈에 해당하는 서비스를 참조하고 있던 로직에 문제 발생
   * 예) 결제 이후 예약(선점)된 좌석의 상태 변경 - Payment -> Concert
 * 해결 방안
   1. Payment 모듈에 Concert Seat 상태 변경 직접 구현
      * Table의 변경 혹은 Seat 처리 방식의 변경이 발생할 경우 매우 곤란해짐
   2. Event Driven
      * Payment 모듈에서 Concert 모듈의 API를 호출하여 처리
      * 좋은 방법으로 예상되나 실패 시 처리 로직이 필요하지 않을가 생각됨
   3. Batch 모듈에 위임
      * Scheduler로 일정 시간마다 처리
      * 실시간성은 떨어지지만 안전한 방법이라고 생각됨(서버가 죽었다가 살아난다고 해도 처리 가능)

#### 2. 공유 자원 관리
 * 세션, 캐시 등의 자원을 서버 내에서 처리할 경우 모듈별로 서로 다른 데이터를 처리할 위험이 있음
   * 예) 로그아웃 한 유저인데 결제가 가능할 수 있음
 * 해결 방안
   1. DB 활용
      * 모든 공유 자원을 DB에 넣고 사용
      * Lock, 속도 저하, DB 부하 등의 단점이 있음
   2. Redis 활용
      * In Memory DB라서 속도가 빠름
      * 동시성에 대한 처리 쉽게 가능
      * 특정 데이터들만 사용하기 때문에 속도 저하 부담 적음