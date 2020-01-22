package se.tengdahl.montyhallproblem;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.rangeClosed;
import static se.tengdahl.montyhallproblem.MontyHallProblem.initialize;
import static se.tengdahl.montyhallproblem.Result.WIN;

public class Runner {

  private static final int ITERATIONS = 10_000_000;

  public static void main(String[] args) {
    Map<Result, Long> withSwitch = executeAndGroupCountByResult(true);
    Map<Result, Long> withoutSwitch = executeAndGroupCountByResult(false);

    printResult("When switching boxes:", withSwitch);
    System.out.println();
    printResult("Without switching boxes:", withoutSwitch);
  }

  private static Map<Result, Long> executeAndGroupCountByResult(boolean switchBox) {
    return rangeClosed(1, ITERATIONS)
        .mapToObj(i -> initialize().run(switchBox))
        .collect(groupingBy(identity(), counting()));
  }

  private static void printResult(String headline, Map<Result, Long> results) {
    System.out.println(headline);
    stream(Result.values()).forEach(result -> System.out.println("" + result + ": " + results.get(result)));
    System.out.println("Win ratio: " + calculateRatio(results));
  }

  private static double calculateRatio(Map<Result, Long> results) {
    return (double) results.get(WIN) / ITERATIONS * 100;
  }

}
