package se.tengdahl.montyhallproblem;

class Box {

  private final boolean containsMoney;
  private boolean picked;
  private boolean opened;

  private Box(boolean containsMoney) {
    this.containsMoney = containsMoney;
  }

  static Box empty() {
    return new Box(false);
  }

  static Box withMoney() {
    return new Box(true);
  }

  void pick() {
    picked = true;
  }

  boolean isPicked() {
    return picked;
  }

  boolean isUnpicked() {
    return !picked;
  }

  boolean isEmpty() {
    return !containsMoney;
  }

  void open() {
    opened = true;
  }

  boolean isClosed() {
    return !opened;
  }

  void switchWith(Box other) {
    this.picked = false;
    other.picked = true;
  }

  boolean containsMoney() {
    return containsMoney;
  }
}
