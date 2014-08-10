/*
 * 分页显示标签类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00    2014.08.10  gaoyj           程序·发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2014 cms System. - All Rights Reserved.
 *
 */
package cn.com.cms.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.com.cms.common.CommonConstant;

/**
 * <p>分页显示标签</p>
 * @author gaoyj
 *
 */
public class PagingTag extends TagSupport {
    /** 序列号 */
    private static final long serialVersionUID = 7313107737668941157L;

    /** 表单名 */
    private String form;
    /** 请求地址 */
    private String url;
    /** 事件名 */
    private String event;
    /** 提交方式 */
    private String type = "post";


    /**
     * 输出分页显示模型。
     * @return 跳转类型
     * @throws JspTagException JSP异常
     */
    public int doStartTag() throws JspTagException {
    	
    	 String HTML_3DOT_STRING = "<a class=\"dot\">...</a>";
    	
         HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
         String offsetName = CommonConstant.PAGEROW_OFFSET_NAME;
	
	     PageInfo pageInfo = (PageInfo)request.getAttribute(CommonConstant.PAGEROW_OBJECT_KEY);
	     /** 当前页码 */
	     int currentPageNo = pageInfo.getCurrentPageNo();
	     /** 总页数 */
	     int pageCount = pageInfo.getPageCount();
	     /** 总纪录数 */
	     int recordCount = pageInfo.getRecordCount();
	     /** 每页显示的纪录数 */
	     int pageRowCount = pageInfo.getPageRowCount();
	     /** 是否存在后一条记录 */
	     boolean hasNext = pageInfo.hasNext();
	     /** 是否存在前一条记录 */
	     boolean hasPrevious = pageInfo.hasPrevious();
	     /** 画面显示的页码数量 */
	     int pageNoCount = CommonConstant.PAGEROW_CURR_NENT_COUNT;	 
	     /** 当前偏移量 */
	     int currOffset = pageInfo.getCurrOffset();
	
	
	     // CSS代码
	     StringBuffer cssStr = new StringBuffer();
	     cssStr.append("<style type=\"text/css\">");
	     cssStr.append(".b_page{ width:738px; height:30px; display:block; clear:both;}");
	     cssStr.append(".b_page { width:auto; height:30px; line-height:30px; color:#5B5B5B; margin:7px 0 0 20px; float:right;}");
	     cssStr.append(".b_page a { width:auto; height:20px; line-height:22px; border:1px solid #cdcdcb; float:left; margin-right:4px; padding:0 8px; }");
	     cssStr.append(".b_page a.dot{ border:none;padding:0 1px}.b_page a.dot:hover{border:none; cursor:text; background:none;color:#5B5B5B;}");
	     cssStr.append(".b_page a:hover { background:#739a2c; border:1px solid #4b6f0a; cursor:pointer; color:#FFF; }");
	     cssStr.append(".b_page a.on { background:#739a2c; border:1px solid #4b6f0a; cursor:pointer; color:#FFF; }");
	     cssStr.append(".b_page a, .b_page a:visited { color:#5B5B5B; font-size:12px; display:block;}");
	     cssStr.append(".b_page a:hover,.b_page li:hover a{ color:#fff; display:block;}");
	     cssStr.append(".b_page a.disabled { background:#FFF;  border:1px solid #cdcdcb; color:#5B5B5B; cursor:text; }");
	     cssStr.append(".clear{clear:both;display:block;overflow:hidden;visibility:hidden;width:0px;height:0px;}");
	     cssStr.append("</style>");
	
	     // 上一页
	     StringBuffer prePage = new StringBuffer();
	     if (hasPrevious) {
	         prePage.append("<a href=\"javascript:doSkipPage("+ (currentPageNo-2)*pageRowCount +")\">");
	         prePage.append("上一页");
	         prePage.append("</a>");
	     } else {
	         prePage.append("<a class=\"disabled\">上一页</a>");
	     }
	
	
	     // 下一页
	     StringBuffer nextPage = new StringBuffer();
	     if (hasNext) {
	         nextPage.append("<a href=\"javascript:doSkipPage("+ currentPageNo*pageRowCount +")\">");
	         nextPage.append("下一页");
	         nextPage.append("</a>");
	     } else {
	         nextPage.append("<a class=\"disabled\">下一页</a>");
	     }
	
	
	     // 中间页
	     StringBuffer midPage = new StringBuffer();
	
	     // 只有一页数据
	     if (pageCount == 1) {
	     	midPage.append("<a class=\"on\">1</a>");
	
	     // 总页数<=显示页数
	     } else if (pageCount > 1 && pageCount <= pageNoCount) {
	         for (int i = 1; i <= pageCount; i++) {
	         	if (currentPageNo == i) {
	         		midPage.append("<a class=\"on\">"+ i +"</a>");
	         	} else {
	         		midPage.append("<a href=\"javascript:doSkipPage("+ (i - 1)*pageRowCount +")\">");
	         		midPage.append(i);
	         		midPage.append("</a>");
	         	}
	         }
	         
	     // 总页数>显示页数
	     } else if (pageCount > pageNoCount) {
	
	     	// 当前页数在起始显示页数内
	     	if (currentPageNo <= pageNoCount) {
	         	for (int i = 1; i <= pageCount; i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:doSkipPage("+ (i - 1)*pageRowCount +")\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         		
	         		if (i == pageNoCount) {
	         			// 加上...
	         			midPage.append(HTML_3DOT_STRING);
	         			// 加上末页数字
	         			midPage.append("<a href=\"javascript:doSkipPage("+ (pageCount - 1)*pageRowCount +")\">");
	         			midPage.append(pageCount);
	         			midPage.append("</a>");
	         			break;
	         		}
	         	}
	         
	         // 当前页数在末页显示页数内
	     	} else if (currentPageNo > (pageCount - pageNoCount)) {
	     	
	         	// 加上首页数字
	         	midPage.append("<a href=\"javascript:doSkipPage(0)\">");
	         	midPage.append(1);
	         	midPage.append("</a>");
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	
	         	for (int i = (pageCount - pageNoCount + 1); i <= pageCount; i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:doSkipPage("+ (i - 1)*pageRowCount +")\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         	}
	     	} else {
	     	    // 加上首页数字
	         	midPage.append("<a href=\"javascript:doSkipPage(0)\">");
	         	midPage.append(1);
	         	midPage.append("</a>");
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	         	for (int i = currentPageNo; i < (currentPageNo + pageNoCount); i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:doSkipPage("+ (i - 1)*pageRowCount +")\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         	}
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	         	// 加上末页数字
	         	midPage.append("<a href=\"javascript:doSkipPage("+ (pageCount - 1)*pageRowCount +")\">");
	         	midPage.append(pageCount);
	         	midPage.append("</a>");
	     	}
	     // 没有数据
	     } else {
	     	midPage.append("");
	     }
	
	
	     //log.info(pageRowCount);
	     //log.info(pageNoCount);
	     //log.info(selectPageNo);
	     //log.info(prePage);
	     //log.info(nextPage);
	     //log.info(midPage);
	
	
	     // 其他输出项
	     StringBuffer other = new StringBuffer();
	     other.append("<input type=\"hidden\" name=\""+ offsetName +"\" id=\""+ offsetName +"\" />");
	     other.append("<input type=\"hidden\" name=\"currOffset\" id=\"currOffset\" value=\""+ currOffset +"\" />");
	
	     // 汇总输出
	     StringBuffer pageCom = new StringBuffer();
	     pageCom.append(cssStr);
	     pageCom.append("<div class=\"b_page\">");
	     pageCom.append(prePage);
	     pageCom.append(midPage);
	     pageCom.append(nextPage);
	     pageCom.append("<a class=\"disabled\">共"+ recordCount +"条</a>");
	     pageCom.append("<div class=\"clear\"></div>");
	     pageCom.append("</div>");
	     pageCom.append(other);
	
	     // 输出javascript函数
	     StringBuffer javascriptStr = new StringBuffer();
	     javascriptStr.append("<script language=\"javascript\">");
	     if ("post".equals(this.type)) {
		     javascriptStr.append("function doSkipPage(offset){");
		     javascriptStr.append("	document."+ form +"."+ offsetName +".value = offset;");
		     javascriptStr.append("	document."+ form +".action = \""+ url+"_"+event +".action?"+"\";");
		     javascriptStr.append("    document."+ form +".submit();");
		     javascriptStr.append("}");
	     } else {
		     javascriptStr.append("function doSkipPage(offset){");
		     javascriptStr.append("	document."+ form +"."+ offsetName +".value = offset;");
		     javascriptStr.append("	document."+ form +".action = \""+ url+"_"+event +".action?"+"\";");
		     javascriptStr.append("    document."+ form +".submit();");
		     javascriptStr.append("}");
	     }
	     javascriptStr.append("</script>");
	     pageCom.append(javascriptStr);  
	     
        try {
            JspWriter out = super.pageContext.getOut();
            out.print(pageCom);
        } catch (IOException ioe) {
            throw new JspTagException(ioe);
        }

        return EVAL_PAGE;
    }



	/**
	 * 表单名取得
	 * @return 表单名
	 */
	public String getForm() {
	    return form;
	}



	/**
	 * 表单名设定
	 * @param form 表单名
	 */
	public void setForm(String form) {
	    this.form = form;
	}



	/**
	 * 请求地址取得
	 * @return 请求地址
	 */
	public String getUrl() {
	    return url;
	}



	/**
	 * 请求地址设定
	 * @param url 请求地址
	 */
	public void setUrl(String url) {
	    this.url = url;
	}



	/**
	 * 事件名取得
	 * @return 事件名
	 */
	public String getEvent() {
	    return event;
	}



	/**
	 * 事件名设定
	 * @param event 事件名
	 */
	public void setEvent(String event) {
	    this.event = event;
	}



	/**
	 * 提交方式取得
	 * @return 提交方式
	 */
	public String getType() {
	    return type;
	}



	/**
	 * 提交方式设定
	 * @param type 提交方式
	 */
	public void setType(String type) {
	    this.type = type;
	}
}
