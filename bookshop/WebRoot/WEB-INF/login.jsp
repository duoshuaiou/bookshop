<i><%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="header.jsp"></jsp:include>


	<center>
		<div class="panel panel-default">
			<div class="panel-body">
				<span class="loginTop">书城用户登录</span>
				<form action="${pageContext.request.contextPath }/LoginController"
					method="post">
					<input type="hidden" name="op" value="login">
					<div style="width: 300px" class="input-group">
						<span class="input-group-addon id=" basic-addon1=""><sapn
								class="glyphicon glyphicon-user"></sapn></span> <input id="user"
							type="text" class="form-control" name="username"
							placeholder="用户名" aria-describedby="basic-addon1">
					</div>
					<br>
					<div style="width: 300px" class="input-group">
						<span class="input-group-addon" id="basic-addon1"><sapn
								class="glyphicon glyphicon-eye-close"></sapn></span> <input
							type="password" class="form-control" name="password"
							placeholder="密码" aria-describedby="basic-addon1">
					</div>
					
					<br>
					<div style="width: 300px" class="input-group">
						<span class="input-group-addon" id="basic-addon1"><sapn
								class="glyphicon glyphicon-barcode"></sapn></span> <input
							type="text" class="form-control" name="validate"
							placeholder="验证码" aria-describedby="basic-addon1">	
					</div>
						<input id="yanzheng" type="hidden"  name="truevalidate" value=""/> 	
						<span><canvas class="col-md-offset-5"id="canvas" width="120" height="45"></canvas>
             			<a id="changeImg">看不清，换一张</a></span>	
					<br> <br> <input class="btn btn-info" type="submit" value="提交">
				</form>
			</div>
		</div>

	</center>
	<body>
	 <%
		String message = (String)request.getAttribute("message");
		if(message == "online"){
			%>
	 			<script type="text/javascript">
						alert("用户已在线!");
						window.location.href="./Login";
				 </script>
			<%
		}else if(message == "false"){
			%>
 			<script type="text/javascript">
					alert("用户状态更新失败!");
					window.location.href="./Login";
			 </script>
		<%
		}else if(message == "no"){
			%>
 			<script type="text/javascript">
					alert("用户不存在或用户名、密码错误!");
					window.location.href="./Login";
			 </script>
		<%
		}
		else if(message == "paranull"){
			%>
 			<script type="text/javascript">
					alert("登录输入项不能为空!");
					window.location.href="./Login";
			 </script>
		<%
		}
		else if(message == "validateerror"){
			%>
 			<script type="text/javascript">
					alert("验证码错误!");
					window.location.href="./Login";
			 </script>
		<%
		}
	 %>
	  <script>	 
  /**生成一个随机数**/
  function randomNum(min,max){
    return Math.floor( Math.random()*(max-min)+min);
  }
  /**生成一个随机色**/
  function randomColor(min,max){
    var r = randomNum(min,max);
    var g = randomNum(min,max);
    var b = randomNum(min,max);
    return "rgb("+r+","+g+","+b+")";
  }
  var valida=drawPic();
  document.getElementById('yanzheng').value=valida;
  valida = document.getElementById("changeImg").onclick = function(){
    valida = drawPic();
    document.getElementById('yanzheng').value=valida;
  }
 
  /**绘制验证码图片**/
  function drawPic(){
    var canvas=document.getElementById("canvas");
    var width=canvas.width;
    var height=canvas.height;
    var ctx = canvas.getContext('2d');
    ctx.textBaseline = 'bottom';
 
    /**绘制背景色**/
    ctx.fillStyle = randomColor(180,240); //颜色若太深可能导致看不清
    ctx.fillRect(0,0,width,height);
    /**绘制文字**/
    var str = 'ABCEFGHJKLMNPQRSTWXY123456789';
    var result = '';
    for(var i=0; i<4; i++){
      var txt = str[randomNum(0,str.length)];
      ctx.fillStyle = randomColor(50,160);  //随机生成字体颜色
      ctx.font = randomNum(15,40)+'px SimHei'; //随机生成字体大小
      var x = 10+i*25;
      var y = randomNum(25,45);
      var deg = randomNum(-45, 45);
      //修改坐标原点和旋转角度
      ctx.translate(x,y);
      ctx.rotate(deg*Math.PI/180);
      ctx.fillText(txt, 0,0);
      //恢复坐标原点和旋转角度
      ctx.rotate(-deg*Math.PI/180);
      ctx.translate(-x,-y);
      result+=txt;
    }
    /**绘制干扰线**/
    for(var i=0; i<8; i++){
      ctx.strokeStyle = randomColor(40,180);
      ctx.beginPath();
      ctx.moveTo( randomNum(0,width), randomNum(0,height) );
      ctx.lineTo( randomNum(0,width), randomNum(0,height) );
      ctx.stroke();
    }
    /**绘制干扰点**/
    for(var i=0; i<100; i++){
      ctx.fillStyle = randomColor(0,255);
      ctx.beginPath();
      ctx.arc(randomNum(0,width),randomNum(0,height), 1, 0, 2*Math.PI);
      ctx.fill();
    }
    return result;
  }
  </script>
	</body>
	<html></html> </i>