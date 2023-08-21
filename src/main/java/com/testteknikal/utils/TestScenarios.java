package com.testteknikal.utils;

public enum TestScenarios {
    //Scenario Modul Login Page
    T1("Check access login page with invalid url"),
    T2("Check access login page with invalid url"),
    T3("Check Login is Failed with invalid password"),
    T4("Check Login is Falied with invalid username"),
    T5("Check Login is Falied with null password"),
    T6("Check Login is Failed with null username"),
    T7("Check Login is falied with null username and password"),
    T8("Check Login is success with valid credentials"),


    private String testCaseName;

    TestScenarios(String value) {
        testCaseName = value;
    }

    public String getTestCaseName() {
        return testCaseName;
    }
}
