package br.com.ulteriorti.parking.service;

import br.com.ulteriorti.parking.model.Parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingCheckOut {
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADDTIONAL_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 20.00;

    public static Double getBill(Parking parking){
        return getBill(parking.getEntryDate(),parking.getExitDate());
    }
    private static Double getBill(LocalDateTime entry, LocalDateTime exit){
        Double bill = 0.0;
        Duration duration = Duration.between(entry, exit);
        Double diff = Double.valueOf(duration.toMinutes());
        diff = Double.valueOf(Math.ceil(diff/60));
        System.out.println("->>>>>>>>"+diff);
        if(diff <= 0)
            bill = ONE_HOUR_VALUE;
        if(diff >=1 && diff<24)
            bill = ONE_HOUR_VALUE +((diff-1)*ADDTIONAL_HOUR_VALUE);
        if(diff >= 24)
            bill = ((diff/24)*DAY_VALUE) + ((diff%24)*ADDTIONAL_HOUR_VALUE);
        System.out.println("->>>>>>>>>"+bill);
        return bill;
    }
}
