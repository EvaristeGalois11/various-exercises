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
    var json =
        statistics.stream().map(this::toJson).collect(Collectors.joining(",\n", "[\n", "\n]"));
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
