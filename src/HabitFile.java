import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class HabitFile {
    public static void saveHabits(ArrayList<Habit> habits){
        Path path = Path.of("habits.txt");

        ArrayList<String> lines = new ArrayList<>();

        for (Habit h: habits) {
            lines.add(h.getName() + " | " + h.isCompleted() + " | " + h.getPriority());
        }

        try {


            Files.write(path, lines, StandardCharsets.UTF_8);
            System.out.println("Habits saved.");
        } catch (IOException e) {
            System.out.println("Error saving habits." + e.getMessage());
        }
    }

    public static void loadHabits(ArrayList<Habit> habits){
        Path path = Path.of("habits.txt");
        try {
            habits.clear();
            Files.readAllLines(path).forEach(line -> {
                String[] parts = line.split("\\s*\\|\\s*");

                if (line.isEmpty() || parts.length < 3) {
                    return;
                }
                if (parts.length == 3) {
                    String name = parts[0];
                    boolean completed = Boolean.parseBoolean(parts[1]);
                    try {
                        Habit.Priority priority = Habit.Priority.valueOf(parts[2]);
                        habits.add(new Habit(name, completed, priority));
                    } catch(IllegalArgumentException e){
                        System.out.println("Skipped wrong priority: " + line);
                    }

                }
                //habits.add(new Habit(parts[0], Boolean.parseBoolean(parts[1]), Habit.Priority.valueOf(parts[2])));
            });

            System.out.println("Habits loaded.");
        } catch (IOException e) {
            System.out.println("Error loading habits." + e.getMessage());
        }
    }
}
