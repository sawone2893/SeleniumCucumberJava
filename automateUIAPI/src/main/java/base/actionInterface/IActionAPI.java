package base.actionInterface;

import java.util.Map;

import org.json.JSONArray;


public interface IActionAPI {
	
	public JSONArray getRequest(String resourceName);
	public JSONArray getRequest(String resourceName,Map<String,String> params);
	public JSONArray postRequest(String resourceName,String payLoad);
	public JSONArray putRequest(String resourceName,String payLoad);
	public JSONArray deleteRequest(String resourceName);
	public void setContentType(String contectType);
	public String getContentType();
	public String getHeader(String headerName);
	public void setHeader(String headerName,String headerValue);
	public int getStatusCode();
	public String getErrorMessage();
	public void setParams(Map<String,String> params);
}
