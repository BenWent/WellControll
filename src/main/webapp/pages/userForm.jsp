<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
  <!-- 添加.nowrap是为了让表格数据能横向滚动scrollX -->
  <table id="user" 
    class="table table-striped table-bordered nowrap"
    style="width: 100%">
      <!--表格头部 -->
      <thead>
        <tr>
          <th>序号</th>
          <th>用户名称</th>
          <th>登录密码</th>
          <th>用户身份</th>
        </tr>
      </thead>
      <!-- 表格尾部 -->
      <tfoot>
        <tr>
          <th>序号</th>
          <th>用户名称</th>
          <th>登录密码</th>
          <th>用户身份</th>
        </tr>
      </tfoot>
    </table>
</body>
</html>

<script type="text/javascript">
  //返回SpringMVC能解析的JSON数据格式
  function produceMVCJsonData(data){
    var jsonStr = '{'
        + "\"name\":" + "\"" + data.name + "\""
        + ",\"password\":" + "\"" + data.password + "\""
        + ",\"privilege\":" + "\"" + data.privilege + "\""
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
          url:"<c:url value='/user/insertUser.action'/>",
          data:function(data){
            var result;
          
            for(var i in data.data){
              result = data.data[i];
            }
            return produceMVCJsonData(result);
          }
        },
        edit:{
          url:"<c:url value='/user/updateUser.action'/>",
          data:function(data){
            var result;
            
            for(var i in data.data){
              result = data.data[i];
            }

            return produceMVCJsonData(result);
          },
        },  
        remove:{
          url:"<c:url value='/user/removeUserByName.action'/>",
          data:function(data){
            var result;
            
            for(var i in data.data){
              result = data.data[i];
            }
            return result;
          }
        },
        error:{
            system:'操作失败'
        }
      },
      table:"#user",  
      //editor要求DataTables的每一行都必须指定一个ID，以便于告诉服务器此时修改、删除的是哪一行数据
      idSrc:'name',
      fields:[{label:'用户名称',name:'name'},
        {label:'登录密码',name:'password'},
        {label:'用户身份',name:'privilege',type:"select",options:[
            {label:'普通用户',value:'1'},
            {label:'管理员',value:'0'}
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
            _:"确定删除%条用户信息吗？",
            1:'确定删除该用户信息吗？'
          }
        },
        error:{
          "system":"操作失败..."
        }
      }
    });
    //不准用户修改用户名（用户名是主键）
    editor.on("initEdit",function(){
    	editor.field("name").disable();
    });

    //加载DataTables
      var table = $('#user').DataTable( {
          //"sPaginationType": "full_numbers",//翻页按钮显示为first、pre、next、last
          //将dataTables中的英文替换为中文
          "oLanguage": {
              'sUrl':'language/zh_CN.txt'
          },
          "ajax":{
        	  "url":"<c:url value='/user/findAllUsers.action'/>",
              "type":'POST',
              "dataSrc":""
          },
          columns: [//数据对应项
              { data: 'index', defaultContent: '' },
              { data: "name" },
              { data: "password" },
              { data: "privilege" ,render:function(data){
                  return data == 0 ? "管理员" : "普通用户";
                }
              }
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
            {extend:'remove',editor:editor,text:'删除'}
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