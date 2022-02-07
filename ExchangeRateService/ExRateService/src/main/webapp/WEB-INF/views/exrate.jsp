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
	
		<div id="currForm"> 
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
			<div><input type="button" value ="submit" onclick="btnClick()"></div>
		</div> 
	
	<div id="result"></div> <!-- 수취금액 결과  -->
	
	
	<script>
	

	/* select box 수취국가 선택시 ajax로 환율 정보 서버로 요청 */
	function selectCurrency(currency){
		/* 선택한 해당 수취국가 KRW  */
		var currency = currency;  	
							
		 $.ajax({
			   url:'<c:url value="/getExrate"/>',
			   type:'GET',
			   data:{
				   currency : currency
			   },
			   success: function(data){
				console.log(data);
				
				if(data.success){
					var curr = data.currency;
					
				    /* span value 값  */
				    $('#exchangeRate').val(curr);
				    
				    /* 소수점 2째자리까지 */   
					curr = parseFloat(curr).toFixed(2);
						  
				    /* 3자리 이상 콤마 표현  */
					curr = curr.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						  
					/* 환율 정보 */
				    $('#exchangeRate').text(curr+' '+currency +'/USD');
				}else{
					/* API Exception 발생함 */
					alert('환율 정보를 가져오지 못했습니다.');
					$('#exchangeRate').text('');
				}
				  
			  
			   },/* success 끝 */
			   error : function(request,status,error){
					alert('환율 정보를 가져오지 못했습니다.');
			   }/* error 끝  */
			   
		   });/*ajax끝 */
	}
		

	/* submit 버튼 클릭 */
	function btnClick(){
		/* 송금액 */
		var amount = $('#amount').val();
		amount = parseInt(amount);
		console.log(amount);
		/* 수취국가 */
		var currency = $('#currencies option:selected').val();
		
		/* 현재 환율 */
		var exrate = $('#exchangeRate').val();
		exrate = parseFloat(exrate);
		console.log(exrate);
		
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
		}else if(!currency){
			alert('수취국가를 선택하세요');
			return false;
		}else{
			/* 수취금액 계산 */
			var result = amount * exrate;
			
			/* 소수점 2째자리까지 */   
			result = parseFloat(result).toFixed(2);
				   
			/* 3자리 이상 콤마 표현  */
			result =  result.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");	
			
			/* 수취금액 화면에 표현 */
			$('#result').text('수취금액은 '+result+' '+ currency +' 입니다.');
			
		}
		
		
	};
	
	</script>	
</body>
</html>