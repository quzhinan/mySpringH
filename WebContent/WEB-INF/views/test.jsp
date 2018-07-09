<script>
	function click111() {
		callAjax("http://172.20.15.132:8082/JMODE_ASP/faces/aOrderSumServlet", "", "POST", false, "xml", function(data){
			/* console.log(data)
			sessionStorage.setItem("data",JSON.stringify(data.data)); 
			$("#div2").text(sessionStorage.getItem("data"))*/
			/* $("#img1").attr("src","data:image/gif;" + data); */
			alert(data)
		})
	}
	
</script>

<div id="div1">
<button id="button" onclick="click111()">click</button>
</div>
<div id="div2">
<img id="img1" src="">
</div>
<canvas id="canvas"></canvas>
