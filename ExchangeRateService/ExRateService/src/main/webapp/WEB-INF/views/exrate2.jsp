<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환율 계산 서비스</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
	*{
		font-size : 20px;
	}
	body{
		margin-top:50px;
		margin-left: 70px;
	}
	div{
		padding: 10px;
	}
	
</style>
</head>
<body>
	<h1>환율 계산</h1>
	
	<form id="transferForm">
		<div> 
			<div>송금국가 : 미국(USD) </div>
			<div>수취국가: 
				<select name ="currencies" id="currencies" onchange="selectCurrency(this.value);">
					<option value="">--선택--</option>
					<option value="KRW">한국(KRW)</option>
					<option value="JPY">일본(JPY)</option>
					<option value="PHP">필리핀(PHP)</option>
				</select>
			</div>
			<div>환율: <span id="exchangeRate"></span></div>
			<div>송금액: <input type="number" id="amount" size="20px" maxlength="10" required> USD</div>
			<div><input type="button" value ="submit" ></div>
		</div> 
	</form> <!-- from 끝 -->
	
	<div id="result"></div>
	
	
	<script>
	
	/* access_key 고정, 송금국가 USD 고정 */
	const access_key='8711282f6324e9bbcaf2ea89273b8953';
	const source ='USD';
	
	/* select box 수취국가 선택시 ajax로 환율 정보 서버로 요청 */
	function selectCurrency(currency){
		/* 선택한 해당 수취국가 USDKRW  */
		var currency = currency;  	
							
		 $.ajax({
			   url:'http://apilayer.net/api/live',
			   dataType: 'json',
			   type:'GET',
			   data:{
				   access_key : access_key,
				   currencies : currency,
				   source : source				  
			   },
			   success: function(data){
				   console.log(data);
				  				   
				   if(data.success){
					   /* 수취국가 : 환율 반환 */
					   var curr = Object.values(data.quotes);
					   /* 소수점 2째자리까지 */   
					   curr = parseFloat(curr).toFixed(2);
					   $('#exchangeRate').val(curr);
					   
					   /* 3자리 이상 콤마 표현  */
					   curr = curr.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					   				   
					   /* 환율 정보 */
					   $('#exchangeRate').text(curr+' '+country +'/USD');
					   
				   }else{
					   /* data.success == false */
					   alert('서버 요청 실패');
				   }
			   }/* success 끝 */
			   
		   });/*ajax끝 */
	}
		

	/* submit 버튼 클릭 */
	$('#transferForm').on('click','input[type=button]',function(){
		/* 송금액 */
		var amount = $('#amount').val();
		amount = parseInt(amount);
		console.log(amount);
		/* 수취국가 */
		var selectedCurrency = $('#currencies option:selected').val();
		/* 현재 환율 */
		var currExrate = $('#exchangeRate').val();
		currExrate = parseFloat(currExrate);
		console.log(currExrate);
		/* 	
			송금 불가
			1. 송금액 0원 이하인 경우
			2. 입력하지 않은 경우
			3. 10,000보다 큰 금액인 경우
			4. select box 수취국가 선택하지 않은 경우
		*/
		
		if(!amount || (amount <=0) || (amount > 10000)){
			alert('송금액이 바르지 않습니다.');
			return false;
		}else if(!selectedCurrency){
			alert('수취국가를 선택하세요');
			return false;
		}else{
			/* 수취금액 -> 계산! */
			/* 수취 금액 계산 */
			/* 여기 비동기 통신으로? */
			var result = amount * currExrate;
			console.log(amount * currExrate);
			
			$('#result').text('수취금액은'+ result+'입니다');
		}
		
		
	});
	
	
	</script>	
</body>
</html>