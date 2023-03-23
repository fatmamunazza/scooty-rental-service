package com.assignment.scooty.rental.repository;

import com.assignment.scooty.rental.model.Model;
import com.assignment.scooty.rental.model.Outlet;
import com.assignment.scooty.rental.model.OutletName;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OutletRepository extends ReactiveMongoRepository<Outlet, String> {

  Flux<Outlet> findAllByOutletNameAndVehiclesModel(OutletName outletName, Model model);

  Flux<Outlet> findByOutletName(OutletName outletName);
}
