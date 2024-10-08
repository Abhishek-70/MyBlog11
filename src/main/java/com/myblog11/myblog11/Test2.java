package com.myblog11.myblog11;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test2 {

    public static void main(String[] args) {

//        List<Person> persons= Arrays.asList(
//                new Person("Stalin","Male",23),
//                new Person("Mike","Male",21),
//                new Person("Ruchika","Female",24),
//                new Person("Tomar", "Female",21)
//        );
//        Map<String, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(e->e.getGender()));
//       // System.out.println(collect); it will print key as grouping component and value as Person Object
//
//
//      for(Map.Entry<String,List<Person>> entryref:collect.entrySet()){
//          String gender = entryref.getKey();
//
//          System.out.println("Gender :"+gender);
//          for (Person persona:entryref.getValue()){
//              System.out.println(persona.getName());
//              System.out.println(persona.getAge());
//
//          }
//      }




        List<Student> names=Arrays.asList(
                new Student(1,"Abhishek","Bangalore","abhi@gmail.com"),
                new Student(2,"Vishal","Chennai","vishal@gmail.com"),
                new Student(3,"Pradeep","Chennai","pradeep@gmail.com"),
                new Student(4,"Vishwa","Bangalore","vish@gmail.com")
        );
        Map<String, List<Student>> groupedByCity = names.stream().collect(Collectors.groupingBy(n -> n.getAddress()));

        for(Map.Entry<String,List<Student>> enteredData:groupedByCity.entrySet()){
            String Address = enteredData.getKey();
            List<Student> StudentByCity = enteredData.getValue();
            System.out.println("The city Name is :"+Address);
            for(Student s:StudentByCity){
                System.out.println(s.getID());
                System.out.println(s.getName());
                System.out.println( s.getEmail());

            }

        }

        //2. For Giving the Output as the Bangalore people whose name starts with 'V' Only in grouping concept
        Map<String, List<Student>> foundedWithV=names.stream().collect(Collectors.groupingBy(n->n.getAddress()));


                  }


}
