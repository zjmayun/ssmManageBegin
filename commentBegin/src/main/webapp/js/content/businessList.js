function remove(id) {
	var id=$("#id").val(id);
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses/"+id);
	$("#mainForm").submit();
}

function search(currentPage) {
	$("#mainForm").attr("method","GET");
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}