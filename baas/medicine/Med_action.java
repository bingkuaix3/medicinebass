package medicine;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.alibaba.fastjson.JSONObject;
import com.justep.baas.Utils;
import com.justep.baas.action.ActionContext;
import com.justep.baas.data.DataUtils;
import com.justep.baas.data.Table;
import com.justep.baas.data.Transform;
import com.justep.baas.data.sql.SQLException;

public class Med_action {
	private static final String DATASOURCE_TAKEOUT = "med";

	public static JSONObject queryPassword(JSONObject params, ActionContext context) throws SQLException, NamingException, java.sql.SQLException {
		String username = params.getString("username");
		String password = params.getString("password"); 

		List<Object> sqlParams = new ArrayList<Object>();
		// 构造过滤条件
		List<String> filters = new ArrayList<String>();

		sqlParams.add(username);
		sqlParams.add(password);

		Table table = null;
		Connection conn = context.getConnection(DATASOURCE_TAKEOUT);

		String querysql = "select * from signin where username=? and password=?";

		try {
			table = DataUtils.queryData(conn, querysql, sqlParams, null, null, null);
			JSONObject res=new JSONObject();
			if(table.getRows().size()==1){
				res.put("result", "success");
			}else{
				res.put("result", "fail");
			}
			return res;
		} finally {
			conn.close();
		}
	}

}
