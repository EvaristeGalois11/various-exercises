package org.example.ipreport.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import org.example.ipreport.model.Statistic;
import org.example.ipreport.util.PathUtils;
import org.junit.jupiter.api.Test;

class StatisticJsonWriterTest extends StatisticsWriteTest {
  private final StatisticJsonWriter statisticJsonWriter = new StatisticJsonWriter();

  @Test
  void writeStatisticsTest() throws IOException, URISyntaxException {
    var statistic = new Statistic("127.0.0.1", 42, 50.496d, 1000, 25.232);
    var testFile = PathUtils.getTestFile("write-statistics.csv");
    statisticJsonWriter.writeStatistics(List.of(statistic, statistic), testFile);
    try (var stream = Files.lines(testFile)) {
      var lines = stream.collect(Collectors.joining("\n"));
      assertEquals(
          """
                      [
                      {
                        "ip": "127.0.0.1",
                        "requests": 42,
                        "percentageRequests": "50,50",
                        "bytes": 1000,
                        "percentageBytes": "25,23"
                      },
                      {
                        "ip": "127.0.0.1",
                        "requests": 42,
                        "percentageRequests": "50,50",
                        "bytes": 1000,
                        "percentageBytes": "25,23"
                      }
                      ]""",
          lines);
    }
  }
}
