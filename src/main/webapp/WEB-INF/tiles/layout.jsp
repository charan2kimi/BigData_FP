<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style type="text/css">

body
{
width: 100%;
}

.body{
width:100%;
}

.container{
width:100%;
height:800px;


}
input[type="button"]
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
}

.textfields {
  border: 1px solid #ccc;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    -moz-box-shadow: 2px 2px 3px #666;
    -webkit-box-shadow: 2px 2px 3px #666;
    box-shadow: 2px 2px 3px #666;
    font-size: 20px;
    padding: 4px 7px;
outline: 0;
  -webkit-appearance: none;
  width:10%

}

.textfields:focus { 
    outline: none;
    border-color: #9ecaed;
    box-shadow: 0 0 10px #9ecaed;
}

.header
{
width:100%;
height:10%;
background-color: #8AC007;
}



.sidebar{
width:15%;
height:90%;
float:left;

border-right: 2px;
border-right-style: groove;
}

.content{
width:84.8%;
height:90%;
float:right;

text-align: left;
}


.sidebar ul{
border: 1px solid #eee;
padding: 0;
margin: 0;
list-style: none;
}

.sidebar ul li{
margin:0;
padding:0;
}

.sidebar ul li a{
display:block;
text-transform: uppercase;
color: #494949;
padding: 10px 15px;
text-decoration: none;
border-bottom: 1px solid #cacaca;
border-right: 1px solid #cacaca; /*right border between menu items*/
-moz-box-shadow: inset 7px 0 10px rgba(114,114,114, 0.6); /* Add inset shadow to each menu item. First 3 values in (114,114,114, 0.5) specifies rgb values, last specifies opacity */
-webkit-box-shadow: inset 7px 0 10px rgba(114,114,114, 0.6);
box-shadow: inset 7px 0 10px rgba(114,114,114, 0.6);
text-shadow: 0 -1px 1px #cfcfcf; /* CSS text shadow to give text some depth */
-moz-transition: all 0.2s ease-in-out; /* Enable CSS transition between property changes */
-webkit-transition: all 0.2s ease-in-out;
-o-transition: all 0.2s ease-in-out;
-ms-transition: all 0.2s ease-in-out;
transition: all 0.2s ease-in-out;
}

.sidebar ul li a:hover, .sidebar ul li a.selected{
color: black;
-moz-box-shadow: inset 7px 0 10px rgba(152,191,33, 0.5), inset 0 0 15px rgba(152,191,33, 0.6), inset 0 0 20px rgba(152,191,33, 0.8); /* Add 3 inset shadows to each menu item  */
-webkit-box-shadow: inset 7px 0 10px rgba(152,191,33, 0.5), inset 0 0 15px rgba(152,191,33, 0.6), inset 0 0 20px rgba(152,191,33, 0.8);
box-shadow: inset 7px 0 10px rgba(152,191,33, 0.5), inset 0 0 15px rgba(152,191,33, 0.6), inset 0 0 20px rgba(152,191,33, 0.8);
}


.tableDiv{
 overflow: auto; 
    height: 200px; 
    width: 100%;
    
}
.myTable
{
font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
width:97%;
border-collapse:collapse;

 }
.myTable td, .myTable th 
{
font-size:1em;
border:1px solid #98bf21;
padding:3px 7px 2px 7px;
}
.myTable th 
{
font-size:1.1em;
text-align:left;
padding-top:5px;
padding-bottom:4px;
background-color:#A7C942;
color:#ffffff;
}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" ></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
</head>
<body>

<div class="body" align="center" style="border-radius:15px;">
<div class="container">
<div class="header">
<tiles:insertAttribute name="header"  />
</div>
<div class="sidebar" align="left" style="border-radius:15px;">
<tiles:insertAttribute name="menu" />
</div>
<div class="content">
<tiles:insertAttribute name="body" />
</div>

</div >
</div>
</body>
</html>