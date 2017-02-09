$(function(){
	var action ={
		loginAction : function(){
			var name = $('#count').val();
			var password = $('#password').val();
			var url = 'user/login.do';
			var param = {'name':name,'password':password};
			$.post(url,param,function(result){
				if(result.state==0){
					location.href = 'edit.html';
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
			if(reg.test(name)){
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
		}
		
	}
	$('#count').focus().blur(action.checkUsername);
	$('#password').blur(action.checkPassword);
	$('#login').click(action.loginAction);
	
	
});

