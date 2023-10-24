package budgetPlanning;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

	public List showFullData() {
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();
		for (HandleData item : dataList) {
			System.out.println("Month: " + item.getMonth());
			System.out.println("Day: " + item.getDay());
			System.out.println("Expenses: " + item.getExpenses());
			System.out.println("Value: " + item.getValue());
			System.out.println();
		}
		return dataList;
	}

	public void getSumOfBalanceOfMonth() {
		Data data = new Data();
		DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
		List<HandleData> dataList = myObject.getData();

		String currentMonth = "";
		double totalIncome = 0;
		double totalExpenses = 0;

		for (HandleData item : dataList) {
			String month = item.getMonth();
			double value = item.getValue();
			boolean isExpense = item.getExpenses();

			if (!month.equals(currentMonth)) {
				if (!currentMonth.isEmpty()) {
					// Print the totals for the previous month
					System.out.println("Month: " + currentMonth);
					System.out.println("Income: " + totalIncome);
					System.out.println("Expenses: " + totalExpenses);
				}

				// Reset totals for the new month
				currentMonth = month;
				totalIncome = 0;
				totalExpenses = 0;
			}

			if (isExpense) {
				totalExpenses += value;
			} else {
				totalIncome += value;
			}
		}

		// Print the totals for the last month
		System.out.println("");
		System.out.println("Month: " + currentMonth);
		System.out.println("Income: " + totalIncome);
		System.out.println("Expenses: " + totalExpenses);
		System.out.println("");
	}

	public List getMaxOfYear() {
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

		System.out.println("This is the max value: " + maxValue);

		return dataList;
	}

	/*
	 * public List getMinOfYear() { Data data = new Data(); DataContainer myObject =
	 * data.gson.fromJson(data.jsonString, DataContainer.class); List<HandleData>
	 * dataList = myObject.getData(); //double value; double minValue = 500;
	 * 
	 * Collections.max();
	 * 
	 * for (HandleData item : dataList) { double value = item.getValue(); //double
	 * minValue = item.getValue();
	 * 
	 * if (value < minValue) { minValue = value; } }
	 * 
	 * System.out.println("This is the min value: " + minValue);
	 * 
	 * return dataList; }
	 */

	public void displayMaxMinForMonths() {
	    Data data = new Data();
	    DataContainer myObject = data.gson.fromJson(data.jsonString, DataContainer.class);
	    List<HandleData> dataList = myObject.getData();

	    String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

	    // Initialize arrays to store max and min values for each month
	    double[] maxIncomeByMonth = new double[12];
	    double[] minIncomeByMonth = new double[12];
	    double[] maxExpenseByMonth = new double[12];
	    double[] minExpenseByMonth = new double[12];
	    
	 // Initialize minIncomeByMonth and minExpenseByMonth with high initial values
	    Arrays.fill(minIncomeByMonth, Double.MAX_VALUE);
	    Arrays.fill(minExpenseByMonth, Double.MAX_VALUE);

	    for (HandleData item : dataList) {
	        String month = item.getMonth();
	        int monthIndex = Arrays.asList(months).indexOf(month);
	        boolean isExpense = item.getExpenses();

	        double value = item.getValue();

	        // Update max and min values for the specific month
	        if (!isExpense) {
	            if (value > maxIncomeByMonth[monthIndex]) {
	                maxIncomeByMonth[monthIndex] = value;
	            }
	            if (value < minIncomeByMonth[monthIndex]) {
	                minIncomeByMonth[monthIndex] = value;
	                
	            }
	        } else {
	            if (value > maxExpenseByMonth[monthIndex]) {
	                maxExpenseByMonth[monthIndex] = value;
	            }
	            if (value < minExpenseByMonth[monthIndex]) {
	                minExpenseByMonth[monthIndex] = value;
	            }
	        }
	    }

	    // Output the results for each month
	    for (int i = 0; i < 12; i++) {
	        String month = months[i];
	        System.out.println("----------------------------------------");
	        System.out.println(month);
	        System.out.println("----------------------------------------");
	        System.out.println("\tEinkommen");
	        System.out.println("\t \tmaximum: " + maxIncomeByMonth[i]);
	        System.out.println("\t \tminimum: " + minIncomeByMonth[i]);
        	System.out.println("\tAusgaben");
	        System.out.println("\t \tmaximum: " + maxExpenseByMonth[i]);
	        System.out.println("\t \tminimum: " + minExpenseByMonth[i]);
	        System.out.println("");
	        
	    
	
		}
	    
	    
	}
}
