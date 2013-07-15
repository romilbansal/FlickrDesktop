function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}
$(document).ready(function(){
    
  var imageURL = getURLParameter("imageURL");
  imageURL = imageURL.replace("_m.jpg", "_b.jpg");
  $("#filenameHeader").text(imageURL);
  var backFolder = "http://server-zerovelocity.rhcloud.com/FlickDesk/getFolder?next="+getURLParameter("backFolder");
  var content = "<img src =\""+imageURL+"\" id=\"imageShow\" />";
  $(".displayImageScreen").html(content);
  $("#signIn").click(function(e){
	  window.location.href = backFolder;
  });
  $("#filenameHeader").text(imageURL);
  
  
});