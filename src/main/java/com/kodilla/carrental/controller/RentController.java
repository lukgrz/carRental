package com.kodilla.carrental.controller;

import com.kodilla.carrental.domain.Rent;
import com.kodilla.carrental.dto.RentDto;
import com.kodilla.carrental.mapper.RentMapper;
import com.kodilla.carrental.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;
    private final RentMapper rentMapper;

    @GetMapping(value = "/rents/{id}")
    public RentDto getRent(@PathVariable Long id) {
        Rent rent = rentService.getRent(id);
        return rentMapper.mapToRentDto(rent);
    }

    @GetMapping(value = "/rents")
    public List<RentDto> getRents() {
        List<Rent> rents = rentService.getRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    @PostMapping(value = "/rents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        rentService.saveRent(rent);
    }


    @PutMapping(value = "/rents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        rentService.saveRent(rent);
    }

    @DeleteMapping(value = "/rents/{id}")
    public void deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
    }

    @PutMapping(value = "/rents/{rentId}/addEquipment/{equipmentId}")
    public Rent addEquipment(@PathVariable Long rentId, @PathVariable Long equipmentId) {
        rentService.addEquipmentToRent(rentId, equipmentId);
        return rentService.getRent(rentId);
    }

    @PutMapping(value = "/rents/{rentId}/removeEquipment/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Rent removeEquipment(@PathVariable Long rentId, @PathVariable Long equipmentId) {
        Rent rent = rentService.getRent(rentId);
        rentService.removeEquipmentFromRent(rentId, equipmentId);
        return rentService.getRent(rentId);
    }
}
