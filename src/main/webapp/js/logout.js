$(function(){
	$('#logout').click(logoutAction);
	
});
function logoutAction(){
	var url = 'user/logout.do';
	var param = {};
	$.post(url,param,function(result){
		if(result.state == SUCCESS){
			if(result.data == 0){
				//location.href = 'log_in.html';
				//location.reload(true);
				//location.href = 'edit.html';
				location.reload(true);
			}
		}
	});
}