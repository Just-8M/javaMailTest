<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录注册界面</title>
<style type="text/css">
.table tr {
   border-collapse: collapse;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#btn").click(function(){
		if($(".email").val()){
					$.ajax({
						type:"POST",
						url :"SendEmailServlet?random"+Math.random(),
						data:{
							email:$(".email").val(),
						},
						success:function(data){
							alert("success");
						},
					})
		}else{
			alert("fail");
			$("#notice").html("请填写邮箱");
			setTimeout(function(){
				$("#notice").hide();
			},500);
		}
	});
	//  判断用户是否可以注册
	$("#submit").click(function(){
		if($(".email").val()){
			$("#RegistForm").submit();
		}else{   //  如果没有值
			$("#notice").html("请完整信息");
			setTimeout(function(){
				$("#notice").hide();
			},500);
		}
	});
});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/RegistServlet" id="RegistForm"    method="post">
	<table width="300" border="1" class="table" >
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>密&nbsp;码:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>邮箱:</td>
			<td><input type="text" name="email" class="email"></td>
			<td>
				<input type="button" class="btn" id="btn" value="发送邮箱验证码"/><br/>
			</td>
		</tr>
		<tr>
			<td>验证码:</td>
			<td><input type="text" name="code" class="code"></td>
		</tr>
		<span id="notice" class="hide">请先完成邮箱验证</span><br/>
		<tr>
			<td colspan="2"><input type="submit" id="submit" value="注册"></td>
		</tr>
	</table>
 </form>
</body>
</html>