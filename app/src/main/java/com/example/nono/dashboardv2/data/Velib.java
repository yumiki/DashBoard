package com.example.nono.dashboardv2.data;

import android.util.Pair;

import java.util.List;

/**
 * Created by nono on 24/03/2017.
 */

public class Velib {
    List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public class Record{
        String datasetid;
        Field fields;

        public String getDatasetid() {
            return datasetid;
        }

        public Field getFields() {
            return fields;
        }
    }

    public class Field{
        String status;
        String contract_name;
        String name;
        String bonus;
        int bikeStands;
        int number;
        String lastUpdate;
        int availableBikeStands;
        boolean banking;
        int availableBikes;
        String address;
        List<Double> position;

        public String getStatus() {
            return status;
        }

        public String getContract_name() {
            return contract_name;
        }

        public String getName() {
            return name;
        }

        public String getBonus() {
            return bonus;
        }

        public int getBikeStands() {
            return bikeStands;
        }

        public int getNumber() {
            return number;
        }

        public String getLastUpate() {
            return lastUpdate;
        }

        public int getAvailableBikeStands() {
            return availableBikeStands;
        }

        public boolean isBanking() {
            return banking;
        }

        public int getAvailableBikes() {
            return availableBikes;
        }

        public String getAddress() {
            return address;
        }

        public List<Double> getPosition() {
            return position;
        }
    }

    public static class Station{
        String status;
        String name;
        int bikeStands;
        String lastUpdate;
        int availableBikeStands;
        boolean banking;
        int availableBikes;
        String address;
        List<Double> position;

        public Station(Field field) {
            this.status = field.getStatus();
            this.name = field.getName();
            this.bikeStands = field.getBikeStands();
            this.lastUpdate = field.getLastUpate();
            this.availableBikeStands = field.getAvailableBikeStands();
            this.banking = field.isBanking();
            this.availableBikes = field.getAvailableBikes();
            this.address = field.getAddress();
            this.position = field.getPosition();
        }

        public String getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public int getBikeStands() {
            return bikeStands;
        }

        public String getLastUpdate() {
            return lastUpdate;
        }

        public int getAvailableBikeStands() {
            return availableBikeStands;
        }

        public boolean isBanking() {
            return banking;
        }

        public int getAvailableBikes() {
            return availableBikes;
        }

        public String getAddress() {
            return address;
        }

        public List<Double> getPosition() {
            return position;
        }
    }
}
