package se.tengdahl.montyhallproblem;

public enum Result {
  WIN, LOSS;

  static Result from(Box box) {
    return box.containsMoney() ? WIN : LOSS;
  }
}
