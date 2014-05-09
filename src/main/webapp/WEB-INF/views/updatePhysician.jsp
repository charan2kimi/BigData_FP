<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Physician</title>
</head>
<body>
<form action="updatePhysician1" method="GET">
<h2 style="color: green;" align="center">Update Physician</h2>
<hr style="height: 1px; width: 100%; color: #008000; background-color:#008000 " />
<table>


<tr><td>ID:</td><td><input type="text"   class="textfields" name="pid" value="${pid}" readonly></td></tr>
<tr><td>First Name:</td><td><input type="text" class="textfields" value="${fname}" name="fname"></td></tr>
<tr><td>Last Name:</td><td><input type="text" class="textfields" value="${lname}" name="lname"></td></tr>

<tr><td>Role:</td><td><input type="text" class="textfields" value="${role}" readonly name="role" value="PHYSICIAN"></td></tr>
<tr><td>Admission Category:</td><td> <input type="text" value="${admission}" readonly class="textfields" name="admission" value="NONE"></td></tr>
<tr><td>Address</td></tr>
<tr><td>Line:</td><td><input type="text" class="textfields" value="${address}" name="address"></td></tr>
<tr><td>Phone::</td><td><input type="text" class="textfields" value="${phone}" name="phone"></td></tr>
<tr><td>City:</td><td><input type="text" class="textfields" value="${city}" name="city"></td></tr>
<tr><td>State:</td><td><input type="text" class="textfields" value="${state}" name="state"></td></tr>
<tr><td>Zip:</td><td> <input type="text" class="textfields" value="${zip}" name="zip"></td></tr>
<tr><td>Domain:</td><td><input type="text" class="textfields" value="${domain}" name="domain"></td></tr>
<tr><td>Pager:</td><td> <input type="text" class="textfields" value="${pager}" name="pager"></td></tr>
</table>
<div>
<input type="submit" value="Submit">
</div>
</form>

</body>
</html>