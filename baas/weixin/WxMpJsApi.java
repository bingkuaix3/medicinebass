package weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.bean.WxJsapiPrepayOrder;
import me.chanjar.weixin.common.bean.WxJsapiUnifiedOrder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.http.SimpleXmlPostRequestExecutor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;

import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;

/**
 * 
 * @author 007slm
 * @email 007slm@163.com
 * 
 */
public class WxMpJsApi {

	static WxMpServiceInstance instance = WxMpServiceInstance.getInstance();

	public static JSONObject service(JSONObject params, ActionContext context) throws IOException {
		HttpServletRequest req = (HttpServletRequest) context.get(ActionContext.REQUEST);
		HttpServletResponse resp = (HttpServletResponse) context.get(ActionContext.RESPONSE);
		String action = req.getParameter("action");
		if ("getTicket".equals(action)) {
			getTicket(req, resp);
		} else if ("chooseWXPay".equals(action)) {
			chooseWXPay(req, resp);
		}
		return null;
	}

	private static void chooseWXPay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		WxJsapiPrepayOrder prepayOrder = getPrepayOrder(req);
		Map<String, String> payReq = new HashMap<String, String>();
		payReq.put("appId", prepayOrder.getAppId());
		payReq.put("timeStamp", "" + (System.currentTimeMillis() / 1000));
		payReq.put("nonceStr", prepayOrder.getNonceStr());
		payReq.put("package", "prepay_id=" + prepayOrder.getPrepayId());
		System.out.println("appId===="+prepayOrder.getAppId());
		System.out.println(("timeStamp===="+System.currentTimeMillis() / 1000));
		System.out.println("nonceStr===="+prepayOrder.getNonceStr());
		System.out.println("package===="+prepayOrder.getPrepayId());
		payReq.put("signType", "MD5");
		String paySign = WxMpCryptUtil.genWithAmple(payReq, instance.getWxMpConfigStorage().getAppKey());
		payReq.remove("appId");
		payReq.put("paySign", paySign);

		String payReqResult = WxGsonBuilder.create().toJson(payReq, HashMap.class);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(payReqResult);
	}

	private static WxJsapiPrepayOrder getPrepayOrder(HttpServletRequest req) {
		WxJsapiUnifiedOrder unifiedOrder = new WxJsapiUnifiedOrder();
		WxMpOAuth2AccessToken oauth2AccessToken = (WxMpOAuth2AccessToken) req.getSession().getAttribute("weixinOauth2AccessToken");
		if (oauth2AccessToken == null) {
			throw new RuntimeException("请先通过微信Oauth2对x5外面授权,然后才能发起支付");
		}
		unifiedOrder.setOpenId(oauth2AccessToken.getOpenId());
		unifiedOrder.setAppId(instance.getWxMpConfigStorage().getAppId());
		unifiedOrder.setBody(req.getParameter("body"));
		unifiedOrder.setMchId(req.getParameter("mchId"));
		unifiedOrder.setNotifyUrl(req.getParameter("notifyUrl"));
		unifiedOrder.setOutTradeNo(req.getParameter("outTradeNo"));
		unifiedOrder.setSpbillCreateIp(req.getRemoteAddr());
		unifiedOrder.setTotalFee(req.getParameter("totalFee"));
		try {
			/* WxJsapiPrepayOrder prepayOrder =
			 instance.getWxMpService().getPrePayOrder(unifiedOrder);*/
			String str1 = RandomUtils.getRandomStr();
			unifiedOrder.setNonceStr(str1);
			System.out.println("instance.getWxMpConfigStorage().getAppKey():" + instance.getWxMpConfigStorage().getAppKey());
			WxMpCryptUtil.addJsapiPaySign(unifiedOrder, instance.getWxMpConfigStorage().getAppKey());
			System.out.println("-------unifiedOrder--->" + unifiedOrder);
			String strxml = unifiedOrder.toXml();
			System.out.println("unifiedOrder.toXml()--->" + strxml);
			strxml=strxml.replace("__", "_");
			System.out.println("unifiedOrder.toXml()--->" + strxml);
			String str2 = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String str3 = (String) instance.getWxMpService().execute(new SimpleXmlPostRequestExecutor(), str2, strxml);
			System.out.println("str3--->" + str3);
			WxJsapiPrepayOrder prepayOrder = WxJsapiPrepayOrder.fromXml(str3);
			String prepayOrderStr = prepayOrder.toXml();
			System.out.println("prepayOrderStr--->" + prepayOrderStr);
			return prepayOrder;
		} catch (WxErrorException e) {
			throw new RuntimeException(e);
		}
	}

	private static void getTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String jsapiTicket = instance.getWxMpService().getJsapiTicket();
			resp.getWriter().write(jsapiTicket);
		} catch (WxErrorException e) {
			throw new RuntimeException(e);
		}
	}
}
