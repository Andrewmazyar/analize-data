package app.statistic.service.impl;

import app.statistic.dao.CollectData;
import app.statistic.lib.Inject;
import app.statistic.lib.Service;
import app.statistic.model.InputData;
import app.statistic.service.OutputService;
import app.statistic.service.ParseService;
import app.statistic.service.ReadFileService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadFileServiceImpl implements ReadFileService {
    private static final char WRITE = 'C';
    private static final char READ = 'D';

    @Inject
    private CollectData collectData;

    @Inject
    private ParseService parseService;

    @Inject
    private OutputService outputService;

    @Override
    public List<String> readFile(String path) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            throw new IOException("No such file: ", e);
        }
        return strings;
    }

    public List<InputData> makeStatistic(String path) throws IOException {
        int count = 0;
        int fileValue = 0;
        for (String line : readFile(path)) {
            if (count == 0) {
                fileValue = Integer.parseInt(line);
                count++;
            } else if (count <= fileValue) {
                String[] str = line.split(" ");
                if (line.charAt(0) == WRITE) {
                    collectData.addData(parseService.parseToData(line));
                } else if (line.charAt(0) == READ) {
                    System.out.println(outputService.outputResult(line));
                }
                count++;
            } else {
                break;
            }
        }
        return collectData.getAll();
    }
}
