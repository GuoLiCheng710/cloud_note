function showNotebooksAction(){
		var url = 'notebook/list.do';
		var param = {'userId':getCookie('userId')};
		$.getJSON(url,param,function(result){
			if(result.state==SUCCESS){
				var notebooks = result.data;
				model.updateNotebookView(notebooks);
			}
			
		});
}

//模型中的更新视图方法, 当收到笔记本数据时候更新界面的显示内容
model.updateNotebookView= function(notebooks){
	//this就是当前对象，就是model
	this.notebooks = notebooks;
	var ul = $('#notebook').empty();
	var template = '<li class="online">'+
						'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"/>'+
						'</i>notebook.name</a>'+
				    '</li>';
	for(var i=0;i<this.notebooks.length;i++){
		var notebook = this.notebooks[i];
		var li = $(template.replace('notebook.name',notebook.name));
		//每一个li绑定一个位置信息，用于‘点击笔记本显示笔记功能’
		li.data('index',i);
		ul.append(li);
	}
}