package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionsStreamsTest {

    public static List<Integer> filterEven(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n%2==0)
                .toList();
    }
    public static List<Integer> squares(List<Integer> nums) {
        return nums.stream()
                .map(n -> n * n)
                .toList();
    }

    public static int sum(List<Integer> nums){
        return nums.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
    @Test
     void warmUPTasks(){
         List<Integer> integerList = new ArrayList<>();
         for (int i = 0; i < 10; i++) {
             integerList.add(i);
         }
        System.out.println(filterEven(integerList));
        System.out.println(squares(integerList));
        System.out.println(sum(integerList));
        System.out.println(max(integerList));
     }
    public static Optional<Integer> max(List<Integer> nums) {
        return nums.stream()
                .max(Integer::compareTo);
    }

    public static String join(List<String> words) {
        return words.stream()
                .collect(Collectors.joining(""));
    }
    public static long countLongWords(List<String> words) {
        return words.stream()
                .filter(w-> w.length()>3)
                .count();
    }

    @Test
    void testStreamPart2() {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            words.add("a"+i);
            words.add("Word"+i);
        }
        words.add("");

        System.out.println(words.stream().allMatch(w -> !w.isEmpty()));
        System.out.println(words.stream().noneMatch(w -> w.length() > 100));

        System.out.println(words);
        System.out.println(join(words));
        System.out.println(countLongWords(words));


    }

    @Test
    void tasksCollectionsStreamsTest(){
//        Task1 remove duplicates and order by lenght
        List<String> words = List.of("apple", "banana", "pear", "apple", "kiwi");
        //        [pear, kiwi, apple, banana]
        List<String> result = words.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println(result);
//        Task2 count qty of each char in list.
        List<String> words2 = List.of("a", "b", "a", "c", "b", "a");
//        {a=3, b=2, c=1}
        Map <String, Long> freq = words2.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )
        );
        System.out.println(freq);
// Task3 find max salary for each deparment
        record Employee(String name, String dept, int salary) {}
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 100),
                new Employee("Bob", "IT", 200),
                new Employee("Charlie", "HR", 150),
                new Employee("Diana", "HR", 50)
        );
//        {HR=150, IT=200}
        Map<String, Integer> maxSalaryByDep = employees.stream().collect(
            Collectors.groupingBy(
                    Employee::dept,
                    Collectors.collectingAndThen(
                            Collectors.maxBy(Comparator.comparingInt(Employee::salary)),
                            opt -> opt.get().salary()
                    )
            )
        );
        System.out.println(maxSalaryByDep);
        System.out.println(employees);

        Map<String, Integer> maxSalaryByDep2 = employees.stream()
                .collect(Collectors.toMap(
                        Employee::dept,
                        Employee::salary,
                        Integer::max
                ));
        System.out.println(maxSalaryByDep2);
        System.out.println(employees);

    }

    @Test
    void secondPortion(){
        List<String> words = List.of("a","b","a","c","b","a","d","d","d","b");
        int k = 2;

        List<Map.Entry<String,Long>> result = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                ))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // по убыванию
                .limit(k)
                .toList();
        System.out.println(result);
    }
}
