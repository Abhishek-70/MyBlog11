package com.myblog11.myblog11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FuctionFunctionalInterface {
    public static void main(String[] args) {
        //here we use second implemented Interface of the Functional Interface.
        //2.FUNCTION functional Interface

        Function<Integer,Integer> nums=n->n%2;
        Integer result = nums.apply(17);
        //(i)->it has an [apply()] Method inbuilt,, //Like Predicate Interface has A [test()] Method inbuilt
         System.out.println(result);

//(ii)->While it Came to Stream Collections,map() Method is their to put condition on its collections object
        //same as that of filter() method in the Predicate Interface

        List<String> list1 = Arrays.asList("Mike", "subject","point","Mike","subject");
        List<String> uniqueObject = list1.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueObject);

        // (ii)-> Using map() method we can implement the concept of Function-Functional Interface

        List<String> details=Arrays.asList("Aman","Ankit","shakti","Satya");
        details.stream().map(q->q.equals("Satya")).collect(Collectors.toList());
//
//        List<String> foundedName = list1.stream().filter(n -> n.equals("Mike")).collect(Collectors.toList());
//
//        System.out.println(foundedName);


        //3.consumer functional interface take input but produces no output //Here the concept of for each will used

        List<String> name=Arrays.asList("Arrays","Mike","adam");
       Consumer<String> variable= var-> System.out.println(var);
        name.forEach(variable);

        //Consumer Interface defined by it Name BySelf It Consume Value But Not Produces Any Different value

        //4.Supplier functional interface produce an output but doesn't take any value It only produces output
        //Generally it differ from other functional interface such that it doesn't take input only produces output

        Supplier<Integer> x=()->new Random().nextInt(100);
        Integer y=x.get();
        System.out.println(y);
    }
}
