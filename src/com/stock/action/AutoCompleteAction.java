package com.stock.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class AutoCompleteAction extends ActionSupport implements ServletRequestAware,
	ServletResponseAware
{
	private HttpServletRequest request;
	 
	 private HttpServletResponse response;
	 private Map map ;
	 
	 private String key;
	 
	 

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	 private String getAutoCompleteInformation(List list){
		  StringBuffer sb = new StringBuffer();
		  sb.append("<response>");
		  for (Iterator it = list.iterator(); it.hasNext();) {
		   String str = (String) it.next();
		   sb.append(createXMLView(str));
		  }
		  sb.append("</response>");
		  return sb.toString();
		 }
		 private List<String> createResults(){
		  Set info = (Set) map.get("autoComplete");
		  List<String> list = null;
		  if(info!=null){
		   list = new ArrayList<String>();
		   for (Iterator it = info.iterator(); it.hasNext();) {
		    String str = (String) it.next();
		    String getStr = str.toUpperCase();
		    String keyword = key.toUpperCase();
		    if(getStr.startsWith(keyword)){
		     list.add(str);
		    }
		   }
		  }
		  return list;
		 }
		 
		 private String createXMLView(String str){
		  StringBuffer sb = new StringBuffer();
		  sb.append("<value>");
		  sb.append(str);
		  sb.append("</value>");
		  return sb.toString();
		 }
		 
		 public String addAutoComplete(){
		  if(map.containsKey("autoComplete")){
		   Set<String> set = null;
		   set = (Set) map.get("autoComplete");
		   set.add(key);
		  } else {
		   Set<String> set = new HashSet<String>();
		   set.add(key);
		   map.put("autoComplete", set);
		  }
		  return SUCCESS;
		 }
		 
		 public void returnAutoComplete(){
		  PrintWriter out = null;
		  try {
		    out = response.getWriter();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  List list = createResults();
		  if(list != null && list.size()>0){
		   response.setContentType("text/xml");
		   response.setHeader("Cache-Control", "no-cache");
		   out.println(getAutoCompleteInformation(list));
		   out.close();
		  } else {
		   response.setStatus(204);
		  }
		 }
		 
		 public String execute(){
		  addAutoComplete();
		  return SUCCESS;
		 }

}
