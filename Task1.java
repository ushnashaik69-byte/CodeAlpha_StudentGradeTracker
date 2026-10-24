
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> rollNumbers = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allMarks = new ArrayList<>();
        ArrayList<Double> averages = new ArrayList<>();
        ArrayList<String> grades = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== STUDENT GRADE MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Class Summary Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter roll number: ");
                    int rollNo = sc.nextInt();

                    System.out.print("Enter number of subjects: ");
                    int n = sc.nextInt();

                    if (n <= 0) {
                        System.out.println(
                                "Number of subjects must be positive.");
                        break;
                    }

                    ArrayList<Integer> marks = new ArrayList<>();
                    int total = 0;
                    boolean valid = true;

                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter marks for subject "
                                + (i + 1) + " (0-100): ");
                        int mark = sc.nextInt();

                        if (mark < 0 || mark > 100) {
                            System.out.println("Invalid marks!");
                            valid = false;
                            break;
                        }

                        marks.add(mark);
                        total += mark;
                    }
                    sc.nextLine();

                    if (!valid) {
                        System.out.println(
                                "Student was not saved. Try again.");
                        break;
                    }

                    double average = (double) total / n;

                    String grade;
                    if (average >= 90) {
                        grade = "A";
                    } else if (average >= 75) {
                        grade = "B";
                    } else if (average >= 50) {
                        grade = "C";
                    } else {
                        grade = "Fail";
                    }

                    names.add(name);
                    rollNumbers.add(rollNo);
                    allMarks.add(marks);
                    averages.add(average);
                    grades.add(grade);

                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (names.isEmpty()) {
                        System.out.println("No students available.");
                        break;
                    }

                    System.out.println("\n===== ALL STUDENTS REPORT =====");

                    for (int i = 0; i < names.size(); i++) {
                        ArrayList<Integer> m = allMarks.get(i);
                        int sum = 0;

                        for (int mark : m) {
                            sum += mark;
                        }

                        System.out.println("\nName: " + names.get(i));
                        System.out.println("Roll No: "
                                + rollNumbers.get(i));
                        System.out.println("Marks: " + m);
                        System.out.println("Total: " + sum);
                        System.out.printf("Average: %.2f%n",
                                averages.get(i));
                        System.out.println("Highest: "
                                + Collections.max(m));
                        System.out.println("Lowest: "
                                + Collections.min(m));
                        System.out.println("Grade: " + grades.get(i));
                    }
                    break;

                case 3:
                    if (names.isEmpty()) {
                        System.out.println("No students available.");
                        break;
                    }

                    double classTotal = 0;
                    int topper = 0;
                    int passed = 0;

                    for (int i = 0; i < names.size(); i++) {
                        classTotal += averages.get(i);

                        if (averages.get(i) > averages.get(topper)) {
                            topper = i;
                        }

                        if (!grades.get(i).equals("Fail")) {
                            passed++;
                        }
                    }

                    System.out.println("\n===== CLASS SUMMARY =====");
                    System.out.println("Total Students: " + names.size());
                    System.out.printf("Class Average: %.2f%n",
                            classTotal / names.size());
                    System.out.println("Topper: " + names.get(topper));
                    System.out.printf("Topper Average: %.2f%n",
                            averages.get(topper));
                    System.out.println("Passed: " + passed);
                    System.out.println("Failed: "
                            + (names.size() - passed));
                    break;

                case 4:
                    System.out.println("Thank you! Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
