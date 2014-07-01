package com.stock.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		SearchStockAction ss = (SearchStockAction) invocation.getAction();
		System.out.println("À¹½ØÆ÷¿ªÊ¼¡£¡£¡£");
		String searchtype = ss.getSearchtype();
		String searchkeyword = ss.getSearchkeyword();
		if("".equals(searchtype)||"".equals(searchkeyword))
		{
			
		}
		System.out.println("À¹½ØÆ÷½áÊø¡£¡£¡£");
		return null;
	}

}
