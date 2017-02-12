function showNotebooksAction(){
		var url = 'notebook/list.do';
		var param = {'userId':getCookie('userId')};
		$.post(url,param,function(result){
			
		});
}