import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private List<String> menuOptions;

  public Menu(String ...args){
    menuOptions = new ArrayList<String>(Arrays.asList(args));
  }

  public void display() {
    StringBuilder builder = new StringBuilder();
    builder.append("==============================\n");
    int counter = 0;
    for(String option : menuOptions){
      counter++;
      builder.append(counter);
      builder.append(") ");
      builder.append(option);
      builder.append("\n");
    }
    builder.append("==============================\n");
    builder.append("Options 1 - " + menuOptions.size());
    System.out.println(builder.toString());
  }

  public String getInput(String prompt) {
    Scanner scan = new Scanner(System.in);
    System.out.println(prompt);
    String response = scan.nextLine();
    return response;
  }

}
