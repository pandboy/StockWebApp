<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script type="text/javascript" src="js/page.js"></script>
</head>
<body>
<form name="PageForm" action="searchStockAction.action">
<input type="hidden" name="searchkeyword" value=<s:property value="#request.searchkeyword"/>>
<input type="hidden" name="jumpPage"/>
<input type="hidden" name="searchtype" value=<s:property value="#request.searchtype"/>>
<s:if test="#request.pageControl.maxPage>1">
	
	<s:if test="#request.pageControl.curPage>1">
		<a href="javascript:gotoPage(<s:property value='#request.pageControl.curPage-1'/>)">上一页</a>
	</s:if>
	
	<s:if test="#request.pageControl.maxPage<11">
	</s:if>
	<span>当前第<s:property value="#request.pageControl.curPage"/>页</span>
	<s:if test="#request.pageControl.curPage<#request.pageControl.maxPage">
	<!--<s:url id="next_url" action="searchStockAction.action?type=get" >
		<s:param name="searchkeyword" value="#session.searchkeyword"/>
		<s:param name="jumpPage" value="#request.pageControl.curPage+1"></s:param>
	</s:url>
	<s:a href="%{next_url}">下一页</s:a>
	-->
	<a href="javascript:gotoPage(<s:property value='#request.pageControl.curPage+1'/>)">下一页</a>
	</s:if>
	
	<span class="nums" style="margin-left: 120px">找到相关结果<s:property value="#request.pageControl.totalHits"/>个</span>
</s:if>
</form>
</body>
</html>