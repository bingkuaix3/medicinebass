
	
package justep;	
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;

public class Netease__do {
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
	
	
	public static JSONObject queryNetease_user(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,99,111,110,100,105,116,105,111,110,34,58,34,102,73,68,61,58,117,115,101,114,97,34,44,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,110,101,116,101,97,115,101,95,117,115,101,114,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,118,97,114,45,117,115,101,114,97,34,58,34,117,115,101,114,34,125};
		JSONObject publicParams = JSONObject.parseObject(new String(publicParamBytes,"UTF-8"));
		
		//进行参数处理
		initActionParams(privateParams,publicParams,params);
		
		try{
			try {
				JSONObject ret = com.justep.baas.action.CRUD.query(params, context);
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
	
	
	
	public static JSONObject saveNetease_user(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,112,101,114,109,105,115,115,105,111,110,115,34,58,123,34,110,101,116,101,97,115,101,95,117,115,101,114,34,58,34,34,125,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,125};
		JSONObject publicParams = JSONObject.parseObject(new String(publicParamBytes,"UTF-8"));
		
		//进行参数处理
		initActionParams(privateParams,publicParams,params);
		
		try{
			try {
				JSONObject ret = com.justep.baas.action.CRUD.save(params, context);
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
	
	
	
	public static JSONObject queryRegist_user(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,99,111,110,100,105,116,105,111,110,34,58,34,102,80,104,111,110,101,78,117,109,98,101,114,61,58,117,115,101,114,80,104,111,110,101,32,97,110,100,32,112,97,115,115,119,100,61,58,112,97,115,115,119,111,114,100,34,44,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,110,101,116,101,97,115,101,95,117,115,101,114,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,118,97,114,45,112,97,115,115,119,111,114,100,34,58,34,49,50,51,52,53,54,34,44,34,118,97,114,45,117,115,101,114,80,104,111,110,101,34,58,34,49,56,53,48,49,57,55,56,53,56,48,34,125};
		JSONObject publicParams = JSONObject.parseObject(new String(publicParamBytes,"UTF-8"));
		
		//进行参数处理
		initActionParams(privateParams,publicParams,params);
		
		try{
			try {
				JSONObject ret = com.justep.baas.action.CRUD.query(params, context);
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
	
	