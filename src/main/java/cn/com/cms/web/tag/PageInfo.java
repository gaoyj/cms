/*
 * 分页显示用分页信息类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00    2014.08.10  gaoyj            程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2014 cms System. - All Rights Reserved.
 *
 */
package cn.com.cms.web.tag;

import java.io.Serializable;

import cn.com.cms.common.CommonConstant;

/**
 * <p>分页显示用分页信息</p>
 * @author gaoyj
 *
 */
public class PageInfo implements Serializable {

    /** 序列号 */
    private static final long serialVersionUID = 8378488769777243128L;

    /** 当前页码 */
    private int currentPageNo;

    /** 总页数 */
    private int pageCount;

    /** 总纪录数 */
    private int recordCount;

    /** 当前页第一条数据的索引 */
    private int currPageFirstIndex;

    /** 当前页最后一条数据的索引 */
    private int currPageLastIndex;

    /** 每页显示的纪录数 */
    private int pageRowCount;

    /** 是否存在后一条记录 */
    private boolean hasNext;

    /** 是否存在前一条记录 */
    private boolean hasPrevious;
    
    /** 当前偏移量 */
    private int currOffset;
    

	/**
     * <p>重新初始化当前页。</p>
     */
    public void initCurrPage() {
        if (getCurrentPageNo() <= 1) {
            currentPageNo = 1;
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }

        if (currentPageNo >= pageCount) {
            currentPageNo = pageCount;
            hasNext = false;
        } else {
            hasNext = true;
        }
//        System.out.println("----------xxxxxxx---------"+currentPageNo);
        // 计算当前页首条数据的索引。（和数据库记录集同步）
        currPageFirstIndex = (currentPageNo - 1) * pageRowCount;
        // 计算当前页尾条数据的索引。（和数据库记录集同步）
        int tmpCurrPageLastIndex = currPageFirstIndex + pageRowCount - 1;
        if (tmpCurrPageLastIndex < recordCount) {
            currPageLastIndex = tmpCurrPageLastIndex;
        } else {
            currPageLastIndex = recordCount - 1;
        }
    }

    /**
     * <p>初始化总页数。</p>
     * @param recordCount  总记录数
     * @param pageRowCount 每页记录数
     */
    public void initPageCount(int recordCount, int pageRowCount) {
        this.recordCount = recordCount;
        this.pageRowCount = pageRowCount;

        if (recordCount > 0 &&pageRowCount!=0) {
            int mod = recordCount % pageRowCount;
	            if (mod == 0) {
	                pageCount = recordCount / pageRowCount;
	            } else {
	                pageCount = (recordCount / pageRowCount) + 1;
	            }
        }else{
        	pageCount=0;
        }
    }

    public PageInfo() {}
    
    /**
     * Constructor for PageInfo.
     *
     * @param recordCount
     *            总纪录数.
     */
    public PageInfo(int recordCount) {
        initPageCount(recordCount, CommonConstant.PAGEROW_DEFAULT_COUNT);
        initCurrPage();
    }
    
    /**
     * Constructor for PageInfo.
     *
     * @param recordCount
     *            总纪录数.
     * @param pageRowCount
     *            每页显示的纪录数.
     */
    public PageInfo(int recordCount, int pageRowCount) {
        initPageCount(recordCount, pageRowCount);
        initCurrPage();
    }

    /**
     * Constructor for PageInfo.
     *
     * @param recordCount
     *            总纪录数.
     * @param pageRowCount
     *            每页显示的纪录数.
     */
    public PageInfo(int recordCount, int pageRowCount, int offset) {
        initPageCount(recordCount, pageRowCount);
        this.currentPageNo = offset/pageRowCount + 1;
        initCurrPage();
    }

    /**
     * <p>翻向前一页</p>
     */
    public void pageUp() {
        currentPageNo -= 1;
        initCurrPage();
    }

    /**
     * <p>翻向后一页</p>
     */
    public void pageDown() {
        currentPageNo += 1;
        initCurrPage();
    }

    /**
     * <p><翻向指定页</p>
     * @param currentPageNo
     *            the currentPageNo to set
     */
    public void gotoPage(int currentPageNo) {
        this.currentPageNo = currentPageNo;
        initCurrPage();
    }

    /**
	 * 总纪录数设定
	 * @param recordCount 总纪录数
	 */
	public void setRecordCount(int recordCount) {
	    this.recordCount = recordCount;
	}

	/**
     * @return the hasNext
     */
    public boolean hasNext() {
        return hasNext;
    }

    /**
     * @return the hasPrevious
     */
    public boolean hasPrevious() {
        return hasPrevious;
    }

    /**
	 * 当前页码取得
	 * @return 当前页码
	 */
	public int getCurrentPageNo() {
		if(pageRowCount<=0){
			return 0;
		}
		currentPageNo=currOffset/pageRowCount + 1;
	    return currentPageNo;
	}

	/**
	 * 当前页码设定
	 * @param currentPageNo 当前页码
	 */
	public void setCurrentPageNo(int currentPageNo) {
	    this.currentPageNo = currentPageNo;
	}

	/**
	 * 当前页最后一条数据的索引设定
	 * @param currPageLastIndex 当前页最后一条数据的索引
	 */
	public void setCurrPageLastIndex(int currPageLastIndex) {
	    this.currPageLastIndex = currPageLastIndex;
	}

	/**
	 * 总页数取得
	 * @return 总页数
	 */
	public int getPageCount() {
	    return pageCount;
	}

	/**
	 * 总页数设定
	 * @param pageCount 总页数
	 */
	public void setPageCount(int pageCount) {
	    this.pageCount = pageCount;
	}

	/**
	 * 总纪录数取得
	 * @return 总纪录数
	 */
	public int getRecordCount() {
	    return recordCount;
	}

	/**
	 * 当前页第一条数据的索引取得
	 * @return 当前页第一条数据的索引
	 */
	public int getCurrPageFirstIndex() {
		if(currPageFirstIndex<0){
        	currPageFirstIndex=0;
        }
	    return currPageFirstIndex;
	}

	/**
	 * 当前页第一条数据的索引设定
	 * @param currPageFirstIndex 当前页第一条数据的索引
	 */
	public void setCurrPageFirstIndex(int currPageFirstIndex) {
	    this.currPageFirstIndex = currPageFirstIndex;
	}

	/**
	 * 当前页最后一条数据的索引取得
	 * @return 当前页最后一条数据的索引
	 */
	public int getCurrPageLastIndex() {
		return currPageLastIndex;
	}

	/**
	 * 每页显示的纪录数取得
	 * @return 每页显示的纪录数
	 */
	public int getPageRowCount() {
	    return pageRowCount;
	}

	/**
	 * 每页显示的纪录数设定
	 * @param pageRowCount 每页显示的纪录数
	 */
	public void setPageRowCount(int pageRowCount) {
	    this.pageRowCount = pageRowCount;
	}

	/**
	 * 是否存在后一条记录取得
	 * @return 是否存在后一条记录
	 */
	public boolean isHasNext() {
	    return hasNext;
	}

	/**
	 * 是否存在后一条记录设定
	 * @param hasNext 是否存在后一条记录
	 */
	public void setHasNext(boolean hasNext) {
	    this.hasNext = hasNext;
	}

	/**
	 * 是否存在前一条记录取得
	 * @return 是否存在前一条记录
	 */
	public boolean isHasPrevious() {
	    return hasPrevious;
	}

	/**
	 * 是否存在前一条记录设定
	 * @param hasPrevious 是否存在前一条记录
	 */
	public void setHasPrevious(boolean hasPrevious) {
	    this.hasPrevious = hasPrevious;
	}

	/**
	 * 当前偏移量取得
	 * @return 当前偏移量
	 */
	public int getCurrOffset() {
//		System.out.println("xxxxxxxxxxxxxx"+currentPageNo);
		if (currentPageNo > 0) {
			return currOffset = (currentPageNo-1)*pageRowCount;
		} else {
			return 0;
		}
	}

	/**
	 * 当前偏移量设定
	 * @param currOffset 当前偏移量
	 */
	public void setCurrOffset(int currOffset) {
	    this.currOffset = currOffset;
	}

}
