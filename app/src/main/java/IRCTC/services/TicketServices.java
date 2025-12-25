package IRCTC.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import IRCTC.entities.Train;
import IRCTC.entities.Ticket;
import IRCTC.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketServices {

    public static boolean checkSeatAvaliablity(Train train, int numberOfSeats) {
        int oneCounter = 0;
        for (List<Integer> currentList : train.getSeats()) {
            for (Integer currentSeat : currentList) {
                if (currentSeat == 0) {
                    oneCounter++;
                }
            }
        }
        return numberOfSeats < oneCounter;
    }

    public static void bookSeats(String trainId, String userId, Ticket currentTicket, int numberOfSeats) {
        for (User user : UserServices.getAllUsers()) {
            if (user.getUserId().equals(userId)) {
                List<Ticket> allTickets = user.getTicketBooked();
                allTickets.add(currentTicket);
                user.setTicketBooked(allTickets);
                break;
            }
        }
        for (Train train : TrainServices.getallTrains()) {
            if (!train.getTrainId().equals(trainId)) {
                continue;
            }
            int booked = 0;
            for (List<Integer> seatRow : train.getSeats()) {
                for (int i = 0; i < seatRow.size(); i++) {

                    if (seatRow.get(i) == 0) {
                        seatRow.set(i, 1);
                        booked++;

                        if (booked == numberOfSeats) {
                            break;
                        }
                    }
                }

                if (booked == numberOfSeats) {
                    break;
                }
            }
            break;
        }
        try {
            UserServices.addUserToFileList();
            TrainServices.setNewTrainData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
