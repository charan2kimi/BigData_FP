<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style type="text/css">

body
{
width: 100%;
}

input[type="submit"]
{
-moz-box-shadow:inset 0px 1px 0px 0px #caefab;
	-webkit-box-shadow:inset 0px 1px 0px 0px #caefab;
	box-shadow:inset 0px 1px 0px 0px #caefab;
	background-color:#77d42a;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #268a16;
	display:inline-block;
	color:#306108;
	font-family:arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:1px 1px 0px #aade7c;
}
.body{
width:100%;
}

.container{
width:100%;
height:800px;
}

.header
{
width:100%;
height:10%;
background-color: #8AC007;

}



.content{
width:100%;
height:90%;
float:right;
background-color: #ffffff;
text-align: left;
}
</style>

</head>
<body>

<div class="body" align="center">
<div class="container">
<div class="header">
<tiles:insertAttribute name="header" />
</div>
<div class="content">
<tiles:insertAttribute name="body" />
</div>

</div>
</div>
</body>
</html>