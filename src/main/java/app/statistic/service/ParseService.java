package app.statistic.service;

import app.statistic.model.InputData;

public interface ParseService {
    
    InputData parseToData(String line);
}
