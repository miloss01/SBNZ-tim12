package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;
import org.kie.api.definition.type.Role;

import java.util.Objects;

@Role(Role.Type.FACT)
public class Plant {

    @Position(0)
    private String plant;
    @Position(1)
    private String type;

    public Plant() {
    }

    public Plant(String plant, String type) {
        this.plant = plant;
        this.type = type;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant1 = (Plant) o;
        return Objects.equals(plant, plant1.plant) && Objects.equals(type, plant1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plant, type);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plant='" + plant + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
