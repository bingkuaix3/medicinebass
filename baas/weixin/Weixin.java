package weixin;

import javax.naming.NamingException;

import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;
import com.justep.baas.data.sql.SQLException;

public class Weixin {
	static WxMpServiceInstance instance = WxMpServiceInstance.getInstance();

	public static JSONObject wx(JSONObject params, ActionContext context) throws SQLException, NamingException {
		String id = params.getString("id");
		System.out.println(id);
		instance.downloadimg(id);
		return null;
	}

	public static JSONObject ts(JSONObject params, ActionContext context) throws SQLException, NamingException {
		String id = params.getString("id");
		String doctors_recommend = params.getString("doctors_recommend");
		String orderid = params.getString("orderid");
		String logisticstelephone = params.getString("logisticstelephone");
		String logistics = params.getString("logistics");
		String logisticsnumber = params.getString("logisticsnumber");
		if (doctors_recommend.length() == 0) {
			doctors_recommend = "谢谢您的支持";
		}
		String transportprice = params.getString("transportprice");
		String medprice = params.getString("medprice");
		System.out.println(id);
		System.out.println(doctors_recommend);
		System.out.println(orderid);
		System.out.println(logisticstelephone);
		System.out.println(logistics);
		System.out.println(logisticsnumber);
		System.out.println(medprice);
		System.out.println(transportprice);
		instance.customMessageSend(doctors_recommend, medprice, transportprice, id, orderid, logisticstelephone, logistics, logisticsnumber);

		return null;
	}
}
