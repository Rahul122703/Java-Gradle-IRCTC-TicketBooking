package IRCTC.services;

import IRCTC.entities.Train;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TrainServices {
    static List<Train> allTrains;
    private static final String TRAIN_PATH = "D:\\RahulSharma\\Learn\\Java\\IRCTC\\app\\src\\main\\java\\IRCTC\\localDB\\trains.json";

    public static void setTrains() throws Exception {
        File TrainsFile = new File(TRAIN_PATH);
        ObjectMapper OM = new ObjectMapper();
        allTrains = OM.readValue(TrainsFile, new TypeReference<List<Train>>() {
        });
    }

    public static void setNewTrainData() throws IOException {
        System.err.println("Yes this is running");
        File trainsFilePath = new File(TRAIN_PATH);
        ObjectMapper OM = new ObjectMapper();
        OM.writeValue(trainsFilePath, allTrains);
    }

    public static List<Train> getallTrains() {
        return allTrains;
    }

    public static List<Train> availableTrains(String source, String destination) {
        return allTrains.stream()
                .filter(train -> ValidTrain(train, source, destination))
                .collect(Collectors.toList());
    }

    public static int[] stationCountArray() {
        return allTrains.stream()
                .mapToInt(train -> train.getStations().size())
                .toArray();
    }

    public static boolean ValidTrain(Train train, String source, String destination) {
        int sourceIndex = train.getStations().indexOf(source);
        int destinationIndex = train.getStations().indexOf(destination);
        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    public static void printAllTrains() {
        System.out.println(allTrains.size() + " trains loaded successfully.");
        System.out.println("\n=========== LOADED TRAIN DATA ===========");
        for (Train train : allTrains) {
            System.out.println("----------------------------------------");
            System.out.println("Name        : " + train.getName());
            System.out.println("Train ID    : " + train.getTrainId());
            System.out.println("Train No    : " + train.getTrainNo());
            System.out.println("Depart Time : " + train.getDepartTime());
            System.out.println("Arrive Time : " + train.getArrivalTime());
            System.out.println("Stations    : " + train.getStations());
            System.out.println("Stations #  : " + train.getStations().size());
            System.out.println("Timing      : " + train.getTiming());
            System.out.println("Seats       : " + train.getSeats());
        }
        System.out.println("========================================\n");
    }
}
