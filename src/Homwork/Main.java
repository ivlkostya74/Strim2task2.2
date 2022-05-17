package Homwork;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

        }







        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("количество несовершеннолетних= " + count);


        System.out.println("призывники ");
        List<String> recruts = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.getFamily())
                .limit (100)
                .collect(Collectors.toList());
               System.out.println(recruts);

        System.out.println("писок потенциально работоспособных " +
                "людей с высшим образованием в выборке " +
                "(т.е. людей с высшим образованием от 18 до 60 " +
                "лет для женщин и до 65 лет для мужчин).");
        List<Person> workers = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getSex().equals(Sex.MAN) ? person.getAge() < 65 : person.getAge() < 60)
                .filter(person -> person.getAge() > 18)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .limit(1000)
                .collect(Collectors.toList());
        System.out.println(workers);


    }
}

