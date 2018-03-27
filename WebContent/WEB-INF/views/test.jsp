<html>
    <script>  
    	var click111 = function(){
    		alert(1);
    		click222();
    	}
    	var click222 = function(){
    		alert(2)
    	}
    
    </script>  
    <div id="div1">
    <button id="button" onclick="click111()">click</button>
    </div>
</html>  
