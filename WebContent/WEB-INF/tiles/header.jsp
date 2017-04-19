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
</style>

<script language="JavaScript">
	var mark = "${mark}";
	menuId = 0;
	if (mark == "admin") {
		$("#td1").css("background-color", "#9F9000");
		menuId = 1;
		$("#main-title").html("ユーザー管理");
	}
	if (mark == "supplier") {
		$("#td2").css("background-color", "#9F9000");
		menuId = 2;
		$("#main-title").html("提携事業者（自治体）管理");
	}
	if (mark == "group") {
		$("#td3").css("background-color", "#9F9000");
		menuId = 3;
		$("#main-title").html("公開先グループ");
	}
	if (mark == "notice") {
		$("#td4").css("background-color", "#9F9000");
		menuId = 4;
		$("#main-title").html("地域のお知らせ");
	}

	if (mark == "jppostMenu") {
		$("#td5").css("background-color", "#9F9000");
		menuId = 5;
		$("#main-title").html("郵便局からのお知らせ");
	}

	if (mark == "speak") {
		$("#td6").css("background-color", "#9F9000");
		menuId = 6;
		$("#main-title").html("今日の一言");
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

			if (menuId == 30)

				$("#td30").css("background-color", "#9F9000");
			else
				$("#td30").css("background-color", "#AA0000");

		}
	}
</script>

</head>
<body>
	<div class="titleDiv">SpringMVC</div>
	<table width="100%" class="main-menu-box" border="0" cellSpacing="2"
		cellPadding="0">
		<tr>
			<td id="td1" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='adminUserList.htm'">ユーザー管理</td>
			<td id="td6" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='speakList.htm'">今日の一言</td>
			<td id="td2" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='supplierList.htm'">提携事業者（自治体）管理</td>
			<td id="td3" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='groupList.htm'">公開先グループ</td>
			<td id="td4" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='groupList.htm'">公開先グループ</td>
			<td id="td5" nowrap="nowrap"
				style="cursor: pointer; background-color: #AA0000"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="mouseoutevent()"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='jppostMenuList.htm'">郵便局からのお知らせ</td>
			<td width="100%" nowrap="nowrap"></td>
			<td nowrap="nowrap"
				onmouseover="this.style.backgroundColor='#9F9000';"
				onmouseout="this.style.backgroundColor='#AA0000';"
				onmousedown="this.style.backgroundColor='#9F5B00';"
				onmouseup="this.style.backgroundColor='#9F9000';"
				onclick="window.location='logout.htm'" style="cursor: pointer;">ログアウト</td>
		</tr>
	</table>
</body>
</html>