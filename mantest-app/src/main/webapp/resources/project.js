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

function changeState(state, button){
	document.getElementById("state").value=state;
	document.getElementsByClassName("selected")[0].classList.remove("selected");
	button.classList.add("selected");
	document.getElementsByName("comment")[0].focus();
}
