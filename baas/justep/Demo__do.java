
	
package justep;	
import java.sql.SQLException;
import com.alibaba.fastjson.JSONObject;
import com.justep.baas.action.ActionContext;

public class Demo__do {
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
	
	
	public static JSONObject queryUser(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,99,111,117,110,116,83,113,108,34,58,34,83,69,76,69,67,84,32,67,79,85,78,84,40,102,73,68,41,32,70,82,79,77,32,116,97,107,101,111,117,116,95,117,115,101,114,32,87,72,69,82,69,32,40,48,61,58,117,115,101,83,101,97,114,99,104,41,32,111,114,32,40,102,73,68,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,78,97,109,101,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,80,104,111,110,101,78,117,109,98,101,114,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,65,100,100,114,101,115,115,32,76,73,75,69,32,58,115,101,97,114,99,104,41,34,44,34,100,98,34,58,34,116,97,107,101,111,117,116,34,44,34,115,113,108,34,58,34,83,69,76,69,67,84,32,117,46,102,73,68,44,32,117,46,102,78,97,109,101,44,32,117,46,102,80,104,111,110,101,78,117,109,98,101,114,44,32,117,46,102,65,100,100,114,101,115,115,44,32,67,79,85,78,84,40,111,114,100,46,102,73,68,41,32,65,83,32,111,114,100,101,114,67,111,117,110,116,32,70,82,79,77,32,116,97,107,101,111,117,116,95,117,115,101,114,32,117,32,76,69,70,84,32,74,79,73,78,32,116,97,107,101,111,117,116,95,111,114,100,101,114,32,111,114,100,32,79,78,32,117,46,102,73,68,32,61,32,111,114,100,46,102,85,115,101,114,73,68,32,87,72,69,82,69,32,40,48,61,58,117,115,101,83,101,97,114,99,104,41,32,111,114,32,40,117,46,102,73,68,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,117,46,102,78,97,109,101,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,117,46,102,80,104,111,110,101,78,117,109,98,101,114,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,117,46,102,65,100,100,114,101,115,115,32,76,73,75,69,32,58,115,101,97,114,99,104,41,32,71,82,79,85,80,32,66,89,32,117,46,102,73,68,44,32,117,46,102,78,97,109,101,44,32,117,46,102,80,104,111,110,101,78,117,109,98,101,114,44,32,117,46,102,65,100,100,114,101,115,115,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,116,97,107,101,111,117,116,95,117,115,101,114,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,118,97,114,45,117,115,101,83,101,97,114,99,104,34,58,48,125};
		JSONObject publicParams = JSONObject.parseObject(new String(publicParamBytes,"UTF-8"));
		
		//进行参数处理
		initActionParams(privateParams,publicParams,params);
		
		try{
			try {
				JSONObject ret = com.justep.baas.action.CRUD.sqlQuery(params, context);
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
	
	
	
	public static JSONObject saveUser(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,116,97,107,101,111,117,116,34,44,34,112,101,114,109,105,115,115,105,111,110,115,34,58,123,34,116,97,107,101,111,117,116,95,117,115,101,114,34,58,34,34,125,125};
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
	
	
	
	public static JSONObject getOrderCount(JSONObject params, ActionContext context) throws Exception{
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
				JSONObject ret = justep.Demo.getOrderCount(params, context);
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
	
	
	
	public static JSONObject queryOrder(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,99,111,110,100,105,116,105,111,110,34,58,34,40,48,61,58,117,115,101,83,101,97,114,99,104,41,32,111,114,32,40,102,85,115,101,114,78,97,109,101,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,80,104,111,110,101,78,117,109,98,101,114,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,65,100,100,114,101,115,115,32,76,73,75,69,32,58,115,101,97,114,99,104,32,79,82,32,102,67,111,110,116,101,110,116,32,76,73,75,69,32,58,115,101,97,114,99,104,41,34,44,34,100,98,34,58,34,116,97,107,101,111,117,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,116,97,107,101,111,117,116,95,111,114,100,101,114,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,34,118,97,114,45,117,115,101,83,101,97,114,99,104,34,58,48,125};
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
	
	
	
	public static JSONObject saveOrder(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,116,97,107,101,111,117,116,34,44,34,112,101,114,109,105,115,115,105,111,110,115,34,58,123,34,116,97,107,101,111,117,116,95,111,114,100,101,114,34,58,34,34,125,125};
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
	
	
	
	public static JSONObject queryRegionTree(JSONObject params, ActionContext context) throws Exception{
		boolean selfContext = null==context;
		if(selfContext) context = new ActionContext(getDBConfig());
		if(params.containsKey(ActionContext.REQUEST)) context.put(ActionContext.REQUEST, params.get(ActionContext.REQUEST));
		if(params.containsKey(ActionContext.RESPONSE)) context.put(ActionContext.RESPONSE, params.get(ActionContext.RESPONSE));
		byte[] privateParamBytes = {123,34,100,98,34,58,34,116,97,107,101,111,117,116,34,44,34,116,97,98,108,101,78,97,109,101,34,58,34,116,97,107,101,111,117,116,95,114,101,103,105,111,110,34,125};
		JSONObject privateParams = JSONObject.parseObject(new String(privateParamBytes,"UTF-8"));
		byte[] publicParamBytes = {123,125};
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
	
	