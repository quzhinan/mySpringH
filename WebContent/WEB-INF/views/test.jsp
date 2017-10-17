<html lang="ja" xmlns="http://www.w3.org/1999/xhtml">
<script>
	loadXML = function(xmlString) {
		var xmlDoc = null;
		//判断浏览器的类型
		//支持IE浏览器 
		if (!window.DOMParser && window.ActiveXObject) { //window.DOMParser 判断是否是非ie浏览器
			var xmlDomVersions = [ 'MSXML.2.DOMDocument.6.0',
					'MSXML.2.DOMDocument.3.0', 'Microsoft.XMLDOM' ];
			for (var i = 0; i < xmlDomVersions.length; i++) {
				try {
					xmlDoc = new ActiveXObject(xmlDomVersions[i]);
					xmlDoc.async = false;
					xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
					break;
				} catch (e) {
				}
			}
		}
		//支持Mozilla浏览器
		else if (window.DOMParser && document.implementation&& document.implementation.createDocument) {
			try {
				domParser = new DOMParser();
				xmlDoc = domParser.parseFromString(xmlString, 'application/xml');
			} catch (e) {
			}
		} else {
			return null;
		}

		return xmlDoc;
	}

	$(document).ready(function() {
		$.ajax({
				url : "http://localhost:8081/springH/testservlet.htm",
				data : "",
				type : "GET",
				async : true,
				dataType : "xml",
				success : function(data) {
					var xmlDoc = loadXML(data);
					var elements = xmlDoc.getElementsByTagName("item");
					for (var i = 0; i < elements.length; i++) {
						var name = elements[i].getElementsByTagName("title")[0].firstChild.nodeValue;
					}
				}
		});
	});
</script>
</html>