<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://maps.google.com/maps/api/js?key=AIzaSyCdxCl6WCrF59mX2pDbqy5Ls9sm40ljH3M"></script>
<script type="text/javascript">
	var latlon;
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {
			$("#message").html("该浏览器不支持定位。");
		}
	}

	function showPosition(position) {
		$("#latitude").html(position.coords.latitude);
		$("#longitude").html(position.coords.longitude);
		$("#addressOnMap").removeAttr("disabled");

		var lat = position.coords.latitude;
		var lon = position.coords.longitude;
		latlon = new google.maps.LatLng(lat, lon);

	}

	function showError(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			$("#message").html("用户拒绝对获取地理位置的请求。");
			break;
		case error.POSITION_UNAVAILABLE:
			$("#message").html("位置信息是不可用的。");
			break;
		case error.TIMEOUT:
			$("#message").html("请求用户地理位置超时。");
			break;
		case error.UNKNOWN_ERROR:
			$("#message").html("未知错误。");
			break;
		}
	}

	function getLocationOnMap() {
		var myOptions = {
			center : latlon,
			zoom : 14,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			mapTypeControl : false,
			navigationControlOptions : {
				style : google.maps.NavigationControlStyle.SMALL
			}
		};
		mapholder=document.getElementById('imageMap')
		mapholder.style.height="210px";
		
		var map = new google.maps.Map(document.getElementById('imageMap'), myOptions);
		var marker = new google.maps.Marker({
			position : latlon,
			map : map
		});
	}
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12 text-center">
			<h2>我的位置</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="table-wrap">
				<table class="table table-striped table-bordered" id="addressTable">
					<tr>
						<td colspan="2"><label id="message"></label></td>
						<td class="col-sm-6" rowspan="6"><div id="imageMap"></div></td>
					</tr>
					<tr>
						<td colspan="2">地理位置</td>
					</tr>
					<tr>
						<td class="col-sm-1">经度:</td>
						<td><span id="longitude"></span></td>
					</tr>
					<tr>
						<td>纬度:</td>
						<td><span id="latitude"></span></td>
					</tr>
					<tr>
						<td><input type="button" name="address"
							id="address" class="btn btn-primary" value="获取"
							onclick="getLocation()" /></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" name="addressOnMap"
							id="addressOnMap" class="btn btn-primary" value="在地图上显示"
							onclick="getLocationOnMap()" disabled="disabled" />
							<input type="button" class="btn btn-primary" value="返回" 
							onclick="window.location='index.htm'" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>


