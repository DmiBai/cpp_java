package com.example.laboratory_work;

import org.springframework.data.annotation.Id;

public class DataClass {
   
    private final long first;
    private final long second;
    private final long third;
    private boolean isEquilateral = false;
    private boolean isIsosceles = false;
    private boolean isRectangular = false;

    private final Long id = 0L;
    public static Long idd=0L;
    
    public long getFirst() {
        return first;
    }

    public long getSecond() {
        return second;
    }

    public long getThird() {
        return third;
    }

    public DataClass(long first, long second, long third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean isEquilateral() {
        if((first == second) && (second == third)){
            isEquilateral = true;
        }
        return isEquilateral;
    }

    public boolean isIsosceles() {
        if((first == second) || (second == third) || (first == third)) {
            isIsosceles = true;
        }
        return isIsosceles;
    }

    public boolean isRectangular() {
        if (!isEquilateral) {
            long hypotenuse = first;
            long firstLeg = second;
            long secondLeg = third;

            if((firstLeg > hypotenuse) && (firstLeg > secondLeg)){
                hypotenuse = second;
                firstLeg = first;
            } else if ((secondLeg > hypotenuse) && (secondLeg > firstLeg)){
                hypotenuse = third;
                secondLeg = first;
            }

            if((hypotenuse * hypotenuse) - (firstLeg * firstLeg) - (secondLeg * secondLeg) == 0) {
                isRectangular = true;
            }
        }
        return isRectangular;
    }

    public void setResult(){
        this.isEquilateral();
        this.isIsosceles();
        this.isRectangular();
    }

    public String getResult(){

            String res = "Is equilateral: " + String.valueOf(isEquilateral()) + "<br>" +
            "Is isosceles: " + String.valueOf(isIsosceles()) + "<br>" +
            "Is rectangular: " + String.valueOf(isRectangular()).toString();

            return res;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(getClass()!=o.getClass()) return false;
        DataClass dataClass = (DataClass) o;
        return (this.first == dataClass.first && this.second == dataClass.second && this.third == dataClass.third);
    }

    
}
