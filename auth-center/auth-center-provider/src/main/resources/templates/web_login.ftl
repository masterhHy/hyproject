<!DOCTYPE html>
<html lang="en" class="no-js" style="height: 100%" >
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="css/component.css" />
    <!--[if IE]>
    <script src="js/html5.js"></script>
    <![endif]-->

</head>
<body class="full-height">
<div class="container demo-1 full-height">
    <div class="content full-height">
        <div id="large-header" class="large-header full-height">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎你</h3>
                <div class="js_loginbox" style="position: relative;top: 0px;">
                    <form action="login" id="form" method="post" >
                        <div class="input_outer">
                            <span class="u_user"></span>
                            <input id="username" name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input id="password" name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
                        </div>
                        <div class="mb2" id="submit"><a class="act-but submit" href="javascript:;" style="color: #FFFFFF">登录</a></div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <div class="register js_go">
                        去注册
                    </div>
                    <div>
                    <#if RequestParameters.error?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
                        <#if Session.SPRING_SECURITY_LAST_EXCEPTION.message == "Bad credentials">
                            <div class="ui red message">
                                密码错误
                            </div>
                        <#else >
                            <div class="ui red message">
                                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
                            </div>
                        </#if>
                    </#if>

                    </div>
                </div>
                <div class="js_registerbox" style="position: relative;top: -242px;left: -370px;" >
                    <form action="register" id="registerForm" method="post">
                        <div class="input_outer">
                            <span class="u_user"></span>
                            <input  name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入手机号">
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
                        </div>
                        <div class="input_outer">
                            <span class="us_uer"></span>
                            <input name="repassword" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请再次输入密码">
                        </div>
                        <div class="input_outer">
                            <span class="code_uer"></span>
                            <input id="code" name="code"  class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;width: 90px;"value="" placeholder="请输入验证码"  >
                            <div class="getCode js_getCode">获取验证码</div>
                        </div>
                        <div class="mb2" id="go"><a class="act-but submit" href="javascript:;" style="color: #FFFFFF">注册</a></div>
                        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    </form>
                    <div class="ui red message js_registerErrorMsg" style="display: none"></div>
                    <div class="register js_back">
                        返回
                    </div>
                </div>



            </div>

        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<#if isMobile >
<script>

</script>
<#else >
<script src="js/TweenLite.min.js"></script>
<script src="js/EasePack.min.js"></script>
<script src="js/rAF.js"></script>
<script src="js/demo-1.js"></script>
</#if>
<script>
    $("#submit").click(function () {
        if ($("#username").val() && $("#password").val()) {
            $("#form").submit();
        }
    });
    $("#go").click(function () {
        $(".js_registerErrorMsg").hide();
        var username = $("#registerForm input[name=username]").val();
        var password = $("#registerForm input[name=password]").val();
        var repassword = $("#registerForm input[name=repassword]").val();
        var code = $("#registerForm input[name=code]").val();
        var errorMsg="";
        if(!checkPhone(username)){
            errorMsg="电话号格式不正确";
        }
        if(!password){
            errorMsg="密码不能为空";
        }
        if(password!=repassword){
            errorMsg="密码不一致";
        }
        if(!code){
            errorMsg="验证码不能为空";
        }

        checkUniquePhone(username,function(data){
            if(!data.res){
                errorMsg = json.msg;
            }

            if(errorMsg){
                $(".js_registerErrorMsg").html(errorMsg);
                $(".js_registerErrorMsg").show();
            }else{
                //提交注册信息
                $.ajax({
                    url:"open/register",
                    dataType:"json",
                    type:"get",
                    data:{username:username,password:password,code:code,moduel:"regist"},
                    success:function(res){
                        if(res.status){
                            //注册成功后
                            $("input").not(":hidden").val("");
                            $(".js_back").trigger("click");
                            $("#username").val(username);

                        }else{
                            $(".js_registerErrorMsg").html(res.message);
                            $(".js_registerErrorMsg").show();
                        }
                    }
                });

            }
        })



    });

    $("#registerForm input[name=username]").change(function () {
        var phone  = $(this).val();
        var errorMsg="";
        if(!checkPhone(phone)){
            errorMsg="电话号格式不正确";
        }
        if(errorMsg){
            $(".js_registerErrorMsg").html(errorMsg);
            $(".js_registerErrorMsg").show();
        }else{
            //检验号码是否存在
            checkUniquePhone(phone,function(data){
                if(!data.res){
                    $(".js_registerErrorMsg").html(data.msg);
                    $(".js_registerErrorMsg").show();
                }else{
                    $(".js_registerErrorMsg").hide();
                }
            })
        }

    });
    
    function checkUniquePhone(phone,callback) {
        $.ajax({
            url:"open/checkUsername",
            dataType:"json",
            type:"get",
            data:{username:phone},
            success:function(res){
                callback({res:res.status,msg:res.message});
            }
        });

    }

    function checkPhone(phone){
        var reg =/^1[34578]\d{9}$/;
        return reg.test(phone);
    }


    $(".js_go").click(function () {
        $(".js_loginbox").animate({left:"-370px"},1000,function () {
            $(".js_registerbox").animate({left:"0px"},1000)
        });
    });
    $(".js_back").click(function () {
        $(".js_registerbox").animate({left:"-370px"},1000,function () {
            $(".js_loginbox").animate({left:"0px"},1000)
        });
    });
    $("body").on("click",".js_getCode",function(){
        var username = $("#registerForm input[name=username]").val();
        var _this=this;
        if(checkPhone(username)){
            $.ajax({
                url:"open/getCode",
                dataType:"json",
                type:"get",
                data:{username:username,moduel:"regist"},
                success:function(res){
                    if(res.status){
                        $(_this).removeClass("js_getCode");
                        var bc = $(_this).css("background-color");
                        $(_this).css("background-color","#ccc");
                        var str = $(_this).html();
                        var num =60;
                        $(_this).html("已获取（"+num+"）");
                        num--;
                        var codeInterval = setInterval(function(){
                            $(_this).html("已获取（"+num+"）");
                            num--;
                            if(num<0){
                                clearInterval(codeInterval);
                                $(_this).html(str);
                                $(_this).addClass("js_getCode");
                                $(_this).css("background-color",bc);
                            }
                        },1000);
                    }else{
                        alert("服务器出错了~~");
                    }
                }
            });
        }


    })


</script>
</body>
</html>