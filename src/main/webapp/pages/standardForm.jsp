<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   <!-- 定义模态框 -->
   <div class="modal fade" id="standardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
          <button id="removeAllStandardsModalButton" type="button" class="btn btn-primary">确认</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
 </div>
	<!-- 添加.nowrap是为了让表格数据能横向滚动scrollX -->
	<table id="standard" 
		class="table table-bordered nowrap"
		style="width: 100%">
      <!--表格头部 -->
      <thead>
        <tr>
          <th>序号</th>
          <th>规格</th>
          <th>标准名称</th>
          <th>抽样类型</th>
          <th>领域代码</th>
          <th>限制领域</th>
          <th>备注</th>
          <th>地址</th>
          <th>状态</th>
        </tr>
      </thead>
      <!-- 表格尾部 -->
      <tfoot>
        <tr>
          <th>序号</th>
          <th>规格</th>
          <th>标准名称</th>
          <th>抽样类型</th>
          <th>领域代码</th>
          <th>限制领域</th>
          <th>备注</th>
          <th>地址</th>
          <th>状态</th>
        </tr>
      </tfoot>
    </table>
</body>
</html>

<script type="text/javascript">
  var editor;
  $(function () {
    //创建DataTables Editor
    editor = new $.fn.dataTable.Editor({
      ajax:{
        create:{
          url:"<c:url value='/standard/insertStandard.action'/>",
          data:function(data){
            var result;
            for(var i in data.data){
              result = data.data[i];
              result.id = i;
            }
            return result;
          }
        },
        edit:{
          url:"<c:url value='/standard/updateStandard.action'/>",
          data:function(data){
            var result;
            
            for(var i in data.data){
              result = data.data[i];
              result.id = i;
            }
            return result;
          }
        },
        remove:{
          url:"<c:url value='/standard/removeStandardById.action'/>",
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
      table:"#standard",  
      //editor要求DataTables的每一行都必须指定一个ID，以便于告诉服务器此时修改、删除的是哪一行数据
      idSrc:'id',
      fields:[{label:'规格',name:'specification'},
        {label:'标准名称',name:'name'},
        {label:'抽样类型',name:'sampleType'},
        {label:'领域代码',name:'domainCode'},
        {label:'限制领域',name:'limitedField'},
        {label:'备注',name:'mark'},
        {label:'地址',name:'address'},
        {label:'状态',name:'status',type:"select",options:[
	        	{label:"执行",value:'1'},
	        	{label:'废弃',value:'0'}
        	]
        }
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
            _:"确定删除%d条标准吗？",
            1:'确定删除该条标准吗？'
          }
        },
        error:{
          "system":"操作失败..."
        }
      }
    });
    
    //加载DataTables
      var table = $('#standard').DataTable( {
          //"sPaginationType": "full_numbers",//翻页按钮显示为first、pre、next、last
          //将dataTables中的英文替换为中文
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
              "url":"<c:url value='/standard/findAllStandards.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [//数据对应项
          	  { data: 'index', defaultContent: '' },
              { data: "specification" },
              { data: "name" },
              { data: "sampleType" },
              { data: "domainCode" },
              { data: "limitedField" },
              { data: "mark" },
              { data: "address" },
              { data: "status",render:function(data){
              		return data == 1 ? '执行' : '废弃';
              }}
          ],
          "columnDefs": [ {
	            "searchable": false,
	            "orderable": false,
	            "targets": 0
	        } ],
          "order":[[1,'asc']],
          "scrollX": true,
          /**
            l - 每页显示行数的控件Lengthchage
            f - 检索条件Filter
            t - 表格Table
            i - 表信息 Info
            p - 分页控件Paginattion
            r - 处理中的控件pRocess
            < and > - div
          */
          dom:'Bftipl',
          buttons:[
            {extend:'create',editor:editor,text:'新增'},
            {extend:'edit',editor:editor,text:'修改'},
            {extend:'remove',editor:editor,text:'删除'},
            {text:'清除',action:function(){
            	//显示模态框
                $("#standardModal").modal('show');
                $("#removeAllStandardsModalButton").on("click",function(){
              	  $.post({
               			url:"<c:url value='/standard/removeAllStandards.action'/>",
               			dataType:'json',
               			success:function(data){
               				table.ajax.reload();
               			}
               	  });
              	  //隐藏模态框
              	  $("#standardModal").modal('hide');  
                });
	           }
            },
            {extend:'excel',text:'导出'}
          ],
          select:true,//使用选中插件
          colReorder:true,
          rowCallback:function(row,data,dataIndex){
              if(data.status == 0){
                $(row).addClass("btn-danger");
              }else{
                $(row).removeClass("btn-danger");
              }

          }
      });
  	table.on('order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
            //table.cell(cell).invalidate('dom');
        } );
    } ).draw();
  });
</script>