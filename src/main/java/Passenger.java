public class Passenger extends PersonOnShip {

  public Passenger(String firstName, String lastName, String nickname) {
    super(firstName, lastName, nickname);
  }

  @Override
  public String getDescription(){
    return "Passenger " + super.getDescription();
  }
}
