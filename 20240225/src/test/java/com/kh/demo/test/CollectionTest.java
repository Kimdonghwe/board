package com.kh.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
//@SpringBootTest
public class CollectionTest {

  @Autowired
  Person person;

  @Test
  void generic(){
    class Person<N,A>{
      N name;
      A age;
    }

    Person<String,Integer> person = new Person<String,Integer>();
    person.name = "홍길동";
    String name = person.name;
    log.info("name={}",name);

    Person<StringBuffer,Integer> person2 = new Person<StringBuffer,Integer>();
    person2.name = new StringBuffer();
    person2.name.append("홍길동");
    log.info("name={}",person2.name.toString());

  }

  @Test
  void list_1(){
    //log.info("person={}",person);
    @Data
    @AllArgsConstructor
    class Person {
      String name;
      int age;
    }

    // 제너릭이란? : 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는방법
    List<Person> persons = new ArrayList<Person>();  //list 컬렉션 생성

    //추가
    Person p1 = new Person("홍길동",30);
    persons.add(p1);
    persons.add(new Person("홍길순",20));
    persons.add(new Person("홍길남",10));

    //순회
    for (Person person : persons){
      log.info("name={}, age={}", person.getName(), person.getAge());
    }

    persons.add(p1);
    log.info("persons={}",persons);

    //제거
    persons.remove(p1);
    persons.remove(0);
    log.info("persons={}",persons);

    //존재유무
    log.info("p1존재유무={}",persons.contains(p1));

    //요소갯수
    log.info("요소갯수={}", persons.size());

    //전체요소 비우기
    persons.clear();

    //요소갯수
    log.info("요소갯수={}", persons.size());

  }

  @Test
  void set_1(){
    @Data
    @AllArgsConstructor
    class Person {
      String name;
      int age;
    }

    Set<Person> persons = new HashSet<>(); // set 컬렉션 객체 생성

    //추가
    Person p1 = new Person("홍길동", 30);
    persons.add(p1);
    persons.add(new Person("홍길순", 20));
    persons.add(new Person("홍길남", 40));

    //순회
    for (Person person : persons){
      log.info("name={}, age={}", person.getName(), person.getAge());
    }

    //추가
    persons.add(p1);
    log.info("persons={}",persons);
  }

  @Test
  void map_1(){
    @Data
    @AllArgsConstructor
    class Person {
      String name;
      int age;
    }

    Map<Integer,Person> persons = new HashMap<>();
    Person p1 = new Person("홍길동", 30);
    //추가
    persons.put(1,p1);
    persons.put(2, new Person("홍길순", 20));
    persons.put(3, new Person("홍길남", 40));

    //순회
    //1) entry
    for (Map.Entry entry : persons.entrySet()){
      log.info("key={},value={}",entry.getKey(),entry.getValue());
    }
    //2) key
    for (Integer key: persons.keySet()){
      log.info("key={},value={}",key, persons.get(key));
    }
    //3) value
    for(Person person : persons.values()){
      log.info("person={}", person);
    }
    //추가
    persons.put(1,p1);
    log.info("size={}",persons.size());

    //제거
    persons.remove(1);

    //객체유무
    log.info("p1={}",persons);
    log.info("p1존재유무={}",persons.containsKey(1));
  }
}
