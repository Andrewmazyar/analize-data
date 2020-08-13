package app.statistic.dao;

import app.statistic.model.InputData;
import java.util.List;

public interface CollectData {

    InputData addData(InputData data);

    List<InputData> getAll();

}
