
	
package justep;	
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;

public class Account__do {
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
	
	
	public static JSONObject queryAccount_class(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,97,99,99,111,117,110,116,95,99,108,97,115,115,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,111,114,100,101,114,66,121,34,58,34,102,84,121,112,101,32,65,83,67,44,32,102,67,108,97,115,115,32,65,83,67,34,125};
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
	
	
	
	public static JSONObject saveAccount_class(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,112,101,114,109,105,115,115,105,111,110,115,34,58,123,34,97,99,99,111,117,110,116,95,99,108,97,115,115,34,58,34,34,125,125};
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
	
	
	
	public static JSONObject queryAccount(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,97,99,99,111,117,110,116,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,111,114,100,101,114,66,121,34,58,34,102,68,97,116,101,32,68,69,83,67,34,125};
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
	
	
	
	public static JSONObject saveAccount(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,97,99,99,111,117,110,116,34,44,34,112,101,114,109,105,115,115,105,111,110,115,34,58,123,34,97,99,99,111,117,110,116,34,58,34,34,125,125};
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
	
	
}
	
	