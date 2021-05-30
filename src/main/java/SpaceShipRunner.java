import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SpaceShipRunner {
  private static final String FIREFLY_MANIFEST_FILE = "./crewManifest.txt";
  private static final String MOYA_MANIFEST_FIlE = "./farscapeManifest.txt";


  public static void main(String[] args) throws IOException {
    Menu menu = new Menu("Display Manifest", "Read Captain Bulletin", "Write to Captain's Log", "Read from Captain's Log", "Exit");
    boolean exit = true;
    boolean shipFound = false;
    String shipName = menu.getInput("\nGreetings! I am your automated dock assistant. Please enter the name of your ship.");
    SpaceShip ship = null;

    while(!shipFound) {
      if(shipName.equalsIgnoreCase("firefly")){
        ship = new SpaceShip(shipName);
        ship.boardShip(FIREFLY_MANIFEST_FILE);
        System.out.println("Successfully boarded " + shipName);
        shipFound = true;
        exit = false;
      } else if (shipName.equalsIgnoreCase("moya")) {
        ship = new SpaceShip(shipName);
        ship.boardShip(MOYA_MANIFEST_FIlE);
        System.out.println("Successfully boarded " + shipName);
        shipFound = true;
        exit = false;
      } else {
        shipName = menu.getInput("Ship not found. Please enter another ship name.");
      }

      while(!exit) {
        if (shipName.equalsIgnoreCase("firefly") || shipName.equalsIgnoreCase("moya")) {
          // Display menu
          System.out.println("\nOptions for " + shipName);
          menu.display();
          String input = menu.getInput("Make a choice: ");

          // Evaluate choices
          if (input.equalsIgnoreCase("1")) {
            System.out.println(ship.manifest());
          } else if (input.equalsIgnoreCase("2")) {
            System.out.println(ship.getCaptain().bulletin());
          } else if (input.equalsIgnoreCase("3")) {
            String captainMessage = menu
                .getInput("Greetings, captain! What message would you like to log?");
            ship.getCaptain().writeToLog(ship.getShipName(), captainMessage);
          } else if (input.equalsIgnoreCase("4")) {
            String captainsLog = ship.getCaptain().readFromLog(ship.getShipName());
            System.out.println(captainsLog);
          } else if (input.equalsIgnoreCase("5")) {
            exit = true;
            input = menu.getInput("Would you like to enter another ship's name? (y/n)");
            if (input.equalsIgnoreCase("y")) {
              // Build new ship if selected
              shipFound = false;
              ship = null;
              shipName = menu.getInput("Please enter the name of the ship.");
              if (shipName.equalsIgnoreCase("firefly")) {
                ship = new SpaceShip(shipName);
                ship.boardShip(FIREFLY_MANIFEST_FILE);
                shipFound = true;
                System.out.println("Successfully boarded " + shipName);
                exit = false;
              } else if (shipName.equalsIgnoreCase("moya")) {
                ship = new SpaceShip(shipName);
                ship.boardShip(MOYA_MANIFEST_FIlE);
                System.out.println("Successfully boarded " + shipName);
                shipFound = true;
                exit = false;
              }
            } else {
              shipFound = true;
              System.out.println("Thank you for using the helpful dock assistant.");
            }

          } else {
            input = menu.getInput("Please make a valid selection using numbers 1 - 6");
          }
        }
        else {
          shipName = menu.getInput("Ship name not found. Please enter another ship name.");
          shipFound = false;
        }
      }
    }
  }
}
