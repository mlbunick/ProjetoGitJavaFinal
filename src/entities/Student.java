package entities;

public class Student {

    private int code;
    private String name;
    private float testOne;
    private float testTwo;
    private float testThree;

    public Student() {}

    public Student(int code, String name, float testOne, float testTwo, float testThree) {
        this.code = code;
        this.name = name;
        this.testOne = testOne;
        this.testTwo = testTwo;
        this.testThree = testThree;
    }

    public String getName() {
        return name;
    }

//    public void setTestOne(float testOne) {
//        this.testOne = testOne;
//    }
//
//    public void setTestTwo(float testTwo) {
//        this.testTwo = testTwo;
//    }
//
//    public void setTestThree(float testThree) {
//        this.testThree = testThree;
//    }

    public float getTestOne() {
        return testOne;
    }

    public float getTestTwo() {
        return testTwo;
    }

    public float getTestThree() {
        return testThree;
    }

    public int getCode() {
        return code;
    }
}
