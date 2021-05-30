import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpaceShip {

  private String shipName;
  private Captain captain;
  List<PersonOnShip> peopleOnShip;

  public SpaceShip(String shipName){
    this.shipName = shipName;
    this.captain = null;
    peopleOnShip = new ArrayList<>();
  }

  public String getShipName(){
    return this.shipName;
  }

  public Captain getCaptain() {
    return this.captain;
  }

  public void assignCaptain(Captain captain){
    this.captain = captain;
  }

  public void boardShip(String manifestFileName) throws FileNotFoundException {
    try{
      File manifestFile = new File(manifestFileName);
      Scanner scanManifest = new Scanner(manifestFile);
      while(scanManifest.hasNextLine()){
       String lineOnManifest = scanManifest.nextLine();
       String[] personOnShip = lineOnManifest.split(" ");
       String firstName = personOnShip[1];
       String nickname = "";
       String lastName;
       if(personOnShip.length == 4) {
         nickname = personOnShip[2];
         lastName = personOnShip[3];
       } else {
         lastName = personOnShip[2];
       }
       if(personOnShip[0].equalsIgnoreCase("passenger")){
         Passenger newPassenger = new Passenger(firstName, lastName, nickname);
         peopleOnShip.add(newPassenger);
       } else if(personOnShip[0].equalsIgnoreCase("captain")){
         Captain newCaptain = new Captain(firstName, lastName, nickname, "Captain");
         assignCaptain(newCaptain);
         peopleOnShip.add(newCaptain);
       } else {
         String occupation = personOnShip[0];
         CrewMember newCrewMember = new CrewMember(firstName, lastName, nickname, occupation);
         peopleOnShip.add(newCrewMember);
       }
      }
    } catch(FileNotFoundException exception){
      System.out.println("File not found.");
    }
  }

  public int passengerCount(){
    int count = 0;
    for(PersonOnShip personOnShip : peopleOnShip){
      if(personOnShip instanceof Passenger){
        count++;
      }
    }
    return count;
  }

  public int crewCount(){
    int count = 0;
    for(PersonOnShip personOnShip : peopleOnShip){
      if (personOnShip instanceof Captain) {
        count++;
      } else if(personOnShip instanceof CrewMember){
        count++;
      }
    }
    return count;
  }

  public String manifest() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n\n          Ship Manifest for ");
    builder.append(this.shipName);
    builder.append("\n================================================");
    builder.append("\nCaptained by: ");
    builder.append(this.captain.getDescription());
    builder.append("\n");
    builder.append("\nNumber of CrewMembers on Ship: ");
    builder.append(this.crewCount());
    builder.append("\n");
    builder.append("\n");
    for(PersonOnShip personOnShip : peopleOnShip) {
      if(personOnShip instanceof CrewMember) {
        builder.append(personOnShip.getDescription());
        builder.append("\n");
      }
    }
    builder.append("\nNumber of Passengers on Ship: ");
    builder.append(this.passengerCount());
    builder.append("\n");
    for(PersonOnShip personOnShip : peopleOnShip) {
      if(personOnShip instanceof Passenger) {
        builder.append(personOnShip.getDescription());
        builder.append("\n");
      }
    }
    builder.append("\nTotal People on Ship: ");
    builder.append(this.peopleOnShip.size());
    return builder.toString();
  }
}
