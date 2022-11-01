package org.example.restclients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.log.Log;
import org.example.models.Pet;

import static io.restassured.RestAssured.given;

public class PetRestClient {

    private static PetRestClient instance;
    private static RequestSpecification request;

    public static PetRestClient getInstance() {
        if (instance == null) {
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
            request = given().accept("application/json").contentType("application/json");
            instance = new PetRestClient();
        }
        return instance;
    }

    public Response getPetById(long id) {
        String path = "/pet/" + id;
        Log.info("Sending get request to " + RestAssured.baseURI + path);
        Response response = request.get(path);
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    public Response postPet(Pet pet) {
        Log.info("Sending post request to " + RestAssured.baseURI + "/pet");
        Response response = request.body(pet).post("/pet");
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    public Response deletePetById(long id) {
        String path = "/pet/" + id;
        Log.info("Sending delete request to " + RestAssured.baseURI + path);
        Response response = request.delete(path);
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    public Response getPetsByStatus(String status) {
        String path = "/pet/findByStatus";
        Log.info("Sending get request to " + RestAssured.baseURI + path);
        Response response = request.queryParam("status", status).get(path);
        Log.info("Response body is " + response.asPrettyString());
        return response;
    }

    public Response getStoreInventory() {
        String path = "/store/inventory";
        Log.info("Sending get request to " + RestAssured.baseURI + path);
        Response response = request.get(path);
        Log.info("Response body is " + response.asPrettyString());
        return response;

    }
}
