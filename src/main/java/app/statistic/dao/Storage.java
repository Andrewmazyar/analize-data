package app.statistic.dao;

import app.statistic.model.InputData;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<InputData> data = new ArrayList<>();

    public List<InputData> getData() {
        return data;
    }

    public void setData(List<InputData> data) {
        this.data = data;
    }
}
