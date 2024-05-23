package com.tasknoter;

public class Main {

    public static void main(String[] args) {
        TaskApp taskApp = new TaskApp();
        try {
            switch (args[0]) {
                case "--list", "-l" -> {}
                case "--add", "-a" -> taskApp.addTask(args[1]);
                case "--complete", "-c" -> taskApp.completeTask(Integer.parseInt(args[1]));
                case "--remove", "-r" -> taskApp.removeTask(Integer.parseInt(args[1]));
                default -> System.out.println("unknown argument");
            }
            TaskAppUtil.printTable(taskApp);
        } catch (NumberFormatException e) {
            System.err.format("%s%n", e);
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException aie) {
            System.out.println("""
                    Available options are:
                        -l, --list\tPrint the tasks in a table
                        -a, --add\tAdd a new task to the table
                        -c, --complete\tSet a task as completed
                        -r, --remove\tRemove a task from the table
                    
                    Examples:
                        taskapp.jar -l
                        taskapp.jar -a "new todo"
                        taskapp.jar -c 5
                        taskapp.jar -r 1""");
            System.exit(0);
        }
    }
}
