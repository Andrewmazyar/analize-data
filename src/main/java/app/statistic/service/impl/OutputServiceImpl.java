package app.statistic.service.impl;

import app.statistic.dao.CollectData;
import app.statistic.lib.Inject;
import app.statistic.lib.Service;
import app.statistic.model.InputData;
import app.statistic.service.OutputService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    private static final DateTimeFormatter PARSE_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int QUESTION = 2;
    private static final int DATE = 4;

    @Inject
    private CollectData collectData;

    @Override
    public Integer outputResult(String line) {
        String[] str = line.split(" ");
        int result = 0;
        int amount = 0;
        if (str[DATE].split("-").length == 2) {
            List<LocalDate> dateList = convertToDate(str[DATE].split("-"));
            for (InputData data : collectData.getAll()) {
                if (data.getDateTime().isAfter(dateList.get(0))
                        && data.getDateTime().isBefore(dateList.get(1))) {
                    boolean service = isService(str[1], data.getServiceId(),
                            data.getVariationId());
                    boolean question = isQuestion(str[QUESTION],
                            data.getQuestionTypeId(),
                            data.getCategoryId(),
                            data.getSubCategoryId());
                    if (service && question) {
                        result = result + data.getVariable();
                        amount++;
                    }
                }
            }
        }
        return amount == 0 ? result : result / amount;
    }

    public List<LocalDate> convertToDate(String[] dates) {
        List<LocalDate> dateList = new ArrayList<>();
        for (String date : dates) {
            String[] check = date.split("\\.");
            check[0] = check[0].length() < 2 ? "0" + check[0] : check[0];
            check[1] = check[1].length() < 2 ? "0" + check[1] : check[1];
            String d = String.join(".", check);
            dateList.add(LocalDate.parse(d, PARSE_DATE));
        }
        return dateList;
    }

    public boolean isService(String line, Integer id, Integer variation) {
        if (line.equals("*")) {
            return true;
        }
        String[] len = line.split("\\.");
        Integer checkId = Integer.parseInt(len[0]);
        if (len.length == 2) {
            Integer checkVariation = Integer.parseInt(len[1]);
            if (id == null || variation == null) {
                return false;
            }
            return id.equals(checkId) && variation.equals(checkVariation);
        }
        return id.equals(checkId);
    }

    public boolean isQuestion(String line, Integer id, Integer category, Integer sub) {
        if (line.equals("*")) {
            return true;
        }
        String[] len = line.split("\\.");
        Integer checkId = Integer.parseInt(len[0]);
        if (len.length == 3) {
            Integer checkCategory = Integer.parseInt(len[1]);
            Integer checkSub = Integer.parseInt(len[2]);
            if (id == null && category == null && sub == null) {
                return false;
            }
            return checkId.equals(id) && checkCategory.equals(category) && checkSub.equals(sub);
        } else if (len.length == 2) {
            Integer checkCategory = Integer.parseInt(len[1]);
            if (id == null && category == null) {
                return false;
            }
            return checkId.equals(id) && checkCategory.equals(category);
        }
        return checkId.equals(id);
    }
}
