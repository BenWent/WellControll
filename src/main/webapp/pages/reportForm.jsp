<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
   <!-- 定义模态框 -->
   <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
          你确定清除所有的抽查结果吗？
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭
          </button>
          <button id="removeAllReportsModalButton" type="button" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
  <!-- 添加.nowrap是为了让表格数据能横向滚动scrollX -->
  <table id="report" 
    class="table table-striped table-bordered nowrap"
    style="width: 100%">
      <!--表格头部 -->
      <thead>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>抽查单位</th>
          <th>执行标准</th>
          <th>检验项目</th>
          <th>主要不合格项</th>
          <th>抽样日期</th>
          <th>检验日期</th>
          <th>是否合格</th>
          <th>是否复查</th>
          <th>是否存档</th>
          <th>是否委外</th>
          <th>是否邮寄</th>
          <th>邮寄时间</th>
          <th>邮寄地点</th>
          <th>邮寄单号</th>
          <th>联系人</th>
          <th>联系电话</th>
          <th>操作用户</th>
          <th>是否提交</th>
          <th>备注</th>
        </tr>
      </thead>
      <!-- 表格尾部 -->
      <tfoot>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>抽查单位</th>
          <th>执行标准</th>
          <th>检验项目</th>
          <th>主要不合格项</th>
          <th>抽查日期</th>
          <th>检验日期</th>
          <th>是否合格</th>
          <th>是否复查</th>
          <th>是否存档</th>
          <th>是否委外</th>
          <th>是否邮寄</th>
          <th>邮寄时间</th>
          <th>邮寄地点</th>
          <th>邮寄单号</th>
          <th>联系人</th>
          <th>联系电话</th>
          <th>操作用户</th>
          <th>是否提交</th>
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

        jsonStr += "\"checkDate\":" + "\"" + data.checkDate + "\""
        + ",\"status\":" + "\"" + data.status + "\""
        + ",\"mark\":" + "\""  + data.mark + "\""
        + ",\"user.name\":" + "\"" + data.user.name + "\""
        + ",\"failedItems\":" + "\"" + data.failedItems + "\""
        + ",\"result\":" + "\""  + data.result + "\""
        + ",\"contact\":" + "\"" + data.contact + "\""
        + ",\"contactPhoneNumber\":" + "\"" + data.contactPhoneNumber + "\""
        + ",\"isPost\":" + "\""  + data.isPost + "\""
        + ",\"postTime\":" + "\"" + data.postTime + "\""
        + ",\"postReceipt\":" + "\"" + data.postReceipt + "\""
        + ",\"postAddress\":" + "\"" + data.postAddress + "\""
        + ",\"recheck\":" + "\""  + data.recheck + "\""
        + ",\"isOutsoucing\":" + "\"" + data.isOutsoucing + "\""
        + ",\"store\":" + "\"" + data.store + "\""
        + ",\"samplePlan.id\":" + "\"" + data.samplePlan.id + "\""
        + ",\"checkDate\":" + "\"" + data.checkDate + "\""
     +'}';
    return JSON.parse(jsonStr);
  }
  var editor;
  $(function () {
    editor = new $.fn.dataTable.Editor({
      ajax:{
        edit:{
          url:"<c:url value='/report/updateReport.action'/>",
          data:function(data){
            var result;
            
            for(var i in data.data){
              result = data.data[i];
              result.id = i;
            }
            return produceMVCJsonData(result,"edit");
          }
        },
        error:{
            system:'操作失败'
        },
        datetime: {
            months:   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
            weekdays: [ '星期天', '星期一', '二','三', '四', '五', '六']
          }
      },
      table:"#report",  
      idSrc:'id',
      fields:[{label:'产品名称',name:'samplePlan.product.name'},
        {label:'抽查单位',name:'samplePlan.product.manufacturer.name'},
        {label:'执行标准',name:'samplePlan.standard.name'},
        {label:'检查项目',name:'samplePlan.product.items'},
        {label:'主要不合格项',name:'failedItems'},
        {label:'抽查日期',name:'samplePlan.date',type:'datetime'},
        {label:'检验日期',name:'checkDate',type:'datetime'},
        {label:'是否合格',name:'result',type:'select',options:[
            {label:'不合格',value:'0'},
            {label:'合格',value:'1'}
          ]
        },
        {label:'是否复查',name:'recheck',type:'select',options:[
            {label:'否',value:'0'},
            {label:'是',value:'1'}
          ]
        },
        {label:'是否存档',name:'store',type:'select',options:[
            {label:'否',value:'0'},
            {label:'是',value:'1'}
          ]
        },
        {label:'是否委外',name:'isOutsoucing',type:'select',options:[
            {label:'否',value:'0'},
            {label:'是',value:'1'}
          ]
        },
        {label:'是否邮寄',name:'isPost',type:'select',options:[
            {label:'否',value:'0'},
            {label:'是',value:'1'}
          ]
        },
        {label:'邮寄时间',name:'postTime',type:'datetime'},
        {label:'邮寄地点',name:'postAddress'},
        {label:'邮寄单号',name:'postReceipt'},
        {label:'联系人',name:'contact'},
        {label:'联系电话',name:'contactPhoneNumber'},
        {label:'操作用户',name:'user.name'},
        {label:'是否提交',name:'status',type:'select',options:[
            {label:'未提交',value:'0'},
            {label:'已提交',value:'1'}
          ]
        },
        {label:'备注',name:'mark'},
        {label:'抽查计划ID',name:'samplePlan.id'}
      ],
      i18n:{
        edit:{
          title:'编辑',
          submit:'确定'
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

    editor.on("initEdit",function(){
      //将某个字段作为对用户隐藏的字段返回给server
      editor.field("samplePlan.id").hide();

      if(editor.get("status") == 1){
        editor.disable(editor.fields());
      }else{
         editor.enable(editor.fields());
      }

      //user.name由当前操作该表格的用户决定，不能任由用户修改${sessionScope.userName}
      editor.field("user.name").val("${sessionScope.user.name}");

      editor.field("samplePlan.product.name").disable();
      editor.field("samplePlan.product.manufacturer.name").disable();
      editor.field("samplePlan.standard.name").disable();
      editor.field("samplePlan.product.items").disable();
      editor.field("samplePlan.date").disable();
      editor.field("user.name").disable();
    });
    //加载DataTables
      var table = $('#report').DataTable( { 
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
              "url":"<c:url value='/report/findAllReports.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [//数据对应项
              { data: 'index', defaultContent: '' },
              { data: "samplePlan.product.name" },
              { data: "samplePlan.product.manufacturer.name" },
              { data: "samplePlan.standard.name" },
              { data: "samplePlan.product.items" },
              { data: "failedItems" },
              { data: "samplePlan.date" },
              { data: "checkDate" },
              { data: "result" ,render:function(data){
                  return data == 0 ? "不合格" : "合格";
                }
              },
              { data: "recheck" ,render:function(data){
                  return data == 0 ? "否" : "是";
                }
              },
              { data: "store" ,render:function(data){
                  return data == 0 ? "否" : "是";
                }
              },
              { data: "isOutsoucing" ,render:function(data){
                  return data == 0 ? "否" : "是";
                }
              },
              { data: "isPost" ,render:function(data){
                  return data == 0 ? "否" : "是";
                }
              },
              { data: "postTime" },
              { data: "postAddress" },
              { data: "postReceipt" },
              { data: "contact" },
              { data: "contactPhoneNumber" },
              { data: "user.name" ,defaultContent:"${sessionScope.user.name}"},
              { data: "status" ,render:function(data){
                  return data == 1 ? '已提交' : '未提交'; 
                }
              },
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
            {extend:'edit',editor:editor,text:'修改'},
            {text:'清除',action:function(){
            	if("${sessionScope.user.privilege}" == '0'){
            		//显示模态框
                    $("#reportModal").modal('show');
                    $("#removeAllReportsModalButton").on("click",function(){
                  	  $.post({
                   			url:"<c:url value='/report/removeAllReports.action'/>",
                   			dataType:'json',
                   			success:function(data){
                   				table.ajax.reload();
                   			}
                   	  });
                  	  //隐藏模态框
                  	  $("#reportModal").modal('hide');  
                    });
            	} 
            }},
            {extend:'excel',text:'导出'}
          ],
          select:true,
          colReorder:true,
          rowCallback:function(row,data,dataIndex){
              if(data.status == 1){
               $(row).css("background-color","#9370DB");
              }
          }
      });
    table.on('order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
  });
</script>