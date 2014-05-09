<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$('#drugTable').on('click', '.update', function () {
		   window.location = '/myApp/updatePhysician?pid='+this.id;
		 });
	$('#drugTable').on('click', '.delete', function () {
		

		var phid=this.id;
		$.getJSON("deletePhysician",{pid:phid }, function (data){	
			location.reload();
			});
		
	});
	rel();	
});

function rel(){
	$.getJSON("listPhys", function (data){
		$("#drugTable").html('');
		$("#drugTable").append('<tr><th>Phys ID</th><th>First Name</th><th>Last Name </th><th>Domain</th><th>City</th><th>Update</th><th>Delete</th></tr>');		
		for(var i in data) {
						
			  if(!isNaN(i)) {
					$("#drugTable").append('<tr><td>'+data[i].physicianID+'</td><td >'+data[i].fname+'</td><td>'+data[i].lname+'</td><td>'+data[i].domain+'</td><td>'+data[i].city+'</td><td><input type="button" class="update" id="'+data[i].physicianID+'" value="Update"></td><td><input type="button" class="delete" id="'+data[i].physicianID+'" value="Delete"></td></tr>');
			  }}
		});	
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Physicians</title>
</head>
<body>
<table  class="myTable" id="drugTable">



</table>
</body>
</html>