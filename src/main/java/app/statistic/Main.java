package app.statistic;

import app.statistic.lib.Injector;
import app.statistic.service.ReadFileService;
import java.io.IOException;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("app.statistic");

    public static void main(String[] args) throws IOException {
        ReadFileService readFileService
                = (ReadFileService) INJECTOR.getInstance(ReadFileService.class);
        readFileService.makeStatistic("src/main/resources/datas");
    }
}
