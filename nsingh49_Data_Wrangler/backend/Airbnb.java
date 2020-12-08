
public class Airbnb {
  String name;
  String typeOfPlace;
  String city;
  String State;
  int price;
  int minNight;
  int review;

  private Airbnb(String name, String typeOfPlace, String city, String State, int price,
      int minNight, int review) {
    this.name = name;
    this.typeOfPlace = typeOfPlace;
    this.city = city;
    this.State = State;
    this.price = price;
    this.minNight = minNight;
    this.review = review;

  }

  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private String getTypeOfPlace() {
    return typeOfPlace;
  }

  private void setTypeOfPlace(String typeOfPlace) {
    this.typeOfPlace = typeOfPlace;
  }

  private String getCity() {
    return city;
  }

  private void setCity(String city) {
    this.city = city;
  }

  private String getState() {
    return State;
  }

  private void setState(String state) {
    State = state;
  }

  private int getPrice() {
    return price;
  }

  private void setPrice(int price) {
    this.price = price;
  }

  private int getMinNight() {
    return minNight;
  }

  private void setMinNight(int minNight) {
    this.minNight = minNight;
  }

  private int getReview() {
    return review;
  }

  private void setReview(int review) {
    this.review = review;
  }
}
