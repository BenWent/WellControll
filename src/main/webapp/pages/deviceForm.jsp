<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   <!-- 定义模态框 -->
   <div class="modal fade" id="deviceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
          <button id="removeAllDevicesModalButton" type="button" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
  <table id="device" 
    class="table displayed table-bordered nowrap"
    style="width: 100%">
      <!--表格头部 -->
      <thead>
        <tr>
          <th>序号</th>
          <th>设备名称</th>
          <th>设备类型</th>
          <th>制造单位</th>
          <th>设备数量</th>
          <th>仪器编号</th>
          <th>使用日期</th>
          <th>使用寿命</th>
          <th>设备原值</th>
          <th>放置地点</th>
          <th>备注</th>
        </tr>
      </thead>
      <!-- 表格尾部 -->
      <tfoot>
        <tr>
          <th>序号</th>
          <th>设备名称</th>
          <th>设备类型</th>
          <th>制造单位</th>
          <th>设备数量</th>
          <th>仪器编号</th>
          <th>使用日期</th>
          <th>使用寿命</th>
          <th>设备原值</th>
          <th>放置地点</th>
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

        jsonStr += "\"name\":" + "\"" + data.name + "\""
        + ",\"address\":" + "\"" + data.address + "\""
        + ",\"beginningStartUseTime\":" + "\"" + data.beginningStartUseTime + "\""
        + ",\"type\":" + "\"" + data.type + "\""
        + ",\"overdueDate\":"  + "\"" + data.overdueDate + "\""
        + ",\"mark\":" + "\""  + data.mark + "\""
        + ",\"cost\":" + (data.cost == "" ? 0 : data.cost)
        + ",\"amount\":" + (data.amount == "" ? 0 : data.amount) 
        + ",\"number\":" + "\"" + data.number + "\""
        + ",\"manufacturer.name\":" + "\"" + data.manufacturer.name + "\""
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
          url:"<c:url value='/device/insertDevice.action'/>",
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
          url:"<c:url value='/device/updateDevice.action'/>",
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
          url:"<c:url value='/device/removeDeviceById.action'/>",
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
      table:"#device",  
      idSrc:'id',
      fields:[{label:'设备名称',name:'name'},
        {label:'设备类型',name:'type'},
        {label:'制造单位',name:'manufacturer.name'},
        {label:'设备数量',name:'amount'},
        {label:'仪器编号',name:'number'},
        {label:'使用日期',name:'beginningStartUseTime',type:'datetime'},
        {label:'有效期',name:'overdueDate',type:'datetime'},
        {label:'设备原值',name:'cost'},
        {label:'放置地点',name:'address'},
        {label:'备注',name:'mark'}
      ],
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
            _:"确定删除%d条设备信息吗？",
            1:'确定删除该条设备信息吗？'
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

    //加载DataTables
      var table = $('#device').DataTable( {
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
              "url":"<c:url value='/device/findAllDevices.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [
              { data: 'index', defaultContent: '' },
              { data: "name" },
              { data: "type" },
              { data: "manufacturer.name" },
              { data: "amount" },
              { data: "number" },
              { data: "beginningStartUseTime" },
              { data: "overdueDate" },
              { data: "cost" ,render:$.fn.dataTable.render.number( ',', '.', 2, '￥' )},
              { data: "address" },
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
                $("#deviceModal").modal('show');
                $("#removeAllDevicesModalButton").on("click",function(){
              	  $.post({
               			url:"<c:url value='/device/removeAllDevices.action'/>",
               			dataType:'json',
               			success:function(data){
               				table.ajax.reload();
               			}
               	  });
              	  //隐藏模态框
              	  $("#deviceModal").modal('hide');  
                });
	            }
            },
            {extend:'excel',text:'导出'}
          ],
          select:true,//使用选中插件
          colReorder:true,
          rowCallback:function(row,data,dataIndex){
              if(new Date().getTime() > new Date(data.overdueDate).getTime()){
                $(row).addClass("btn-danger");
              }else{
                $(row).removeClass("btn-danger");
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