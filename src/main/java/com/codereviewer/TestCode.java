package com.codereviewer;

public class TestCode {

    public static void processStudent() {

        System.out.println("Starting");

        int marks1 = 80;
        int marks2 = 70;
        int marks3 = 90;

        int total = marks1 + marks2 + marks3;

        double average = total / 3.0;

        System.out.println("Marks 1: " + marks1);
        System.out.println("Marks 2: " + marks2);
        System.out.println("Marks 3: " + marks3);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);

        if (average >= 90) {
            System.out.println("Grade A");
        } else if (average >= 75) {
            System.out.println("Grade B");
        } else if (average >= 60) {
            System.out.println("Grade C");
        } else {
            System.out.println("Fail");
        }

        System.out.println("Checking attendance");
        System.out.println("Checking assignments");
        System.out.println("Checking projects");
        System.out.println("Checking practicals");
        System.out.println("Checking behavior");
        System.out.println("Processing completed");
    }

    public static void main(String[] args) {
        processStudent();
    }
}