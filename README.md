# BookMoa 프로젝트
독서 습관 정리 및 온라인 이북 플랫폼 이벤트 정리 앱입니다.
![image](https://user-images.githubusercontent.com/100047095/178304262-4f4b2f3f-6f88-4d82-acb6-341129c1a478.png)

<br/>

### Team
서민경, 정은정 <br/>

### Features
1. Main : 독서 습관 기록 
2. Sub : 이북 플랫폼 이벤트 정리<br/>

### 사용 기술
1. Android Studio
2. Kotlin
3. Firebase
4. Retrofit2
<br/><br/>
## Detail
### 1. 설계 구조
![image](https://user-images.githubusercontent.com/100047095/178301888-d89c947b-c116-49d4-83a9-a62578f8c8d6.png)
<br/>

### 2. DB 구조
![image](https://user-images.githubusercontent.com/100047095/178322071-f6f7c7ff-b4c8-4ac1-856c-26c271073c6c.png)
<br/>

### 3. 독서 기록
RecyclerView로 달력 구현

Retrofit2를 이용하여 Naver 검색 API와 통신하여 검색한 키워드에 일치하는 책 목록 불러오기

불러온 책 정보를 오늘의 독서 습관으로 기록한 경우 Firebase DB에 저장

![image](https://user-images.githubusercontent.com/100047095/178299975-1ddf606e-e301-4c0a-9a7d-a27d4aef8307.png)
<br/>

### 4. 이북 플랫폼 이벤트 정리

JSoup을 사용해 이벤트 정보를 크롤링한 후 사용자에게 제공

![image](https://user-images.githubusercontent.com/100047095/178300699-4c57fa82-3226-4e18-8146-58f721651ee8.png)

<br/>

### 5. 프로젝트 전체 구조 
![image](https://user-images.githubusercontent.com/100047095/178322505-b243f0ed-0e82-4c42-bf95-b693ab02b9ad.png)


