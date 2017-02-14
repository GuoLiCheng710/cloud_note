var SUCCESS=0;
var ERROR=1;

//页面中的数据模型，存储页面中显示的数据，以及封装更新试图的方法
var model={};

$(function(){
	//加载笔记本方法
	showNotebooksAction();
	//点击笔记本后，加载笔记本下的笔记
	$('#notebook').on('click','li',showNotesAction);
	//点击笔记本后，加载笔记内容
	$('#note').on('click','li',showNoteBodyAction);
	//点击保存笔记按钮后,保存修改内容
	$('#save_note').click(saveNoteAction);
});