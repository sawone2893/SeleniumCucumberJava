package base.modal;

import java.util.Map;

import org.json.JSONArray;

import base.actionInterface.IActionAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredActions implements IActionAPI{
	
	RequestSpecification request =null;
	Response response=null;
	JSONArray JSONResponseBody = null;
	
	public RestAssuredActions() {
		request=RestAssured.given();
	}
	
	@Override
	public JSONArray getRequest(String resourceName) {
		this.setContentType("JSON");
		response=request.get(resourceName);
		JSONResponseBody = new   JSONArray(response.body().asString());
		return JSONResponseBody;
	}

	@Override
	public JSONArray getRequest(String resourceName, Map<String, String> params) {
		this.setContentType("JSON");
		this.setParams(params);
		response=request.get(resourceName);
		JSONResponseBody = new   JSONArray(response.body().asString());
		return JSONResponseBody;
	}

	@Override
	public JSONArray postRequest(String resourceName, String payLoad) {
		this.setContentType("JSON");
		request.body(payLoad);
		response=request.post(resourceName);
		JSONResponseBody = new   JSONArray(response.body().asString());
		return JSONResponseBody;
	}

	@Override
	public JSONArray putRequest(String resourceName, String payLoad) {
		this.setContentType("JSON");
		request.body(payLoad);
		response=request.put(resourceName);
		JSONResponseBody = new   JSONArray(response.body().asString());
		return JSONResponseBody;
	}

	@Override
	public JSONArray deleteRequest(String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentType(String contectType) {
		switch(contectType.toUpperCase()) {
		case "JSON":
			request.contentType(ContentType.JSON);
			break;
		case "MULTIPART":
			request.contentType(ContentType.MULTIPART);
			break;
		case "XML":
			request.contentType(ContentType.XML);
			break;
		case "ANY":
			request.contentType(ContentType.ANY);
			break;
		case "BINARY":
			request.contentType(ContentType.BINARY);
			break;
		case "HTML":
			request.contentType(ContentType.HTML);
			break;
		case "TEXT":
			request.contentType(ContentType.TEXT);
			break;
		default:
			System.out.println("Unsupported Content type: "+contectType);
		}

	}

	@Override
	public String getContentType() {
		return response.getContentType();
	}

	@Override
	public String getHeader(String headerName) {
		// TODO Auto-generated method stub
		return response.getHeader(headerName);
	}

	@Override
	public void setHeader(String headerName, String headerValue) {
		request.header(headerName,headerValue);
		
	}

	@Override
	public int getStatusCode() {
		return response.getStatusCode();
	}

	@Override
	public String getErrorMessage() {
		return response.getStatusLine();
	}

	@Override
	public void setParams(Map<String, String> params) {
		request.queryParams(params);	
	}
	

}
