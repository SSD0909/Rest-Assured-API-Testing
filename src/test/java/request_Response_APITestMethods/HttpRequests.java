package request_Response_APITestMethods;

import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.*;
public class HttpRequests {
	
	
	int id;
	
	// method to get new single user from RequestResponse API
	@Test(priority=1)
	void getSingleUSer() {
		
	
	when()
	
	.get("https://reqres.in/api/users/2")
	.then()
	.statusCode(200)
	.log().all();
	}
	
	//Method to get all users
	@Test(priority=2)
	void getAllUSers() {
		
		given()
		
	.when()
	.get("https://reqres.in/api/users?page=2")
	
	.then()
	.statusCode(200)
	.body("page",equalTo(2))
	.log().all();
		
		
	}
	
	//create a post method to create user
	
	@Test(priority =3)
	void CreateUser() {
		
		//this is one way of generating the data hard coding data values)
		HashMap data= new HashMap();
		data.put("name","sam");
		data.put("job","analyst");
		
		id = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	
		//.then()
	//		.statusCode(201)
	//		.log().all();
		
		
	}
	//update the user
	@Test(priority =4,dependsOnMethods= {"CreateUser"})
	void UpdateUser() {
		
		//this is one way of generating the data hard coding data values)
		HashMap data= new HashMap();
		data.put("name","sam");
		data.put("job","analyst");
		
	given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
			
	
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	
	//Delete User
	@Test(priority =5)
	void DeleteUser() {
		
		
		
		when()
			.delete("https://reqres.in/api/users/"+id)
			
	
		.then()
	.statusCode(204);
		
		
		
	}
}
