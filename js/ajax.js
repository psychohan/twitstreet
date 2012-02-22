

var reloadInterval = 60 * 1000;

var lastUpdateMap = new Object();
function ajaxLoad(url, queryString, containerDiv, loadingDiv, isReload, callbackFunction,callbackParameters){
	
	if(!isReload){
		blockElementWithMsg(loadingDiv);		
	}
	else if(!timeToReload(lastUpdateMap[containerDiv])){
		return;
	}
	$.ajax({
		type : "get",
		url : url,
		data : queryString,
		success : function(data) {
			
			
			lastUpdateMap[containerDiv] = new Date();
			unblockElement(loadingDiv);
			
			$(containerDiv).empty();
			$(containerDiv).append($(data))
			
			if(typeof callbackFunction == 'function'){
				if(callbackParameters!=null){
					callbackFunction(callbackParameters);
				}
				else{					
					callbackFunction(data);	
				}
			};
		}
	});
	
	
}

function timeToReload(lastUpdate){
	if(lastUpdate==null) return true;
	return (new Date()).getTime() - lastUpdate.getTime() > reloadInterval;
	
}