//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


class Habit {
    private String name;
    private boolean completed;
    private Priority priority;

    public Habit(String name, boolean completed, Priority priority) {
        this.name = name;
        this.completed = completed;
        this.priority = priority;
    }

    enum Priority {
        High,
        Medium,
        Low

    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Priority getPriority() {
        return priority;
    }

}

class HabitService {

    public static int calculateCompletion(ArrayList<Habit> habits) {
        int counter = 0;
        for (Habit h : habits) {
            if (h.isCompleted()) {
                counter++;
            }
        }
        return counter;
    }

    public static Habit findHabit(ArrayList<Habit> habits, String name) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(name)) {
                return h;
            }
        }
        return null;
    }

    public static String dayType(int habits_amount, int habits_done) {
        double percent = (habits_done * 100.0) / habits_amount;

        if (percent == 100.0) {
            return "Perfect day";
        } else if (percent >= 70) {
            return "Strong day";
        } else if (percent >= 50) {
            return "System day";
        } else if (percent > 0) {
            return "Recovery day";
        } else {
            return "Zero day";
        }

    }

    public static double dayPercent(int totalHabits,int completedHabits) {
        double percent = (completedHabits * 100.0) / totalHabits;
        return percent;
    }

    public void sortByPriority(ArrayList<Habit> habits) {
        habits.sort((h1, h2) -> h1.getPriority().ordinal() - h2.getPriority().ordinal());
    }

    public static void menu(ArrayList<Habit> habits, Scanner sc) {
        while (true) {
            System.out.println("1. Show all habits\n" +
                    "2. Show done habits\n" +
                    "3. Show not done habits\n" +
                    "4. Mark habit completed\n" +
                    "5. Search habit\n" +
                    "6. Filter by priority\n" +
                    "7. Show stats\n" +
                    "0. Exit");

            System.out.println("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                for (Habit h : habits) {
                    System.out.println("- " + h.getName()
                            + " | completed: " + h.isCompleted()
                            + " | priority: " + h.getPriority());
                }
            } else if (choice == 0) {
                System.out.println("Execute.");
                break;
            } else if (choice == 2) {
                System.out.println("Done habits: ");
                for (Habit h : habits) {
                    if (h.isCompleted()) {
                        System.out.println(h.getName());
                    }
                }
            } else if (choice == 3) {
                System.out.println("Not done habits: ");
                for (Habit h : habits) {
                    if (!h.isCompleted()) {
                        System.out.println(h.getName());
                    }
                }
            } else if (choice == 4) {
                System.out.println("Choose habit to mark completed: ");

                ArrayList<Integer> indexes = new ArrayList<>();
                int position = 1;

                for (int i = 0; i < habits.size(); i++) {
                    Habit h = habits.get(i);

                    if (!h.isCompleted()) {
                        System.out.println(position + ". " + h.getName());
                        indexes.add(i);
                        position++;
                    }
                }

                int select = sc.nextInt();
                sc.nextLine();

                if (select >= 1 & select <= indexes.size()) {
                    int realIndex = indexes.get(select - 1);
                    HabitPrinter.markCompleted(habits, realIndex);
                    System.out.println(habits.get(realIndex).getName() + " completed");
                } else {
                    System.out.println("Wrong choice.");
                }
            } else if (choice == 5) {
                System.out.println("Enter habit name: ");
                String searchName = sc.nextLine();

                Habit foundHabit = HabitService.findHabit(habits, searchName);
                if (foundHabit != null) {
                    System.out.println("Found: " + foundHabit.getName()
                            + " completed: " + foundHabit.isCompleted()
                            + " priority: " + foundHabit.getPriority());
                } else {
                    System.out.println("Habit not found.");
                }
            } else if (choice == 6) {
                Habit.Priority priority = Main.readPriority(sc);
                HabitPrinter.printHabitsByPriority(habits, priority, priority + " priority habits:");
            } else if (choice == 7) {
                System.out.println("Total habits: " + habits.size());
                System.out.println("Completed: " + HabitService.calculateCompletion(habits));
                System.out.println("Not completed: " + (habits.size() - HabitService.calculateCompletion(habits)));
//                HabitPrinter.printHabitsByStatus(habits, true, "Completed habits: ");
//                HabitPrinter.printHabitsByStatus(habits,false, "Not completed yet: ");
                System.out.println("Progress: " + HabitService.dayPercent(habits.size(), HabitService.calculateCompletion(habits)));
                System.out.println(HabitService.dayType(habits.size(), HabitService.calculateCompletion(habits)));

            } else {
                System.out.println("Not implemented yet.");
            }
        }
    }


}

class HabitPrinter {
    public static void printHabitsByStatus(ArrayList<Habit> habits, boolean completed, String title) {
        System.out.println(title);

        boolean found = false;

        for (Habit h : habits) {
            if (h.isCompleted() == completed) {
                System.out.println("- " + h.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("- none");

        }
    }

    public static void markCompleted(ArrayList<Habit> habits, int index) {
        if (index >= 0 && index < habits.size()) {
            habits.get(index).setCompleted(true);

        } else {
            System.out.println("Wrong habits number! ");
        }

    }

    public static void printHabitsByPriority(ArrayList<Habit> habits, Habit.Priority priority, String title) {
        System.out.println(title);

        boolean found = false;


        for (Habit h : habits) {


            if (h.getPriority() == priority) {
                System.out.println("- " + h.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("-  none");
        }
    }
}

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
            System.out.println("Enter the name of habit #" + (i + 1) + ": ");
            String name = sc.nextLine();
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
