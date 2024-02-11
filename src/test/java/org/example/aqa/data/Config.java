package org.example.aqa.data;

public class Config {
    public static String username = System.getProperty("server.username");
    public static String password = System.getProperty("server.password");
    public static String url = System.getProperty("server.url");
    public static int port = Integer.parseInt(System.getProperty("server.port"));
}
