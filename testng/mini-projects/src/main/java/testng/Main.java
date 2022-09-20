package testng;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
	public static void main(String[] args) {
		String pathName = "./src/test/resources/requests.json";
		ReadJson reader = new ReadJson();
		JSONArray requestList = reader.readJsonFile(pathName);
		requestList.forEach(req -> reader.parseEmployeeObject((JSONObject) req));
	}
}
