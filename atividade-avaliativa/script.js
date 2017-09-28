 $(document).ready(function(){

    $("td[id|='out']").css("border","0.5px dashed red" );

    $("li[id|='out'").mouseenter(function() {
        $("td#"+this.id).css({"color":"blue","border":"1.5px dashed blue", "font-weight":"bolder"});
        $("#"+this.id+" + ol").css("color","blue");
    });

    $("li[id|='out'").mouseout(function() {
    	$("td#"+this.id).css({"color":"black","border":"1px dashed #FF5400", "font-weight":"normal"});
        $("#"+this.id+" + ol").css("color","black");
    });

	$("li[id|='out'").mouseenter(function() {
        $("td#"+this.id).css({"color":"blue","border":"1.5px dashed blue", "font-weight":"bolder"});
        $("#"+this.id+" + ul").css("color","blue");
    });

    $("li[id|='out'").mouseout(function() {
    	$("td#"+this.id).css({"color":"black","border":"1px dashed #FF5400", "font-weight":"normal"});
        $("#"+this.id+" + ul").css("color","black");
    });

});
