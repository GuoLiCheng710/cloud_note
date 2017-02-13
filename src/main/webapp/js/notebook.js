function showNotebooksAction(){
		var url = 'notebook/list.do';
		var param = {'userId':getCookie('userId')};
		$.post(url,param,function(result){
			if(result.state==SUCCESS){
				var notebooks = result.data;
				model.updateNotebookView(notebooks);
			}
			
		});
}

model.updateNotebookView= function(notebooks){
	this.notebooks = notebooks;
	var ul = $('#notebook');
	var template = '<li class="online">'+
						'<a><i class="fa fa-book" title="online" rel="tooltip-bottom"/>'+
						'</i>notebook.name</a>'+
				    '</li>';
	for(var i=0;i<this.notebooks.length;i++){
		var notebook = this.notebooks[i];
		var li = template.replace('notebook.name',notebook.name);
		ul.append(li);
	}
}