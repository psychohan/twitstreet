
	mainTabsArray = new Array();

	mainTabContentsArray = new Array();
	mainTabsArray.push(".users-tab");
	mainTabsArray.push(".stocks-tab");

	mainTabContentsArray.push("#stocks-container");
	mainTabContentsArray.push("#users-container");


function showTabMain(tabId,tabContentId){
	var i;
	for(i=0; i<mainTabsArray.length; i++){
		
		if(tabId == mainTabsArray[i]){
			
			$(mainTabsArray[i]).addClass("youarehere");
			$(mainTabsArray[i]+'-right-content').fadeTo('fast',1,null);
		}
		else{
			
			$(mainTabsArray[i]).removeClass("youarehere");
			$(mainTabsArray[i]+'-right-content').hide();
		}
	}
	
	for(i=0; i<mainTabContentsArray.length; i++){
		
		if(tabContentId == mainTabContentsArray[i]){
			
			$(mainTabContentsArray[i]).fadeTo('fast',1,null);
		}
		else{
			
			$(mainTabContentsArray[i]).hide();
		}
		
	}	
	
}
