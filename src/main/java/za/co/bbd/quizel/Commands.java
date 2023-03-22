package za.co.bbd.quizel;

public class Commands {
    public Commands() {
    }
    public static void ProcessInput(String input){
        String userInput = input.toLowerCase().trim();
        if (userInput.equals("quit") || userInput.equals("q")) {
            CloseProgram();
        } else if (userInput.equals("A") || userInput.equals("B") || userInput.equals("C") || userInput.equals("D")) {
            ProcessUserAnswer(userInput);
        } else {
            ListCommands();
        }

    }

    public static void ListCommands(){
        System.out.println("Place Holder");
    }

    public static void ProcessUserAnswer(String answer){
        return;
    }

    public static void CloseProgram(){
        System.exit(0);
    }
}
