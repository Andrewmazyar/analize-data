package app.statistic.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import app.statistic.service.impl.OutputServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OutputTest {
    OutputServiceImpl outputService;

    @Before
    public void init() {
        outputService = new OutputServiceImpl();
    }

    @Test
    public void checkQuestionOk() {
        Assert.assertTrue(outputService.checkQuestion("1.1.1", 1,1, 1));
    }

    @Test
    public void checkQuestionOkForAll() {
        Assert.assertTrue(outputService.checkQuestion("*", 1,2, 1));
    }

    @Test
    public void checkQuestionFalse() {
        Assert.assertFalse(outputService.checkQuestion("1.1.1", 1,2, 1));
    }

    @Test
    public void checkServiceFalse() {
        Assert.assertFalse(outputService.checkService("1.1", 1, 2));
    }

    @Test
    public void checkServiceOk() {
        Assert.assertTrue(outputService.checkService("*", 1,2));
    }

    @Test
    public void checkService_Ok() {
        Assert.assertTrue(outputService.checkService("1.2", 1,2));
    }

    @Test
    public void checkService_with_different_parametr_ok() {
        Assert.assertTrue(outputService.checkService("1", 1,2));
    }

    @Test
    public void checkService_check_date() {
        String[] dates = new String[2];
        dates[0] = "08.08.2020";
        dates[1] = "09.12.2020";
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2020, 8, 8));
        localDates.add(LocalDate.of(2020, 12, 9));
        Assert.assertEquals(localDates.get(0), outputService.convertToDate(dates).get(0));
        Assert.assertEquals(localDates.get(1), outputService.convertToDate(dates).get(1));
    }
}
