package app.statistic.service;

import app.statistic.model.InputData;
import java.io.IOException;
import java.util.List;

public interface ReadFileService {
    List<String> readFile(String file) throws IOException;

    List<InputData> makeStatistic(String path) throws IOException;
}
