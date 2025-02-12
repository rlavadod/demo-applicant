package com.example.demoapplicant.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilTest {

  @Test
  void whenTheBirthdateIsNullThenObtainZero() {
    //Arrange
    LocalDate birthdate = null;
    //Act
    Integer response = Util.obtainAge(birthdate);
    //Assert
    Assertions.assertEquals(Constants.ZERO, response);
  }

}