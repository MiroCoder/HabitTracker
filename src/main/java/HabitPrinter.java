import java.util.ArrayList;

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