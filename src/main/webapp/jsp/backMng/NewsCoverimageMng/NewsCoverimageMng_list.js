$(function(){

	$("#list_addBtn").click(function(){
		var NEWS_ID=$("#NEWS_ID").val();
		$('.h_bg').show();
		$('.h_categoryAdd').load('add.htm?NEWS_ID='+NEWS_ID);
		$('.h_categoryAdd').show();
		funShowDivCenter();
	});
	
	$("input[type='button'][name='list_editBtn']").click(function(){
		var objId=$(this).attr("objId");
		
		$('.h_bg').show();
		$('.h_categoryAdd').load("edit.htm?ID="+objId);
		$('.h_categoryAdd').show();
		funShowDivCenter();
	});

	$("input[type='button'][name='list_viewBtn']").click(function(){
		var objId=$(this).attr("objId");
		
		$('.h_bg').show();
		$('.h_categoryAdd').load("view.htm?ID="+objId);
		$('.h_categoryAdd').show();
		funShowDivCenter();
	});
	
	$("input[type='button'][name='list_deleteBtn']").click(function(){
		var objId=$(this).attr("objId");
		if(confirm('确实要删除吗？')){
			$.ajax({
				type : 'post',
				url : 'delete.htm',
				data:{'ID':objId},
				dataType : 'json',
				success : function(data, textStatus) {
					if(data.success){
						query();
					}else{
						alert(data.message);
					}
				},
				error : function(data, textStatus) {
					alert('保存失败');
				}

			});
		}
	});
	
});

function query(){
	document.getElementById('pageForm').submit();
}

function funShowDivCenter() {
    var top = ($(window).height() - $('.h_categoryAdd').height()) / 3;
    var left = ($(window).width() - $('.h_categoryAdd').width()) / 2;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $('.h_categoryAdd').css({ position: 'absolute', 'top': top + scrollTop, 'left': left + scrollLeft }).show();
}

function hideBg() {
    $('.h_bg').hide();
    $('.h_categoryAdd').hide();
}