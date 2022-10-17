package br.com.ulteriorti.parking.service;

import br.com.ulteriorti.parking.exception.ParkingNotFoundException;
import br.com.ulteriorti.parking.model.Parking;
import br.com.ulteriorti.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();

    }
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }
@Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }
@Transactional
    public void delete(String id) {
        Parking parking = findById(id);
        parkingRepository.deleteById(id);

    }
@Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        //System.out.println(parkingCreate.toString());
        if (!parkingCreate.getColor().isBlank())
            parking.setColor(parkingCreate.getColor());
        if (!parkingCreate.getLicense().isBlank())
            parking.setLicense(parkingCreate.getLicense());
        if (!parkingCreate.getState().isBlank())
            parking.setState(parkingCreate.getState());
        if(!parkingCreate.getModel().isBlank())
            parking.setModel(parkingCreate.getModel());
        parkingRepository.save(parking);
        return parking;

    }
@Transactional
    public Parking exit(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
