package testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestGetRequests {
	private String requestUrl;
	private int statusCode;

	public TestGetRequests(String requestUrl, int statusCode) {
		this.requestUrl = requestUrl;
		this.statusCode = statusCode;
	}
	
	@BeforeTest
	public void setupBaseUrl() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}

	@Test
	public void getRequests() {
		Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(this.requestUrl)
                .then()
                .extract().response();

		assertEquals(response.statusCode(), this.statusCode);
	}
}
