import java.util.ArrayList;
import java.util.Scanner;

public class HabitMenu {
    public static void menu(ArrayList<Habit> habits, Scanner sc) {
        while (true) {
            System.out.println("1. Show all habits\n" +
                    "2. Show done habits\n" +
                    "3. Show not done habits\n" +
                    "4. Mark habit completed\n" +
                    "5. Search habit\n" +
                    "6. Filter by priority\n" +
                    "7. Show stats\n" +
                    "8. Save habits\n" +
                    "9. Load habits\n" +
                    "0. Exit");

            int choice = Main.readInt(sc, "Choose option: ");
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

                int select = Main.readInt(sc, "Choose habit: ");

                if (select >= 1 && select <= indexes.size()) {
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
                System.out.println("Progress: " + HabitService.dayPercent(habits.size(), HabitService.calculateCompletion(habits)) + " %");
                System.out.println(HabitService.dayType(habits.size(), HabitService.calculateCompletion(habits)));

            } else if (choice == 8) {
                HabitFile.saveHabits(habits);
            } else if (choice == 9) {
                HabitFile.loadHabits(habits);
                for (Habit h : habits) {
                    System.out.println("- " + h.getName()
                            + " | completed: " + h.isCompleted()
                            + " | priority: " + h.getPriority());
                }
            } else {
                System.out.println("Not implemented yet.");
            }
        }
    }
}
