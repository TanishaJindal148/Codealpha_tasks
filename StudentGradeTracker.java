import java.util.ArrayList;
import java.util.Scanner;
public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> marks = new ArrayList<>();
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.next();
            names.add(name);
            System.out.print("Enter marks: ");
            int mark = sc.nextInt();
            marks.add(mark);
        }
        // Calculations
        int total = 0;
        int highest = marks.get(0);
        int lowest = marks.get(0);
        for (int m : marks) {
            total += m;
            if (m > highest)
                highest = m;
            if (m < lowest)
                lowest = m;
        }
        double average = (double) total / n;
        // Display summary
        System.out.println("\n----- Student Summary Report -----");
        System.out.println("Name\tMarks");
        for (int i = 0; i < n; i++) {
            System.out.println(names.get(i) + "\t" + marks.get(i));
        }
        System.out.println("\nAverage Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        sc.close();
    }
}
