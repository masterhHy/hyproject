$(document).ready(function(){

    nav();
 	about_M();
	Solution_M();
	pro_list();
	hideSnav();
	jg_M();
	
   $(window).bind("scroll", function(event){
    
	 header();
	 tj_pro();
	 Service();
	 about_show();
	 Solution_shou();
	 jg_scroll_show();
	 
	 
   });
})
//行业解决方案鼠标放上去动画
function Solution_M(){
	
	$(".sol_list li").mouseover(function()
			 {   
 			$(this).find('img').stop().animate({ height:'200'});
			$(this).stop().animate({backgroundColor:"#616161"});
				 
  			 }
	 );
	
	$(".sol_list li").mouseout(function()
			 {   
 			$(this).find('img').stop().animate({ height:'220'});
			$(this).stop().animate({backgroundColor:"#9f2925"});	 
  			 }
	 );
	
	}

//首页介绍的图片鼠标放上去动画
function about_M(){
	
	$(".about_list li").not('.aboutnot').mouseover(function()
			 {   
			   
				 
 			 }
	 );
	
	$(".about_list li").mouseout(function()
			 {
 			   
 			 }
	 );
	
	 
 }


//推荐产品移上去动画
function tj_pro_li(){
	
	$(".pro_list li").mouseover(function()
			 {
 			 
				$(this).find('.pro_title').stop().animate({height:'90',backgroundColor:"#616161"},300);
 			 }
	 );
     

	 
	$(".pro_list li").mouseout(function()
			 {
 			     
				$(this).find('.pro_title').stop().animate({height:'70',backgroundColor:"#9f2925"},800);
 			 }
			 
			 
		
	 );
	 
	
}
//创新服务鼠标移上动动画
function service_M(){

     $(".ser_1,.ser_2,.ser_3,.ser_4").mouseover(function()
			 {
 				$(this).stop().animate({opacity:1,width:350 });
  			 }
	 );
	 
	 $(".ser_1,.ser_2,.ser_3,.ser_4").mouseout(function()
			 {
 				$(this).stop().animate({opacity:0.9,width:320});
  			 }
	 );
   

}

//创新服务轮显
function Service(){
 	 var fold = $(window).height() + $(window).scrollTop();
 	 if(fold >=1500){
	     $(".ser_title").stop().animate({opacity:1,left:0},1000);
		 $(".ser_1").stop().animate({opacity:0.9,left:350},1000);
		 $(".ser_2").stop().animate({opacity:0.9,left:760},1000);
		 $(".ser_3").stop().animate({opacity:0.9,left:350},1000);
		 $(".ser_4").stop().animate({opacity:0.9,left:760},1000);
		  setTimeout(function(){ service_M()},1000);
     };	
	  
	 
}
//行业解决方案动画
function Solution_shou(){
	var fold = $(window).height() + $(window).scrollTop();
	if(fold >=1000){
	   $(".sol_title").stop().animate({opacity:1,top:0} );
       $(".sol_list").stop().animate({opacity:1,top:180} );
	};	
	
}


//公司简介淡出动画
function about_show(){
 	 var fold = $(window).height() + $(window).scrollTop();
 	 if(fold >=2000){
	     $(".about_list").stop().animate({opacity:1,top:0},500);
		 
     };	
	 
}

//产品推荐轮显
function tj_pro(){
 	 var fold = $(window).height() + $(window).scrollTop();
 	 if(fold >=2800){
	      
		  var i = 0;
		  t = $('.pro_list li').length;
		  setInterval(function(){
           $('.pro_list li').eq(i > t-1 ? i = 0 : i++).show(300);
             },300);   
     };	
	 
	 setTimeout(function(){tj_pro_li();},2000);
	 
}



//头部通栏置顶滚动 
function header(){
 	 var fold = $(window).height() + $(window).scrollTop();
 	 if(fold >=50){
		$(".header").stop().animate({top:'-40'}); 
     };	
	 if( $(window).scrollTop() <120){
		 $(".header").stop().animate({top:'0'}); 
	 };
}



//主导航按钮底色变化
function nav(){
   	$(".nav li a").mouseover(function()
			 {   
			   // $(".nav li a").stop().animate({width:'95'},200);
			    $(".snav").stop().hide(); 
 			    $(this).stop().animate({backgroundColor:'#9f2925',color:'#fff',width:'110'},200);
				 $(".header").stop().animate({height:'150'}); 
				  $(".header").css("overflow","visible");
				 var dataId="#"+$(this).attr("dataId");
 				 $(dataId).stop().show(); 
				 
 			 }
		);
    $(".nav li a").mouseout(function()
			 {   
 			    $(this).stop().animate({backgroundColor:'#fff',color:'#000000',width:'100'},800);
				//$(".nav li a").stop().animate({width:'100'},200);
				
				 
 			 }
		);
 }
 
 function hideSnav(){
	 $(".header").mouseleave(function(){
  								  $(".header").stop().animate({height:'115'}); 
								   $(".header").css("overflow","visible");
				                    $(".snav").stop().hide(200); 
								 
 								});
	 
	 
	 }
 
 //头部显示二维码
 function showQRcode(){
 	    $(".ewm").toggle(500);
	 }
	 
	 
//推荐产品列表页鼠标放上去动画
function pro_list(){
	
	$(".pro_list2 li").mouseover(function()
			 {
 			    $(this).find('img').stop().animate({height:'220'});
 				$(this).stop().animate({backgroundColor:"#616161"});
 			 }
	 );
     
	$(".pro_list2 li").mouseout(function()
			 {
 			     $(this).find('img').stop().animate({height:'255'});
				$(this).stop().animate({backgroundColor:"#9f2925"});
 			 }
	 );
	
}
//分布机构鼠标放上去按钮动画
function jg_M(){
	
	$(".Dmenu li").mouseover(function()
			 {
 			    $(this).stop().animate({top:'-5'});
				//alert( $(this).index()  ); 
  			 }
	 );
	$(".Dmenu li").mouseout(function()
			 {
 			     $(this).stop().animate({top:'0'});
 			 }
	 );
	
	}
	
//动态展示机构
function jg_show(i){
   var D= ".d"+i;
   //alert(D);
   $(".d1,.d2,.d3,.d4").stop().animate({opacity:0},300,function(){ $(D).stop().animate({opacity:1})});
 }
 
function jg_scroll_show(){
	 var fold = $(window).height() + $(window).scrollTop();
 	 if(fold >=3400){
	     
		$(".D").stop().animate({opacity:1,top:0},function(){$(".d1").stop().animate({opacity:1,top:'0'},function(){$(".d2").stop().animate({opacity:1,top:'0'},function(){$(".d3").stop().animate({opacity:1,top:'105'},function(){$(".d4").stop().animate({opacity:1,top:'102'} )})})})  }); 
		
		
		
     };	
	 
	
	} 
	
//产品页点击后提交产品需求表单
 function submit_file(){
         $(".tj_info").html("<img src='/tpl/images/loading.gif' width=30 > 正在提交... "); 
		
		 p_name=$('#p_name').val();
		 p_email=$('#p_email').val();
		 p_phone=$('#p_phone').val();
		 p_remarks=$('#p_remarks').val();
		 
		 if(p_name==''){ $(".tj_info").html("联系人没有填写");$('#p_name').focus();      return; }
		 if(p_email==''){ $(".tj_info").html("联系邮箱没有填写");$('#p_email').focus();  return; }
		 if(p_phone==''){ $(".tj_info").html("联系电话没有填写");$('#p_phone').focus();  return; }
		 if(p_remarks==''){ $(".tj_info").html("所需资料没有说明");$('#p_remarks').focus(); return;}
		 
	    $.post("/admin/doProAction.php?act=addToFile",{p_remarks:p_remarks,p_phone:p_phone,p_email:p_email,p_name:p_name},function(data){
		    alert("提交成功");
			 $('#p_name').val("");
		     $('#p_email').val("");
		     $('#p_phone').val("");
		     $('#p_remarks').val("");
			$(".tj_info").html(""); 
		    $('#tofile').hide(500);   
 		});
   
   }

 
	   function tofile(){
   
      $('#tofile').show(500);
   
   
   }
   
      function close_pro(){
       $('#tofile').hide(500);  
	   $('#p_name').val('');
	   $('#p_email').val('');
	   $('#p_phone').val('');
	   $('#p_remarks').val('');
   }