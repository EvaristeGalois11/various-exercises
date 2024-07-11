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
                        "percentageRequests": "50.50",
                        "bytes": 1000,
                        "percentageBytes": "25.23"
                      },
                      {
                        "ip": "127.0.0.1",
                        "requests": 42,
                        "percentageRequests": "50.50",
                        "bytes": 1000,
                        "percentageBytes": "25.23"
                      }
                      ]""",
          lines);
    }
  }
}
