$(function(){

	$("#xzqd").click(function(){
		$(".fadebj").show();
		$(".fadebj").load("add.htm");
	});
	

	$(".xiangq").click(function(){
		var objId=$(this).attr("objId");
		$(".fadebj").show();
		$(".fadebj").load("view.htm?ID="+objId);
	});
	
	$(".shanchu").click(function(){
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