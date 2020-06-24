package com.scc;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * sample app for aws batch reading secrets from secretss manager
 *
 */
public class App {

    private final static String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
    private final static String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";

    public static void main(String[] args) {
        System.out.println("Batch startet");
        //adapt your secrets name here
        String dbSecret = System.getenv("dbSecret");
        System.out.println("secret: " + dbSecret);

        if (dbSecret == null) {
            return;
        }
        String dbUser = getString(dbSecret, "username");
        String dbPassword = getString(dbSecret, "password");
        System.out.println("db user" + dbUser);
        System.out.println("password " + dbPassword);

        // if you want to change app properties (spring etc) you can change them now
//		Properties props = new Properties();
//		props.put(SPRING_DATASOURCE_USERNAME, dbUser);
//		props.put(SPRING_DATASOURCE_PASSWORD, dbPassword != null ? dbPassword : "--");
//		System.getenv().putIfAbsent(SPRING_DATASOURCE_USERNAME, dbUser);
//		System.getenv().putIfAbsent(SPRING_DATASOURCE_PASSWORD, dbPassword);
        System.out.println("Done, shutdown");
    }

    private static String getString(String json, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            return root.path(path).asText();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
