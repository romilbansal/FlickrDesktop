$(document)
		.ready(
				function() {

					$(".testDiv").click(function() {
						// only for text
						$(this).hide();
					});

					// $(".topMenu").hide();

				/*	$("#signIn").click(function() {
						window.location.href = './requestPermissions';
					});
				*/	
					$("#canvasloader-container").css("visibility", 'hidden');
					
					//alert("test");
					var result = jQuery.parseJSON(getURLParameter("j"));
					curFolder = result.current;
					saveFolder=curFolder.replace(/->/g, '/');
					strAction = "uploadServlet?folder=";
					
					$("#filePath").val(saveFolder);
					$("#myform").attr('action', strAction);
					$('#signIn').click(function() {
						  //alert("tetst");
						  if($("#filePath").val().length > 3 && $("#filePath").val().indexOf("home")==0 ){
							  strAction = strAction + $("#filePath").val(); 
						  }
						  else{
							  strAction = strAction + saveFolder;
						  }
						  $("#myform").attr('action', strAction);
						  $("#myform").submit();
						  $("#canvasloader-container").css("visibility", 'visible');
						  return false;
						});
					var folders = curFolder.split("->");
					
					var i=0;
					var menuCrumbs="";
					for(i=0; i<folders.length; i++){
						var path = "";
						for(j=0; j<=i; j++){
							if(j!=0)
								path = path + "->";
							path = path + folders[j];
						}
						menuCrumbs = menuCrumbs + "<a href=\"http://server-zerovelocity.rhcloud.com/FlickDesk/getFolder?next="+path+"\">"+ folders[i] +"</a>";
					}
					$("#topMenu").html(menuCrumbs);
					$("title").text("Picture Browser : "+folders);

					$(".tempMenu").mouseout(function(e) {
						
					});

					$("#tempMenu").mouseout(function(e) {
						$(this).css("visibility", "false");
						

					});

					$("body").click(function(e) {
						$(".thumbMenu").css("display", "none");
					});
					
					/*$("#signIn").click(function(e){
						$("#canvasloader-container").css("visibility", 'visible');
					});
					*/


					var cl = new CanvasLoader('canvasloader-container');
					cl.setDiameter(86); // default is 40
					cl.setDensity(17); // default is 40
					cl.setRange(0.7); // default is 1.3
					cl.setSpeed(1); // default is 2
					cl.setFPS(22); // default is 24
					cl.show(); // Hidden by default
					
					// This bit is only for positioning - not necessary
					  var loaderObj = document.getElementById("canvasLoader");
			  		loaderObj.style.position = "absolute";
			  		loaderObj.style["top"] = cl.getDiameter() * -0.5 + "px";
			  		loaderObj.style["left"] = cl.getDiameter() * -0.5 + "px";
					
					
				});

function receiveServerData() {
	var jsonData = getURLParameter("j");

	var result = jQuery.parseJSON(jsonData);

	// Display Results on the Screen
	var i = 0;
	var count = 0;
	var user = result.user;

	$("#greetUser").text("Hola " + user + "!");
	var innerHTML = "<table class=\"galleria\"><tr>";

	for (i = 0; i < result.folders.length; i++) {

		var folderName = result.folders[i].name;
		var queryNext = result.folders[i].next;
		if (count % 4 == 0 && count != 0) {
			innerHTML = innerHTML + "</tr><tr>";
		}
		innerHTML = innerHTML
				+ "<td class=\"showThumb\"><img src=\"images/folder.jpg\" class=\"thumbFolder\" alt=\""
				+ queryNext + "\"  /><br>" + folderName + "</td>";
		count++;
	}

	for (i = 0; i < result.photos.length; i++) {

		var photoName = result.photos[i].name;
		if (count % 4 == 0 && count != 0) {
			innerHTML = innerHTML + "</tr><tr>";
		}
		innerHTML = innerHTML + "<td class=\"showThumb\"><img src=\""
				+ photoName + "\" class=\"thumbImage\" /><br></td>";
		count++;
	}

	while (count % 4 != 0) {

		innerHTML = innerHTML
				+ "<td class=\"showThumb\" style=\"visibility:hidden\"><img src=\"images/folder.jpg\" class=\"thumbFolder\"   /><br></td>";
		count++;
	}

	innerHTML = innerHTML + "</tr></table>";
	$("#galleriaDiv").append(innerHTML);
}

function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

/*
 * 
 * 
 * function flickrQueryToBackend(queryt) { var strURL =
 * "http://localhost:8080/FlickDesk/ReqResults?q="+query;
 * 
 * $.ajax({ url:strURL, timeout:20000, async: true, type: 'GET', dataType:
 * 'jsonp', crossDomain:true, success: function(result){ // Display Results on
 * the Screen var i = 0; var count =0; var user = result.userName;
 * 
 * 
 * var innerHTML = "<table class=\"galleria\"><tr>";
 * 
 * for(i = 0; i<result.folders.length; i++){
 * 
 * count++; var folderName = result.folders[i].folderName; var queryNext =
 * result.folders[i].queryNext; if (count%4==0 && count!=0) { innerHTML = "</tr><tr>"; }
 * innerHTML = innerHTML + "<td class=\"showThumb\"><img
 * src=\"images/folder.jpg\" class=\"thumbFolder\" /><br>"+ folderName + "</td>"; }
 * 
 * for(i = 0; i<result.photos.length; i++){ count++; var folderName =
 * result.photos[i].name; if (count%4==0 && count!=0) { innerHTML = "</tr><tr>"; }
 * innerHTML = innerHTML + "<td class=\"showThumb\"><img
 * src=\"images/folder.jpg\" class=\"thumbImage\" /><br>"+ "</td>"; }
 * 
 * innerHTML = innerHTML + "</tr></table>"
 * $("#galleriaDiv").append(innerHTML); }, error: function(req, error) {
 * console.log(req); console.log(error); //alert("AJAX-JSON Error :
 * "+req.error); } }); }
 * 
 */