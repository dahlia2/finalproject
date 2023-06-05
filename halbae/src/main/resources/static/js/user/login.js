/**
 * 
 */
 
$(function() {
	$('#reg').text('');

});

// 로그인 시 이메일 비밀번호 확인하는 정규식
function checkInfo() {
	
	let regId = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]{2,}(\.[a-zA-Z]{2,6}){1,2}$/;
	let verifyEmail = regId.test($('#userId').val());

	let pw = $('#userPw').val();
  let pwLength = $('#userPw').val().length;
	let validCnt = /[a-z]/.test(pw)
						+ /[A-Z]/.test(pw)
						+ /[0-9]/.test(pw)
						+ /[^a-zA-Z0-9]/.test(pw);
	
	verifyPw = (pwLength >=4) && (pwLength <=20) && (validCnt >=3);
	
	if(verifyEmail == false && verifyPw == false) {
		$('#reg').text('이메일과 비밀번호를 확인해주세요.');
	}else if(verifyEmail == false) {
		$('#reg').text('이메일을 확인해주세요.');
	}else if(verifyPw == false) {
		$('#reg').text('비밀번호를 확인해주세요.');		
	}else {
	$('#loginFrm').submit();
	}
}; 
 
 // 눈 모양 아이콘 클릭 시 input타입 password -> text로 변경
 function togglePw() {
	if($('#userPw').attr("type") === "text") {
		$('#userPw').attr("type", "password");
	}else {
		$('#userPw').attr("type", "text");
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 