package com.example.hamroride.historyRecyclerView;

public class HistoryObject {
    private String rideId;

    public HistoryObject(String rideId){
        this.rideId = rideId;
    }

    public String getRideId(){return rideId;}
    public void setRideId(String rideId) {
        this.rideId = rideId;
    }
}
