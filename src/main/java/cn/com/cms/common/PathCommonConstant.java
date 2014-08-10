package cn.com.cms.common;

/**
 * <p>系统使用路径常量</p>
 * @author Huyunlin
 *
 */
public class PathCommonConstant extends CommonConstant {
    
	/** 上传的文件种类：图片 */
	public final static String UPLOAD_CATAGORY_IMAGE = "image";
	/** 上传的文件种类 ：动画*/
	public final static String UPLOAD_CATAGORY_FLASH = "flash";
	/** 上传的文件种类 ：多媒体*/
	public final static String UPLOAD_CATAGORY_MEDIA = "media";
	/** 上传的文件种类 ：普通文件*/
	public final static String UPLOAD_CATAGORY_FILE = "file";
	/** 上传的文件种类 ：模板文件*/
	public final static String UPLOAD_CATAGORY_TEMPLATE = "template";
	
	/** 上传的图片类型 */
	public final static String UPLOAD_TYPE_IMAGE = "gif,jpg,jpeg,png,bmp";
	/** 上传的多媒体类型 */
	public final static String UPLOAD_TYPE_MEDIA = "swf,flv,mp4,mpg,wmv";
	/** 上传的文件类型 */
	public final static String UPLOAD_TYPE_FILE = "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,ftl";
	/** 上传的模板文件类型 */
	public final static String UPLOAD_TYPE_TEMPLATE = "ftl";
	
	/** 上传的最大视频大小 */
	public final static long UPLOAD_VIDEO_MAX_SIZE = 1024 * 1024 * 1024;
	/** 上传的最大图片大小 */
	public final static long UPLOAD_PIC_MAX_SIZE=1024 * 1024;
	
    /**--------------------用到文本编辑器的模块-------------------------*/
	/** 是否由富文本编辑器上传 */
	public final static String IS_RICH_EDITOR = "isrich";
}
