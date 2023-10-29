package budgetPlanning;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Constructor of Object
 */
public class HandleData {

	private String month;
	private int day;
	private Boolean expenses;
	private double value;

	public HandleData() {

	}

	public HandleData(String month, int day, Boolean expenses, double value) {
		super();
		this.month = month;
		this.day = day;
		this.expenses = expenses;
		this.value = value;
	}

	/**
	 * Get the month
	 * 
	 * @return String with Name of Month
	 */

	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month
	 * 
	 * @param month as String
	 */

	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Gets the wanted number of a day within a month
	 * 
	 * @return day as a number (integer)
	 */

	public int getDay() {
		return day;
	}

	/**
	 * sets the day
	 * 
	 * @param day
	 */

	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Gets the all the expenses of every month
	 * 
	 * @return expenses as an boolean value. If expenses are false, then its
	 *         displayed as income
	 */

	public Boolean getExpenses() {
		return expenses;
	}

	/**
	 * sets expenses as true or false
	 * 
	 * @param expenses
	 */

	public void setExpenses(Boolean expenses) {
		this.expenses = expenses;
	}

	/**
	 * gets the value of data object
	 * 
	 * @return value as an double
	 */

	public double getValue() {
		return value;
	}

	/**
	 * sets the value of data object
	 * 
	 * @param value
	 */

	public void setValue(double value) {
		this.value = value;
	}

	public List<HandleData> showFullData() {
		System.out.println("------------------------------------------");
		System.out.println("------------------ DATEN -----------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();
		for (HandleData item : dataList) {
			System.out.println("------------------------------------------");
			System.out.println("Monat: " + item.getMonth());
			System.out.println("------------------------------------------");
			System.out.println("\tTag: " + item.getDay());
			System.out.println("\tAusgaben: " + item.getExpenses());
			System.out.println("\tWert: " + item.getValue());
			System.out.println();
		}
		return dataList;
	}

	/**
	 * Displays the sum of the balance monthly.
	 * 
	 */

	/**
	 * Shows the sum of income and expenses of each each month
	 */
	public void getSumOfBalanceOfMonth() {
		System.out.println("------------------------------------------");
		System.out.println("----- EINKOMMEN & AUSGABEN PRO MONAT -----");

		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		// LinkedHashMap: keeps the order of months
		Map<String, Map<Boolean, Double>> monthlySum = new LinkedHashMap<>();

		for (HandleData item : dataList) {
			String month = item.getMonth();
			boolean isExpense = item.getExpenses();
			double value = item.getValue();

			// calculate (compute) the monthly sums with LinkedHashMap
			// If the month doesn't exist in the outer map, create a new LinkedHashMap
			// If the category (expense or income) doesn't exist in the inner map, create a
			// new entry with the value
			// If the category already exists, merge the value using Double::sum
			monthlySum.computeIfAbsent(month, k -> new LinkedHashMap<>()).merge(isExpense, value, Double::sum);
		}

		// Iterate over the LinkedHashMap to print the results
		monthlySum.forEach((month, categorySum) -> {
			System.out.println("------------------------------------------");
			System.out.println("Monat: " + month);
			System.out.println("------------------------------------------");

			System.out.println("\tEinkommen: " + categorySum.getOrDefault(false, 0.0));
			System.out.println("\tAusgaben: " + categorySum.getOrDefault(true, 0.0));
			System.out.println("");
		});
	}

	public List<HandleData> getMaxOfYear() {
		System.out.println("------------------------------------------");
		System.out.println("-------------- JAHR MAXIMUM --------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();
		double value = 0;
		double maxValue = 0;

		for (HandleData item : dataList) {
			value = item.getValue();

			if (value > maxValue) {
				maxValue = value;
			}
		}

		System.out.println("Das ist der maximum Wert: " + maxValue);

		return dataList;
	}

	/**
	 * Shows the maximum of income and maximum of expenses for every month within
	 * one year
	 * 
	 */

	/**
	 * Shows the maximum & minimum for each month
	 */
	public void displayMaxMinForMonths() {
		System.out.println("------------------------------------------");
		System.out.println("------- MAXIMUM & MINIMUM PRO MONAT ------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		String[] months = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };

		// Map to store statistics (collecting max/ min) for each month
		// the statistics map has submaps for each category (expenses -> true, income ->
		// false)
		Map<String, Map<Boolean, DoubleSummaryStatistics>> monthStatistics = new HashMap<>();

		// Initialize the map with empty statistics for each month
		Arrays.stream(months).forEach(month -> {
			Map<Boolean, DoubleSummaryStatistics> statistics = new HashMap<>();
			statistics.put(true, new DoubleSummaryStatistics());
			statistics.put(false, new DoubleSummaryStatistics());
			monthStatistics.put(month, statistics);
		});

		// Calculate statistics (max & min values) for each month
		dataList.forEach(item -> {
			String month = item.getMonth();
			boolean isExpense = item.getExpenses();
			double value = item.getValue();

			// Get the statistics map for the current month
			// Map contains statistics (expenses & income) for that month
			Map<Boolean, DoubleSummaryStatistics> statistics = monthStatistics.get(month);
			// Updates the statistics (for current month & expense category)
			// accept(): updates max/ min value for income/ expense
			statistics.get(isExpense).accept(value);
		});

		// Print the results for each month
		Arrays.stream(months).forEach(month -> {
			System.out.println("------------------------------------------");
			System.out.println(month);
			System.out.println("------------------------------------------");

			System.out.println("\tEinkommen");
			System.out.println("\t \tmaximum: " + monthStatistics.get(month).get(false).getMax());
			System.out.println("\t \tminimum: " + monthStatistics.get(month).get(false).getMin());

			System.out.println("\tAusgaben");
			System.out.println("\t \tmaximum: " + monthStatistics.get(month).get(true).getMax());
			System.out.println("\t \tminimum: " + monthStatistics.get(month).get(true).getMin());

			System.out.println();
		});
	}

	public void savingsPotencial() {
		System.out.println("------------------------------------------");
		System.out.println("-------------- SPARPOTENZIAL -------------");
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		// Store the monthly totals
		// preserve the order of the months with LinkedHashMap
		Map<String, Double> monthTotals = new LinkedHashMap<>();

		// Calculate the monthly totals
		dataList.forEach(item -> {
			String month = item.getMonth();
			double value = item.getValue();
			boolean isExpense = item.getExpenses();

			// initialize a monthly total
			monthTotals.putIfAbsent(month, 0.0);

			// Update the total for the current month
			monthTotals.compute(month, (key, total) -> isExpense ? total - value : total + value);
		});

		// Print the summary for each month
		monthTotals.forEach((month, total) -> printMonthSummary(month, total));
	}

	private void printMonthSummary(String month, double total) {
		System.out.println("------------------------------------------");
		System.out.println("Monat: " + month);
		System.out.println("------------------------------------------");
		System.out.println("\tEinkommen: " + Math.max(total, 0));
		System.out.println("\tAusgaben: " + Math.max(-total, 0));
		System.out.println("\tDifferenz: " + total);
		System.out.println("\t \t --> " + getSavingsPotential(total));
		System.out.println();
	}

	private String getSavingsPotential(double total) {
	    return Stream.of(
	            new Object[] { 10.0, "Kein Sparpotenzial" },
	            new Object[] { 200.0, "Mittel Sparpotenzial" }
	    )
	    .filter(array -> total < (Double) array[0])
	    .map(array -> (String) array[1])
	    .findFirst()
	    .orElse("Hohes Sparpotenzial");
	}
}
