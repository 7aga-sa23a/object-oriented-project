package haga_talga.model;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Doctor {

    public int signup(String name, String id, String password) {
        Scanner sc = new Scanner(System.in);

        String fileName = "data/doctors.json";
        JsonArray doctorsArray = new JsonArray();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // if id is already exist 
        boolean uniqueID = true;
        for (JsonElement element : doctorsArray) {
            String Id = element.getAsJsonObject()
                    .get("ID")
                    .getAsString();

            if (Id.equals(id)) {
                uniqueID = false;
                break;
            }
        }
        if (!uniqueID) {
            System.out.println(
                    "ID already exists. Please enter :\n1- unique ID.\n2- login if you already have an account.");
            int choose = sc.nextInt();
            while (choose != 1 && choose != 2) {
                System.out.println(
                        "Invalid choice. Please enter :\n1- unique ID.\n2- login if you already have an account.");
                choose = sc.nextInt();
            }
            if (choose == 2) {
                return 2;
            }
            else {
                id = sc.nextLine();
            }
        }
        while (!id.matches("[0-9]+")) {
            System.out.println("ID should be numbers only. Please enter ID again: ");
            id = sc.nextLine();
        }

        // constructor to create doctor object and save it in json file
        try (FileReader reader = new FileReader(fileName)) {
            JsonElement element = JsonParser.parseReader(reader);

            if (element.isJsonArray()) {
                doctorsArray = element.getAsJsonArray();
            } else {
                doctorsArray = new JsonArray();
            }

        } catch (Exception e) {
            doctorsArray = new JsonArray();
        }

        JsonObject doctor = new JsonObject();
        doctor.addProperty("name", name);
        doctor.addProperty("id", id);
        doctor.addProperty("password", password);

        doctorsArray.add(doctor);

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(doctorsArray, writer);
        } catch (IOException e) {
            System.out.println("Error saving doctors data");
            e.printStackTrace();
        }

        return 0;
    }

    // check login of doctor by checking if the ID and password match with the ones in the json file
    public int login(String id, String password) {

        // if doctor enters "signup" in either ID or password, return 2 to go to signup page
        String test1 = id.toLowerCase();
        String test2 = password.toLowerCase();
        if (test1.equals("signup") || test2.equals("signup")) {
            return 2;
        }

        // ! FIXME: Program always prints "Data file not found. Please contact support."
        String fileName = "../../../resources/doctors.json";
        JsonArray doctorsArray;
        try (FileReader reader = new FileReader(fileName)) {
            JsonElement element = JsonParser.parseReader(reader);

            if (element.isJsonArray()) {
                doctorsArray = element.getAsJsonArray();
            } else {
                doctorsArray = new JsonArray();
            }

        } catch (Exception e) {
            return -2;
        }

        for (JsonElement doctorElement : doctorsArray) {
            JsonObject doctor = doctorElement.getAsJsonObject();
            if (doctor.get("id").getAsString().equals(id) && doctor.get("password").getAsString().equals(password)) {
                return 1;
            }
            if (doctor.get("id").getAsString().equals(id) && !doctor.get("password").getAsString().equals(password)) {
                return -1;
            }
            if (!doctor.get("id").getAsString().equals(id) && doctor.get("password").getAsString().equals(password)) {
                return 0;
            }
        }
        return -3;
    }
}
