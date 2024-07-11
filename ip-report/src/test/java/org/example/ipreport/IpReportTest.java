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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import org.example.ipreport.util.PathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IpReportTest {
  @BeforeEach
  void prepareFiles() throws IOException, URISyntaxException {
    PathUtils.truncateFile("report.csv");
    PathUtils.truncateFile("report.json");
  }

  @Test
  void generateReportCsvTest() throws IOException, URISyntaxException {
    generateReportTest(IpReport.Type.CSV);
  }

  @Test
  void generateReportJsonTest() throws IOException, URISyntaxException {
    generateReportTest(IpReport.Type.JSON);
  }

  void generateReportTest(IpReport.Type type) throws IOException, URISyntaxException {
    var extension = type == IpReport.Type.CSV ? ".csv" : ".json";
    var testFile = PathUtils.getTestFile("requests.log");
    var reportFile = PathUtils.getTestFile("report" + extension);
    IpReport.generate(testFile, reportFile, type);
    var expectedLines = Files.readAllLines(PathUtils.getTestFile("expected-report" + extension));
    var actualLines = Files.readAllLines(reportFile);
    assertEquals(expectedLines, actualLines);
  }
}
