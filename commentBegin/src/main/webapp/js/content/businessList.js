function remove(id) {
	$("#id").val(id);
	$("#mainForm").attr("action",$("#basePath").val() + "/business/delete");
	$("#mainForm").submit();
}

function search(currentPage) {
	$("#mainForm").attr("method","GET");
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/business/modifyInit/" + id;
}