//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        HabitService service = new HabitService();
        HabitPrinter printer = new HabitPrinter();


        int amount = readAmount(sc);
//        boolean[] bool = new boolean[ amount];
//        String[] habits = new String[ amount];
        ArrayList<Habit> habits = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            String name = readHabitName(sc, i + 1);
            Habit.Priority priority = readPriority(sc);

            boolean completed = readComplete(sc, name);

            Habit habit = new Habit(name, completed, priority);
            habits.add(habit);
//            habits[i] = sc.nextLine();
//            bool[i] = readComplete(sc,habits[i]);
//            String name = sc.nextLine();
//            boolean bool = readComplete(sc,name);


        }

        HabitService.menu(habits, sc);
        //saveToFile(habits,bool);

        int done;
//        System.out.println("Done habits: ");
//        for (Habit h : habits) {
//            if (h.isCompleted()){
//                System.out.println(h.getName());
//            }
//        }
//
//        System.out.println("Not done habits: ");
//        for (Habit h : habits) {
//            if (!h.isCompleted()){
//                System.out.println(h.getName());
//            }
//        }
        service.sortByPriority(habits);

        System.out.println("Enter the number of habit to set it completed: ");
        int UserChoice = sc.nextInt();
        sc.nextLine();
        HabitPrinter.markCompleted(habits, UserChoice - 1);
        done = service.calculateCompletion(habits);

        printer.printHabitsByStatus(habits, true, "Done habits: ");
        printer.printHabitsByStatus(habits, false, "Not done habits: " + (amount - done));

        printer.printHabitsByPriority(habits, Habit.Priority.valueOf("High"), "High priority:");
        printer.printHabitsByPriority(habits, Habit.Priority.valueOf("Medium"), "Medium priority:");
        printer.printHabitsByPriority(habits, Habit.Priority.Low, "Low priority habits:");


        System.out.println("Search habit by name: ");
        String searchName = sc.nextLine();

        Habit foundHabit = HabitService.findHabit(habits, searchName);
        if (foundHabit != null) {
            System.out.println("Found: " + foundHabit.getName()
                    + " completed: " + foundHabit.isCompleted()
                    + " priority: " + foundHabit.getPriority());
        } else {
            System.out.println("Habit not found.");
        }
        System.out.println("You've completed " + done + " of " + amount + " habits (" + ((done * 100.0) / amount) + "%)");


        System.out.println("You had a " + service.dayType(amount, done) + ".");

        if (done == amount) {
            System.out.println("You've completed all habits! Congratulations!");
        } else {
            System.out.println("You haven't completed " + (amount - done) + " habits yet");
        }
        sc.close();

    }

    public static String readHabitName(Scanner sc, int number) {
        while (true) {
            System.out.println("Enter the name of habit #" + number + ": ");
            String name = sc.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Habit name cannot be empty.");
        }
    }


    public static int readAmount(Scanner sc) {
        int amount;
        while (true) {
            System.out.println("How many habits do you want to add: ");
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                sc.nextLine();

                if (amount > 0) {
                    break;
                } else {
                    System.out.println("Please enter a number greater than 0.");
                }
            } else {
                System.out.println("Invalid input. Enter a whole number.");
                sc.nextLine();
            }
        }
        return amount;
    }

    public static Habit.Priority readPriority(Scanner sc) {
        while (true) {
            System.out.println("Enter priority: low/medium/high: ");
            String answer = sc.nextLine().trim().toLowerCase();

            if (answer.equals("low")) {
                return Habit.Priority.Low;

            } else if (answer.equals("medium")) {
                return Habit.Priority.Medium;
            } else if (answer.equals("high")) {
                return Habit.Priority.High;
            } else {
                System.out.println("Invalid input. Enter low, medium or high.");
            }
        }
    }

    public static boolean readComplete(Scanner sc, String habitName) {

        while (true) {
            System.out.println("The " + habitName + " is done? yes/no");
            String answer = sc.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("y")) {
                return true;
            } else if (answer.equals("no") || answer.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);

            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                sc.nextLine();
                return number;
            } else {
                System.out.println("Invalid input. Enter a number.");
                sc.nextLine();
            }
        }
    }

//    public static void saveToFile(String[] habits, boolean[] bool) throws IOException {
//        Path path = Path.of("habits.txt");
//
//        StringBuilder content = new StringBuilder();
//
//        for (int i = 0; i < habits.length; i++) {
//            content.append(habits[i]).append(":").append(bool[i]).append("\n");
//        }
//
//        Files.writeString(path, content.toString());
//
//    }


}
