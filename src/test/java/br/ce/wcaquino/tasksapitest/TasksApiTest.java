package br.ce.wcaquino.tasksapitest;

import static org.hamcrest.Matchers.is;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class TasksApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas() {
        given()
        .when()
            .get("/todo")
        .then()
            .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"task\": \"Teste via REST Assured\", \"dueDate\": \"2126-12-01\"}")
        .when()
            .post("/todo")
        .then()
            .statusCode(201)
            .body("task", is("Teste via REST Assured"));
    }

    @Test
    public void naoDeveAdicionarTarefaComDataPassada() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"task\": \"Teste data passada\", \"dueDate\": \"2020-01-01\"}")
        .when()
            .post("/todo")
        .then()
            .statusCode(400);
    }
}
