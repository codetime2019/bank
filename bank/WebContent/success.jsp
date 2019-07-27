<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%> 
<html> 
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="/bank/css/bootstrap.min.css" />
	<script type="text/javascript" src="/bank/js/jquery-3.3.1.min.js" ></script>
	</head>
	<body>
		<div class="container" style="margin-top: 50px ;" >
			<div class="row">
				<div class="col-md-6 col-md-push-3" style="height:250px;">
					<table class="table table-bordered">
						<tr>
							<th>转账账户</th>
							<th>收款账户</th>
							<th>业务时间</th>
							<th>业务金额</th>
						</tr>
						<c:forEach items="${table.list}" var="Journal">
   							<tr>
   								<td>${Journal.accout}</td>
   								<td>${Journal.accin}</td>
   								<td>${Journal.dates}</td>
   								<td>${Journal.amount}</td>
   							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="row" >
				<div class="col-md-6 col-md-push-3">
					<div class="row" >
						<div class="col-sm-5 text-center" >
							<button class="btn btn-default" id="top" >上一页</button>
						</div>
						<div class="col-sm-2" >
							   <input type="text" class="form-control text-center" id="page" disabled="disabled" value="${table.page}" />
						</div>
						<div class="col-sm-5 text-center" >
							<button class="btn btn-default" id="bottom" >下一页</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			
			//o 总数条数
			var total=parseInt(${table.total});
			
			//o 显示大小
			var size=parseInt(${table.size});
			
			
			$("#top").click(function(){
				
				if((getPage()-1)==0){
					return;
				}
				
				setPage(getPage()-1);
				
				window.location.href="/bank/Show?size="+size+"&page="+getPage();
			});
			
			$("#bottom").click(function(){
				
				
				var ss=0;
				
				if(total/size%1>0){
					
					ss=parseInt(total/size)+1;
					
				}else{
					ss=parseInt(total/size);
				}
				
					
				if((getPage()+1)>ss){
					return;
				}
				
				setPage(getPage()+1);
				
				window.location.href="/bank/Show?size="+size+"&page="+getPage();
				
			});
			
		});
		
		function getPage(){
			
			$("#page").removeAttr("disabled");
			var page=parseInt($("#page").val());
			$("#page").attr({'disabled':'disabled'});
			return page;
		}
		
		function setPage(num){
			
			$("#page").removeAttr("disabled");
			$("#page").val(num);
			$("#page").attr({'disabled':'disabled'});
		}
		
	</script>
</html>