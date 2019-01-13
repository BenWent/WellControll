<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   <!-- 定义清除模态框 -->
   <div class="modal fade" id="planModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title">
            清除
          </h4>
        </div>
        <div class="modal-body">
          你确定清除所有的设备信息吗？
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭
          </button>
          <button id="removeAllPlansModalButton" type="button" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
 <!-- 定义添加错误模态框 -->
   <div class="modal fade" id="addPlanErrorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title">
            错误
          </h4>
        </div>
        <div id="addPlanErrorMessage" class="modal-body">
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭
          </button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
  <!-- 添加.nowrap是为了让表格数据能横向滚动scrollX -->
  <table id="plan" 
    class="table table-striped table-bordered nowrap"
    style="width: 100%">
      <!--表格头部 -->
      <thead>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>产品类型</th>
          <th>抽查单位</th>
          <th>抽查时间</th>
          <th>执行标准</th>
          <th>操作用户</th>
          <th>备注</th>
        </tr>
      </thead>
      <!-- 表格尾部 -->
      <tfoot>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>产品类型</th>
          <th>抽查单位</th>
          <th>抽查时间</th>
          <th>执行标准</th>
          <th>操作用户</th>
          <th>备注</th>
        </tr>
      </tfoot>
    </table>
</body>
</html>

<script type="text/javascript">
  //返回SpringMVC能解析的JSON数据格式
  function produceMVCJsonData(data,tag){
    var jsonStr = '{';
        
        if(tag == "edit"){
          jsonStr += "\"id\":" + "\"" + data.id + "\","
        }

        jsonStr += "\"product.name\":" + "\"" + data.product.name + "\""
        // + ",\"product.productType.name\":" + "\"" + data.product.productType.name + "\""
        // + ",\"product.manufacturer.name\":" + "\"" + data.product.manufacturer.name + "\""
        + ",\"date\":" + "\"" + data.date + "\""
        + ",\"standard.name\":" + "\"" + data.standard.name + "\""
        + ",\"mark\":" + "\""  + data.mark + "\""
        + ",\"user.name\":" + "\"" + data.user.name + "\""
     +'}';
     console.log(jsonStr);
    return JSON.parse(jsonStr);
  }
  var editor;
  $(function () {
    //创建DataTables Editor
    editor = new $.fn.dataTable.Editor({
      ajax:{
        create:{
          url:"<c:url value='/plan/insertSamplePlan.action'/>",
          data:function(data){
            var result;
          
            for(var i in data.data){
              result = data.data[i];
              result.id = i;
            }

            return produceMVCJsonData(result,"create");
          }
        },
        edit:{
          url:"<c:url value='/plan/updateSamplePlan.action'/>",
          data:function(data){
            var result;
            
            for(var i in data.data){
              result = data.data[i];
              result.id = i;
            }

            return produceMVCJsonData(result,"edit");
          },
        },  
        remove:{
          url:"<c:url value='/plan/removeSamplePlanById.action'/>",
          data:function(data){
            var result = {};
            for(var i in data.data){
              id = i; 
            }
            result.id = id;
            return result;
          }
        },
        error:{
            system:'操作失败'
        }
      },
      table:"#plan",  
      //editor要求DataTables的每一行都必须指定一个ID，以便于告诉服务器此时修改、删除的是哪一行数据
      idSrc:'id',
      fields:[{label:'产品名称',name:'product.name'},
        // {label:'产品类型',name:'product.productType.name'},
        // {label:'抽查单位',name:'product.manufacturer.name'},
        {label:'抽查时间',name:'date',type:'datetime'},
        {label:'执行标准',name:'standard.name'},
        {label:'操作用户',name:'user.name'},
        {label:'备注',name:'mark'}
      ],
      //汉化弹出提示框
      i18n:{
        create:{
          title:'创建',
          submit:'确定'
        },
        edit:{
          title:'编辑',
          submit:'确定'
        },
        remove:{
          title:'删除',
          submit:'确定',
          confirm:{
            _:"确定删除%d条抽样计划吗？",
            1:'确定删除该条抽样计划吗？'
          }
        },
        error:{
          "system":"操作失败..."
        },
        datetime: {
          months:   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
          weekdays: [ '星期天', '星期一', '二','三', '四', '五', '六']
        }
      }
    });
    
    //新增抽查计划异常
    editor.on("postSubmit",function(e,d){
        if(typeof(d.responseText) == 'string'){
          editor.close();
          $("#addPlanErrorMessage").text(d.responseText);
          $("#addPlanErrorModal").modal("show");
        }
    });
   
    editor.on("initCreate",function(){
      //默认的用户
      editor.field("user.name").val("${sessionScope.user.name}");
      //禁止修改用户
      editor.field("user.name").disable();
    });

    //加载DataTables
      var table = $('#plan').DataTable( {
          //"sPaginationType": "full_numbers",//翻页按钮显示为first、pre、next、last
          //将dataTables中的英文替换为中文
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
              "url":"<c:url value='/plan/findAllSamplePlans.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [//数据对应项
              { data: 'index', defaultContent: '' },
              { data: "product.name" },
              { data: "product.productType.name" },
              { data: "product.manufacturer.name" },
              { data: "date" },
              { data: "standard.name" },
              { data: "user.name" },
              { data: "mark" }
          ],
          "columnDefs": [ {
              "searchable": false,
              "orderable": false,
              "targets": 0
          } ],
          "order":[[1,'asc']],
          "scrollX": true,
          dom:'Bftipl',
          buttons:[
            {extend:'create',editor:editor,text:'新增'},
            {extend:'edit',editor:editor,text:'修改'},
            {extend:'remove',editor:editor,text:'删除'},
            {text:'清除',action:function(){
            	//显示模态框
                $("#planModal").modal('show');
                $("#removeAllPlansModalButton").on("click",function(){
              	  $.post({
               			url:"<c:url value='/plan/removeAllSamplePlans.action'/>",
               			dataType:'json',
               			success:function(data){
               				table.ajax.reload();
               			}
               	  });
              	  //隐藏模态框
              	  $("#planModal").modal('hide');  
                });
	           }
            },
            {extend:'excel',text:'导出'}
          ],
          select:true,//使用选中插件
          colReorder:true
      });
    table.on('order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
  });
</script>