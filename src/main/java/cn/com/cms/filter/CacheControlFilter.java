/**
 * 这里主要提供了js，css缓存
 * VERSION          DATE                 BY              REASON
 * -------- -------------------  ---------------------- ------------------------------------------
 * 1.00           2014-08-10            gaoyj            程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2014 cms  System. - All Rights Reserved.
 */
package cn.com.cms.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * 这里主要提供了js，css缓存
 * @author wuxiaogang 
 *
 */
public class CacheControlFilter implements Filter {
	static Logger log = Logger.getLogger(CacheControlFilter.class);
	private String[][] replyHeaders = { {} };

	public void init(FilterConfig config) {
		Enumeration<?> names = config.getInitParameterNames();
		ArrayList<String[]> tmp = new ArrayList<String[]>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = config.getInitParameter(name);
			log.debug("Adding header name: " + name + "='" + value + "'");
			String[] pair = { name, value };
			tmp.add(pair);
		}
		replyHeaders = new String[tmp.size()][2];
		tmp.toArray(replyHeaders);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Apply the headers
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		for (int n = 0; n < replyHeaders.length; n++) {
			String name = replyHeaders[n][0];
			String value = replyHeaders[n][1];
			
			if("Expires".equals(name)){
				long relExpiresInMillis = System.currentTimeMillis() + Long.parseLong(value);
				value= getGMTTimeString(relExpiresInMillis);
			}
			
			httpResponse.setHeader(name, value);
		}
		//--缓存3*24小时---
		
		chain.doFilter(request, response);
	}

	public static String getGMTTimeString(long milliSeconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'");
		return sdf.format(new Date(milliSeconds));
	}

	public void destroy() {
	}
}
