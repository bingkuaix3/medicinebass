package weixin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import org.apache.commons.lang3.StringUtils;

public class WxMpServiceInstance {
	private WxMpService wxMpService;

	public WxMpService getWxMpService() {
		return wxMpService;
	}

	public void setWxMpService(WxMpService wxMpService) {
		this.wxMpService = wxMpService;
	}

	public WxMpConfigStorage getWxMpConfigStorage() {
		return wxMpConfigStorage;
	}

	public void setWxMpConfigStorage(WxMpConfigStorage wxMpConfigStorage) {
		this.wxMpConfigStorage = wxMpConfigStorage;
	}

	public WxMpMessageRouter getWxMpMessageRouter() {
		return wxMpMessageRouter;
	}

	public void setWxMpMessageRouter(WxMpMessageRouter wxMpMessageRouter) {
		this.wxMpMessageRouter = wxMpMessageRouter;
	}

	private WxMpConfigStorage wxMpConfigStorage;
	private WxMpMessageRouter wxMpMessageRouter;

	private static WxMpServiceInstance instance = null;

	public static WxMpServiceInstance getInstance() {
		if (instance == null) {
			instance = new WxMpServiceInstance();
		}
		return instance;
	}

	public WxMpServiceInstance() {
		try {
			InputStream inputStream = WxMpServiceInstance.class.getResourceAsStream("weixin.config.xml");
			WxMpXMLInMemoryConfigStorage config = WxMpXMLInMemoryConfigStorage.fromXml(inputStream);
			wxMpConfigStorage = config;
			wxMpService = new WxMpServiceImpl();
			wxMpService.setWxMpConfigStorage(config);
			wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
			this.addTestRouter();
			this.addMenuRouter();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}

	// 拦截内容为test的消息
	private void addTestRouter() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
				System.out.println("响应test指令********************");
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("成功收到测试指令").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};
		wxMpMessageRouter.rule().async(false).content("test").handler(handler).end();
	}

	// 创建菜单
	private void buildMenu() {
		List<WxMenuButton> x5Meuns = new ArrayList<WxMenuButton>();

		WxMenuButton indexPage = new WxMenuButton();
		indexPage.setName("起步科技");
		indexPage.setType(WxConsts.BUTTON_VIEW);
		indexPage.setUrl("http://www.justep.com");
		x5Meuns.add(indexPage);
		
		List<WxMenuButton> indexsubmenu = new ArrayList<WxMenuButton>();
		WxMenuButton subPage=new WxMenuButton();
		subPage.setName("百度");
		subPage.setType(WxConsts.BUTTON_VIEW);
		subPage.setUrl("http://www.baidu.com");
		indexsubmenu.add(subPage);
		indexPage.setSubButtons(indexsubmenu);
		
		WxMenuButton demoPage = new WxMenuButton();
		demoPage.setName("SYF");
		demoPage.setType(WxConsts.BUTTON_VIEW);
		demoPage.setUrl("http://x5.justep.com/x5/UI2/takeout/index.w");
		x5Meuns.add(demoPage);

		WxMenuButton takeout = new WxMenuButton();
		takeout.setName("拍方抓药");
		takeout.setType(WxConsts.BUTTON_VIEW);
		takeout.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdaceea00f5e0f79d&redirect_uri=http%3A%2F%2Fwww.jianhaola.com%2Fmedicine%2Fv-6b2emi-zh_CN-%2Fmedicine%2Fcamera.w&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		System.out.println(takeout.getUrl());
		x5Meuns.add(takeout);

		WxMenu x5Menu = new WxMenu();
		x5Menu.setButtons(x5Meuns);
		try {
			wxMpService.menuDelete();
			wxMpService.menuCreate(x5Menu);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	// 接收到menu指令后重构菜单
	private void addMenuRouter() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
				System.out.println("响应menu指令********************");
				buildMenu();
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("菜单已重构").fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};
		// 拦截内容为menu的消息
		wxMpMessageRouter.rule().async(false).content("menu").handler(handler).end();
	}

	public void doResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			response.getWriter().println("非法请求");
			return;
		}

		String echostr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			response.getWriter().println(echostr);
			return;
		}

		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");

		WxMpXmlMessage inMessage = null;

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
		} else if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
		} else {
			response.getWriter().println("不可识别的加密类型");
			return;
		}

		WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
		if (outMessage != null) {
			if ("raw".equals(encryptType)) {
				response.getWriter().write(outMessage.toXml());
			} else if ("aes".equals(encryptType)) {
				response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
			}
			return;
		}
	}

	public void downloadimg(String picture_media_id) {
		File picturefile = null;
		try {
			picturefile = wxMpService.mediaDownload(picture_media_id);
			System.out.println(picturefile);
		} catch (WxErrorException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String newpath = "D:/tomcat7/webapps/img/";
		File fnewpath = new File(newpath);
		if (!fnewpath.exists()) {
			fnewpath.mkdirs();
		}
		File newfile = new File(newpath + picture_media_id + ".jpg");
		picturefile.renameTo(newfile);
	}

	public void customMessageSend(String doctors_recommend, String medprice, String transportprice, String userid, String orderid, String logisticstelephone, String logistics, String logisticsnumber) {
		// TODO 自动生成的方法存根
		WxMpCustomMessage.WxArticle article = new WxMpCustomMessage.WxArticle();
		// article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdaceea00f5e0f79d&redirect_uri=http%3A%2F%2F353637syf.imwork.net%2Fx5%2FUI2%2Fv_4677_nol_zh_CNs_d_m%2Fmedicine%2Fpay.w&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdaceea00f5e0f79d&redirect_uri=http%3A%2F%2Fwww.jianhaola.com%2Fmedicine%2Fv-VV7rY3-zh_CN-%2Fmedicine%2Fpay.w&response_type=code&scope=snsapi_userinfo&state="+orderid+"#wechat_redirect");
		article.setPicUrl("http://www.jianhaola.com/img/1.jpg");
		String money = new DecimalFormat("#0.00").format(Float.parseFloat(medprice) + Float.parseFloat(transportprice));
		article.setDescription("药费：" + medprice + "  运费：" + transportprice + "  合计：" + money);
		article.setTitle(doctors_recommend);
		WxMpCustomMessage message;
		if ("1".equals(orderid)) {
			String text = "物流公司：" + logistics + "\n公司电话：" + logisticstelephone + "\n订单号码：" + logisticsnumber+"\n您的订单马上出货了，请耐心等待收货吧！";
			message = WxMpCustomMessage.TEXT().toUser(userid).content(text).build();
		} else {
			message = WxMpCustomMessage.NEWS().toUser(userid).addArticle(article).build();
		}

		try {
			wxMpService.customMessageSend(message);
		} catch (WxErrorException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
