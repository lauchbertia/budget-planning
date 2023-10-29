package budgetPlanning;

import com.google.gson.Gson;

/**
 * GSON Object for JSON data
 * JSON data as an String
 * @see DataContainer#getData()
 * 
 */

public class Data {
	Gson gson = new Gson();
	String jsonString = "{\n"
			+ "  \"data\": [\n"

			+ "  ]\n"
			+ "}";
}
