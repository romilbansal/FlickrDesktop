$(document).ready(function(){
  var tempI = 0;
  var remove = true;
  $(".testDiv").click(function(){
    // only for text
    $(this).hide();
  });
  
  $("#signIn").click(function(){
	  window.location.href = './requestPermissions';
  });
  
    $(".thumbImage").click(function(e){
    
        var xCoord = e.pageX;
        var yCoord = e.pageY;
    
        var content = "<div class='thumbMenu' id='tempMenu'>HELLOOOO</div>";
        $(this).after(content);
    
        $("#tempMenu").css("visibility","visible");
        $("#tempMenu").css("left",xCoord-30);
        $("#tempMenu").css("top",yCoord-30);
        return false;
      
    });

    $(".thumbImage").mouseout(function(e){
        
        $("#tempMenu").css("background-color","gray");
        return false;
      
    });
    
    
    $(".thumbMenu").mouseout(function(e){
        alert("Hi");
        
    });
    
});