$(document).ready(function(){
  var IP = "";
  var tempI = 0;
  var remove = true;
  var exists = false;
  
  $(".testDiv").click(function(){
    // only for text
    $(this).hide();
  });

  //$(".topMenu").hide();
  
  $("#signIn").click(function(){
    window.location.href = './requestPermissions';
  });
  
  $(".thumbImage").click(function(e){
  
      var xCoord = e.pageX;
      var yCoord = e.pageY;
      $("#tempMenu").remove();
      
      var content = "<div class='thumbMenu' id='tempMenu'></div>";
      $(this).after(content);
      var moreHTML = "<ul><li><a href=\"\"> View Image </a></li><li><a href=\"\"> Delete Image </a></li><li><a href=\"\"> Move Image </a></li>";
      var folderHTML = "<ul><li><a href=\"\"> View Image </a></li><li><a href=\"\"> O </a></li><li><a href=\"\"> View Image </a></li>";
      $("#tempMenu").html(moreHTML);
      
      exists= true;
      $("#tempMenu").css("visibility","visible");
      $("#tempMenu").css("left",xCoord-30);
      $("#tempMenu").css("top",yCoord-30);
      return false;
    
  });
  
  $(".tempMenu").mouseout(function(e){
    alert("Hi");
  });

  
  $("#tempMenu").mouseout(function(e){
    $(this).css("visibility","false");
    alert("Hi");
      
  });

  $("body").click(function(e){
    $(".thumbMenu").css("display","none");
  });

});








function flickrQueryToBackend(queryt) {
  var strURL = "http://localhost:8080/FlickrDemo/ReqResults?q="+query;

  $.ajax({
    url:strURL,
    timeout:20000,
    async: true,
    type: 'GET',
    dataType: 'jsonp',
    crossDomain:true,
    success: function(result){
      // Display Results on the Screen
    var i = 0;
    var user = result.userName;

    for(i = 0; i<result.folders.length; i++){
      var folderName = result.folders[i].folderName;
      var queryNext = result.folders[i].queryNext;

  /////// To Build
  //   <td class="showThumb"><img src="images/folder.jpg" class="thumbImage" /><br>Folder Name</td>
  ///////    
      
      var innerHTML = "";

      innerHTML = "<span class = \"bingTitle\"> <h3 class=\"vscaption\">"+tempTitle+"</h3> </span>";
      innerHTML = innerHTML + "<a href = "+tempURL+" class = \"vsurl\"> "+tempURL+" </a>";
      innerHTML = innerHTML + "<span class = \"vsdescription\"> "+tempDesc+" </span>";
      innerHTML = innerHTML + "<hr style='width:99%'/>";

      $("#vresults").append(innerHTML);
}

    },
    error: function(req, error) { 
      console.log(req);
      console.log(error);
      //alert("AJAX-JSON Error : "+req.error);
    }
  });

}		


function sendQueryToBackend(qresult) {
  var strURL = "http://localhost:8080/VerticalSearch/BingSearch?q="+qresult+"&callback=?";

  $.ajax({
    url:strURL,
    timeout:20000,
    async: true,
    type: 'GET',
    dataType: 'jsonp',
    crossDomain:true,
    success: function(result){
      // Display Results on the Screen
    var i = 0;


    for(i = 0; i<result.searchResults.length; i++){
      var tempTitle = result.searchResults[i].jBingTitle;
      var tempURL = result.searchResults[i].jBingURL;
      var tempDesc = result.searchResults[i].jBingDesc;

      var innerHTML = "";

      innerHTML = "<span class = \"bingTitle\"> <h3 class=\"vscaption\">"+tempTitle+"</h3> </span>";
      innerHTML = innerHTML + "<a href = "+tempURL+" class = \"vsurl\"> "+tempURL+" </a>";
      innerHTML = innerHTML + "<span class = \"vsdescription\"> "+tempDesc+" </span>";
      innerHTML = innerHTML + "<hr style='width:99%'/>";

      $("#vresults").append(innerHTML);
}

    },
    error: function(req, error) { 
      console.log(req);
      console.log(error);
      //alert("AJAX-JSON Error : "+req.error);
    }
  });

}		
