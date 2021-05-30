public abstract class PersonOnShip {

  private final String firstName;
  private final String lastName;
  private String nickname;

  public PersonOnShip(String firstName, String lastName, String nickname){
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getDescription(){
    StringBuilder builder = new StringBuilder();
    builder.append(this.firstName);
    builder.append(" ");
    if(!this.nickname.equals("")){
      builder.append(this.nickname);
      builder.append(" ");
    }
    builder.append(this.lastName);
    return builder.toString();
  }
}
