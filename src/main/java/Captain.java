import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Captain extends CrewMember{

  public Captain(String firstName, String lastName, String nickname, String occupation) {
    super(firstName, lastName, nickname, occupation);
  }

  @Override
  public String getDescription(){
    return super.getDescription() + " (Captain)";
  }

  public void writeToLog(String shipName, String captainMessage) throws IOException {
    try{
      // Create file if does not already exist
      File captainLogFile = new File(shipName + "-captain-log.txt");
      FileWriter captainLogFW = new FileWriter(captainLogFile, true);
      StringBuilder builder = new StringBuilder();

      // Log Captain's name and ship
      builder.append("Captain's Log for the " + shipName + "\n");
      builder.append(this.getDescription());
      builder.append("\n");

      // Add date to log
      DateFormat dateFormatter = DateFormat.getDateInstance();
      Calendar calendar = Calendar.getInstance();
      builder.append(dateFormatter.format(calendar.getTime()));
      builder.append("\n- ");

      // Add captain's message
      builder.append(captainMessage);
      builder.append("\n");
      builder.append("\n");
      captainLogFW.write(builder.toString());
      captainLogFW.close();
      if(captainLogFile.exists()){
        System.out.println("Captain message successfully saved to file.");
      }
    } catch(IOException exception) {
      System.out.println("File not found or not able to write to file.");
    }
  }

  public String readFromLog(String shipName) throws FileNotFoundException {
    File captainLogFile = new File(shipName + "-captain-log.txt");
    String captainLog = "File not found.";
    try{
      Scanner scanCaptainLog = new Scanner(captainLogFile);
      StringBuilder builder = new StringBuilder();

      while(scanCaptainLog.hasNextLine()){
        builder.append(scanCaptainLog.nextLine());
        builder.append("\n");
      }
      captainLog = builder.toString();
    } catch(FileNotFoundException exception) {
      System.out.println("Captain log file not found. Please writeToLog to create file before reading from it.");
    }

    return captainLog;
  }

  public String bulletin() {
    return "Welcome aboard! I am Captain " + this.getFirstName() + " " + this.getLastName() + ", and we'll be on our way shortly.";
  }

}
