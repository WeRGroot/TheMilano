package com.shivam.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Location {
    private double x;
    private double y;

    public double distance(Location l2){
        return Math.sqrt( Math.pow(this.x - l2.getX(), 2)  +  Math.pow( this.y - l2.getY(), 2)) ;
    }
}
