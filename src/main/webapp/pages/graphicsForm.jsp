<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<body>
	<div  class="container">
		<div class="row">
			<div class="col-md-2 col-md-offset-4 col-xs-9 col-xs-offset-1">
				<select id="year" class="selectpicker" >
					<option value="" selected disabled>请选择年份</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div id="graphicsContainer" class="col-md-7 col-md-offset-2 col-xs-9 col-xs-offset-1"></div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
  $(function(){
  	//获取highCharts格式的JSON数据
  	function getHighChartsJsonData(data){
		var result = [];
		for(var i = 0;i < data.length;i++){
			result[i] = [data[i].companyName,data[i].unqualifiedProductCount];
		}
		return result;
	}

  	//创建年份选项
  	$.ajax({
  		url:"<c:url value='/report/countCheckDateYear.action'/>",
  		type:'post',
  		async: true,
  		success:function(data){
  			var $select = $("#year");
  			var json = JSON.parse(data);

  			if(json.length != 0){
  				for(var i = 0;i < json.length;i++){
  					$select.append("<option value = \"" + json[i] + "\">" 
  						+ json[i] + "</option>");
  				}
  			}
	        $select.selectpicker('refresh');

	        $select.on("change",function(){
	        	$.ajax({
	        		url:"<c:url value='/report/findUnqualifiedMessages.action'/>",
			  		type:'post',
			  		async: true,
			  		data:{
			  			year:$('#year').selectpicker('val')
			  		},
			  		success:function(data){
			  			var messages = JSON.parse(data);
			  			console.log(messages);
			  			$("#graphicsContainer").highcharts({
					  		title:{
					  			text:'不合格产品统计'
					  		},
					  		subtitle:{
					  			text:'井控产品质量监督系统'
					  		},
					  		credits:{//去掉图像右下角的highcharts标识符
					  			enabled:false
					  		},
					  		tooltip:{
					  			valueSuffix:'个'
					  		},
					  		plotOptions:{
					  			pie:{
					  				allowPointSelect: true,//点击某一部分使某一部分扇形块分离出去
					  				cursor:'pointer',
					  				dataLabels:{
					  					enabled:true,
					  					format: '{point.name}: {point.percentage:.1f} %',
					  					style: {
						                  color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
						               }

					  				}
					  			}
					  		},
					  		series:[
					  			{
						  			type:'pie',
						  			name:'不合格产品数量',
						  			marker:{
						  				enabled:false
						  			},
						  			data:getHighChartsJsonData(messages)
					  			}
					  		]
					  	});//highcharts
			  		}//success
	        	});//highcharts ajax
	        });//select.on("change")
  		}//创建年份success
  	});//创建年份ajax
  });
</script>