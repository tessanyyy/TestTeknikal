package com.testteknikal.utils;

public enum TestScenarios {
    //Scenario Booking
    T1("Booking Validation"),
    T2("Attempt to double book")


    private String testCaseName;

    TestScenarios(String value) {
        testCaseName = value;
    }

    public String getTestCaseName() {
        return testCaseName;
    }
}
