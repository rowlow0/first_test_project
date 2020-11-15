var xmlHttp;

function createXMLHttpRequest(){
	
	var xmlReq=false;
	if(window.XMLHttpRequest){
		xmlReq=new XMLHttpRequest(); // IE7++,others
	}else if(window.ActiveXObject){
		try{
			/*xmlReq=new ActiveXObject("Msxml2.XMLHTTP"); //ie5&6*/
		}catch(e){
			/*xmlReq=new ActiveXObject("Microsoft.XMLHTTP"); // ms ie 5 이하*/
		}
	}
	return xmlReq;
}
//ie5&6의 지원은 종료됨.