package com.example.nono.dashboardv2.data;

import java.util.List;

/**
 * Created by nono on 24/03/2017.
 */

public class Velib {
    int nhits;
    List<Records> records;

    public int getNhits() {
        return nhits;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public class Records{
        String datasetid;

        public String getDatasetid() {
            return datasetid;
        }
    }
}
