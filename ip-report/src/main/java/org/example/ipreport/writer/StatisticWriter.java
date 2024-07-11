package org.example.ipreport.writer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.example.ipreport.model.Statistic;

public interface StatisticWriter {
  void writeStatistics(List<Statistic> statistics, Path path) throws IOException;
}
