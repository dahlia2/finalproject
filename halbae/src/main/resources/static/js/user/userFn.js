/**
 * 
 */
 
$(function() {
	
})
 
	
function fnLogout() {
	location.href="/user/logout.do?loginId=" + $('.loginId').val();	
}

function fnFindId() {
	location.href="/user/findId.html";
}

function fnFindPw() {
	location.href="/user/findPw.html";
}
 
function fnSendCode() {
	// 입력한 이메일
	let email = $('#email').val();
	
	// ajax는 비동기식이라 각각 실행해주기 위해 Promise 사용
	new Promise(function(resolve, reject) {
		// 정규식
		let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]{2,}(\.[a-zA-Z]{2,}){1,2}$/;
		
		// 정규식을 테스트 결과에 따른 함수 사용
		if(regEmail.test(email)) {
			resolve();
		}else{
			reject();
		}
	}).then(function() {
		$.ajax({
			type: 'get',
			url: '/user/sendCode.do',
			data: 'email=' + email,
			dataType: 'json',
			success: function(res) {
				alert('메일이 전송되었습니다. 인증코드는 : ' + res.authCode);
				$('.validate').on('click', function() {
					if(res.authCode == $('#authCode').val()) {
						alert('인증되었습니다.');
						$('#findFrm').submit();
					}else {
						alert('인증번호를 확인해주세요.');
					}
				})				
			}
		})
	}).catch(function() {
		alert('이메일을 다시 확인해주세요.');
	})

}// 함수 종료 괄호

function fnTempPw() {
	// 입력한 이메일
	let email = $('#email').val();

	// 정규식
	let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]{2,}(\.[a-zA-Z]{2,}){1,2}$/;
	
	// 정규식을 테스트 결과에 따른 함수 사용
	if(regEmail.test(email)) {
		$.ajax({
			type: 'get',
			url: '/user/sendTempPw.do',
			data: 'email=' + email,
			dataType: 'json',
			success: function(res) {
				alert('메일이 전송되었습니다. 비밀번호는 : ' + res.tempPw);
				$('#tempPw').val(res.tempPw);
				$('#tempPwFrm').submit();
			},
			error: function() {
				alert('전송 실패');
			}
		})
	}else{
		alert('이메일을 다시 확인해주세요.');
	}
}// 함수 종료 괄호
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 