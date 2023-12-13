# 구름톤 트레이닝 과제 #10(12/07 ~ 12/13)

**응답/에러 모델 만들기**

공통 Api 응답모델과 에러 모델을 만들고 간단한 성적 저장/조회하는 api 를 구현해 봅시다.

`Status.code` : http status 가 아닌 서버에서 정의하는 code값이 담겨져 있어야 합니다.(정상응답에서는 항상 200)  

`Status.message` : 정상응답시에는 “OK”, 에러 응답에서는 에러에대한 상세한 이유를 담아줍니다.  

`Metadata.resultCount` :  정상 응답시에 나타나는 값입니다. results list 의 count 를 담아줍니다.  

`Results` : 정상응답시에 나타나는 값입니다. 항상 list 형태로, 실제 응답으로 내주고싶은 정보가 표시됩니다.  

`Data` : 에러 응답시에 나타나는 값으로, 에러 응답시에 frontend 에서 사용자에게 어떤 이유로 요청이 거부되었는지 메세지를 만들기 쉽게 필요한 데이터를 넣어줍니다.   



[API 요구사항]

1. 이름과 성적을 입력받아 저장하는 api
   - 성적의 입력은 특정 범위의 값을 넘길 경우에는 에러응답을 나타내야 합니다.
    ![스크린샷 2023-12-13 오후 2 22 00](https://github.com/BearWithpy/goorm-resp-err-model/assets/58184008/a7a78b4b-7246-4bfa-9c01-ff4b88e34734)
    ![스크린샷 2023-12-13 오후 2 19 07](https://github.com/BearWithpy/goorm-resp-err-model/assets/58184008/cc0e980d-cb56-4adb-8484-f7873ac84143)


     
2. 입력된 성적을 조회 하는 api
   - 성적 오름차순으로 조회가 되어야 합니다  
    ![스크린샷 2023-12-13 오후 2 17 12](https://github.com/BearWithpy/goorm-resp-err-model/assets/58184008/83c5e087-15b0-41be-9195-12cf5a36fa90)

     
3. 특정 성적을 입력받아, 해당 성적의 학생만 조회하는 api
   ![스크린샷 2023-12-13 오후 2 33 06](https://github.com/BearWithpy/goorm-resp-err-model/assets/58184008/6b630c87-5484-4050-82da-6055ca5623e0)

   
[구현 사항]

1. Controller 에서 응답 모델로 만들어 주어야 합니다.
   
   a. ApiResponse<T>: 여러가지 데이터 타입(클래스) 를 result 로 넣을 수 있도록 제네릭을 사용하여서 구현 해야 합니다.
    
   b. makeResponse(T result), makeResponse(List<T> results): 결과를 응답객체로 만들어주기 위한 메서드입니다. 단건과 복수건 결과 모두 응답 객체로 만들어 줄 수 있도록 두개 모두 구현 해야 합니다.  

2. 에러응답을 만들기 위해서는 @ExceptionHander 를 사용하여 exception 의 데이터를 이용해야 합니다.
   
3. exceptionHander 에서 응답모델을 만들때 필요한 데이터가 포함시킬 수 있는 customException 을 구현 해야 합니다.

    a. ex. CustomException(ErrorCode, massge, data) - ErrorCode 는 enum 으로 정의 합니다.  
      - ErrorCode 는 enum 으로 정의 합니다.

개발 기간: 2023. 12. 07. ~ 2023. 12. 13.




### 비고

-   EMPTY
