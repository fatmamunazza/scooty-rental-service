package com.assignment.scooty.rental.api;

import com.assignment.scooty.rental.dto.ModelDTO;
import com.assignment.scooty.rental.dto.OutletDTO;
import com.assignment.scooty.rental.dto.OutletNameDTO;
import com.assignment.scooty.rental.dto.UserDTO;
import com.assignment.scooty.rental.service.OutletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@AllArgsConstructor
@Component
@Slf4j
public class OutletsDetailApiDelegateImpl implements OutletsDetailApiDelegate {

  private final OutletService outletService;

  @Override
  public Mono<ResponseEntity<OutletDTO>> addOutlet(
      Mono<OutletDTO> outletDTO, ServerWebExchange exchange) {

    return outletService
        .addOutlet(outletDTO)
        .map(
            outletDto ->
                ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(outletDto));
  }

  @Override
  public Mono<ResponseEntity<Flux<OutletDTO>>> getAllOutlets(ServerWebExchange exchange)
      throws Exception {
    return Mono.just(ResponseEntity.status(HttpStatus.OK).body(outletService.getAllOutlets()));
  }

  @Override
  public Mono<ResponseEntity<OutletDTO>> createReservation(
      OutletNameDTO outletName,
      ModelDTO model,
      UserDTO reservedBy,
      LocalDate reservedUntil,
      ServerWebExchange exchange)
      throws Exception {

    return outletService
        .createReservation(outletName, model, reservedBy, reservedUntil)
        .map(
            outletDto ->
                ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(outletDto));
  }

  @Override
  public Mono<ResponseEntity<OutletDTO>> getOutletByName(
      OutletNameDTO outletNameDTO, ServerWebExchange exchange) {
    return outletService
        .findByOutletName(outletNameDTO)
        .map(
            outletDto ->
                ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(outletDto));
  }
}
