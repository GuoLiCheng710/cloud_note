$(function(){
	var action = {
		getFocus : function(){
			$('#regist_username').focus();
		},
		moveFocus : function(){
			$('#count').focus();
		},
		registAction : function(){
			if(action.checkUsername()+action.checkPassword()+action.checkConfirmPassword()!=3){
				return;
			}
			var name = $('#regist_username').val();
			var nick = $('#nickname').val();
			var password = $('#regist_password').val();
			var confirmPassword = $('#final_password').val();
			var url = 'user/regist.do';
			var param = {
					'name':name,
					'nick':nick,
					'password':password,
					'confirmPassword':confirmPassword
					};
			$.post(url,param,function(result){
				if(result.state==0){
					location.reload();
				}else if(result.state==2){
					$('#warning_1').empty().text(result.message);
				}else if(result.state==3){
					$('#warning_2').empty().text(result.message);
				}
			});
		},
		checkUsername : function(){
			var name = $('#regist_username').val();
			if(!name){
				$('#warning_1').empty().text('用户名不能为空');
				return false;
			}
			var reg = /^\w{3,10}$/;
			if(reg.test(name)){
				$('#warning_1').empty();
				return true;
			}else{
				$('#warning_1').empty().text('3-10个字符');
				return false;
			}
			var url = 'user/checkname.do';
			var param = {
					'name':name
					};
			$.get(url,param,function(result){
				if(result.state==0){
					if(result.data==false){
						$('#warning_1').empty().text('该用户名已存在');
						return false;
					}else{
						$('#warning_1').empty();
						return true;
					}
				}else{
					$('#warning_1').empty().text(result.message);
					return false;
				}
			});
		},
		checkPassword : function(){
			var password = $('#regist_password').val();
			if(!password){
				$('#warning_2').empty().text('密码不能为空');
				return false;
			}
			var reg = /^\w{3,10}$/;
			if(reg.test(password)){
				$('#warning_2').empty();
				return true;
			}else{
				$('#warning_1').empty().text('3-10个字符');
				return false;
			}
		},
		checkConfirmPassword : function(){
			var password = $('#regist_password').val();
			var confirmPassword = $('#final_password').val();
			if(password == confirmPassword){
				$('#warning_3').empty();
				return true;
			}else{
				$('#warning_3').empty().text('两次输入不一致');
				return false;
			}
		}
	}
	
	$('#regist_button').click(action.registAction);
	$('#regist_username').blur(action.checkUsername);
	$('#regist_password').blur(action.checkPassword);
	$('#final_password').blur(action.checkConfirmPassword);
	$('#back').click(action.moveFocus);
	$('#sig_in').click(action.getFocus);
});
