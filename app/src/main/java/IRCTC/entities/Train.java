package IRCTC.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Train {

    private String name;
    private String trainId;
    private String trainNo;
    private String departTime;
    private String arrivalTime;
    private List<List<Integer>> seats;
    private Map<String, String> timing;
    private List<String> stations;

    // ✅ REQUIRED by Jackson
    public Train() {
    }

    public Train(
            String train_name,
            String train_trainId,
            String train_trainNo,
            String train_departTime,
            String train_arrivalTime,
            List<List<Integer>> train_seats,
            Map<String, String> train_timing,
            List<String> train_stations) {

        this.name = train_name;
        this.trainId = train_trainId;
        this.trainNo = train_trainNo;
        this.departTime = train_departTime;
        this.arrivalTime = train_arrivalTime;
        this.seats = train_seats;
        this.timing = train_timing;
        this.stations = train_stations;
    }

    public String getName() {
        return name;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getTiming() {
        return timing;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setTiming(Map<String, String> timing) {
        this.timing = timing;
    }

    // ✅ THIS is the key fix
    @JsonProperty("stations")
    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo() {
        return "Train ID : " + trainId + " | Train No : " + trainNo;
    }
}
