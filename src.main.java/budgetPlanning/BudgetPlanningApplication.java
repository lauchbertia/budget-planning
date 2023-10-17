package budgetPlanning;
import java.util.List;

import com.google.gson.Gson;

public class BudgetPlanningApplication {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		String jsonString = "{\n"
				+ "  \"data\": [\n"
				+ "    {\"month\": \"Januar\", \"day\": 1, \"expenses\": true, \"value\": 100.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 2, \"expenses\": true, \"value\": 150.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 3, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 4, \"expenses\": false, \"value\": 3000.0},"
				+ "    {\"month\": \"Januar\", \"day\": 5, \"expenses\": true, \"value\": 50.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 6, \"expenses\": true, \"value\": 250.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 7, \"expenses\": true, \"value\": 70.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 8, \"expenses\": false, \"value\": 800.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 9, \"expenses\": true, \"value\": 90.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 10, \"expenses\": true, \"value\": 200.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 11, \"expenses\": true, \"value\": 110.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 11, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 11, \"expenses\": true, \"value\": 60.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 12, \"expenses\": true, \"value\": 170.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 13, \"expenses\": true, \"value\": 60.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 14, \"expenses\": true, \"value\": 220.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 14, \"expenses\": true, \"value\": 130.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 15, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 16, \"expenses\": true, \"value\": 250.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 17, \"expenses\": true, \"value\": 85.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 18, \"expenses\": true, \"value\": 160.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 19, \"expenses\": true, \"value\": 55.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 19, \"expenses\": true, \"value\": 90.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 19, \"expenses\": true, \"value\": 70.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 20, \"expenses\": true, \"value\": 230.0},\n"
				+ "    {\"month\": \"Januar\", \"day\": 30, \"expenses\": true, \"value\": 210.0},\n"			
				+ "    {\"month\": \"Februar\", \"day\": 1, \"expenses\": true, \"value\": 60.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 1, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 2, \"expenses\": true, \"value\": 170.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 3, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 4, \"expenses\": false, \"value\": 3200.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 5, \"expenses\": true, \"value\": 65.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 6, \"expenses\": true, \"value\": 180.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 7, \"expenses\": true, \"value\": 70.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 8, \"expenses\": false, \"value\": 900.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 9, \"expenses\": true, \"value\": 80.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 10, \"expenses\": true, \"value\": 150.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 11, \"expenses\": true, \"value\": 55.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 12, \"expenses\": true, \"value\": 200.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 13, \"expenses\": true, \"value\": 90.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 14, \"expenses\": true, \"value\": 170.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 15, \"expenses\": true, \"value\": 75.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 16, \"expenses\": true, \"value\": 160.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 17, \"expenses\": true, \"value\": 65.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 18, \"expenses\": true, \"value\": 180.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 19, \"expenses\": true, \"value\": 70.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 19, \"expenses\": true, \"value\": 90.0},\n"
				+ "    {\"month\": \"Februar\", \"day\": 19, \"expenses\": true, \"value\": 50.0}\n"
				+ "  ]\n"
				+ "}";
		//System.out.print(gson);
		/*Data myObject = gson.fromJson(jsonString, Data.class);
		System.out.println(myObject.getMonth());*/
		
	     DataContainer myObject = gson.fromJson(jsonString, DataContainer.class);
	     List<HandleData> data = myObject.getData();
	     for (HandleData item : data) {
	            System.out.println("Month: " + item.getMonth());
	            System.out.println("Day: " + item.getDay());
	            System.out.println("Expenses: " + item.getExpenses());
	            System.out.println("Value: " + item.getValue());
	            System.out.println();
	        }
	    }
		
		
		/*System.out.println(myObject.getDay());
		System.out.println(myObject.getExpenses());
		System.out.println(myObject.getValue());*/
	}
	


