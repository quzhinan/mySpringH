<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.titleDiv {
	width: 50%;
	/* height: 100px; */
	min-height: 100px;
	margin: 0 auto;
	text-align: center;
	font-size: 80px;
	color: red;
}

div#page-main table.main-menu-box {
	border-color: #FFFFFF;
}

div#page-main table.main-menu-box td {
	background-color: #AA0000;
	border-color: #FFFFFF;
	font-size: 14px;
	font-weight: bold;
	height: 40px;
	text-align: center;
	color: #FFFFFF;
	padding: 0px 10px 0px 10px;
}
</style>

</head>
<body>
	<div class="titleDiv">SpringMVC</div>
	<div id="page-main">
		<table width="100%" class="main-menu-box" border="0" cellSpacing="2"
			cellPadding="0">
			<tr>
				<td id="td1" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='adminUserList.htm'">首页</td>
				<td id="td6" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='speakList.htm'">目的地</td>
				<td id="td2" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='supplierList.htm'">景点</td>
				<td id="td3" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='groupList.htm'">攻略</td>
				<td id="td4" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='groupList.htm'">线路</td>
				<td id="td5" nowrap="nowrap"
					style="cursor: pointer; width:13%; background-color: #AA0000"
					onmouseover="this.style.backgroundColor='#9F9000';"
					onmouseout="mouseoutevent()"
					onmousedown="this.style.backgroundColor='#9F5B00';"
					onmouseup="this.style.backgroundColor='#9F9000';"
					onclick="window.location='jppostMenuList.htm'">导游</td>
				<td width="100%" nowrap="nowrap"></td>
			</tr>
		</table>
	</div>
</body>

<script language="JavaScript">
	var mark = "${mark}";
	menuId = 0;
	if (mark == "admin") {
		$("#td1").css("background-color", "#9F9000");
		menuId = 1;
	}
	if (mark == "supplier") {
		$("#td2").css("background-color", "#9F9000");
		menuId = 2;
	}
	if (mark == "group") {
		$("#td3").css("background-color", "#9F9000");
		menuId = 3;
	}
	if (mark == "notice") {
		$("#td4").css("background-color", "#9F9000");
		menuId = 4;
	}

	if (mark == "jppostMenu") {
		$("#td5").css("background-color", "#9F9000");
		menuId = 5;
	}

	if (mark == "speak") {
		$("#td6").css("background-color", "#9F9000");
		menuId = 6;
	}

	function mouseoutevent() {

		if (menuId != "") {

			if (menuId == 1)

				$("#td1").css("background-color", "#9F9000");
			else
				$("#td1").css("background-color", "#AA0000");

			if (menuId == 2)

				$("#td2").css("background-color", "#9F9000");
			else
				$("#td2").css("background-color", "#AA0000");

			if (menuId == 3)

				$("#td3").css("background-color", "#9F9000");
			else
				$("#td3").css("background-color", "#AA0000");

			if (menuId == 4)

				$("#td4").css("background-color", "#9F9000");
			else
				$("#td4").css("background-color", "#AA0000");

			if (menuId == 5)

				$("#td5").css("background-color", "#9F9000");
			else
				$("#td5").css("background-color", "#AA0000");

			if (menuId == 6)

				$("#td6").css("background-color", "#9F9000");
			else
				$("#td6").css("background-color", "#AA0000");

		}
	}
</script>
</html>