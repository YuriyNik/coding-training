package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsStreamsTest {

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
}
