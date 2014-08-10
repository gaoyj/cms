package cn.com.cms.common;



/**
 * <p>系统程序用常量</p>
 * @author wuxiaogang
 *
 */
public class CommonConstant {
	/** 数据库事务默认超时时间 */
	public static final int DB_DEFAULT_TIMEOUT = 300;
	/** 分页对象KEY */
	public static final String PAGEROW_OBJECT_KEY = "PAGEROW_OBJECT_KEY";
	/** 分页偏移量名称 */
	public static final String PAGEROW_OFFSET_NAME = "offset";
	/** 默认画面每页的记录数 */
	public static final int PAGEROW_DEFAULT_COUNT = 15;
	/** 画面显示的页码数量 */
	public static final int PAGEROW_CURR_NENT_COUNT = 15;
	/** SESSION里面存放认证码 */
	public static final String SESSION_VERIFY_CODE = "SESSION_VERIFY_CODE";
	/** SESSION里面存放公共账号信息 */
	public static final String SESSION_WECHAT_BEAN = "SESSION_WECHAT_BEAN_";
	/** SESSION里面存放公共账号信息 flag */
	public static final String SESSION_WECHAT_BEAN_FLAG = "SESSION_WECHAT_BEAN_FLAG_";
	/** SESSION里面存放 用户信息 */
	public static final String SESSION_KEY_USER = "SESSION_KEY_USER";
	
	/** SESSION里面存放系统 用户信息 */
	public static final String SESSION_SYS_KEY_USER = "SESSION_SYS_KEY_USER";
	
	/** SESSION里面存放 用户信息 */
	public static final String SESSION_SYS_KEY_USER_PERMS = "SESSION_SYS_KEY_USER_PERMS";
	
	/** 车主管家SESSION里面存放 用户信息 */
	public static final String SESSION_KEY_CUSER = "SESSION_KEY_CUSER";
	/** 路径分隔符 */
	public static final String PATH_SEPARATOR = "/";
	/** 系统默认编码 */
	public static final String DEFAULT_ENCODE = "UTF-8";
	/** 空字符串 */
	public static final String EMPTY_STRING = "";
	/** 空格 */
	public static final String BLANK_STRING = " ";
	/** 所属地区县 */
	public static final String LOG_ERROR_TITLE = "异常信息";
	
	
	/**缓存时间 车辆   24小时*/
	public static final int VEHICLE_KEEP_TIME = 60*60*24;
	/**缓存时间  保单12小时*/
	public static final int POLICY_KEEP_TIME = 60*60*12;
	/**缓存时间  理赔 30分钟*/
	public static final int CLAIM_KEEP_TIME = 60*60;
	/**缓存时间  违章 24小时*/
	public static final int VIOLATION_KEEP_TIME = 60*60*24;
	
	/**缓存 用户对应的车辆id列表key前缀*/
	public static final String USER_VEHICLE_KEY = "user_vehicle_";
	/**缓存 车辆id对应的车辆信息key前缀*/
	public static final String VEHICLE_KEY = "vehicle_";
	/**缓存 用户对应保单    key前缀(后面加上用户id)*/
	public static final String USER_POLICY_KEY = "user_policy_";
	/**缓存 用户对应历史保单    key前缀(后面加上用户id)*/
	public static final String HISTORY_USER_POLICY_KEY = "history_user_policy_";
	/**缓存 根据用户、车辆id查找保单信息    key前缀(后面加上用户id_车辆id)*/
	public static final String POLICY_USER_VEHICLE_KEY = "policy_user_vehicle_";
	/**缓存 根据保单id查找保单信息    key前缀(后面加上保单id)*/
	public static final String POLICY_KEY = "policy_";
	/**缓存 用户对应理赔  key前缀(后面加上用户id)*/
	public static final String USER_CLAIM_KEY = "user_claim_";
	/**缓存 用户对应历史理赔  key前缀(后面加上用户id)*/
	public static final String HISTORY_USER_CLAIM_KEY = "history_user_claim_";
	/**缓存理赔信息   key前缀( 后面加上用户id_车辆id_保单id)*/
	public static final String CLAIM_USER_VEHICLE_POLICY_KEY = "claim_user_vehicle_policy_";
	/**缓存 根据理赔id查找理赔信息    key前缀(后面加上理赔id)*/
	public static final String CLAIM_KEY = "claim_";
	/**缓存 用户违章key前缀*/
	public static final String VIOLATION_KEY = "user_violation_";
	/** 未处理违章 */
	public static final String UNPECCANCY_KEY = "user_unpeccancy_";
	/** 未处理违章网上查询 */
	public static final String UNPECCANCYONLINE_KEY = "user_unpeccancyonline_";
	/** 已处理违章 */
	public static final String PECCANCYED_KEY = "user_unpeccancyed_";
	
	
	/** 认证信息类型：邮箱 */
	public static final String VERIFY_INFO_TYPE_MAIL = "1";
	/** 认证信息类型：手机 */
	public static final String VERIFY_INFO_TYPE_MOBILE = "2";
	/** 邮箱认证信息有效期限：24小时 */
	public static final int VERIFY_INFO_MAIL_EXPIRATION = 1440;
	/** 手机认证信息有效期限：30分钟 */
	public static final int VERIFY_INFO_MOBILE_EXPIRATION = 30;
	
	/** 绑定手机时的手机号码ID */
	public static final String SESSION_KEY_BIND_MOBILE_ID = "sessionKey_bindMobile";

	
	
	/**保单到期提醒 */
	public static final String POLICY_REMIND_FLAG= "1";
	
	/**违章提醒 */
	public static final String VIOLATION_REMIND_FLAG= "2";


	/** SESSION里面存放 微信粉丝的OPENID信息 */
	public static final String SESSION_KEY_USER_WECHAT_OPENID = "SESSION_KEY_USER_WECHAT_OPENID";

	/**驾照过期提醒 */
	public static final String DRIVING_LICENCE_FLAG= "3";
	
	/**查询车辆列表时传入的固定用户id*/
	//public static   final  String  VEHICLE_LIST_USER_ID="86";//测试环境
	public static   final  String  VEHICLE_LIST_USER_ID="84";//product
	
	/**车辆标题图片*/
	public static   final  String  FILE_VEHICLE_LOGO="btt001";
	/** 存放 微信公共账号的Access_token */
	public static final String SESSION_KEY_USER_WECHAT_ACCESS_TOKEN = "SESSION_KEY_USER_WECHAT_ACCESS_TOKEN";
	/** 案件状态 */
	/** 未绑定手机 不可以实时查看案件状态 */
	public static final String REPORT_NO = "-2";
	/** 出错 */
	public static final String REPORT_ERR = "-1";
	/** 报案*/
	public static final String REPORT_0 = "0";
	/** 定损 */
	public static final String REPORT_1 = "1";
	/** 理赔 */
	public static final String REPORT_2 = "2";
	/** 完结 */
	public static final String REPORT_3 = "3";
	/** 注销 */
	public static final String REPORT_4 = "4";
	/**保单短信提醒*/
	public static final String SMS_TYPE_POLICY="1";
	/**违章短信提醒类型*/
	public static final String SMS_TYPE_VIOLATION="2";
	
	/**车辆年检提醒类型*/
	public static final String SMS_TYPE_VCHECK="3";
	/**保单到期提醒配置 提前天数  30天*/
	public static final int SMS_REMIND_POLICY_DAY=30;
	/**车辆年检提醒*/
	public static final int SMS_REMIND_VCHECK_DAY=30;
	
	/** SESSION里面存放 用户客服登录信息 */
	public static final String SESSION_KEY_USER_CUSTOMER_SERVICE = "SESSION_KEY_USER_CUSTOMER_SERVICE";
}
