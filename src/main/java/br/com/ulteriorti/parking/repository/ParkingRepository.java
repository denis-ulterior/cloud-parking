package br.com.ulteriorti.parking.repository;

import br.com.ulteriorti.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository  extends JpaRepository<Parking,String> {

}
