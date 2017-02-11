$(function(){
	var action = {
		loginAction : function(){
			if(action.checkUsername() + action.checkPassword() != 2){
				return;
			}
			var name = $('#count').val();
			var password = $('#password').val();
			var url = 'user/login.do';
			var param = {'name':name,'password':password};
			$.post(url,param,function(result){
				if(result.state==0){
					location.href = 'edit.html';
				} else if(result.state==2){
					$('#count-msg').empty().text(result.message);
				} else if(result.state==3){
					$('#password-msg').empty().text(result.message);
				} else{
					
				}
			});
		},
		checkPassword : function(){
			var password = $('#password').val();
			if(!password){
				$('#password-msg').empty().text('密码不能为空');
				return false;
			}
			var reg = /^\w{3,10}$/;
			if(reg.test(password)){
				$('#password-msg').empty();
				return true;
			}else{
				$('#password-msg').empty().text('3-10个字符');
				return false;
			}
		},
		checkUsername : function(){
			var name = $('#count').val();
			if(!name){
				$('#count-msg').empty().text('用户名不能为空');
				return false;
			}
			var reg = /^\w{3,10}$/;
			if(reg.test(name)){
				$('#count-msg').empty();
				return true;
			}else{
				$('#count-msg').empty().text('3-10个字符');
				return false;
			}
		},
		keyUpEnter : function(){
			$('#password').bind('keyup',function(event){
				if(event.keyCode==13){
					action.loginAction();
				}
			});
		}
	}
	$('#count').focus().blur(action.checkUsername);
	$('#password').blur(action.checkPassword);
	$('#login').click(action.loginAction);
	action.keyUpEnter();
});


