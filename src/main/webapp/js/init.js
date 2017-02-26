var SUCCESS=0;
var ERROR=1;

//页面中的数据模型，存储页面中显示的数据，以及封装更新试图的方法
var model={};

$(function(){
	//加载笔记本方法(notebook.js)
	showNotebooksAction();
	//点击笔记本后，加载笔记本下的笔记(note.js)
	$('#notebook').on('click','li',showNotesAction);
	//点击笔记本后，加载笔记内容(note.js)
	$('#note').on('click','li',showNoteBodyAction);
	//点击保存笔记按钮后,保存修改内容(note.js)
	$('#save_note').click(saveNoteAction);
	//监听关闭窗口事件
	$('#can').on('click','.close,.cancel',closeAction)
	//点击添加笔记本按钮操作
	$('#add_notebook').click(showNotebookDialogAction);
	//点击添加笔记按钮操作
	$('#add_note').click(showNoteDialogAction);
	//显示笔记子菜单--->以及隐藏(note.js)
	$('#note').on('click','.btn_slide_down',showNoteMenuAction);
	//隐藏笔记子菜单操作
	$('body').click(hideNoteMenuAction);
	//监听点击笔记子菜单中的删除按钮
	$('#note').on('click','.btn_delete',showDeleteNoteDialogAction);
	
	$('#rollback_button').click(showRecyclingNotesAction);
	
});

//点击添加笔记按钮操作
function showNoteDialogAction(){
	var notebookIndex = model.notebookIndex;
	if(!model.notebooks[notebookIndex]){
		alert('未选择笔记本呢！');
		return;
	}
	$('#can').load('./alert/alert_note.html',function(){
		$('.opacity_bg').show();
		//点击创建按钮操作(note.js)
		$('.sure').click(addNoteAction);
	});
}
//点击添加笔记本按钮操作
function showNotebookDialogAction(){
	$('#can').load('./alert/alert_notebook.html',function(){
		$('.opacity_bg').show();
		//点击创建按钮操作(notebook.js)
		$('.sure').click(addNotebookAction);
	});
}
//监听关闭窗口事件
function closeAction(){
	$('#can').empty();
	$('.opacity_bg').hide();
}
