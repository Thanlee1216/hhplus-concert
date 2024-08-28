import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
        { duration: '30s', target: 5000 },  // 30초 동안 5000명의 유저 도달
        { duration: '4m30s', target: 5000 }, // 5000명의 유저를 4분 30초 동안 유지
        { duration: '30s', target: 0 },  // 테스트 종료 시 30초 동안 유저 수 감소
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 응답 시간의 95%가 500ms 이하
        'http_req_failed': ['rate<0.01'], // 실패율이 1% 이하
    },
};

export default function () {
    // 가상 유저 ID(__VU)를 사용하여 고유한 userId 생성
    let userId = __VU;

    let payload = JSON.stringify({
        userId: userId
    });

    // 요청 헤더 설정
    let params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    // HTTP PUT 요청 보내기
    let res = http.put('http://host.docker.internal:8108/queue', payload, params);

    // 응답 확인
    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time is acceptable': (r) => r.timings.duration < 500,
    });

    // 요청 후 15초 대기
    sleep(15);
}
