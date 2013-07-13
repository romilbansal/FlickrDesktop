function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}
$(document).ready(function(){
    
  var imageURL = getURLParameter("imageURL");
  var content = "<img src =\""+imageURL+"\" id=\"imageShow\" />";
  $(".imageScreen").html(content);
  
});