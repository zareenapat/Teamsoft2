package restassuredTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import POJOModels.User;
import POJOModels.UserData;
import POJOModels.UserDetail;
import POJOModels.UserPage;
import POJOModels.UserSupport;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqResTests {

	private static final String JSON = "application/json";
	String baseUrl = "https://reqres.in/api";
	
	/*
	 * given(): set cookies, add auth, add param, set headers info etc… , given()
	 * section includes prerequires
	 * 
	 * when(): get,post,put, delete…, when() section includes what action needs to
	 * do
	 * 
	 * then(): validate status code,extract response, extract headers cookes &
	 * response body ,checks the result
	 */

	
	private String getEndpoint(String endpoint) {
		return baseUrl + endpoint;
	}
	
	@Test
	public void verifyEmailAddress() {

		String endpoint = getEndpoint("/users/2");
		String email = "janet.weaver@reqres.in";

		when()
		.get(endpoint)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("data.email", equalTo(email));
	}
	
	@Test
	public void verifyPostTokenReturnValue() {
		String endpoint = getEndpoint("/login");
		String token = "QpwL5tke4Pnpja7X4";

		JsonObject loginCredentials = new JsonObject();
		loginCredentials.addProperty("email", "eve.holt@reqres.in");
		loginCredentials.addProperty("password", "cityslicka");
		
		given()
		.contentType(JSON)
		.body(loginCredentials.toString())
		.post(endpoint)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("token", is(token));
	}
	
	@Test
	public void verifyDelete() {

		String endpoint = getEndpoint("/users/2");

		given()
		.delete(endpoint)
		.then()
		.assertThat()
		.statusCode(204);
	}
	
	@Test
	public void canPatch() {

		String endpoint = getEndpoint("/users/2");

		User user = new User();
		user.name = "morpheus2";
		user.job = "zion resident";

		given()
		.contentType(JSON)
		.body(user)
		.patch(endpoint)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("name", is(user.name))
		.body("job", is(user.job));
	}
	
	@Test
	public void canPut() {

		String endpoint = getEndpoint("/users/2");

		User user = new User();
		user.name = "morpheus2";
		user.job = "zion resident";

		given()
		.contentType(JSON)
		.body(user)
		.patch(endpoint)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("name", is(user.name))
		.body("job", is(user.job));
	}
	@Test
	public void canDeserializeUserDetails() {

		String endpoint = getEndpoint("/users/2");

		UserData expectedUserDataDetails = new UserData();
		expectedUserDataDetails.id = 2;
		expectedUserDataDetails.email = "janet.weaver@reqres.in";
		expectedUserDataDetails.first_name = "Janet";
		expectedUserDataDetails.last_name = "Weaver";
		expectedUserDataDetails.avatar = "https://reqres.in/img/faces/2-image.jpg";

		UserSupport expectedUserSupportDetails = new UserSupport();
		expectedUserSupportDetails.url = "https://reqres.in/#support-heading";
		expectedUserSupportDetails.text = "To keep ReqRes free, contributions towards server costs are appreciated!";

		UserDetail expectedUserDetails = new UserDetail();
		expectedUserDetails.data = expectedUserDataDetails;
		expectedUserDetails.support = expectedUserSupportDetails;

		UserDetail updatedUser = given()
				.get(endpoint)
				.getBody()
				.as(UserDetail.class);

		Assert.assertEquals(updatedUser.getData().id, expectedUserDetails.getData().id);
		Assert.assertEquals(updatedUser.getData().email, expectedUserDetails.getData().email);
		Assert.assertEquals(updatedUser.getData().first_name, expectedUserDetails.getData().first_name);
		Assert.assertEquals(updatedUser.getData().last_name, expectedUserDetails.getData().last_name);
		Assert.assertEquals(updatedUser.getData().avatar, expectedUserDetails.getData().avatar);

		Assert.assertEquals(updatedUser.getSupport().url, expectedUserDetails.getSupport().url);
		Assert.assertEquals(updatedUser.getSupport().text, expectedUserDetails.getSupport().text);
	}
	}
