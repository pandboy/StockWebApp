<%@ page language="java" import="java.util.*" pageEncoding="gb2312"
	buffer="none"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><s:property value="#request.searchkeyword" />的搜索结果</title>

		<STYLE>
BODY {
	MARGIN: 6px 0px 0px;
	COLOR: #000;
	BACKGROUND-COLOR: #fff;
	FONT-FAMILY: arial
}

TABLE {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	BORDER-LEFT: 0px;
	BORDER-BOTTOM: 0px
}

TD {
	FONT-SIZE: 11pt;
	LINE-HEIGHT: 18px;
	FONT-FAMILY: arial;
	text-align: center
}

.p {
	PADDING-LEFT: 18px;
	FONT-SIZE: 14px;
	WORD-SPACING: 4px
}

#ft {
	CLEAR: both;
	BACKGROUND: #e6e6e6;
	LINE-HEIGHT: 20px;
	TEXT-ALIGN: center;
	FONT-SIZE: 12px;
	COLOR: #77c;
	FONT-FAMILY: Arial
}

.result {
	width: 33.7em;
	table-layout: fixed;
}
</STYLE>

	</head>

	<body>
	<%--<s:form action="#">
		<s:textfield name="searchkeyword" ></s:textfield>
		<s:submit value="搜索"></s:submit>
	</s:form>
	--%><s:property value="#request.pageControl.maxPage"/>
		<s:if test="#request.pageControl.maxPage>0">
			<form action="searchStockAction" method="post" id="pageFrom">
				<table cellpadding="0" cellspacing="0" class="result">
					<tr bgcolor="#72963D">
						<td align="center" class="colsTitle" nowrap height="19">
							查询结果
						</td>
						<td>每页<s:property value="#pageControl.rowPage"/></td>
						<td></td>
					</tr>

					<s:iterator value="#session.stockList">
						<tr>
							<td>
								<tbody>
									<table>
										<tr bgcolor="#F1F0ED">
											<td align="left" nowrap height="19">
												<h3>
													<s:property value="title" />
												</h3>
											</td>
										</tr>
										<tr>
											<td>
												<s:property value="stockContent" />
											</td>
										</tr>
									</table>
								</tbody>
							</td>

						</tr>
						<tr bgcolor="#fff">
						</tr>
					</s:iterator>

				</table>

			</form>
		</s:if>

	</body>

</html>
