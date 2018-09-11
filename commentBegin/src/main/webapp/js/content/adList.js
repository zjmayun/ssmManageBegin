$(function() {
	common.showMessage($("#message").val());
});


function search(currentPage) {
	location.href=$("#basePath").val()+"/ad?page.currentPage="+currentPage;
}


function remove(id) {
	if(confirm("确定要删除这条广告吗？")) {
		$("#id").val(id);
		$("#mainForm").attr("action",$("#basePath").val() + "/ad/remove");
		$("#mainForm").submit();
	}
}

function find1(){
    var title=$("#title").val();
    location.href=$("#basePath").val()+"/ad?title="+title;
}

function modifyInit(id) {
//	$("#id").val(id);
//	$("#mainForm").attr("action",$("#basePath").val() + "/ad/adModify");
//	$("#mainForm").submit();
	location.href=$("#basePath").val()+"/ad/modifyInit/"+id;
}