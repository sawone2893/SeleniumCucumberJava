package api.endpoint;

public class Routes {
	
	public static String BASE_URL="https://petstore.swagger.io/v2";
	
	//User Module"
	public static final String POST_USER=BASE_URL+"/user";
	public static final String GET_USER=BASE_URL+"/user/{username}";
	public static final String UPDATE_USER=BASE_URL+"/user/{username}";
	public static final String DELTE_USER=BASE_URL+"/user/{username}";

}
