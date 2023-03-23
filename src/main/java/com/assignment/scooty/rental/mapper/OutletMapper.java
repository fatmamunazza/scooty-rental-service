package com.assignment.scooty.rental.mapper;

import com.assignment.scooty.rental.dto.*;
import com.assignment.scooty.rental.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OutletMapper {

  default Outlet outletDtoToOutlet(OutletDTO outletDTO) {
    return Outlet.builder()
        .outletName(outletNameDTOToOutlet(outletDTO.getOutletName()))
        .parkingSlots(outletDTO.getParkingSlots())
        .vehicles(vehicleDtoListTovehicleList(outletDTO.getVehicles()))
        .build();
  }

  default OutletDTO outletToOutletDto(Outlet outlet) {
    return OutletDTO.builder()
        .outletName(outletNameToOutletNameDto(outlet.getOutletName()))
        .parkingSlots(outlet.getParkingSlots())
        .vehicles(vehicleListTovehicleDtoList(outlet.getVehicles()))
        .build();
  }

  default Model modelDtoToModel(ModelDTO modelDTO) {
    for (Model model : Model.values()) {
      if (model.getValue().equalsIgnoreCase(String.valueOf(modelDTO.getValue()))) {
        return model;
      }
    }

    throw new IllegalArgumentException("Unexpected value '" + modelDTO + "'");
  }

  default User userDTOToUser(UserDTO userDTO) {
    for (User user : User.values()) {
      if (user.getValue().equalsIgnoreCase(String.valueOf(userDTO.getValue()))) {
        return user;
      }
    }

    throw new IllegalArgumentException("Unexpected value '" + userDTO + "'");
  }

  default OutletName outletNameDTOToOutlet(OutletNameDTO outletNameDTO) {
    for (OutletName outletName : OutletName.values()) {
      if (outletName.getValue().equalsIgnoreCase(String.valueOf(outletNameDTO.getValue()))) {
        return outletName;
      }
    }

    throw new IllegalArgumentException("Unexpected value '" + outletNameDTO + "'");
  }

  default Vehicle vehicleDtoTovehicle(VehicleDTO vehicleDTO) {
    return Vehicle.builder()
        .reservedBy(
            vehicleDTO.getReservedBy() != null ? userDTOToUser(vehicleDTO.getReservedBy()) : null)
        .availability(vehicleDTO.getAvailability())
        .reservedUntil(vehicleDTO.getReservedUntil())
        .model(modelDtoToModel(vehicleDTO.getModel()))
        .build();
  }

  default List<Vehicle> vehicleDtoListTovehicleList(List<VehicleDTO> vehicleDTO) {
    List<Vehicle> list = new ArrayList<>();

    for (int i = 0; i < vehicleDTO.size(); i++) {
      list.add(vehicleDtoTovehicle(vehicleDTO.get(i)));
    }
    return list;
  }

  default VehicleDTO vehicleToVehicleDt0(Vehicle vehicle) {
    return VehicleDTO.builder()
        .reservedBy(vehicle.getReservedBy() != null ? userToUserDto(vehicle.getReservedBy()) : null)
        .availability(vehicle.getAvailability())
        .reservedUntil(vehicle.getReservedUntil())
        .model(modelToModelDto(vehicle.getModel()))
        .build();
  }

  default UserDTO userToUserDto(User user) {
    if (user == null) {
      return null;
    } else {
      UserDTO userDTO;
      switch (user) {
        case USER1:
          userDTO = UserDTO.user1;
          break;
        case USER2:
          userDTO = UserDTO.user2;
          break;
        case USER3:
          userDTO = UserDTO.user3;
          break;
        default:
          throw new IllegalArgumentException("Unexpected enum constant: " + user);
      }

      return userDTO;
    }
  }

  default ModelDTO modelToModelDto(Model model) {
    if (model == null) {
      return null;
    } else {
      ModelDTO userDTO;
      switch (model) {
        case Model1:
          userDTO = ModelDTO.model1;
          break;
        case Model2:
          userDTO = ModelDTO.model2;
          break;
        case Model3:
          userDTO = ModelDTO.model3;
          break;
        default:
          throw new IllegalArgumentException("Unexpected enum constant: " + model);
      }

      return userDTO;
    }
  }

  default OutletNameDTO outletNameToOutletNameDto(OutletName outletName) {
    if (outletName == null) {
      return null;
    } else {
      OutletNameDTO outletNameDto;
      switch (outletName) {
        case outlet1:
          outletNameDto = OutletNameDTO.outlet1;
          break;
        case outlet2:
          outletNameDto = OutletNameDTO.outlet2;
          break;
        case outlet3:
          outletNameDto = OutletNameDTO.outlet3;
          break;
        default:
          throw new IllegalArgumentException("Unexpected enum constant: " + outletName);
      }

      return outletNameDto;
    }
  }

  default List<VehicleDTO> vehicleListTovehicleDtoList(List<Vehicle> vehicle) {
    List<VehicleDTO> list = new ArrayList<>();

    for (int i = 0; i < vehicle.size(); i++) {
      list.add(vehicleToVehicleDt0(vehicle.get(i)));
    }
    return list;
  }
}
