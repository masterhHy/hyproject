$(function(){
$("ul.tab").tabs(".banner .pan > a", {effect:'fade',rotate: true, tabs:"li"}).slideshow({next:".arr_r",prev:".arr_l",autoplay:true,autopause:true,interval:6000});
})