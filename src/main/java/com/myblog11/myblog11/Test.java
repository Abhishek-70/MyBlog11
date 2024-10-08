package com.myblog11.myblog11;

import javax.servlet.ServletOutputStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<LogIn> logins= Arrays.asList(
                new LogIn("Mike","Mike@12"),
                new LogIn("stalin","stallin@12"),
                new LogIn("Pankaj","pankaj@12")
                );

        System.out.println(logins);
   // above sout consist of three Object which we need to copy to the DTO class using Stream Api

      //  Using Stream API Concept we are able to call each object at a time.
        List<LogInDto> MappedObject = logins.stream().map(logs -> mapToDto(logs)).collect(Collectors.toList());
        System.out.println(MappedObject);

        //Question Practice on Stream Api with functional Interface
        //1.Consider a list of Person objects with attributes name, age, and gender.
        // Implement a method that takes a list of persons and a functional interface as parameters.
        // The functional interface should define a condition for filtering the persons based on certain criteria (e.g., age greater than 25, females only, etc.).
        // The method should use the Java Stream API to filter the list of persons based on the given condition and return the filtered list.

        List<Person> personData=Arrays.asList(
                new Person("Pankaj","Male",23),
                new Person("Mike","Male",23),
                new Person("Ruchika","Female",25),
                new Person("TonyStark","Female",21)
        );
        List<Person> filteredName = personData.stream().filter(n -> n.getAge() > 20).filter(m -> m.getGender().equals("Female")).collect(Collectors.toList());
        System.out.println(filteredName);

    }

   // 1. for copying the data to the dto class we have to create a Method
    public static LogInDto mapToDto(LogIn log) {
        LogInDto dto=new LogInDto();
        dto.setUserName(log.getUserName());
        dto.setPassword(log.getPassword());
        return dto;

        //now we have to call this method in the main method,while LogIN have Multiple Object so we have to use Stream Api
        //to pass multiple object at a time.



      


    }


}
