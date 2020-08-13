package app.statistic.dao.impl;

import app.statistic.dao.CollectData;
import app.statistic.dao.Storage;
import app.statistic.lib.Dao;
import app.statistic.model.InputData;
import java.util.List;

@Dao
public class CollectDataImpl implements CollectData {
    Storage storage = new Storage();

    @Override
    public InputData addData(InputData data) {
        storage.getData().add(data);
        return data;

    }

    @Override
    public List<InputData> getAll() {
        return storage.getData();
    }
}
