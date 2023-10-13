package budgetPlanning;
import com.google.gson.Gson;

public class BudgetPlanningApplication {

	public static void main(String[] args) {
		System.out.print("jf");
		
		Gson gson = new Gson();
		String jsonString = "{\"name\":\"John\",\"age\":30}";
		//System.out.print(gson);
		pERSON myObject = gson.fromJson(jsonString, pERSON.class);
		
		System.out.println(myObject.getName());
	}
	

}

