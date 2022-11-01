package org.example.pettests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.models.Pet;
import org.example.restclients.PetRestClient;
import org.example.utils.PetUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class PetTests {

    private Pet expectedPet;

    private final PetRestClient petRestClient = PetRestClient.getInstance();

    @BeforeClass
    private void setUp() {
        expectedPet = PetUtil.generateRandomPet();
    }

    @Test
    public void createPet() {
        Response response = petRestClient.postPet(expectedPet);
        response.then().
                assertThat().
                    statusCode(200).
                    body("id", equalTo(expectedPet.getId())).
                    body("name", equalTo(expectedPet.getName())).
                    body("status", equalTo(expectedPet.getStatus()));
    }

    @Test(dependsOnMethods = "createPet")
    public void getPetById() {
        Response response = petRestClient.getPetById(expectedPet.getId());
        response.then().
                assertThat().
                    statusCode(200).
                    contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "getPetById")
    public void deletePet() {
        Response response = petRestClient.deletePetById(expectedPet.getId());
        response.then().
                assertThat().
                    statusCode(200).
                    body("message", equalTo(String.valueOf(expectedPet.getId())));
    }

    @Test
    public void getAvailablePets() {
        Response availablePets = petRestClient.getPetsByStatus("available");
        Response storeInventory = petRestClient.getStoreInventory();
        int numberOfAvailablePets = storeInventory.path("available");
        availablePets.then().
                assertThat().
                    body("", hasSize(numberOfAvailablePets));
    }
}
