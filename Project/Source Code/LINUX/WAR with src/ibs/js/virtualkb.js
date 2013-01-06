var fieldObj;
var bCaps=false;
var focus_count=0;
var sHTML="";
function getArr()
{
var keyArr=[['~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+'],
			  		['`', ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'], '-', '='],
			  		[['q', 'w', 'e', 'r', 't'], ['y', 'u', 'i', 'o', 'p'], '{', '}', '|'],
			  		[['a', 's', 'd', 'f', 'g'], ['h', 'j', 'k', 'l'], '[', ']', '\\', '/'],
			  		[['z', 'x', 'c', 'v'], ['b', 'n', 'm'], '<', '>', ';', ':', '\'', '"'],
			  		[',', '.', '?']];
return (keyArr);
}
function getFocus(x) {
	
	fieldObj=document.getElementById(x);
}
function constructKeyboard(checkid,bool) {
	var check;
	if(typeof(bool) == 'undefined')
		check= document.getElementById(checkid).checked;
	else
		check = bool;
	var keyArr = getArr();
	//var check = document.getElementById('chkbox').checked;
	var str_trco="</tr><tr>";
	sHTML="<table	border='0' class='keyboardtbl' cellspacing='3px' id='keypad' width='100%'><tr>";
	for (var i=0; i<keyArr.length-1; i++){
		//alert(keyArr[i].length)
		for (var j=0; j<keyArr[i].length; j++){
			var code;
			if(typeof(keyArr[i][j])=='object'){
				while(keyArr[i][j].length>0){
					var ix=Math.floor(Math.random()*keyArr[i][j].length);
					var ch=keyArr[i][j].splice(ix,1);
					code=ch[0].charCodeAt(0);
					if(!check){
						sHTML+="<td class='keyboardtbldis'>" + ch + "</td>";
					}
					else
					{
						sHTML+="<td onClick='putChar(" + code + ")' class='keyboardtblenb'>" + ch + "</td>";
					}
				}
			}else{
				code=keyArr[i][j].charCodeAt(0);
				if(check)
					sHTML+="<td onClick='putChar(" + code + ")' class='keyboardtblenb'>" + keyArr[i][j] + "</td>";
				else
					sHTML+="<td class='keyboardtbldis'>" + keyArr[i][j] + "</td>";
			}
		}
		sHTML+=str_trco;
	}
	if(check){
	sHTML+="<td colspan='5' id='cap' onClick='setCaps(this)' class='keyboardtblenb' style='background:url(images/kb_buttons2.jpg)'>CAPS LOCK ON</td>";
	sHTML+="<td colspan='5' id='clr' onClick='setClearAll()' class='keyboardtblenb' style='background:url(images/kb_buttons2.jpg)'>CLEAR</td>";}
	else{
		sHTML+="<td colspan='5' id='cap'  class='keyboardtbldis'>CAPS LOCK ON</td>";
		sHTML+="<td colspan='5' id='clr'  class='keyboardtbldis'>CLEAR</td>";
	}
	for (var i=0; i<3; i++){
		var code=keyArr[5][i].charCodeAt(0);
		if(check)
			sHTML+="<td onClick='putChar(" + code + ")' class='keyboardtblenb' >" + keyArr[5][i] + "</td>";
		else
			sHTML+="<td  class='keyboardtbldis'>" + keyArr[5][i] + "</td>";
	}
	sHTML+="</tr></table>";
	document.getElementById('kbplaceholder').innerHTML = sHTML;
}
function putChar(code){
	if(fieldObj.value.length < 20){
		var keycode=(code>96 && code<123 && bCaps) ? code-32 : code;
		var text=String.fromCharCode(keycode);
			fieldObj.value += text;
			setCaretTo();
	}
}
function setCaretTo(){
	var pos=fieldObj.value.length;
	if(fieldObj.createTextRange){
		var range=fieldObj.createTextRange();
		range.move('character', pos);
		range.select();
	}else if(fieldObj.selectionStart){
		fieldObj.focus();
		fieldObj.setSelectionRange(pos, pos);
	}
}
function changeCase(){
	var alphabets = document.getElementById('keypad').getElementsByTagName('TD');
	for(var i=0; i<alphabets.length; i++){
		var ch = alphabets[i].innerHTML;
		if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') && ch.length==1){
			alphabets[i].innerHTML = bCaps ? ch.toUpperCase() : ch.toLowerCase();
		}
	}
}
function setCaps(obj){
	bCaps = !(bCaps);
	toggleCap(obj);
	changeCase();
}
function toggleCap(obj){
	 var str = bCaps ? "CAPS LOCK OFF" : "CAPS LOCK ON";
	 obj.innerHTML = str;
}
function setClearAll(){
	fieldObj.value = "";
	fieldObj.focus();
}
