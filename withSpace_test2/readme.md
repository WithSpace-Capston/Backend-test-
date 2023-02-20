테스트 2 

Spring Boot ver 3.0.2

Java 17

DB 
H2 Database, Version 2.1.214


test 라이브러리 - Junit5

- - - - - -

JDBC URL : jdbc:h2:~/withSpace_test2

- - - - - -

2023.02.21.한슬


- 일단 Setter다 열어둔 상태. 후에 보완 필요

- domain들 셋팅 완료 
  전에 의논한friendShip의 승낙/거절 그건 후에 
   테스트 진행하면서 넣는게 좋을 거 같아서 아직은 안 넣었음. 

- 회원과 팀 관련 간단한 테스트 집어넣음 -> 회원가입, 팀생성, 팀가입
   그에 필요한 repository, service, SpringConfig 셋팅 완료

- 회원/팀 서비스를 분리해서 진행하여 teamServiceTest에서
   Member를 만들고 테스트 할때, Member가 persist되지 않아 해당 Member를 DB에서 눈으로 확인하기 힘듦. 
   하지만 분리하는게 맞다고 생각하고 기능적으론 문제 없는데 눈으로..보고싶다면 어쩌지 



- - - - - -

