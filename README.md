# BookMoa 프로젝트
독서 습관 정리 및 온라인 이북 플랫폼 이벤트 정리 앱입니다.

### Team
서민경, 정은정 

### Features
1. Main : 독서 습관 기록 
2. Sub : 이북 플랫폼 이벤트 정리

### 사용 기술
1. Android Studio
2. Kotlin
3. Firebase

## Detail
### 1. 설계 구조

![image](https://user-images.githubusercontent.com/100047095/178299847-13796e18-bc28-46e2-9f82-da2c89d6fb2a.png)


### 2. 독서 기록
RecyclerView로 달력 구현

Retrofit2를 이용하여 Naver 검색 API와 통신하여 검색한 키워드에 일치하는 책 목록 불러오기

불러온 책 정보를 오늘의 독서 습관으로 기록한 경우 Firebase DB에 저장

![image](https://user-images.githubusercontent.com/100047095/178299975-1ddf606e-e301-4c0a-9a7d-a27d4aef8307.png)


### 3. 이북 플랫폼 이벤트 정리

JSoup을 사용해 이벤트 정보를 크롤링한 후 사용자에게 제공

![image](https://user-images.githubusercontent.com/100047095/178300699-4c57fa82-3226-4e18-8146-58f721651ee8.png)
