<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>
<script type="text/javascript">
		$(window).scroll(function() {
		    var dh = $(document).height();
		    var wh = $(window).height();
		    var wt = $(window).scrollTop();
		    
		    if(dh == (wh + wt)) {
		    	console.log("ss");
		    }
		});


	function moreContent(id, cnt) {
		var list_length = $("#"+id+" tr").length-1;
		var name = id+"_btn";
		var calllength = list_length;
		
		$("#startCount").val(calllength);
		$("#viewCount").val(cnt);
		
		$.ajax({
			type: 'post',
			url: '/board/moreListAjax',
			data: {"paging" : calllength},
			dataType: "json",
			success: function(result) {
				var temp;
				for(var i=0; i<result.length; i++) {
					temp += '<tr>';
					temp += '<td>' + result[i].bno + '</td>';
					temp += '<td>' + result[i].title + '</td>';
					temp += '<td>' + result[i].writer + '</td>';
					temp += '<td>' + result[i].regdate + '</td>';
					temp += '<td>' + result[i].viewcnt + '</td>';
					temp += '</tr>';
				}
				
				$("#more_list").append(temp);
			},
			error: function(request, status,error) {
				alert("code = " + request.status + status + error);
			}
		});
	}
</script>

<form id="listForm">
	<input type="hidden" name="viewCount" id="viewCount" value="0">
	<input type="hidden" name="startCount" id="startCount" value="0">
	
	<table id="more_list" class="table table-bordered">
		<tr>
			<th style="width: 10px">BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th style="width:40px">VIEWCNT</th>
		</tr>
		
		<c:forEach items="${list}" var="boardVO">
			<tr>
				<td>${boardVO.bno}</td>
				<td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
				<td>${boardVO.writer}</td>
				<td>${boardVO.regdate}</td>
				<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="more_btn_div">
		<a id="more_btn_a" href="javascript:moreContent('more_list', 5);">더보기(more)</a>
	</div>
</form>
	
<%@ include file="../include/footer.jsp" %>