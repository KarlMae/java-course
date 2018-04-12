package ee.ttu.iti0202;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class StreamPr {
    public List<Integer> numbersList(int start, int end){
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    public List<Integer> evenNumbers(int n) {
        return IntStream.iterate(0, i -> i + 2).limit(n).boxed().collect(Collectors.toList());
    }

    public int smallestPositiveNumber(List<Integer> numbers) {
        return numbers.stream().filter(i -> i >= 0).min(Integer::compare).orElse(0);
    }

    public long countValidNames(List<String> names) {
        return names.stream().filter(s -> s.length() > 0).filter(s -> Character.isUpperCase(s.charAt(0))).count();
    }

    public OptionalInt longestName(List<String> names) {
        return names.stream().mapToInt(String::length).max();
    }

    public long serialCountPrimes(int end) {
        return IntStream.range(0, end).filter(this::isPrime).count();
    }

    public long parallelCountPrimes(int end) {
        return IntStream.range(0, end).parallel().filter(this::isPrime).count();
    }

    private boolean isPrime(final int n) {
        return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    public List<String> uniqueCharacters(List<String> names) {
        return names.stream().filter(n -> !n.equals("")).map(String -> String.toLowerCase().split("")).flatMap(Arrays::stream).sorted().distinct().collect(Collectors.toList());
    }

    public Map<String, Integer> getAllFirstNameAmounts(List<String> children){
        return children.stream().map(s -> s.split(" ")[0]).collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    }

    public int getChildrenWithFirstName(List<String> children, String name){
        return (int)children.stream().filter(s -> s.split(" ")[0].equals(name)).count();
    }
    public long getMatchingLastNameAmount(List<String> children) {
        Set<String> allItems = new HashSet<>();
        return children.stream().map(s -> s.split(" ")[1]).filter(allItems::add).count();
    }

    public static void main(String[] args) {
    }
}
