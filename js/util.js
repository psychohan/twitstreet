
function runScriptsInElement(responseData) {
	var scriptArray = $(responseData).find("script").prevObject;

	for ( var i = 0; i < scriptArray.length; i++) {
		if (scriptArray[i] != null && scriptArray[i].text != null) {
			eval(scriptArray[i].text);

		}
	}

}

function blockElementWithMsg(elementId, msg) {
	if(msg==null){
		msg = 'Loading';
		
	}
	if ($(elementId).hasClass('blockUI'))
		return;
	
	$(elementId).fadeTo('slow', 0.5, null);
	
	// block element
	$(elementId).block({
		message : '<img src="images/activity_indicator_32.gif" /><br>'+msg
	});

}
function unblockElement(elementId) {
	
	$(elementId).fadeTo('fast', 1,null);
	
	// block element
	$(elementId).unblock();

}
function commasep(nStr) {
	nStr = parseFloat(nStr).toFixed(2);
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function getNoRecordsFound() {

	return '<p>No records found.</p>';
}

function selectAllText(textbox) {
	textbox.focus();
	textbox.select();
}

function roundedInteger(number) {
	if (parseInt(number) != number) {

		if (number < 0) {
			return parseInt(number - 1);
		} else if (number > 0) {
			return parseInt(number + 1);
		}
	}
	return number;
}

function getDouble(dbl, minval) {
	var pctgStr = dbl.toFixed(2);

	if (pctgStr == '0.00') {
		if (dbl < 0) {
			pctgStr = pctgStr.replace('0.00', '-' + minval.toFixed(2));
		} else if (dbl > 0) {

			pctgStr = pctgStr.replace('0.00', minval.toFixed(2));
		}
	}
	return pctgStr;
}

function objectExists(id) {
	return $(id).length > 0;
}

function showTweetsOfUserInDiv(username, elementId) {

	new TWTR.Widget({
		version : 2,
		type : 'profile',
		rpp : 20,
		interval : 30000,
		width : 500,
		height : 300,
		id : elementId,
		theme : {
			shell : {
				background : '#f2f2f2',
				color : '#000000'
			},
			tweets : {
				background : '#ffffff',
				color : '#000000',
				links : '#4183c4'
			}
		},
		features : {
			scrollbar : false,
			loop : false,
			live : false,
			behavior : 'all'
		}
	}).render().setUser(username).start();
}
function calculateSold(total, soldPercentage) {
	return total * soldPercentage;
}
