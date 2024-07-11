/*
 *Copyright (C) 2024 Claudio Nave
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.example.ipreport.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.example.ipreport.model.Statistic;

public class StatisticCsvWriter implements StatisticWriter {
  private static final String CSV_TEMPLATE = "%s;%d;%.2f;%d;%.2f";

  @Override
  public void writeStatistics(List<Statistic> statistics, Path path) throws IOException {
    var csv = statistics.stream().map(this::toCsv).collect(Collectors.joining("\n"));
    Files.writeString(path, csv);
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
