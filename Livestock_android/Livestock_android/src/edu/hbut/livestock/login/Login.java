package edu.hbut.livestock.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import edu.hbut.livestock.entity.Department;
import edu.hbut.livestock.entity.User;
import edu.hbut.livestock.entity.UserGroup;
import edu.hbut.livestock.entity.UserInfo;
import edu.hbut.livestock.http.ElectronicRecipesUtils;
import edu.hbut.livestock.util.SettingSystem;

/**
 * 登陆，并获取权限信息
 * 
 * @author Hang
 * 
 */
public final class Login {

	private String text = "";
	private String userId;
	private String password;
	private HttpResponse response;
	
	public Login(String name,String password){
		this.userId = name;
		this.password = password;
	}
	public Boolean login() {
		HttpPost httpPost = new HttpPost(SettingSystem.NET_WORK+"execute_loginAction");//http://192.168.1.107:8080/livestock/execute_loginAction
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = new UrlEncodedFormEntity(getPostDate(), "utf-8");
			httpPost.setEntity(httpEntity);
			response = httpClient.execute(httpPost);
			
			if(response.getStatusLine().getStatusCode()==200){
				String jsoninfo = EntityUtils.toString(response.getEntity());
				String result = this.parseJsonResult(jsoninfo);
				if (result.equals("login success !")) {
					return true;
				}else{
					text = "用户名或者密码错误！";
				}
			}else{
				text = "网络连接异常！";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			text = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			text = e.getMessage().toString();
		}catch(Exception e){
			e.printStackTrace();
			text = e.getMessage().toString();
		}
		
		return false;
	}
	public String getText(){
		return text;
	}
	
	private List<BasicNameValuePair> getPostDate(){
		Map<String , String > map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("userPassword", password);
//		System.out.println("userId=" + userId + "-----------password=" + password);
		
		List<BasicNameValuePair> postDate = new ArrayList<BasicNameValuePair>();
		for(Map.Entry<String,String> entry: map.entrySet()){
			postDate.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return postDate;
	}
	
	private String parseJsonResult(String jsoninfo) throws Exception{
		System.out.println("----------->" + jsoninfo);
		JSONObject userAll = new JSONObject(jsoninfo);
		String result = userAll.getString("result");
		String sessionId = userAll.getString("sessionId");
		ElectronicRecipesUtils.setSessionId(sessionId);
//		JSONObject model = userAll.getJSONObject("model");
//		JSONObject js_userGroup = model.getJSONObject("userGroup");
//		JSONObject js_userinfo = model.getJSONObject("userInfo");
//		
//		UserGroup userGroup = new UserGroup();
//		userGroup.setGroupid(js_userGroup.getString("groupid"));
//		UserInfo userInfo = new UserInfo();
//		userInfo.setAddress(js_userinfo.getString("address"));
//		userInfo.setEmail(js_userinfo.getString("email"));
//		userInfo.setPhone(js_userinfo.getString("phone"));
//		userInfo.setPostcode(js_userinfo.getString("postcode"));
//		userInfo.setSex(js_userinfo.getString("sex"));
//		userInfo.setUserName(js_userinfo.getString("userName"));
//		userInfo.setUserid(js_userinfo.getString("userid"));
//		Department department = null;
//		User user = new User();
//		user.setUserInfo(userInfo);
//		user.setDepartment(department);
//		user.setUserGroup(userGroup);
//		user.setDepartment(department);
		
//		JsonReader reader = new JsonReader(new StringReader(jsoninfo));
//		reader.setLenient(true);
//	    reader.beginObject();  
//	    while(reader.hasNext()){  
//	        String tagName= reader.nextName();  
//	        if(tagName.equals("result")){  
//	            result = reader.nextString(); 
//	            break;
//	        }  
//	    }  
//	    reader.endObject();  
//		reader.close();
		return result;
	}
}
