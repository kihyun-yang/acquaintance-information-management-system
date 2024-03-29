package com.fastcampus.javaallinone.project3.mycontact.repository;

import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();

    @Query(value = "select person from Person person " +
            "where (person.birthday.monthOfBirthday = :monthValueOfToday and person.birthday.dayOfBirthday = :dayOfToday) " +
            "or (person.birthday.monthOfBirthday = :monthValueOfTomorrow and person.birthday.dayOfBirthday = :dayOfTomorrow)")
    List<Person> findBirthdayFriendsTodayOrTomorrow(@Param("monthValueOfToday") int monthValueOfToday,
                                                    @Param("dayOfToday") int dayOfToday,
                                                    @Param("monthValueOfTomorrow") int monthValueOfTomorrow,
                                                    @Param("dayOfTomorrow") int dayOfTomorrow);
}
