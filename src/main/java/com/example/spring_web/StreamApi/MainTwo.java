package com.example.spring_web.StreamApi;

//Lambda, Lambda method inference,
// Stream, sorting, filter, map, Collectors

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTwo {
    public static void main(String[] args) {

//        Older way
//        Walkable obj = new WalkFast();
//        obj.walk(4);

//        Walkable obj = new Walkable() {
//            @Override
//            public int walk(int steps) {
//                return 0;
//            }
//        };

//        Walkable obj = (steps, isEnabled) -> {
//            System.out.println("Walking fast "+ steps +" steps.");
//            return 2*steps;
//        };
//
//        Walkable obj2 = (steps, isEnabled) -> 2*steps;
//
//        obj.walk(4, true);

        List<String> fruits = List.of("Banana", "Apple", "Kiwi", "Mango");

        Map<String, Integer> fruitsList = fruits
                .stream()
//                .map(fruit -> fruit.length())
                .collect(Collectors.toMap(
                        fruit -> fruit,
                        String::length
                ));

        System.out.println(fruitsList);

        Stream<String> stream = fruits.stream();
//
        stream
                .filter(fruit -> fruit.length() < 6)
                .sorted()
                .map(String::length)
                .map(fruitLength -> 2*fruitLength)
                .forEach(fruit -> System.out.println(fruit));



    }
}

interface Walkable {
    int walk(int steps, boolean isEnabled);
}


//class WalkFast implements Walkable{
//
//    @Override
//    public int walk(int steps) {
//        System.out.println("Walking fast "+ steps +" steps.");
//        return 2*steps;
//    }
//}