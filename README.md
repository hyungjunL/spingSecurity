- 스프링부트 3.2.5
- 자바 17
- MAVEN

- SpirngSecurity Oauth2를 이용한 인증서버 구축 스터디
- 클라이언트(API) 서버를 따로 만들지 않고 인증서버에 일단 다 구축, 추후 개발 예정 일단 기능검증만 함
- 클라이언트 관리 또한 하드코딩 돼 있음 추후 클라이언트관리 로직 추가예정(DB: mariadb, orm: jpa 예정)

- 플로우
> 클리언트가 로그인을 요청할 때 인증서버의 http://localhost:9000/oauth2/authorize?response_type=code&client_id=test&scope=openid&redirect_uri=http://localhost:9000/test url을 호출 > oauth2 로그인 페이지 redirection
> 로그인 성공 시 얻은 code값을 활용하여 testController에 있는 http://localhost:9000/test url로 redirection > 호출받은 API에서 code parameter를 이용하여 token_refresh token을 다시 return해줌


![image](https://github.com/hyungjunL/spingSecurity/assets/91936481/453a8756-71fe-4ba9-a81b-1fa10efff363) >>> 호출


![image](https://github.com/hyungjunL/spingSecurity/assets/91936481/0645372f-9621-4b12-8004-236306fcb2e6) >>> 로그인 화면


![image](https://github.com/hyungjunL/spingSecurity/assets/91936481/1c26cacc-9393-4397-847a-1fd404f6aafe) >>> 토근 발급 완료


> 1일차 결론 : 아직 스터디 할 부분이 많음 처음해보는 인증서버 구축이라 소스코드의 모호함이 많음 더 많은 스터디가 필요함... 
