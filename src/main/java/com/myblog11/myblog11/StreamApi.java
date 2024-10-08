package com.myblog11.myblog11;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamApi {
    public static void main(String[] args) {

        //Here use Functional Interface Internally such that we can access the lambdas expression
        //1.Predicate is a Functional Interface-> that Takes one Argument/value and Return An boolean Value
        //it can be used while we have to pass the single value at a time,using the test method

        Predicate<Integer> value = y -> y % 2 == 0;
        boolean result = value.test(10);
        System.out.println(result);


        Predicate<String> val = str -> str.equals("mike");
        boolean result2 = val.test("Stallin");
        System.out.println(result2);

        //we gonna use it internally working
//        List<Integer> list = Arrays.asList(12, 13, 15, 16, 18, 20);//it's show the general representation of the array with the data
//        //stream used here used for accessing the value one by one whole data into the function
//        List<Integer> evenNumber = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
//        System.out.println(evenNumber);

        List<String> names = Arrays.asList("mike", "sai_yam", "world", "Jackson", "mike");
        List<String> res1 = names.stream().filter(a -> a.equals("mike")).collect(Collectors.toList());
        System.out.println(res1);

        //we can use multiple methods present in the filter() method which take the predicate syntax.
        List<Integer> nums = Arrays.asList(12, 10, 13, 19, 29, 38, 48, 50);
        List<Integer> outputData = nums.stream().filter(a -> a / 2 != 0).collect(Collectors.toList());
        System.out.println(outputData);


        List<String> names2 = Arrays.asList("Abhishek", "Singh", "Pradeep", "vishal", "gaurav");

        List<String> sortedNames  = names2.stream().filter(n -> n.startsWith("P")).collect(Collectors.toList());
        System.out.println(sortedNames);


//        List<Boolean> reuslts=Arrays.asList(true,false);
//        List<Boolean> booleanResult = reuslts.stream().filter(n -> n.equals(true)).collect(Collectors.toList());
//
//        System.out.println(booleanResult);

//        List<Integer> numbers=Arrays.asList(34,55,78,98,65,23,76,86);
//        List<Integer> foundedNums = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
//
//        System.out.println(foundedNums);
//


        //2. Now we gonna use (FUNCTION functional Interface )->it will take one argument and generates an output one output value.
        Function<String,Integer> res=s->s.length();
        System.out.println(res.apply("mike"));

//        Function<Integer,Integer> numbers=n->n%2;
//        System.out.println(numbers.apply(19));

        List<String> name=Arrays.asList("Pankaj","Mike","Mayank","vishal");
        List<String> upperCaseName = name.stream().map(n -> n.toUpperCase()).collect(Collectors.toList());
        System.out.println(upperCaseName);
    }
}