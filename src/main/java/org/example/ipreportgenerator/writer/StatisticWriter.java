package org.example.ipreportgenerator.writer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.example.ipreportgenerator.model.Statistic;

public interface StatisticWriter {
  void writeStatistics(List<Statistic> statistics, Path path) throws IOException;
}
