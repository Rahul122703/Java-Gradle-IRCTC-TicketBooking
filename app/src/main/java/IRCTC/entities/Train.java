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
    private Map<String,String> timing;
}
