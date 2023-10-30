package Trail;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
 
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Base.Base;

public class DemoAPI 
{
	public static void main(String[] args) 
	{
		/////ADD place by using POST method 
	  RestAssured.baseURI="https://rahulshettyacademy.com";
	  
	  String oldAddress = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").
	  body(Base.postMethod()).when().post("maps/api/place/add/json").
	  then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).
	  header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	 
	 System.out.println(oldAddress);
	  
	 JsonPath js= new JsonPath(oldAddress);
	 String placeID = js.getString("place_id");
	 System.out.println(placeID);
	 
	 ///////Update place by using PUT method 
	 
	 String newAddress = "407/103 North street Mudhukudi";
	 
	 given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body("{\r\n"
	 		+ "\"place_id\":\""+placeID+"\",\r\n"
	 		+ "\"address\":\""+newAddress+"\",\r\n"
	 		+ "\"key\":\"qaclick123\"\r\n"
	 		+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
	 .body("msg", equalTo("Address successfully updated"));
	 
	 ///////////Retrieving and validating by using GET method
	 String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeID).
	 when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().
	 response().asString();
	
	 JsonPath js1 = new JsonPath(getPlaceResponse);
	 String actualAddress = js1.getString("address");
	 System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, newAddress);
	}
}
