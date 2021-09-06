package com.hh.core.business.lyrlzyw.ca.util;

/**
 * 常量类
 * 
 * @author liuzhipeng
 * 
 */
public interface Constants {

	/**
	 * 后端相关常量
	 * 
	 * @author liuzhipeng
	 * 
	 */
	public interface AdminConstants {
		/**
		 * 消息key
		 */
		public String MESSAGE = "message";

		/**
		 * 错误key
		 */
		public String ERROR = "error";

		/**
		 * 编码
		 */
		public String ENCODING = "UTF-8";

		/**
		 * 系统路径
		 */
		public String CONTEXT_PATH = "ctx";

		/**
		 * 系统名称
		 */
		public String SERVER_NAME = "server_name";

		/**
		 * 当前请求的地址 带参数
		 */
		public String CURRENT_URL = "currentURL";

		/**
		 * 上个页面地址
		 */
		public String BACK_URL = "backURL";

		/**
		 * 新增方法
		 */
		public String METHOD_ADD = "add";

		/**
		 * 修改方法
		 */
		public String METHOD_EDIT = "edit";

		/**
		 * 查看方法
		 */
		public String METHOD_VIEW = "view";

		/**
		 * 编码字典列表缓存名称
		 */
		String SYSCODELISTCACHE = "syscodeListCache";
		/**
		 * 编码字典Map缓存名称
		 */
		String SYSCODEMAPCACHE = "syscodeMapCache";

		/**
		 * 当前用户信息对应的Cache Name
		 */
		String CURRENTUSER_CACHENAME = "sso_currentuser_data";

		/**
		 * CA登录的token 缓存
		 */
		String CA_TOKEN_CACHENAME = "loginToken";

		/**
		 * 当前用户
		 */
		String CURRENTUSER = "currentUser";
		
		/**
		 * 网厅用户认证项目地址
		 */
		public String AUTH_URL = "auth_url";
		
		/**
		 * 网厅门户项目地址
		 */
		public String PORTAL_URL = "portal_url";
		
		/**
		 * 网厅单位项目地址
		 */
		public String COMPANY_URL = "company_url";
		
		/**
		 * 网厅个人项目地址
		 */
		public String PERSON_URL = "person_url";
	}

	/**
	 * 账户类别
	 * 
	 * @author liuzhipeng
	 * 
	 */
	public interface AuthUserType {
		/**
		 * 个人
		 */
		public final static String PERSON = "person";

		/**
		 * 企业
		 */
		public final static String COMPANY = "company";

		/**
		 * 管理后台
		 */
		public final static String ADMIN = "admin";

	}

	/**
	 * 角色类型
	 * 
	 */
	public interface ROLETYPE {
		/**
		 * 网站角色
		 */
		public final static String WZJS = "001";
		/**
		 * 后台角色
		 */
		public final static String HTJS = "002";
	}

	/**
	 * 功能菜单类型
	 * 
	 * @author liuzhipeng
	 * 
	 */
	public interface FUNCTIONTYPE {
		/**
		 * 后台管理员
		 */
		public final static String HTGLY = "htgly";

		/**
		 * 个人用户
		 */
		public final static String GRYH = "gryh";
	}
	
	/**
	 * 统一操作服务参数
	 * @author zhucq
	 *
	 */
	public interface Tyczfw{
		
		/**
		 * 普通查询服务（返回分页结果）
		 */
		public final static String FYCX = "1";
		/**
		 * 查询全部内容
		 */
		public final static String ALLCX = "4";
		
		/**
		 * 普通保存服务（保存单条sql）
		 */
		public final static String DTBC = "1";
		/**
		 * 批量保存服务（保存多条sql）
		 */
		public final static String PLBC = "2";
		
		/**
		 * 保存上传的图片
		 */
		public final static String BCTP = "1";
		/**
		 * 修改上传的图片
		 */
		public final static String XGTP = "2";
		
		/**
		 * 图片解密
		 */
		public final static String PICDE = "0";
		
		/**
		 * 图片加密
		 */
		public final static String PICEN = "1";
		
		/**
		 * 保存
		 */
		public final static String SAVE="001";
		
		/**
		 * 批量更新
		 */
		public final static String SAVEBATCH="002";

	}

	public interface CAConfigUrl{
		public static final String LY_CA_SERVER_URL = PropertyConfigUtil.getProperty("ca.server.ly.url");
		public static final String JYT_ROOT_URL = PropertyConfigUtil.getProperty("jyt.root.url");
		public static final String JYT_SUPPORT_URL = PropertyConfigUtil.getProperty("jyt.support.url");
//		public static final String OPERATIONAL_SUPPORT_URL = PropertyConfigUtil.getProperty("operational.support.url");
		public static final String OPERATIONAL_SUPPORT_URL = "http://support.edtsoft.com/";
		public static final String EDT_TOKEN_URL = PropertyConfigUtil.getProperty("lyggfwwt.token.url");
	}

	public interface LOGIN_TYPE{
		public static final String PERSON = "person";
		public static final String ORG = "org";
	}
}