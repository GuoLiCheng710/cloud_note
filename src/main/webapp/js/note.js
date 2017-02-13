//显示笔记列表
function showNotesAction(){
	var index = $(this).data('index');
	var notebookId = model.notebooks[index].id;
	var url = 'note/list.do';
	var param = {'notebookId':notebookId};
	$.getJSON(url,param,function(result){
		if(result.state == SUCCESS){
			var notes = result.data;
			model.updateNoteView(notes);
		}
	});
}
//更新笔记试图
model.updateNoteView = function(notes){
	this.notes = notes;
	var ul = $('#note').empty();
	var template = '<li class="online">'+
						'<a>'+
							'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
							'note.title<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'+
							'<i class="fa fa-chevron-down"></i></button>'+
						'</a>'+
						'<div class="note_menu" tabindex="-1">'+
							'<dl>'+
								'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
								'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
								'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
							'</dl>'+
						'</div>'+
					'</li>';
	//遍历笔记集合，
	for(var i=0;i<this.notes.length;i++){
		var note = notes[i];
		var li = $(template.replace('note.title',note.title));
		li.data('index',i);
		ul.append(li);
	}
}
//显示笔记内容
function showNoteBodyAction(){
	var index = $(this).data('index');
	var noteId = model.notes[index].id;
	var url = 'note/body.do';
	var param = {'noteId':noteId};
	$.getJSON(url,param,function(result){
		if(result.state == SUCCESS){
			model.updateBodyView(result.data);
		}
	});
}
model.updateBodyView = function(note){
	this.note = note;
	$('#input_note_title').empty().val(this.note.title);
	um.setContent(this.note.body);

}


