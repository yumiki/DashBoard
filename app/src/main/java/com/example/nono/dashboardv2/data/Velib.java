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
        int bike_stands;
        int number;
        String last_update;
        int available_bike_stands;
        boolean banking;
        int available_bikes;
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

        public int getBike_stands() {
            return bike_stands;
        }

        public int getNumber() {
            return number;
        }

        public String getLast_update() {
            return last_update;
        }

        public int getAvailable_bike_stands() {
            return available_bike_stands;
        }

        public boolean isBanking() {
            return banking;
        }

        public int getAvailable_bikes() {
            return available_bikes;
        }

        public String getAddress() {
            return address;
        }

        public List<Double> getPosition() {
            return position;
        }
    }

    public class Station{

    }
}
