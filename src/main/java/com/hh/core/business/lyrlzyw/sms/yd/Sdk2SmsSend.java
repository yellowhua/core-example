package com.hh.core.business.lyrlzyw.sms.yd;

import java.util.List;

import com.mascloud.model.MoModel;
import com.mascloud.model.StatusReportModel;
import com.mascloud.sdkclient.Client;
import com.mascloud.util.JsonUtil;


/**
 * 移动短信sdk调用（sdk调用需要引入移动的sdk包，sdk包中集成并重写了很多第三方包比如spring、日志等等，会跟我们自己项目的依赖包冲突，导致项目启动不起来，因此不推荐使用）
 *
 * @author huanghua
 * @date 2020/08/13
 */
public class Sdk2SmsSend {

	public static void main( String[] args ) {
		Client client = Client.getInstance( );
		// 登录地址需另外提供
		boolean isLoggedin = client.login( "http://112.35.4.197:15000", "rlzycs", "rlzycs350800", "龙岩市社会保障卡服务中心" );
		if( isLoggedin ) {
			System.out.println( "Login successed" );
		} else {
			System.out.println( "Login failed" );
			return;
		}

		// 普通短信
		int rt = client.sendDSMS( new String[]{ "18005976692" }, "最新消息！根据您发布的求职意向，系统匹配到3家企业发布的岗位符合您，可前往龙岩人力资源网超市系统至“简历管理-简历管理-岗位匹配”进行查看。", "123", 1, "HyOXuWMDC", null, true );
		System.out.println( rt );

		// 模板短信
		/*int rtm = client.sendTSMS( new String[]{ "18965082080" }, "c81c8ced4e0844a797e84858e5fdaf92",
				new String[]{ "123456"}, "123", 0, "bM16CfN2B", null );
		System.out.println( rtm );*/

		// 获取状态报告——开始
		List<StatusReportModel> statusReportlist = client.getReport( );
		System.out.println( "getReport : " + JsonUtil.toJsonString( statusReportlist ) );
		// 获取状态报告——结束

		// 获取上行短信——开始
		List<MoModel> deliverList = client.getMO( );
		System.out.println( "getMO : " + JsonUtil.toJsonString( deliverList ) );
		// 获取上行短信——结束

	}
}
