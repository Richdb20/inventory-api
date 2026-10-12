package com.example.inventory_api.controller;

import com.example.inventory_api.repository.SwitchRepository;
import com.example.inventory_api.model.Switch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/switches")
public class SwitchController {

    private final SwitchRepository switchRepository;

    public SwitchController(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    // GET: Retrieve all switches
    @GetMapping
    public List<Switch> getAllSwitches() {
        return switchRepository.findAll();
    }

    // GET: Retrieve one switch by ID
    @GetMapping("/{id}")
    public ResponseEntity<Switch> getSwitchById(
            @PathVariable int id) {

        return switchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create a new switch
    @PostMapping
    public Switch createSwitch(@RequestBody Switch networkSwitch) {
        return switchRepository.save(networkSwitch);
    }

    // PUT: Update an existing switch
    @PutMapping("/{id}")
    public ResponseEntity<Switch> updateSwitch(
            @PathVariable int id,
            @RequestBody Switch updatedSwitch) {

            return switchRepository.findById(id)
                .map(existingSwitch ->{
                    existingSwitch.setSwitchModel(
                            updatedSwitch.getSwitchModel());

                    existingSwitch.setSwitchHostName(
                            updatedSwitch.getSwitchHostName());

                    existingSwitch.setSwitchLocation(
                            updatedSwitch.getSwitchLocation());

                    existingSwitch.setSwitchLifecycle(
                            updatedSwitch.getSwitchLifecycle());

                    Switch savedSwitch =
                            switchRepository.save(existingSwitch);

                    return ResponseEntity.ok(savedSwitch);
                })
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Delete a switch by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {

        if (!switchRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        switchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
