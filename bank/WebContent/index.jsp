<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%> 
<html> 
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<title>new files</title>
		<link rel="stylesheet" type="text/css" href="/bank/css/bootstrap.min.css" />
		<style type="text/css">
			
		</style>
		<script src="/bank/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/bank/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal" id="ss" action="BankServlet?code=3" method="post" >
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">账户：</label>
							<div class="col-sm-6">
								<input type="text" name="account01" class="form-control" id="account" placeholder="">
							</div>
							<span class="help-block" style="color:red;" id="accountspan" ></span>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password" placeholder="">
							</div>
							<span class="help-block" id="passwordspan" ></span>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">待转账户：</label>
							<div class="col-sm-6">
								<input type="text" name="account02" class="form-control" id="eachaccount" placeholder="">
							</div>
							<span class="help-block" id="2span" ></span>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">转账金额：</label>
							<div class="col-sm-6">
								<input type="text" name="amount" class="form-control" id="amount" placeholder="">
							</div>
							<span class="help-block" id="span3" ></span>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
								<button type="submit" class="btn btn-default col-md-12">确认转账</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			
			var b1=false;
			
			var b2=false;
			
			var b3=false;
			
			var b4=false;
			
			
			//用户账户是否输入正确
			$("#account").focusout(function(){
				
				var account=$(this).val();
				
				if(account.trim()==""){
					
					$("#accountspan").css({
						'color':'red'
					});
					$("#accountspan").html("请先填写账户");
					
					return;
				}
				
				$.ajax({
					'url':'BankServlet?code=1&account='+account,
					'async':'false',
					'dataType':'json',
					'success':function(data){
						if(data.toString()=='false'){
							b1=false;
							$("#accountspan").css({
								'color':'red'
							});
							$("#accountspan").html("账户不存在，请核对账户");
						}
						
						if(data.toString()=='true'){
							b1=true;
							$("#accountspan").css({
								'color':'green'
							});
							$("#accountspan").html("填写正确");
						}
					}
				});
			});
			
			$("#password").focus(function(){
				if(b1==false){
					$("#passwordspan").css({
						'color':'red'
					});
					$("#passwordspan").html("请先输入正确的账户");
					$("#account").focus();
					return;
				}
			});
			
			$("#password").focusout(function(){
				
				if(b1==false){
					return;
				}
				
				var account=$("#account").val();
				var password=$("#password").val();
				
				$.ajax({
					'url':'BankServlet',
					'data':'code=2&account='+account+'&password='+password,
					'async':'false',
					'type':'post',
					'success':function(data){
						
						if(data.toString()=='true'){
							b2=true;
							$("#passwordspan").css({
								'color':'green'
							});
							$("#passwordspan").html("密码输入正确");
						}else{
							b3=false;
							$("#passwordspan").css({
								'color':'red'
							});
							$("#passwordspan").html("密码输入错误");
						}
						
					}
				});
				
			});
			
			$("#eachaccount").focusout(function(){
				
				var account=$(this).val();
				
				if(account.trim()==""){
					
					$("#2span").css({
						'color':'red'
					});
					$("#2span").html("请填写账户");
					
					return;
				}
				
				$.ajax({
					'url':'BankServlet',
					'type':'post',
					'data':'code=1&account='+account,
					'dataType':'json',
					'success':function(data){
						
						if(data.toString()=="true"){
							b3=true;
							$("#2span").css({
								'color':'green'
							});
							$("#2span").html("请核对您要转入的账户");
							
						}else{
							b3=false;
							$("#2span").css({
								'color':'red'
							});
							$("#2span").html("账户不存在");
						}
						
					}
				});
				
			});
			
			$("#amount").focusout(function(){
				
				var amount=$(this).val();
				
				if(amount.trim()==""){
					
					$("#span3").css({
						'color':'red'
					});
					$('#span3').html('请输入金额');
					
					b4=false;
					return;
				}
				
				b4=true;
			});
			
			$("#ss").submit(function(){
				
				if(b1==true&&b2==true&&b3==true&&b4==true){
					
					return true;
					
				}
				
				return false;
				
			});
		});
	</script>
</html>