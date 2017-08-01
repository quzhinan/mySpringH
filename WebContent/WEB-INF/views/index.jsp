<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var username = "${adminUser.getUsername()}";
	var adminUserListJson = ${adminUserList};
	var adminUserList = eval(adminUserListJson);
	$(document).ready(function() {
		var userTr = $("#userTr");
		userTr.append("<td>姓名:</td><td>" + username + "</td>");
		var insertTrs="";
		for(i=0;i<adminUserList.length;i++){
			var adminUser=adminUserList[i];
			var insert="<tr class='list-content-datas'>"+
   						"<td>"+(i+1)+"</td>"+
   						"<td>"+adminUser.username+"</td>"+		
   						"<td>"+adminUser.password+"</td>"+
   						"<td>"+formatString(adminUser.age)+"</td>"+
   						"<td>"+formatString(adminUser.address)+"</td>"+
   					"</tr>"
   			insertTrs = insertTrs + insert;
		}
		var usersTr = $("#usersTr");
		usersTr.after(insertTrs);
	});
	function formatString(string){
		if(string == null){
			return "";
		}else{
			return string;
		}
	}
</script>
<table>
	<tr id="userTr"></tr>
</table>
<table>
	<tr id="usersTr"><td style="width:100px;">序号</td><td style="width:100px;">姓名</td><td style="width:100px;">密码</td><td style="width:100px;">年龄</td><td style="width:100px;">地址</td></tr>
</table>


