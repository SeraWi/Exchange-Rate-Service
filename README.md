# Exchange-Rate-Service
환율 계산 서비스 구현하기

* 사용한 기술 스택 : Java8, Spring framework 4.3.18, JavaScript, jQuery
* 빌드 툴 : 메이븐
* MVC 패턴으로 개발
---------
### 환율 정보 가져오기
* Ajax 로 구현하였습니다.
* 선택한 수취 국가의 환율만 서버에 요청합니다.
* select box에 수취 국가를 바꿀 때 마다 매번 서버에 요청합니다.
* 환율 정보를 가져오지 못한 경우 CurrencyApiException이 발생합니다.
* 환율 결과는 자바스크립트로 포맷을 맞추었습니다.
---------
### 수취 금액 보여주기
* select box에 선택된 수취 국가의 환율을 자바스크립트로 가져와서 계산하였습니다.
* 환율 정보를 서버에 다시 요청하지 않습니다. 
* [송금불가] 1. 0원 이하 송금액 입력 2. 10,000USD 초과 송금액 입력 3. 송금액을 입력하지 않았거나 수취국가를 선택하지 않은 경우는 자바스크립트로 처리했습니다.
---------
### 구현화면
![환율 정보](https://user-images.githubusercontent.com/84219233/152953673-d64148fa-c576-4e7d-abcb-702cbc2d71dc.jpg)
![수취금액](https://user-images.githubusercontent.com/84219233/152953791-43657e10-01e2-44c6-aa0e-082a9c9a6a6a.jpg)
