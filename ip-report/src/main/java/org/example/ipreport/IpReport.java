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
package org.example.ipreport;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.example.ipreport.model.Log;
import org.example.ipreport.model.Statistic;
import org.example.ipreport.reader.LogCsvReader;
import org.example.ipreport.writer.StatisticCsvWriter;
import org.example.ipreport.writer.StatisticJsonWriter;

public class IpReport {
  public static void generate() throws IOException {
    generate(Path.of("/reports/ipaddr.csv"), Path.of("/logfiles/requests.log"));
  }

  public static void generate(Path input, Path output) throws IOException {
    generate(input, output, Type.CSV);
  }

  public static void generate(Path input, Path output, Type type) throws IOException {
    var logs = readAndFilterLogs(input);
    var totalRequests = calculateRequests(logs);
    var totalBytes = calculateBytes(logs);
    var statistics = calculateStatistics(logs, totalRequests, totalBytes);
    writeReport(statistics, output, type);
  }

  // TODO Filter by timestamp
  private static List<Log> readAndFilterLogs(Path input) throws IOException {
    var reader = new LogCsvReader();
    return reader.readLogs(input).stream().filter(log -> log.status() == 200).toList();
  }

  private static long calculateRequests(List<Log> logs) {
    return logs.size();
  }

  private static long calculateBytes(List<Log> logs) {
    return logs.stream().mapToLong(Log::bytes).sum();
  }

  private static List<Statistic> calculateStatistics(
      List<Log> logs, long totalRequests, long totalBytes) {
    var logsByIp = logs.stream().collect(Collectors.groupingBy(Log::ip));
    return logsByIp.entrySet().stream()
        .map(e -> calculateStatistic(e.getKey(), e.getValue(), totalRequests, totalBytes))
        .sorted(Comparator.comparingLong(Statistic::requests).reversed())
        .toList();
  }

  private static Statistic calculateStatistic(
      String ip, List<Log> logs, long totalRequests, long totalBytes) {
    var requests = calculateRequests(logs);
    var bytes = calculateBytes(logs);
    return new Statistic(
        ip,
        requests,
        ((double) requests) / totalRequests * 100,
        bytes,
        ((double) bytes) / totalBytes * 100);
  }

  private static void writeReport(List<Statistic> statistics, Path output, Type type)
      throws IOException {
    var writer = type == Type.CSV ? new StatisticCsvWriter() : new StatisticJsonWriter();
    writer.writeStatistics(statistics, output);
  }

  public enum Type {
    CSV,
    JSON
  }
}
