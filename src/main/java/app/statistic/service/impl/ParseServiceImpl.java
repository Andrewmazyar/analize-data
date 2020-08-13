package app.statistic.service.impl;

import app.statistic.lib.Service;
import app.statistic.model.InputData;
import app.statistic.service.ParseService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ParseServiceImpl implements ParseService {
    private static final DateTimeFormatter PARSE_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int SERVICE = 1;
    private static final int QUESTION = 2;
    private static final int TYPE_ANSWER = 3;
    private static final int DATE = 4;
    private static final int VARIABLE = 5;

    @Override
    public InputData parseToData(String line) {
        InputData inputData = new InputData();
        String[] str = line.split(" ");
        String[] service = str[SERVICE].split("\\.");
        if (service.length == 2) {
            inputData.setServiceId(Integer.parseInt(service[0]));
            inputData.setVariationId(Integer.parseInt(service[1]));
        } else {
            inputData.setServiceId(Integer.parseInt(service[0]));
        }
        String[] question = str[QUESTION].split("\\.");
        if (question.length == 3) {
            inputData.setQuestionTypeId(Integer.parseInt(question[0]));
            inputData.setCategoryId(Integer.parseInt(question[1]));
            inputData.setSubCategoryId(Integer.parseInt(question[2]));
        } else if (question.length == 2) {
            inputData.setQuestionTypeId(Integer.parseInt(question[0]));
            inputData.setCategoryId(Integer.parseInt(question[1]));
        } else {
            inputData.setQuestionTypeId(Integer.parseInt(question[0]));
        }
        if (str[TYPE_ANSWER].equals("P")) {
            inputData.setQuestion("first answer");
        } else {
            inputData.setQuestion("next answer");
        }
        inputData.setDateTime(LocalDate.parse(str[DATE], PARSE_DATE));
        inputData.setVariable(Integer.parseInt(str[VARIABLE]));
        return inputData;
    }
}
