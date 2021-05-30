public class CrewMember extends PersonOnShip {

  private String occupation;

  public CrewMember(String firstName, String lastName, String nickname, String occupation) {
    super(firstName, lastName, nickname);
    this.occupation = occupation;
  }

  public String getOccupation() {
    return occupation;
  }

  @Override
  public String getDescription(){
    return occupation + " " + super.getDescription();
  }
}
