package org.example.ipreportgenerator.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.example.ipreportgenerator.model.Statistic;

public class StatisticJsonWriter implements StatisticWriter {
  private static final String JSON_TEMPLATE =
      """
      {
        "ip": "%s",
        "requests": %d,
        "percentageRequests": "%.2f",
        "bytes": %d,
        "percentageBytes": "%.2f"
      }""";

  @Override
  public void writeStatistics(List<Statistic> statistics, Path path) throws IOException {
    var json = statistics.stream().map(this::toJson).collect(Collectors.joining(",\n", "[\n", "\n]"));
    Files.writeString(path, json);
  }

  private String toJson(Statistic statistic) {
    return JSON_TEMPLATE.formatted(
        statistic.ip(),
        statistic.requests(),
        statistic.percentageRequests(),
        statistic.bytes(),
        statistic.percentageBytes());
  }
}
