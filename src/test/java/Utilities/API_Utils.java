package Utilities;


import io.restassured.path.json.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;


public class API_Utils {

	ConfigFileReader config = null;


	public JsonPath getRequestForBoard(String BoardId) {
		config = new ConfigFileReader();
		String EndpointURL = config.getBoardBaseURL() + BoardId;
		RequestSpecification request = RestAssured.given()
				.queryParam("key", config.getAPIKey())
				.queryParam("token", config.getToken()).when();
		Response response = request.get(EndpointURL).then().contentType(ContentType.JSON).extract().response();
		Assert.assertEquals(response.getStatusCode(),200);
		return response.jsonPath();
	}

	public JsonPath postRequestBoardCreationWithPrivatePermissionLevel(String BoardName, String PermissionLevel) {
		config = new ConfigFileReader();
		String EndpointURL = config.getBoardBaseURL();
		RequestSpecification request = RestAssured.given();
		request.queryParam("name", BoardName);
		request.queryParam("prefs_permissionLevel", PermissionLevel);
		request.queryParam("key", config.getAPIKey());
		request.queryParam("token", config.getToken());
		request.contentType("application/json");
		request.accept("application/json");
		Response response = request.post(EndpointURL);
		Assert.assertEquals(response.getStatusCode(),200);
        return response.jsonPath();
	}

	public JsonPath postRequestForListCreation(String ListName,String BoardID) {
		config = new ConfigFileReader();
		String EndpointURL = config.getCreateListURL();
		RequestSpecification request = RestAssured.given();
		request.queryParam("name", ListName);
		request.queryParam("idBoard", BoardID);
		request.queryParam("key", config.getAPIKey());
		request.queryParam("token", config.getToken());
		request.contentType("application/json");
		request.accept("application/json");
		Response response = request.post(EndpointURL);
		Assert.assertEquals(response.getStatusCode(),200);
        return response.jsonPath();
	}

	public JsonPath getRequestForCardInAList(String ListId) {
		config = new ConfigFileReader();
		String EndpointURL = config.getCreateListURL();
		RequestSpecification request = RestAssured.given().pathParam("id", ListId)
				.queryParam("key", config.getAPIKey())
				.queryParam("token", config.getToken()).when();
		Response response = request.get(EndpointURL+"{id}/cards").then().contentType(ContentType.JSON).extract().response();
		Assert.assertEquals(response.getStatusCode(),200);
		return response.jsonPath();
	}
	
	public JsonPath postRequestForCardCreation(String CardName,String ListId) {
		config = new ConfigFileReader();
		JSONObject requestParams = new JSONObject();
		String EndpointURL = config.getCreateCardURL();
		RequestSpecification request = RestAssured.given();
		requestParams.put("name", CardName);
		request.queryParam("idList", ListId);
		request.queryParam("key", config.getAPIKey());
		request.queryParam("token", config.getToken());
		request.contentType("application/json");
		request.accept("application/json");
		request.body(requestParams.toString());
		Response response = request.post(EndpointURL);
		Assert.assertEquals(response.getStatusCode(),200);
        return response.jsonPath();
	}

	public JsonPath postMoveCardsToOtherList(String ListId, String BoardID,String TargetListId) {
		config = new ConfigFileReader();
		String EndpointURL = config.getCreateListURL();
		RequestSpecification request = RestAssured.given().pathParam("id", ListId);
		request.queryParam("idBoard", BoardID);
		request.queryParam("idList", TargetListId);
		request.queryParam("key", config.getAPIKey());
		request.queryParam("token", config.getToken());
		request.contentType("application/json");
		request.accept("application/json");
		Response response = request.post(EndpointURL+"{id}/moveAllCards");
		Assert.assertEquals(response.getStatusCode(),200);
		return response.jsonPath();
	}

	

//	public void deleteRequestForBoardDeletion(String BoardName) throws IOException {
//		config = new ConfigFileReader();
//		String EndpointURL = config.getBoardBaseURL() + BoardName;
//		RequestSpecification request = RestAssured.given().queryParam("key", config.getAPIKey())
//				.queryParam("token", config.getToken()).when();
//		Response response = request.delete(EndpointURL);
//		Assert.assertEquals(response.getStatusCode(),200);
//	}


}
