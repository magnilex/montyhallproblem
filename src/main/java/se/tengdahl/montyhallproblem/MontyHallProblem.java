package se.tengdahl.montyhallproblem;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

class MontyHallProblem {

  private static final Random RANDOM = new Random(12345);

  private final List<Box> box;

  private MontyHallProblem() {
    this.box = List.of(Box.empty(), Box.empty(), Box.withMoney());
  }

  static MontyHallProblem initialize() {
    return new MontyHallProblem();
  }

  private void pickRandom() {
    box.get(RANDOM.nextInt(3)).pick();
  }

  private void openNonPicked() {
    getBy(Box::isUnpicked, Box::isEmpty).open();
  }

  private void switchSelection() {
    getBy(Box::isPicked).switchWith(getBy(Box::isUnpicked, Box::isClosed));
  }

  @SafeVarargs
  private Box getBy(Predicate<Box>... conditions) {
    Predicate<Box> predicates = stream(conditions).reduce(identity -> true, Predicate::and);
    return box.stream().filter(predicates).findFirst().orElseThrow(() -> new IllegalArgumentException("Expected Box"));
  }

  Result run(boolean switchBox) {
    pickRandom();
    openNonPicked();
    if (switchBox) {
      switchSelection();
    }
    return Result.from(getBy(Box::isPicked));
  }
}
