# Api 명세서


`/token/create` - Token 생성 API
> ### Request
 > * TokenRequestDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_id - 콘서트 식별 ID (Long)
 >   * concert_option_id - 콘서트 옵션 식별 ID (Long)
 >   * queue_number - 대기열 순서 (Long)
> ### Response
 > * TokenResponseDTO
 >   * token - 콘서트 예약을 위한 토큰 (String)
> ### Error
 > * 필수 값의 누락
 > * 존재하지 않는 유저, 콘서트, 콘서트 옵션에 대한 조회
 > * 토큰 생성 실패
> ### Authorization
 > * 로그인 유저 세션

`/concert/list` - 모든 콘서트의 목록을 조회하는 API
> ### Request
> ### Response
 > * List\<ConcertResponseDTO\>
 >   * concert_id - 콘서트 식별 ID (Long)
 >   * concert_name - 콘서트 명 (String)
 >   * concert_option_id - 콘서트 옵션 식별 ID (Long)
 >   * concert_date - 콘서트 일자 (Timestamp)
 >   * concert_reservation_date - 콘서트 예약 시작 일자 (Timestamp)
 >   * limit_seat - 좌석 수 (Long)
> ### Error
> ### Authorization
 > * 로그인 유저 세션

`/concert/list/{concert_date}` - 특정 날짜의 콘서트 목록을 조회하는 API
> ### Request
 > * concert_date - 콘서트일자 YYYYMMDD (String)
> ### Response
 > * List\<ConcertResponseDTO\>
 >   * concert_id - 콘서트 식별 ID (Long)
 >   * concert_name - 콘서트 명 (String)
 >   * concert_option_id - 콘서트 옵션 식별 ID (Long)
 >   * concert_date - 콘서트 일자 (Timestamp)
 >   * concert_reservation_date - 콘서트 예약 시작 일자 (Timestamp)
 >   * limit_seat - 좌석 수 (Long)
> ### Error
 > * 잘못된 날짜 형식
> ### Authorization
 > * 로그인 유저 세션

`/concert/seat` - 특정 콘서트의 좌석 목록을 조회하는 API
> ### Request
 > * ConcertRequestDTO
 >   * concert_id - 콘서트 식별 ID (Long)
 >   * concert_option_id - 콘서트 옵션 식별 ID (Long)
> ### Response
 > * List\<ConcertSeatResponseDTO\>
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long) 
 >   * concert_seat_num - 콘서트 좌석 이름 (String)
 >   * concert_seat_price - 콘서트 좌석 금액 (Long)
 >   * seat_status - 콘서트 좌석 상태(대기, 예약, 결제) (String)
> ### Error
 > * 존재하지 않는 콘서트, 옵션, 좌석에 대한 조회
> ### Authorization
 > * 로그인 유저 세션

`/concert/seat/reservation` - 특정 좌석을 예약하는 API
> ### Request
 > * ConcertReservationRequestDTO
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
 >   * token - 토큰 (String)
> ### Response
 > * List\<ConcertSeatResponseDTO\>
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long) 
 >   * concert_seat_num - 콘서트 좌석 이름 (String)
 >   * concert_seat_price - 콘서트 좌석 금액 (Long)
 >   * seat_status - 콘서트 좌석 상태(대기, 예약, 결제) (String)
> ### Error
 > * 이미 선택된 좌석
 > * 예약자 정보와 토큰 정보 불일치
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크

`/balance/{user_id}` - 유저의 포인트를 조회하기 위한 API
> ### Request
 > * user_id - 유저 식별 ID (Long)
> ### Response
 > * BalanceResponseDTO
 >   * user_id - 유저 식별 ID (Long) 
 >   * balance - 유저 잔액 (Long)
> ### Error
 > * 본인이 아닌 다른 유저의 포인트를 조회
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크

`/balance/charge` - 유저의 포인트를 충전하기 위한 API
> ### Request
 > * BalanceRequestDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * amount - 충전 금액 (Long)
> ### Response
 > * BalanceResponseDTO
 >   * user_id - 유저 식별 ID (Long) 
 >   * balance - 유저 잔액 (Long)
> ### Error
 > * 본인이 아닌 다른 유저의 포인트를 충전
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크

`/balance/history/{user_id}` - 유저의 포인트 충전/사용 내역 조회를 위한 API
> ### Request
 > * BalanceRequestDTO
 >   * user_id - 유저 식별 ID (Long)
> ### Response
 > * List\<BalanceHistoryResponseDTO\>
 >   * balance_history_id - 잔액 내역 식별 ID (Long)
 >   * amount - 금액 (Long)
 >   * transaction_type - 작업 타입(충전, 사용) (String)
 >   * transaction_date - 작업 날짜 (Timestamp)
> ### Error
 > * 본인이 아닌 다른 유저의 내역 조회
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크

`/payment` - 결제 API
> ### Request
 > * PaymentRequestDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
> ### Response
 > * PaymentResponseDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
 >   * seat_status - 콘서트 좌석 상태(대기, 예약, 결제) (Long)
> ### Error
 > * 본인이 아닌 다른 유저의 좌석 결제
 > * 좌석 예약 시간 만료
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크
 > * 토큰 유효성 체크

`/payment/cancel` - 결제 취소 API
> ### Request
 > * PaymentRequestDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
> ### Response
 > * PaymentResponseDTO
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
 >   * seat_status - 콘서트 좌석 상태(대기, 예약, 결제) (Long)
> ### Error
 > * 본인이 아닌 다른 유저의 좌석 결제 취소
> ### Authorization
 > * 로그인 유저 세션
 > * 토큰 탈취 여부 체크

`/payment/history/{user_id}` - 결제/취소 내역 조회 API
> ### Request
 > * user_id - 유저 식별 ID (Long)
> ### Response
 > * List\<PaymentHistoryResponseDTO\>
 >   * payment_history_id - 결제 내역 식별 ID (Long)
 >   * amount - 결제 금액
 >   * transaction_type - 결제 타입(승인, 취소) (String)
 >   * transaction_date - 결제 일자 (Timestamp)
 >   * user_id - 유저 식별 ID (Long)
 >   * concert_id - 콘서트 식별 ID (Long)
 >   * concert_name - 콘서트 명 (String)
 >   * concert_option_id - 콘서트 옵션 식별 ID (Long)
 >   * concert_date - 콘서트 일자 (Timestamp)
 >   * concert_seat_id - 콘서트 좌석 식별 ID (Long)
 >   * concert_seat_num - 콘서트 좌석 명 (String)
 >   * concert_seat_price - 콘서트 좌석 가격 (Long)
> ### Error
 > * 본인이 아닌 다른 유저의 결제 내역 조회
> ### Authorization
 > * 로그인 유저 세션
