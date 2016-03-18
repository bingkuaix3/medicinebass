
	
package unionpay;	
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;

public class Unionpay__do {
	private static byte[] DBConfigBytes = {123,34,97,99,99,111,117,110,116,34,58,34,106,97,118,97,58,99,111,109,112,47,101,110,118,47,97,99,99,111,117,110,116,34,44,34,109,101,100,34,58,34,106,97,118,97,58,99,111,109,112,47,101,110,118,47,109,101,100,34,44,34,116,97,107,101,111,117,116,34,58,34,106,97,118,97,58,99,111,109,112,47,101,110,118,47,116,97,107,101,111,117,116,34,125};
	private static JSONObject DBConfig;
	
	private static JSONObject getDBConfig() throws Exception{
		if(null==DBConfig)
			DBConfig = JSONObject.parseObject(new String(DBConfigBytes,"UTF-8"));
		return DBConfig;
	}
	private static void initActionParams(JSONObject privateParams,JSONObject publicParams,JSONObject params){
		for(String key : privateParams.keySet()){
			params.put(key, privateParams.get(key));
		}
		for(String key : publicParams.keySet()){
			if(!params.containsKey(key))
				params.put(key, publicParams.get(key));
		}
	}
	
	
	public static JSONObject tn(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,125};
		JSONObject publicParams = JSONObject.parseObject(new String(publicParamBytes,"UTF-8"));
		
		//进行参数处理
		initActionParams(privateParams,publicParams,params);
		
		try{
			try {
				JSONObject ret = unionpay.AppConsume.getTN(params, context);
				if(selfContext) context.commit();
				return ret;
			}catch (Exception e){
				try {
					if(selfContext) context.rollback();
				} catch (SQLException e1) {
				}
				throw e;
			}
		} finally {
			if(selfContext) context.closeConnection();
		}
	}
	
	
}
	
	