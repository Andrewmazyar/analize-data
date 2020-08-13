package app.statistic.test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import app.statistic.model.InputData;
import app.statistic.service.impl.ParseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParseServiceTest {
    private static final DateTimeFormatter PARSE_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private ParseServiceImpl parseService;

    @Before
    public void setUp() throws Exception {
        parseService = new ParseServiceImpl();
    }

    @Test
    public void parseServiceSuccess() {
        InputData actual = parseService.parseToData("C 1.1 8.15.1 P 15.10.2012 83");
        InputData expected = new InputData();
        expected.setServiceId(1);
        expected.setVariationId(1);
        expected.setQuestionTypeId(8);
        expected.setCategoryId(15);
        expected.setSubCategoryId(1);
        expected.setQuestion("first answer");
        expected.setDateTime(LocalDate.parse("15.10.2012", PARSE_DATE));
        expected.setVariable(83);
        Assert.assertEquals(actual.getServiceId(), expected.getServiceId());
        Assert.assertEquals(actual.getVariationId(), expected.getVariationId());
        Assert.assertEquals(actual.getQuestionTypeId(), expected.getQuestionTypeId());
        Assert.assertEquals(actual.getCategoryId(), expected.getCategoryId());
        Assert.assertEquals(actual.getSubCategoryId(), expected.getSubCategoryId());
        Assert.assertEquals(actual.getQuestion(), expected.getQuestion());
        Assert.assertEquals(actual.getDateTime(), expected.getDateTime());
        Assert.assertEquals(actual.getVariable(), expected.getVariable());
    }
}
