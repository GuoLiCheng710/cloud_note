//显示笔记列表
function showNotesAction(){
	//获取点击的笔记本序号
	var index = $(this).data('index');
	model.notebookIndex = index;
	var notebookId = model.notebooks[index].id;
	//清除上一次被选中笔记本的样式
	$(this).siblings().find('a').removeClass('checked');
	//为本次点击的笔记本添加样式
	$(this).find('a').addClass('checked');
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
	if(notes){
		this.notes = notes;
	}
	var ul = $('#note').empty();
	var template = '<li class="online">'+
						'<a>'+
							'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
							'note.title<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" style="display:none;">'+
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
		var note = this.notes[i];
		var li = $(template.replace('note.title',note.title));
		li.data('index',i);
		if(model.noteIndex==i){
		    li.find('a').addClass('checked');
		}
		ul.append(li);
	}
}
//显示笔记内容
function showNoteBodyAction(){
	//点击笔记后，先把其他笔记的子菜单隐藏，然后显示选中笔记的子菜单
	$('.btn_slide_down').hide();
	$(this).find('.btn_slide_down').show();
	//获取点击的笔记序号
	var index = $(this).data('index');
	//记录当前笔记的序号
	model.noteIndex = index;
	var noteId = model.notes[index].id;
	$(this).siblings().find('a').removeClass('checked');
	$(this).find('a').addClass('checked');
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
//点击保存按钮，保存修改过后的笔记
function saveNoteAction(){
	var note = model.note;
	
	var noteId = model.notes[model.noteIndex].id;
	var noteTitle = $('#input_note_title').val();
	var noteBody = um.getContent();
	if(noteTitle==note.title && noteBody==note.body){
		return;
	}
	var url = 'note/save.do';
	var param = {'noteId':noteId,'noteTitle':noteTitle,'noteBody':noteBody};
	$('#save_note').attr('disabled','disabled').html('保存中')
	$.post(url,param,function(result){
		setTimeout(function(){
			$('#save_note').removeAttr('disabled').html('保存笔记');
			if(result.state == SUCCESS){
				//model.note.title = noteTitle;
				//model.note.body = noteBody;
				model.notes[model.noteIndex].title = noteTitle;
				model.updateNoteView();
			}else{
				alert(result.message);
			}
		},800);
	});
}
//创建笔记界面，点击创建按钮后操作
function addNoteAction(){
	var notebookId = model.notebooks[model.notebookIndex].id;
	var userId = getCookie('userId');
	var title = $('#input_note').val();
	if(title == ''){
		title = '新建笔记';
	}
	var url = 'note/add.do';
	var param = {'notebookId':notebookId,'userId':userId,'title':title};
	$('.sure').attr('disabled','disabled').html('创建中');
	$.post(url,param,function(result){
		setTimeout(function(){
			if(result.state == SUCCESS){
				$('#can').empty();
				$('.opacity_bg').hide();
				var note = result.data;
				model.notes.unshift({'id':note.id,'title':note.title});
				model.updateNoteView();
			} else {
				alert(result.message);
			}
		}, 400);
	});
}
//显示笔记子菜单操作
function showNoteMenuAction(){
	$(this).parent().next().toggle();
	return false;
}
//隐藏笔记子菜单操作
function hideNoteMenuAction(){
	$('#note .note_menu').hide();
}
//点击笔记子菜单删除按钮操作
function showDeleteNoteDialogAction(){
	$('#can').load('./alert/alert_delete_note.html',function(){
		$('.opacity_bg').show();
		$('.sure').click(deleteNoteAction);
	});
}
//点击确定删除按钮
function deleteNoteAction(){
	var noteId = model.notes[model.noteIndex].id;
	var url = 'note/delete.do';
	var param = {'noteId':noteId};
	$.post(url,param,function(result){
		if(result.state == SUCCESS){
			$('#can').empty();
			$('.opacity_bg').hide();
			model.notes.splice(model.noteIndex,1);
			model.updateNoteView();
		} else {
			alert(result.message)
		}
	});
}
/**
 * 2017/2/15
 * 笔记保存方法有点小绕，这里的处理方法是，保存成功后，修改model.note里的title、body，
 * 以及对应model.notes里面的note的title，用于在更新笔记列表时使用。
 * model.updateNoteView()是用于显示笔记列表的，用到了参数model.notes，
 * 这时notes里面的title已经更新，再次遍历时是一个新的啦
 * 
 * 在showNoteBodyAction里面记录当前打开的笔记的序号  model.noteIndex = index ，
 * 用于在修改notes里面对应note的title时获取到note，以及更新视图时控制选中效果
 */

