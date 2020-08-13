package app.statistic.test;

import java.io.IOException;
import java.util.List;
import app.statistic.service.ReadFileService;
import app.statistic.service.impl.ReadFileServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileReadTest {
    ReadFileService readFileService;

    @Before
    public void init() {
        readFileService = new ReadFileServiceImpl();
    }

    @Test
    public void readFileSuccess() throws IOException {
        StringBuilder expected = new StringBuilder();
        expected.append("[7, ");
        expected.append("C 1.1 8.15.1 P 15.10.2012 83, ");
        expected.append("C 1 10.1 P 01.12.2012 65, ");
        expected.append("C 1.1 5.5.1 P 01.11.2012 117, ");
        expected.append("D 1.1 8 P 01.01.2012-01.12.2012, ");
        expected.append("C 3 10.2 N 02.10.2012 100, ");
        expected.append("D 1 * P 8.10.2012-20.11.2012, ");
        expected.append("D 3 10 P 01.12.2012 0.11.2012]");
        List<String> actual = readFileService.readFile("src/test/java/resources/datas");
        Assert.assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void readLinesOfEmptyFile() throws IOException {
        Assert.assertTrue(readFileService.readFile("src/test/java/resources/emptyFile").isEmpty());
    }
}
