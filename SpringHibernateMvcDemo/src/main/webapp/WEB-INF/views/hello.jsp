<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body onload="load();">
         <input type="hidden" id="user_id" value="0">
         Name: <input type="text" id="name" required="required" name="user_name"></br>
         Email: <input type="email" id="email" required="required" name="email"></br>
         <button onclick="submit();">Submit</button>
         
 <h1>Users List</h1>  
<table id="table" border="2" width="70%" cellpadding="2">  
<tr><th>ID</th><th>Name</th><th>Email</th><th>Edit</th><th>Delete</th></tr>

</table>


<script type="text/javascript">
data="";
submit=function(){

	$.ajax({
		url:'saveOrUpdate',
		type:'POST',
		data:{user_id:$("#user_id").val(), user_name:$("#name").val(),email:$("#email").val()},
		success: function(response){
			
			alert(response.message);
			load();
			clear();
		}
	});
			
}
// ###Author:- Sujeet
clear=function(){
	
	$("#user_id").val("0");
	$("#name").val("");	
	$("#email").val("");	
	
}

delete_=function(id){
	//alert("ID: "+id);
	$.ajax({
		url:'delete',
		type:'POST',
		data:{user_id:id},
		success: function(response){
			
			alert(response.message);
			load();
		}
	});
			
}

edit = function (index){

	//alert(index+" Data: "+data[index].id);
	
	$("#user_id").val(data[index].user_id);
	$("#name").val(data[index].user_name);	
	$("#email").val(data[index].email);
}

load=function(){
	$.ajax({
		url:'list',
		type:'POST',
		success: function(response){
			data = response.data;
			$('.tr').remove();
			for(i=0;i<response.data.length;i++){
				$("#table").append("<tr class='tr'><td> "+response.data[i].user_id+"</td><td> "+response.data[i].user_name+"</td><td>"+response.data[i].email+"</td><td><a href='#' onclick='edit("+i+");'>Edit</a></td><td><a href='#' onclick='delete_("+response.data[i].user_id+");'>Delete</a></td></tr>");
			}
			
		}
	});
}

</script>
</body>
</html>