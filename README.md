# miniWeb
Spring Boot, Spring Data JPA, MySQL을 이용하여 작은 게시판을 만들었습니다.

로그인의 경우 SpringSecurity을 적용해보았습니다. 인증방식 (세션, JWT, Oauth)은 차후 추가해볼 것입니다.
우선적으로 formLogin을 이용한 Login을 구현하였습니다.

화면의 경우 Thymeleaf를 통하여 구현하였기에 Controller에서 model로 데이터를 전달했습니다. 

![miniWeb](https://user-images.githubusercontent.com/77107216/178796333-0d1c8d1e-5531-4f79-93a2-1c2778b63aa2.png)


MySQL Workbench를 통하여 만든 ERD입니다.
