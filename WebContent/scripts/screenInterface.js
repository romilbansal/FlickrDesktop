$(document)
		.ready(
				function() {
					var IP = "";
					var tempI = 0;
					var remove = true;
					var exists = false;

					$(".testDiv").click(function() {
						// only for text
						$(this).hide();
					});

					// $(".topMenu").hide();

					$("#signIn").click(function() {
						window.location.href = './requestPermissions';
					});

					receiveServerData();

					$(".thumbFolder")
							.click(
									function(e) {

										var xCoord = e.pageX;
										var yCoord = e.pageY;
										$("#tempMenu").remove();

										var content = "<div class='thumbMenu' id='tempMenu'></div>";
										$(this).after(content);
										var nextQuery = $(this).attr("alt");
										var moreHTML = "<ul><li><a href=\"getFolder?next="
												+ nextQuery
												+ "\"> Open Folder </a></li>";
										$("#tempMenu").html(moreHTML);

										exists = true;
										$("#tempMenu").css("visibility",
												"visible");
										$("#tempMenu").css("left", xCoord - 30);
										$("#tempMenu").css("top", yCoord - 30);
										return false;

									});

					$(".thumbImage")
							.click(
									function(e) {

										var xCoord = e.pageX;
										var yCoord = e.pageY;
										$("#tempMenu").remove();

										var content = "<div class='thumbMenu' id='tempMenu'></div>";
										$(this).after(content);
										
										var result = jQuery.parseJSON(getURLParameter("j"));
										curFolder = result.current;
										
										var imgU = $(this).attr("src");
										var moreHTML = "<ul><li><a href=\"displayImage.html?imageURL="+imgU+"&backFolder="+curFolder+"\" \> View Image </a></li><li><a href=\"http://localhost:8080/FlickrDemo/deletePhoto?curr="+curFolder+"&url="+imgU+"\"> Delete Image </a></li><li><a href=\"\"> Move Image </a></li>";
										$("#tempMenu").html(moreHTML);

										exists = true;
										$("#tempMenu").css("visibility",
												"visible");
										$("#tempMenu").css("left", xCoord - 30);
										$("#tempMenu").css("top", yCoord - 30);
										return false;

									});

					$(".tempMenu").mouseout(function(e) {
						alert("Hi");
					});

					$("#tempMenu").mouseout(function(e) {
						$(this).css("visibility", "false");
						alert("Hi");

					});

					$("body").click(function(e) {
						$(".thumbMenu").css("display", "none");
					});

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

	innerHTML = innerHTML + "</tr></table>"
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
 * "http://localhost:8080/FlickrDemo/ReqResults?q="+query;
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