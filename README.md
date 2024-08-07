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

### 동시성 문제 발생 케이스
 * 포인트 충전/사용
 * 좌석 예약

### Lock
 * **Optimistic Lock(낙관적락)**
 > * 락을 걸지 않고 업데이트 데이터의 Version으로 충돌 체크
 > * 동일한 로직을 동시에 여러번 실행할 경우 Write 시 Version을 체크하여 나중에 온 요청은 실패시킨다.
 > * 충돌이 적은 환경에 적합
 > * 테스트
 >   * 2명의 유저가 같은 좌석에 거의 동시에 예약 요청을 한 경우를 가정하여 진행
 > ```
 > 요청1 : 좌석 정보 조회(version 1) -> 좌석 상태 업데이트(version 1 -> 2)
 > 요청2 : 좌석 정보 조회(version 1) -> 좌석 상태 업데이트(version 1 -> 2) -> 좌석 데이터가 version 2이므로 실패
 > ```
 > * 장점
 >   * 요청을 잡아두지 않기 때문에 성능이 좋음
 >   * 락을 잡지 않기 때문에 데드락이 발생하지 않음
 > * 단점
 >   * 충돌이 많이 발생하는 로직에서 retry 로직이 있을 경우에는 동시 요청이 많으면 많을수록 성능이 매우 안좋아질 가능성이 높음

 * **Pessimistic Lock(비관적락)**
 > * 데이터에 락을 걸어 데이터의 일관성을 보장
 > * 종류
 >   * PESSIMISTIC_READ : 다른 트랜잭션이 데이터를 수정하지 못하도록 읽기 락을 설정합니다. 여러 트랜잭션이 동시에 읽기 락을 획득할 수 있지만, 쓰기 작업은 불가능합니다. 
 >   * PESSIMISTIC_WRITE : 다른 트랜잭션이 데이터를 읽거나 수정하지 못하도록 쓰기 락을 설정합니다. 한 번에 하나의 트랜잭션만이 락을 획득할 수 있습니다.
 > * 테스트
 >   * 포인트의 충전과 사용이 동시에 발생한 경우를 가정하여 진행(PESSIMISTIC_WRITE)
 > ```
 > 요청1 : 유저 포인트 데이터 조회(읽기와 쓰기에 대한 Lock) -> 유저 포인트 데이터 업데이트(포인트 차감) -> Lock 해제
 > 요청2 : Lock이 풀릴때 까지 대기 -> 유저 포인트 데이터 조회(읽기와 쓰기에 대한 Lock) -> 유저 포인트 데이터 업데이트(포인트 충전) -> Lock 해제
 > ```

### 동시성 문제 해결을 위해 채택한 Lock 방식과 근거
 * 포인트 충전/사용
   * 적용할 Lock : Pessimistic Lock(비관적락) - PESSIMISTIC_WRITE
   * 근거 : 포인트 충전과 사용이 동시에 이루어질 경우 "읽기"도 먼저 되면 안되고 앞선 요청이 완료된 포인트 금액으로 연산을 해야하기 때문
 * 좌석 예약
   * 적용할 Lock : Pessimistic Lock(비관적락) - PESSIMISTIC_WRITE
   * 근거 : 1개의 좌석에는 1명의 유저만 예약이 가능하기 때문에 첫번째 요청에 의해 예약 완료 상태가 된 정보를 조회하여 두번째 요청부터는 튕겨내야함
   * 절충안 : PESSIMISTIC_READ와 version을 모두 사용하여 version이 다른경우 out + PESSIMISTIC_READ로 조회한 데이터의 예약 상태가 완료라면 out
     * 비관적 락과 낙관적 락을 모두 사용하기 때문에 비관적 락의 PESSIMISTIC_WRITE만 사용할때보다 성능이 더 좋을 것으로 예상됨