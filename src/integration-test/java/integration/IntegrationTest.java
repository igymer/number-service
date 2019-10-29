package integration;

import org.junit.Test;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void testAdd() throws IOException {
        String url = "http://localhost:8999/api/number/add/2/3";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            assertEquals("5", responseBody);
        }
    }

    @Test
    public void testSubtract() throws IOException {
        String url = "http://localhost:8999/api/number/subtract/7/3.5";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            assertEquals("3.5", responseBody);
        }
    }

    @Test
    public void testMultiply() throws IOException {
        String url = "http://localhost:8999/api/number/multiply/4/3.2";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            assertEquals("12.8", responseBody);
        }
    }

    @Test
    public void testDivide() throws IOException {
        String url = "http://localhost:8999/api/number/divide/4/1";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            assertEquals("4", responseBody);
        }
    }
}
