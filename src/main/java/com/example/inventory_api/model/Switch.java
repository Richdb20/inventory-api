package com.example.inventory_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "switch_inventory")
public class Switch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "switch_id")
    private Integer switchID;
    private String switchModel;
    private String switchHostName;
    private String switchLocation;
    private Integer switchLifecycle;

    public Switch() {

    }

    public Switch (Integer SwitchID, String switchModel, String switchHostName, String switchLocation, Integer switchLifecycle) {
        this.switchID = switchID;
        this.switchModel = switchModel;
        this.switchHostName = switchHostName;
        this.switchLocation = switchLocation;
        this.switchLifecycle = switchLifecycle;
    }

    public Integer getSwitchID() {
        return switchID;
    }

    public void setSwitchID(Integer switchID) {
        this.switchID = switchID;
    }

    public String getSwitchModel() {
        return switchModel;
    }

    public void setSwitchModel(String switchModel) {
        this.switchModel = switchModel;
    }

    public String getSwitchHostName() {
        return switchHostName;
    }

    public void setSwitchHostName(String switchHostName) {
        this.switchHostName = switchHostName;
    }

    public String getSwitchLocation() {
        return switchLocation;
    }

    public void setSwitchLocation(String switchLocation) {
        this.switchLocation = switchLocation;
    }

    public Integer getSwitchLifecycle() {
        return switchLifecycle;
    }

    public void setSwitchLifecycle(Integer switchLifecycle) {
        this.switchLifecycle = switchLifecycle;
    }
}
