package StepDefinitions;

import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import Utilities.API_Utils;
import Utilities.CustomizedUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.HashMap;


public class ApiTestCases {

	API_Utils api = new API_Utils();
	CustomizedUtils utils = new CustomizedUtils();
	static String BoardName,PermissionLevel,Id,BoardId,CardName;
	static String ToDoListId,InprogressListId,CompletedListId,InTestingListId,DoneListId,DeployedListId;
	JsonPath json;

	@Given("Create a board through API call")
	public void Create_board() {
		BoardName = utils.BoardName();
		PermissionLevel = utils.PrefsPermissionLevel();
		json = api.postRequestBoardCreationWithPrivatePermissionLevel(BoardName,PermissionLevel);
		BoardId = json.getString("shortUrl").split("/")[4];
	}

	@And("Get board to verify through API call")
	public void Get_and_verify_board_data_through_API_call() {
		json = api.getRequestForBoard(BoardId);
		Id = json.getString("id");
		String ActualBoardName = json.getString("name");
		HashMap<String,String> prefs = json.get("prefs");
		String ActualPermissionLevel = prefs.get("permissionLevel");
		Assert.assertEquals(ActualBoardName,BoardName);
		Assert.assertEquals(ActualPermissionLevel,PermissionLevel);
	}

	@When("Create ToDo {string} list for the board through API call")
	public void Create_to_do_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		ToDoListId = json.getString("id");
		String ActualToDoListName = json.getString("name");
		String ActualToDoBoardId = json.getString("idBoard");
		boolean ActualClosedToDoList = json.getBoolean("closed");
		Assert.assertEquals(ActualToDoListName,ListName);
		Assert.assertEquals(ActualToDoBoardId,Id);
		Assert.assertFalse(ActualClosedToDoList);
	}

	@And("Create Inprogress {string} list for the board through API call")
	public void Create_inprogress_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		InprogressListId = json.getString("id");
		String ActualInProgressListName = json.getString("name");
		String ActualInProgressBoardId = json.getString("idBoard");
		boolean ActualClosedInProgressList = json.getBoolean("closed");
		Assert.assertEquals(ActualInProgressListName,ListName);
		Assert.assertEquals(ActualInProgressBoardId,Id);
		Assert.assertFalse(ActualClosedInProgressList);

	}

	@And("Create Completed {string} list for the board through API call")
	public void Create_completed_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		CompletedListId = json.getString("id");
		String ActualCompletedListName = json.getString("name");
		String ActualCompletedBoardId = json.getString("idBoard");
		boolean ActualClosedCompletedList = json.getBoolean("closed");
		Assert.assertEquals(ActualCompletedListName,ListName);
		Assert.assertEquals(ActualCompletedBoardId,Id);
		Assert.assertFalse(ActualClosedCompletedList);
	}

	@And("Create In Testing {string} list for the board through API call")
	public void Create_in_testing_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		InTestingListId = json.getString("id");
		String ActualInTestingListName = json.getString("name");
		String ActualInTestingBoardId = json.getString("idBoard");
		boolean ActualClosedInTestingList = json.getBoolean("closed");
		Assert.assertEquals(ActualInTestingListName,ListName);
		Assert.assertEquals(ActualInTestingBoardId,Id);
		Assert.assertFalse(ActualClosedInTestingList);
	}

	@And("Create Done {string} list for the board through API call")
	public void Create_done_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		DoneListId = json.getString("id");
		String ActualDoneListName = json.getString("name");
		String ActualDoneBoardId = json.getString("idBoard");
		boolean ActualClosedDoneList = json.getBoolean("closed");
		Assert.assertEquals(ActualDoneListName,ListName);
		Assert.assertEquals(ActualDoneBoardId,Id);
		Assert.assertFalse(ActualClosedDoneList);
	}

	@And("Create Deployed {string} list for the board through API call")
	public void Create_deployed_list(String ListName) {
		json = api.postRequestForListCreation(ListName, Id);
		DeployedListId = json.getString("id");
		String ActualDoneListName = json.getString("name");
		String ActualDoneBoardId = json.getString("idBoard");
		boolean ActualDeployedDoneList = json.getBoolean("closed");
		Assert.assertEquals(ActualDoneListName,ListName);
		Assert.assertEquals(ActualDoneBoardId,Id);
		Assert.assertFalse(ActualDeployedDoneList);
	}

	@When("Create a card for the list through API call")
	public void Create_card() {
		CardName = utils.CardName();
		json = api.postRequestForCardCreation(CardName,ToDoListId);
		String ActualCardName = json.getString("name");
		Assert.assertEquals(ActualCardName,CardName);
	}

	@Then("Move cards from todo to inprogress")
	public void Move_cards_todo_to_inprogress() {
		json = api.postMoveCardsToOtherList(ToDoListId,Id,InprogressListId);
	}

	@Then("Move cards from inprogress to completed")
	public void Move_cards_inprogress_to_completed() {
		json = api.postMoveCardsToOtherList(InprogressListId,Id,CompletedListId);
	}

	@Then("Move cards from completed to in testing")
	public void Move_cards_completed_to_in_testing() {
		json = api.postMoveCardsToOtherList(CompletedListId,Id,InTestingListId);
	}

	@Then("Move cards from in testing to done")
	public void Move_cards_in_testing_to_done() {
		json = api.postMoveCardsToOtherList(InTestingListId,Id,DoneListId);
	}

	@Then("Move cards from done to deployed")
	public void Move_cards_done_to_deployed() {
		json = api.postMoveCardsToOtherList(DoneListId,Id,DeployedListId);
	}

	@And("Get to-do card to verify through API call")
	public void getToDoCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(ToDoListId);
		String ActualToDoListId = json.getString("idList");
		String ActualToDoBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualToDoListId.contains(ToDoListId));
		Assert.assertTrue(ActualToDoBoardId.contains(Id));
	}

	@And("Get inprogress card to verify through API call")
	public void getInprogressCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(InprogressListId);
		String ActualInProgressListId = json.getString("idList");
		String ActualInProgressBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualInProgressListId.contains(InprogressListId));
		Assert.assertTrue(ActualInProgressBoardId.contains(Id));
	}

	@And("Get completed card to verify through API call")
	public void getCompletedCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(CompletedListId);
		String ActualCompletedListId = json.getString("idList");
		String ActualCompletedBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualCompletedListId.contains(CompletedListId));
		Assert.assertTrue(ActualCompletedBoardId.contains(Id));
	}

	@And("Get in testing card to verify through API call")
	public void getInTestingCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(InTestingListId);
		String ActualInTestingListId = json.getString("idList");
		String ActualInTestingBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualInTestingListId.contains(InTestingListId));
		Assert.assertTrue(ActualInTestingBoardId.contains(Id));
	}

	@And("Get done card to verify through API call")
	public void getDoneCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(DoneListId);
		String ActualDoneListId = json.getString("idList");
		String ActualDoneBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualDoneListId.contains(DoneListId));
		Assert.assertTrue(ActualDoneBoardId.contains(Id));
	}

	@And("Get deployed card to verify through API call")
	public void getDeployedCardToVerifyThroughAPICall() {
		json = api.getRequestForCardInAList(DeployedListId);
		String ActualDeployedListId = json.getString("idList");
		String ActualDeployedBoardId = json.getString("idBoard");
		Assert.assertTrue(ActualDeployedListId.contains(DeployedListId));
		Assert.assertTrue(ActualDeployedBoardId.contains(Id));
	}


//	@And("Delete a board through API call")
//	public void Delete_board() throws IOException {
//		api.deleteRequestForBoardDeletion(BoardId);
//	}

}
