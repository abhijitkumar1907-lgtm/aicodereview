package com.codereviewer;

import java.util.ArrayList;
import java.util.List;

public class TestCode {

    // 1. Unused field
    private int unusedField = 100;

    public static void main(String[] args) {

        // 2. Unused local variable
        int unusedVariable = 50;

        // 3. Bad naming convention
        int StudentAge = 20;

        // 4. Magic number
        int salary = StudentAge * 5000;

        // 5. Null assignment / null dereference risk
        String name = null;

        System.out.println(name.length());

        // 6. Division by zero
        int divisor = 0;
        int result = salary / divisor;

        System.out.println(result);

        // 7. Empty if statement
        if (salary > 50000) {
        }

        // 8. Unnecessary if condition
        if (true) {
            System.out.println("Always executed");
        }

        // 9. Infinite loop
        int i = 0;

        while (i < 10) {
            System.out.println("Running...");
        }

        // 10. Deeply nested conditions
        if (StudentAge > 18) {

            if (salary > 20000) {

                if (name != null) {

                    if (name.length() > 5) {

                        System.out.println("All conditions satisfied");
                    }
                }
            }
        }

        // 11. Duplicate code
        System.out.println("Processing student");
        System.out.println("Processing student");

        // 12. Empty catch block
        try {

            int x = 10 / 0;

        } catch (ArithmeticException e) {

        }

        // 13. Unreachable/dead code
        calculate();

        // 14. Excessive object creation / unnecessary objects
        String a = new String("Hello");
        String b = new String("Hello");

        System.out.println(a);
        System.out.println(b);
    }

    public static int calculate() {

        int value = 100;

        return value;

        // Unreachable statement
        // System.out.println("This will never execute");
    }

    // Very long / complex method
    public static void processStudent(
            int age,
            int marks,
            boolean attendance,
            String course) {

        if (age >= 18) {

            if (marks >= 40) {

                if (attendance) {

                    if (course != null) {

                        if (course.equals("Java")) {

                            System.out.println("Java student");

                        } else if (course.equals("Python")) {

                            System.out.println("Python student");

                        } else {

                            System.out.println("Other course");
                        }

                    } else {

                        System.out.println("Course is null");
                    }

                } else {

                    System.out.println("Attendance is low");
                }

            } else {

                System.out.println("Student failed");
            }

        } else {

            System.out.println("Student is underage");
        }

        System.out.println("Processing completed");
        System.out.println("Generating report");
        System.out.println("Saving result");
        System.out.println("Sending notification");
    }
}