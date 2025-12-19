package IRCTC.entities;

import java.util.List;
import java.util.Map;

public class Train {
    private String name;
    private String trainId;
    private String trainNo;
    private String departTime;
    private String arrivalTime;
    private List<List<Integer>> seats;
    private Map<String, String> timing;
    private List<String> stations;

    public Train(
            String train_name,
            String train_trainId,
            String train_trainNo,
            String train_departTime,
            String train_arrivalTime,
            List<List<Integer>> train_seats,
            Map<String, String> train_timing,
            List<String> train_stations
    ) {
        this.name = train_name;
        this.trainId = train_trainId;
        this.trainNo = train_trainNo;
        this.departTime = train_departTime;
        this.arrivalTime = train_arrivalTime;
        this.seats = train_seats;
        this.timing = train_timing;
        this.stations = train_stations;
    }

    public String getName() { return this.name; }
    public String getTrainId() { return this.trainId; }
    public String getTrainNo() { return this.trainNo; }
    public String getDepartTime() { return this.departTime; }
    public String getArrivalTime() { return this.arrivalTime; }
    public List<List<Integer>> getSeats() { return this.seats; }
    public Map<String, String> getTiming() { return this.timing; }
    public List<String> getStations() { return this.stations; }

    public void setName(String newName) { this.name = newName; }
    public void setTrainId(String newTrainId) { this.trainId = newTrainId; }
    public void setTrainNo(String newTrainNo) { this.trainNo = newTrainNo; }
    public void setDepartTime(String newDepartTime) { this.departTime = newDepartTime; }
    public void setArrivalTime(String newArrivalTime) { this.arrivalTime = newArrivalTime; }
    public void setSeats(List<List<Integer>> newSeats) { this.seats = newSeats; }
    public void setTiming(Map<String, String> newTiming) { this.timing = newTiming; }
    public void setStations(List<String> newStations) { this.stations = newStations; }

    public String getTrainInfo() {
        return String.format("Train ID : %s and Train No : %s", trainId, trainNo);
    }
}
