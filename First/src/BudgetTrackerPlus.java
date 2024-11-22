import java.util.Scanner;

public class BudgetTrackerPlus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Budget Tracker Plus\n");
        System.out.println("Please enter your monthly income: $");
        double income = scanner.nextDouble();
        System.out.println("Please enter your rent cost: $");
        double rent = scanner.nextDouble();
        System.out.println("Please enter your groceries expenses: $");
        double groceries = scanner.nextDouble();
        System.out.println("Please enter your transportation expenses: $");
        double transportation = scanner.nextDouble();
        System.out.println("Please enter your entertainment expense: $");
        double entertainment = scanner.nextDouble();

        double totalExpenses = rent+groceries+transportation+entertainment;
        double rentPercentage = (rent / income) * 100;
        double groceriesPercentage = (groceries / income) * 100;
        double transportationPercentage = (transportation / income) * 100;
        double entertainmentPercentage = (entertainment / income) * 100;
        double result = income - totalExpenses;

        //Resumen total
        System.out.println("Your net profit each month is: $" + result);
        System.out.println("\n******Budget Summary******\n");
        System.out.println("The percentage of rent is: %" + rentPercentage);
        System.out.println("The percentage of groceries is: %" + groceriesPercentage);
        System.out.println("The percentage of transportation is: %" + transportationPercentage);
        System.out.println("The percentage of entertainment is: %" + entertainmentPercentage);
    }
}