<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.onur.rest.Chart" %>
<%@ page import="com.onur.rest.Rest" %>
 
<%
	Chart c=new Chart();
	String data=c.chart();
	String result=c.addData("Onur", "Açan", 21, "02", "Bus");
	out.print(result);
%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light1", // "light1", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Personal Count"
	},
	data: [{
		type: "pie",
		toolTipContent: "<b>{label}</b>: {y}",
		indexLabelFontSize: 16,
		indexLabel: "{label} - {y}",
		dataPoints: <%out.print(data);%>
	}]
});
chart.render();
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>