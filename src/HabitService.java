import java.util.ArrayList;
import java.util.Scanner;

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


}