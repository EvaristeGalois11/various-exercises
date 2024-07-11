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
package org.example.ipreport.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import org.example.ipreport.model.Log;

public class LogCsvReader implements LogReader {
  @Override
  public List<Log> readLogs(Path path) throws IOException {
    try (var lines = Files.lines(path)) {
      return lines
          .map(line -> line.split(";"))
          .map(
              a -> new Log(Instant.parse(a[0]), Long.parseLong(a[1]), Integer.parseInt(a[2]), a[3]))
          .toList();
    }
  }
}
