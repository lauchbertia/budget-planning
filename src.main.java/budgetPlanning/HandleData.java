package budgetPlanning;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.*;

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Boolean getExpenses() {
		return expenses;
	}

	public void setExpenses(Boolean expenses) {
		this.expenses = expenses;
	}

	public double getValue() {
		return value;
	}

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
	        // If the category (expense or income) doesn't exist in the inner map, create a new entry with the value
	        // If the category already exists, merge the value using Double::sum
	        monthlySum
	            .computeIfAbsent(month, k -> new LinkedHashMap<>())
	            .merge(isExpense, value, Double::sum);
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


	public void displayMaxMinForMonths() {
	    System.out.println("------------------------------------------");
	    System.out.println("------- MAXIMUM & MINIMUM PRO MONAT ------");
	    Data data = new Data();
	    DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
	    List<HandleData> dataList = myObject.getData();

	    String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

	    // Map to store statistics (collecting max/ min) for each month
	    // the statistics map has submaps for each category (expenses -> true, income -> false)
	    Map<String, Map<Boolean, DoubleSummaryStatistics>> monthStatistics = new HashMap<>();

	    // Initialize the map with empty statistics for each month
	    Arrays.stream(months).forEach(month -> {
	        Map<Boolean, DoubleSummaryStatistics> statistics = new HashMap<>();
	        statistics.put(true, new DoubleSummaryStatistics());
	        statistics.put(false, new DoubleSummaryStatistics());
	        monthStatistics.put(month, statistics);
	    });

	    // Calculate statistics using streams
	    dataList.forEach(item -> {
	        String month = item.getMonth();
	        boolean isExpense = item.getExpenses();
	        double value = item.getValue();

	        Map<Boolean, DoubleSummaryStatistics> statistics = monthStatistics.get(month);
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
	    System.out.println("\t \t --> " + getSavingsPotencial(total));
	    System.out.println();
	}

	private String getSavingsPotencial(double total) {
	    if (total < 10) {
	        return "Kein Sparpotenzial";
	    } else if (total < 200) {
	        return "Mittel Sparpotenzial";
	    } else {
	        return "Hohes Sparpotenzial";
	    }
	}
}
