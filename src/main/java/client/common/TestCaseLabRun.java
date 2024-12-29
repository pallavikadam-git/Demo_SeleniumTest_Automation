package client.common;

import com.google.gson.*;
import lombok.SneakyThrows;
import okhttp3.*;

public class TestCaseLabRun {

    private static final OkHttpClient client = new OkHttpClient();
    public static final String BASE_URL = "https://app.testcaselab.com/api/v2";
    public static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJnZW5lcmF0ZWRfYXQiOiIyMDI0LTEyLTI3IDA4OjI1OjU4ICswMDAwIiwidXNlcl9pZCI6bnVsbCwiZXhwIjo0ODkwOTYxNTU4fQ.c9eC5XoMeHTnpgT0fCa5tPNPLv9sNQUZbogYv3eZQko"; // Replace with your API key
    public static final String PROJECT_ID = "12375"; // Replace with your project ID

    public static Integer createTestRun(String testRunName) throws Exception {
        String url = BASE_URL + "/test_runs?project_id=" + PROJECT_ID;

        JsonObject payload = new JsonObject();
        payload.addProperty("name", testRunName);

        RequestBody body = RequestBody.create(payload.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .addHeader("project_id", PROJECT_ID)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {

                JsonObject responseJson = JsonParser.parseString(response.body().string()).getAsJsonObject();
                return responseJson.get("id").getAsInt(); // Returns the test run ID
            } else {
                throw new RuntimeException("Failed to create test run: " + response.code() + " - " + response.message());
            }
        }
    }

    @SneakyThrows
    public static int getTestCaseIdByTitle(String title) {
        // Build the URL
        String url = BASE_URL + "/test_cases?project_id=" + PROJECT_ID;

        // OkHttp client
        OkHttpClient client = new OkHttpClient();

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to fetch test cases: " + response.code() + " " + response.message());
                return -1;
            }

            // Parse the response body
            String responseBody = response.body().string();
            return parseTestCaseId(responseBody, title);
        }
    }

    /**
     * Parses the test case ID from the JSON response by matching the title.
     * @param jsonResponse The JSON response from the TestCaseLab API.
     * @param title        The title of the test case to search for.
     * @return The ID of the test case if found, or -1 if not found.
     */
    private static int parseTestCaseId(String jsonResponse, String title) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Extract the test cases list array
        JsonArray testCasesList = jsonObject.getAsJsonArray("test_cases_list");

        // Iterate through the array to find the test case with the given title
        for (JsonElement testCaseElement : testCasesList) {
            JsonObject testCase = testCaseElement.getAsJsonObject();
            String testCaseTitle = testCase.get("title").getAsString();

            // Match the title
            if (testCaseTitle.equals(title)) {
                return testCase.get("id").getAsInt();
            }
        }

        // Return -1 if the test case title is not found
        return -1;
    }

    public static void addTestCasesToTestRun(Integer testRunId, int[] testCaseIds) throws Exception {
        String url = BASE_URL + "/test_runs/" + testRunId + "/add_test_case?project_id=" + PROJECT_ID;

        JsonObject payload = new JsonObject();
        JsonArray testCaseArray = new JsonArray();
        for (int testCaseId : testCaseIds) {
            testCaseArray.add(testCaseId);
        }
        payload.add("test_case_ids", testCaseArray);
        String requestBodyString = payload.toString();
        RequestBody body = RequestBody.create(requestBodyString, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to add test cases: " + response.code() + " - " + response.message());
            }
        }
    }

    public static void updateTestCaseStatus(Integer testResultId, Integer testRunId, Integer testCaseId, String status) throws Exception {
        String url = BASE_URL + "/test_results/" + testResultId + "?project_id=" + PROJECT_ID;

        JsonObject payload = new JsonObject();
        payload.addProperty("test_case_id", testCaseId);
        payload.addProperty("status", status); // "passed" or "failed"
        payload.addProperty("project_id", PROJECT_ID);
        payload.addProperty("id", testResultId);
        payload.addProperty("test_run_id", testRunId);
        payload.addProperty("comment", "comment");

        RequestBody body = RequestBody.create(payload.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println(request.toString());
        System.out.println(payload.toString());

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to update test case status: " + response.code() + " - " + response.message());
            }
        }
    }

    public static Integer getTestResultIdByTestCaseId(Integer testRunId, int testCaseId) throws Exception {
        String url = BASE_URL + "/test_results?project_id=" + PROJECT_ID + "&test_run_id=" + testRunId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch test results: " + response.code() + " - " + response.message());
            }

            String responseBody = response.body().string();
            JsonArray resultsArray = JsonParser.parseString(responseBody).getAsJsonArray();

            // Iterate through the results to find the matching test_case_id
            for (JsonElement element : resultsArray) {
                JsonObject result = element.getAsJsonObject();

                if (result.get("test_case_id").getAsInt() == testCaseId) {
                    return result.get("id").getAsInt(); // Return the matching ID
                }
            }

            // If no matching test_case_id is found
            throw new RuntimeException("Test case ID not found in response.");
        }
    }
}


