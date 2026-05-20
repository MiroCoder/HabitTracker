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