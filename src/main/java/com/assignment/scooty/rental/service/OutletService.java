package com.assignment.scooty.rental.service;

import com.assignment.scooty.rental.dto.ModelDTO;
import com.assignment.scooty.rental.dto.OutletDTO;
import com.assignment.scooty.rental.dto.OutletNameDTO;
import com.assignment.scooty.rental.dto.UserDTO;
import com.assignment.scooty.rental.exception.NotFoundException;
import com.assignment.scooty.rental.mapper.OutletMapper;
import com.assignment.scooty.rental.model.Outlet;
import com.assignment.scooty.rental.model.OutletName;
import com.assignment.scooty.rental.repository.OutletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Slf4j
public class OutletService {

  private final OutletRepository outletRepository;
  private final OutletMapper outletMapper;

  public OutletService(OutletRepository outletRepository, OutletMapper outletMapper) {
    this.outletRepository = outletRepository;
    this.outletMapper = outletMapper;
  }

  public Mono<OutletDTO> addOutlet(Mono<OutletDTO> outletDTO) {
    return outletDTO
        .map(
            outletDTO1 ->
                outletRepository
                    .save(outletMapper.outletDtoToOutlet(outletDTO1))
                    .map(outletMapper::outletToOutletDto))
        .flatMap(x -> x);
  }

  public Flux<OutletDTO> getAllOutlets() {
    return outletRepository.findAll().map(outletMapper::outletToOutletDto);
  }

  public Mono<OutletDTO> createReservation(
      OutletNameDTO outletName, ModelDTO model, UserDTO reservedBy, LocalDate reservedUntil) {
    return outletRepository
        .findAllByOutletNameAndVehiclesModel(
            outletMapper.outletNameDTOToOutlet(outletName), outletMapper.modelDtoToModel(model))
        .switchIfEmpty(Mono.error(new NotFoundException("0utlet with " + model, "NOT FOUND")))
        .next()
        .map(
            outlet -> {
              boolean available = false;
              for (int i = 0; i < outlet.getVehicles().size(); i++) {
                if (outlet
                        .getVehicles()
                        .get(i)
                        .getModel()
                        .equals(outletMapper.modelDtoToModel(model))
                    && Boolean.TRUE.equals(outlet.getVehicles().get(i).getAvailability())) {
                  log.info("Outlet with model is available");
                  outlet.getVehicles().get(i).setReservedBy(outletMapper.userDTOToUser(reservedBy));
                  outlet.getVehicles().get(i).setReservedUntil(reservedUntil);
                  outlet.getVehicles().get(i).setAvailability(false);
                  available = true;
                }
              }
              if (Boolean.FALSE.equals(available)) {
                try {
                  throw new NotFoundException("0utlet with " + model, "NOT AVAILABLE");
                } catch (NotFoundException e) {
                  throw new RuntimeException(e);
                }
              }
              return outlet;
            })
        .doOnError(throwable -> log.error("ERROR", throwable))
        .map(outletRepository::save)
        .flatMap(x -> x)
        .map(outletMapper::outletToOutletDto);
  }
  // return outletRepository.save(outlet);

  public Mono<OutletDTO> findByOutletName(OutletNameDTO outletName) {
    return outletRepository
        .findByOutletName(outletMapper.outletNameDTOToOutlet(outletName))
        .next()
        .map(outletMapper::outletToOutletDto);
  }
  //  public Mono<OutletDTO> findByOutletName(String id) {
  //    return outletRepository.findById(id).map(outletMapper::outletToOutletDto);
  //  }

  public Flux<Outlet> findAllByOutletName(OutletName outletName) {
    return outletRepository.findByOutletName(outletName);
  }
}
