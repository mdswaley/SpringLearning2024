package com.example.spring_web.StreamApi;

import java.util.List;
import java.util.stream.Stream;

public class MainClass {
    public static void main(String[] args) {
        //    older way
//        Walk obj = new walkFast();
//        obj.walking(2);


//        Another Way (Older)
//        Walk obj = new Walk() {
//            @Override
//            public int walking(int i) {
//                System.out.println("walk fast : "+i);
//                return 2*i;
//            }
//        };


//        Java 8 (Lambda Expression)
        Walk obj = (i,isEnable)->{ // java know we have only one method and what are the type inside interface .
            // So we implement that only. This is also known as Method Signature.
            System.out.println("walk fast : "+i+" "+isEnable);
            return 2*i;
        };

//        if you don't want a body of implementation
        Walk obj2 = (i,isEnable)->2*i;

//        call the method.
//        obj.walking(2,false);
//        System.out.println(obj2.walking(3,true));


//        Stream
        List<String> fruits = List.of("Banana","Apple","Litchi");
        Stream<String> stream = fruits.stream();

//Streams provide a variety of functional operations like map, filter, reduce, forEach, collect, and more.
        stream
                .filter(fruit->fruit.length()<=5) //if fruit is less than equal to 5 then true and go in else false and return.
                .sorted()
                .map((fruit)->fruit.length())//map is convert one data to another like fruit is prev is String but now is in int length.
                .map((fruitLength)->2*fruitLength)//double the length
//                .forEach((fruit)-> System.out.println(fruit));
                .forEach(System.out::println); //Influence method use for not take extra variable for print

    }
}
@FunctionalInterface
interface Walk{
    public int walking(int step,boolean isEnable);
}
// No need bcz of java 8 lambda expression.

//class walkFast implements Walk{
//
//    @Override
//    public int walking(int step) {
//        System.out.println("walk fast : "+step);
//        return step*2;
//    }
//}