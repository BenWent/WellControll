<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
  <!-- 定义模态框 -->
   <div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
          <button id="removeAllProductsModalButton" type="button" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
  <table id="product" 
    class="table table-striped table-bordered nowrap"
    style="width: 100%">
      <thead>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>产品类型</th>
          <th>生产厂家</th>
          <th>抽样单位及地点</th>
          <th>检测项目</th>
          <th>提交状态</th>
          <th>操作用户</th>
        </tr>
      </thead>
      <tfoot>
        <tr>
          <th>序号</th>
          <th>产品名称</th>
          <th>产品类型</th>
          <th>生产厂家</th>
          <th>抽样单位及地点</th>
          <th>检测项目</th>
          <th>提交状态</th>
          <th>操作用户</th>
        </tr>
      </tfoot>
    </table>
</body>
</html>

<script type="text/javascript">
  //返回SpringMVC能解析的JSON数据格式
  function produceMVCJsonData(data,tag){
    var jsonStr = '{';
        
        if(tag == "edit"){
          jsonStr += "\"id\":" + "\"" + data.id + "\","
        }

        jsonStr += "\"name\":" + "\"" + data.name + "\""
        + ",\"productType.name\":" + "\"" + data.productType.name + "\""
        + ",\"items\":" + "\"" + data.items + "\""
        + ",\"manufacturer.name\":" + "\"" + data.manufacturer.name + "\""
        + ",\"storeHouse.name\":" + "\"" + data.storeHouse.name + "\""
        + ",\"status\":" + "\"" + data.status + "\""
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
          url:"<c:url value='/product/insertProduct.action'/>",
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
          url:"<c:url value='/product/updateProduct.action'/>",
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
          url:"<c:url value='/product/removeProductById.action'/>",
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
      table:"#product",  
      idSrc:'id',
      fields:[{label:'产品名称',name:'name'},
              {
    	  		label:'产品类型',name:'productType.name',type:"select",options:[
              		{label:"钻井专用设备及钻采设备产品",value:'钻井专用设备及钻采设备产品'},
              		{label:"油田用其他类产品",value:'油田用其他类产品'}
            	]
      		},
          	{label:'生产厂家',name:'manufacturer.name'},
          	{label:'抽样单位及地点',name:'storeHouse.name'},
          	{label:'检查项目',name:'items'},
          	{label:'状态',name:'status',type:"select",options:[
             	{label:"未提交",value:'0'},
            	{label:'已提交',value:'1'}
           	]
          },
          {label:'操作用户',name:'user.name'}
       ],
      //
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
            _:"确定删除%d条产品信息吗？",
            1:'确定删除该条产品信息吗？'
          }
        },
        error:{
          "system":"操作失败..."
        }
      }
    });
    
    //已被提交的产品不能被再次修改
    editor.on("initEdit",function(){
      if(editor.get("status") == 1){
        editor.disable(editor.fields());
      }else{
         editor.enable(editor.fields());
      }
      
      editor.field("user.name").disable();
    });
    
    editor.on("initCreate",function(){
    	editor.field("user.name").val("${sessionScope.user.name}");
    	editor.field("user.name").disable();
    });

  	  //加载DataTables
      var table = $('#product').DataTable( {
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
              "url":"<c:url value='/product/findAllProducts.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [
              { data: 'index', defaultContent: '' },
              { data: "name" },
              { data: "productType.name" },
              { data: "manufacturer.name" },
              { data: "storeHouse.name" },
              { data: "items" },
              { data: "status",render:function(data){
            	  return data == 1 ? '已提交' : '未提交';
                }
              },
              { data: "user.name" }
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
                $("#productModal").modal('show');
                $("#removeAllProductsModalButton").on("click",function(){
              	  $.post({
               			url:"<c:url value='/product/removeAllProducts.action'/>",
               			dataType:'json',
               			success:function(data){
               				table.ajax.reload();
               			}
               	  });
              	  //隐藏模态框
              	  $("#productModal").modal('hide');  
                });

	           }
            },
            {extend:'excel',text:'导出'}
          ],
          select:true,
          colReorder:true
      });
    table.on('order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
  });
</script>