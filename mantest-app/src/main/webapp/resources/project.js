function addTest(listid){
	document.getElementById("addtest").classList.toggle("active");
	document.getElementById("addtestlistid").value=listid.getAttribute("listid");
}
function cancelAdd(){
	document.getElementById("addtest").classList.toggle("active");
}
function addTestList(){
	document.getElementById("addtestlist").classList.toggle("active");
}
