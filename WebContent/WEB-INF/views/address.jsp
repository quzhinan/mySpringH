<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var msg = $("#message");
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {
			msg.html("该浏览器不支持定位。");
		}
	}
	
	function showPosition(position) {
		$("#latitude").html(position.coords.latitude);
		$("#longitude").html(position.coords.longitude);
	}
	
	function showError(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			msg.html("用户拒绝对获取地理位置的请求。");
			break;
		case error.POSITION_UNAVAILABLE:
			msg.html("位置信息是不可用的。");
			break;
		case error.TIMEOUT:
			msg.html("请求用户地理位置超时。");
			break;
		case error.UNKNOWN_ERROR:
			msg.html("未知错误。");
			break;
		}
	}
</script>
<table>
	<tr>
		<td><label id="message"></label></td>
	</tr>
	<tr>
		<td colspan="2">地理位置</td>
	</tr>
	<tr>
		<td>经度:</td><td><span  id="longitude"></span></td>
	</tr>
	<tr>
		<td>纬度:</td><td><span id="latitude"></span></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" name="address" id="address" value="获取" onclick="getLocation()"/></td>
	</tr>
</table>


