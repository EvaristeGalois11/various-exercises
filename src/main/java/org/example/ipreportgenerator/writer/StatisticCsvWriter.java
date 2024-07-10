package org.example.ipreportgenerator.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.example.ipreportgenerator.model.Statistic;

public class StatisticCsvWriter implements StatisticWriter {
  private static final String CSV_TEMPLATE = "%s;%d;%.2f;%d;%.2f";

  @Override
  public void writeStatistics(List<Statistic> statistics, Path path) throws IOException {
    try (var writer = Files.newBufferedWriter(path)) {
      for (var statistic : statistics) {
        writer.write(toCsv(statistic));
      }
    }
  }

  private String toCsv(Statistic statistic) {
    return CSV_TEMPLATE.formatted(
        statistic.ip(),
        statistic.requests(),
        statistic.percentageRequests(),
        statistic.bytes(),
        statistic.percentageBytes());
  }
}
