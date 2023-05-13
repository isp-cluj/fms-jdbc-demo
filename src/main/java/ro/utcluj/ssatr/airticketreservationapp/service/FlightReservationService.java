package ro.utcluj.ssatr.airticketreservationapp.service;

import ro.utcluj.ssatr.airticketreservationapp.model.FlightInformation;
import ro.utcluj.ssatr.airticketreservationapp.model.FlightReservation;
import ro.utcluj.ssatr.airticketreservationapp.repository.DBAccess;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightReservationService {
    private DBAccess connection;
    private FlightInformationTableModel flightInformationTableModel;
    private List<FlightInformation> list = new ArrayList<>();

    public FlightReservationService() {
        try {
            connection = new DBAccess();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlightReservationService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationService.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        flightInformationTableModel  = new FlightInformationTableModel(connection);
    }

    public FlightInformationTableModel getFlightInformationTableModel() {
        return flightInformationTableModel;
    }

    public void addNewFlight(String flightNumber, int noOfSeats, String departureDate){
        try {
            //....
            FlightInformation f = new FlightInformation(flightNumber,noOfSeats,departureDate);
            connection.insertFlight(new FlightInformation(flightNumber,noOfSeats,departureDate));
            list.add(new FlightInformation(flightNumber,noOfSeats,departureDate));
            flightInformationTableModel.updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//    public boolean makeReservation(String flightNumber, int noOfTikets ){
//        try {
//
//            FlightReservation reservation = new FlightReservation(0,flightNumber,noOfTikets);
//            System.out.println("SERACH FLIGHT");
//            FlightInformation f = dbConn.findFlight(flightNumber);
//            System.out.println(f);
//            if(f!=null){
//                if(f.getNumberOfSeats()>=noOfTikets){
//                    //........UPDATE ROW IN DATABASE
//                    dbConn.updateSeats(flightNumber, f.getNumberOfSeats()-noOfTikets);
//                    dbConn.insertReservation(reservation);
//                    return true;
//                }else{
//                    System.out.println("Nomber of seats not available");
//                }
//            }else{
//                System.out.println("No flight number found.");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FlightReservationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//
//    }

    public boolean makeReservation(String flightNumber, int noOfTikets ){
        try {

            FlightReservation reservation = new FlightReservation(0,flightNumber,noOfTikets);
            System.out.println("SERACH FLIGHT");
            FlightInformation f = connection.findFlight(flightNumber);
            System.out.println(f);
            if(f!=null){
                if(f.getNumberOfSeats()>=noOfTikets){
                    //........UPDATE ROW IN DATABASE
                    connection.makeReservation(reservation);
                    return true;
                }else{
                    System.out.println("Nomber of seats not available");
                }
            }else{
                System.out.println("No flight number found.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public String getAllFlights(){
        String s = "";
        for(FlightInformation fi: list){
            s=s+fi.toString()+"\n";
        }
        s+="-------------------";
        return s;
    }


}
