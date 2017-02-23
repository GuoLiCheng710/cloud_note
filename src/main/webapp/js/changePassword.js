$(function(){
	
	$('#last_password').blur(checkOldPassword);
	$('#new_password').blur(checkNewPassword);
	$('#final_password').blur(checkConfirmPassword);
	$('#changePassword').click(changePassword);
});

var SUCCESS=0;
var ERROR=1;

var a;
var b;
var c;

function changePassword(){
	var d = a;
	var e = b;
	var f = c;
	var g = d+e+f;
	if(g != 3){
		return;
	}
	var userId = getCookie('userId');
	var newPassword = $('#new_password').val();
	var confirmPassword = $('#final_password').val();
	var url = 'user/changePassword.do';
	var param = {'userId':userId,'newPassword':newPassword,'confirmPassword':confirmPassword};
	$.post(url,param,function(result){
		console.log(result);
		if(result.state == SUCCESS){
			if(result.data){
//				location.href = 'edit.html';
//				alert('密码修改成功！');
//				delCookie('LoginAuthorization');
//				location.href = 'log_in.html';
				location.reload(true);
			}
		} else {
			alert(result.message);
		}
		
	});
}

function checkConfirmPassword(){
	var confirmPassword = $('#final_password').val();
	var newPassword = $('#new_password').val();
	if(newPassword == confirmPassword){
		$('#warning_3').empty();
		return c=1;
	}
	$('#warning_3').text('两次输入不一致');
	return c=0;
}

function checkNewPassword(){
	var newPassword = $('#new_password').val();
	if(!newPassword){
		$('#warning_2').text('密码不能为空');
		return b=0;
	}
	var reg = /^\w{3,10}$/;
	if(reg.test(newPassword)){
		$('#warning_2').empty();
		return b=1;
	}
	$('#warning_2').text('3-10个字符');
	return b=0;
}

function checkOldPassword(){
	var oldPassword = $('#last_password').val();
	var userId = getCookie('userId');
	var url = 'user/checkOldPassword.do';
	var param = {'userId':userId,'oldPassword':oldPassword};
	$.post(url,param,function(result){
		if(result.state == SUCCESS){
			if(result.data){
				$('#warning_1').text('');
				return a=1;
			}
			$('#warning_1').html('与原密码不一致');
			return a=0;
		}
		alert(result.message);
		return a=0;
	});
	
	
}