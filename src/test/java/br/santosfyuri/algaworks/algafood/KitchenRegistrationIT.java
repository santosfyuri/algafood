package br.santosfyuri.algaworks.algafood;


import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import br.santosfyuri.algaworks.algafood.utils.ResourceUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class KitchenRegistrationIT extends DefaultIntegrationTest {

    private static final String BASE_PATH = "/kitchens";

    private static final int NONEXISTENT_KITCHEN_ID = 100;

    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen americanKitchen;
    private int numberRegisteredKitchens;
    private String jsonCorrectChineseKitchen;

    protected KitchenRegistrationIT() {
        super(BASE_PATH);
    }

    @BeforeEach
    public void sdfsds() {
        jsonCorrectChineseKitchen = ResourceUtils.getContentFromResource(
                "/json/correct/chinese_kitchen.json");

        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenConsultKitchens() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCorrectKitchensNumber_WhenConsultKitchens() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(numberRegisteredKitchens));
    }

    @Test
    public void shouldReturnStatus201_WhenRegisterKitchen() {
        given()
                .body(jsonCorrectChineseKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnCorrectResponseAndStatus_WhenConsultExistentKitchen() {
        given()
                .pathParam("id", americanKitchen.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(americanKitchen.getName()));
    }

    @Test
    public void shouldReturnStatus404_WhenConsultNonexistentKitchen() {
        given()
                .pathParam("id", NONEXISTENT_KITCHEN_ID)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Kitchen taiKitchen = Kitchen.create()
                .name("Tailandesa")
                .build();
        kitchenRepository.save(taiKitchen);

        americanKitchen = Kitchen.create()
                .name("Americana")
                .build();
        kitchenRepository.save(americanKitchen);

        numberRegisteredKitchens = (int) kitchenRepository.count();
    }
}
