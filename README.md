아직 미완의 서비스 입니다.

api 호출 필수 구현요소(parse, url alias, dto..) 구현 후 서비스 구현 할 예정

해당서비스가 하는 일
1. 로스트아크 api 활용하여 경매장의 유효한 아이템들의 정보를 가져와서 저장
2. 주기적(1분에 100번가량 제한)으로 호출하여 정보 저장
3. hdfs로 datalake에 데이터 저장(향후 LLM공부하여 데이터 분석 맡김)



##main 기능
- [ ] 매 분 100회 이내의 API Call을 통하여 데이터 추출
- [X] API Call을 위한 응답객체 생성
- [ ] 데이터 저장 후 처리(Kafka event발행등)

##architecture 부분
- [X] Spring Cloud config 서버 및 클라이언트 구축하여 사용(config 통합 및 암호화)
- [ ] Logging Elastic - kibana로 확인가능하게 구축
- [X] url Alias로 변환하여 사용
- [X] Audit 컬럼 자동추가

기타 개발 추가사항 확인 시 작성
