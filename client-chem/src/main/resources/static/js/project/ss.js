
var d = document;
var l = window.location.href;
var f = l.substring(l.lastIndexOf('/') + 1);
var timer = false;




$(document).ready(function(){
 autoMaxWidth.ini();
});

$(window).load(function(){

    
   autoMaxWidth.ini();
	//var ifRemote = $("#iframeSon").get(0);
	//alert($(ifRemote.contentWindow.document).find("#worldWide"))
	//lightbox.worldwide = $(ifRemote.contentWindow.document).find("#worldWide")[0];
    
    lightbox.ini();
    menuPopup.ini();
    menuHighlight.exec();
    
    
    
    $(window).resize(function(){
        if (typeof indexSlides != 'undefined' && indexSlides.reformat) 
            indexSlides.reformat();
        autoMaxWidth.ini();
    });
    
  
    
});

var menuHighlight = {};
menuHighlight.exec = function(){
    if (l.indexOf('solutions') > -1) {
        $('#menu .solutions a').addClass('active');
        return $('#menu').data("normal", "solutions");
    }
    if (l.indexOf('products') > -1) {
        $('#menu .products a').addClass('active');
        return $('#menu').data("normal", "products");
    }
    if (l.indexOf('services') > -1) {
        $('#menu .services a').addClass('active');
        return $('#menu').data("normal", "services");
    }
    if (l.indexOf('about-huawei') > -1) {
        $('#menu .about-huawei a').addClass('active');
        return $('#menu').data("normal", "about-huawei");
    }
};

//----------------------------------------------
//add by bob 2011-04-07 (start)




var menuPopup = {};
menuPopup.timer = false;
menuPopup.ini = function(){
    menuPopup.obj = $('#menu-popup');
    
    $('#menu li a').hover(function(){
    
        menuPopup.stop();
        $('#menu li a').removeClass('active');
        $('#menu li a').removeClass('hover');
        menuPopup.obj.find('.popup').hide();
        
        //alter by bob 2011-04-07
        //var target = this.parentNode.className;
        target = this.parentNode.className;
        
        var position = $(this).offset();
        var shadowOffset = 2;
        if ($.browser.msie && parseInt($.browser.version) == 6) 
            shadowOffset = 0;
       
        //alter by bob 2011-04-07
        //var css = {
       	css = {
            top: position.top + 30,
            left: $('#menu li a').eq(0).offset().left - shadowOffset
        };

        //---------------------------------------------------
        //flag&#21028;&#26029;&#26159;&#21542;&#40736;&#26631;&#22312;MENU&#19978;&#20572;&#30041; (add by bob 2011-04-07) (start)
        if(iPx()){
        
        	var $target = menuPopup.obj.css(css).find('.' + target).show();
         					
			if ($target.length > 0) 
			$(this).addClass('hover');
			
		}else{
			if (flag = true) {
				time_temp = setTimeout(showDiv, 500);		
			}
		}
	
/*       
        var $target = menuPopup.obj.css(css).find('.' + target).show();
         
       
        if ($target.length > 0) 
            $(this).addClass('hover');
*/   
  		//(end)
		//---------------------------------------------------
        if ($(this).parent().attr('class') != $('#menu').data("normal")) {
            menuHighlight.exec();
        }
        
    }, menuPopup.hide);
    
    menuPopup.obj.hover(menuPopup.stop, menuPopup.hide);
    
};



//add by bob 2011-04-07 (end)
//-----------------------------------------------


var autoMaxWidth = {};
autoMaxWidth.ini = function(){

    var winW = $(window).width();
    if (winW < 980) 
        winW = 980;
    
    $('.autoMaxWidth').each(function(){
        $(this).width(winW);
        
        var $img = $('img', this).first();
        var imgW = $img.width();
        var left = (winW - imgW) / 2;
        $img.css({ "left": left + "px", "position": "relative" });
        
        if ($.browser.msie && parseInt($.browser.version) == 6) {
            $(this).css({
                'overflow': 'hidden',
                'position': 'relative'
            }).width(winW);
            $('#banner').css({
                'overflow': 'hidden',
                'position': 'relative'
            }).width(winW);
        }
        
    });
}

jQuery.fn.center = function(){
//alert(this.className);
    var top = ($(window).height() - this.height()) / 2 + $(window).scrollTop();
    var left = ($(window).width() - this.width()) / 2 + $(window).scrollLeft();
   // alert("$(window).height()=="+$(window).height()+"/"+"this.height()="+this.height()+"$(window).scrollTop()="+ $(window).scrollTop());

//alert("window.width:"+$(window).width()+"------------this.width:"+this.width()+"--------------window.scrollLeft:"+$(window).scrollLeft());
    this.css("position", "absolute");
    this.css("top", top + "px");
    this.css("left", left + "px");
    return this;
};

jQuery.fn.calCenter = function(minW){
    var winW = $(window).width();
    if (minW && winW < minW) 
        winW = minW;
    var left = (winW - this.width()) / 2 + $(window).scrollLeft();
    return {
        'left': left
    };
};

jQuery.fn.cleanOuterHTML = function(){
    return $("<p/>").append(this.eq(0).clone()).html();
};

jQuery.fn.loadthumb = function(options) {
	options = $.extend({
		 src : ""
	},options);
	var _self = this;
	_self.hide();
	var img = new Image();
	$(img).load(function(){
		_self.attr("src", options.src);
		_self.fadeIn("slow");
	}).attr("src", options.src);  //.atte("src",options.src)&#35201;&#25918;&#22312;load&#21518;&#38754;&#65292;
	return _self;
}

function replaceSwfWithEmptyDiv(targetID){
    var el = document.getElementById(targetID);
    if (el) {
        var div = document.createElement("div");
        el.parentNode.insertBefore(div, el);
        swfobject.removeSWF(targetID);
        div.setAttribute("id", targetID);
    };
    }

	
	
var lightbox = {};
lightbox.status = 0;
lightbox.contentId = false;
lightbox.selector = 'a[rel=lightbox]';
lightbox.width = 706;
lightbox.height = 560;
lightbox.ini = function(){
	
	
 
   
    
};