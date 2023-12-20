package ru.egorovma.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileTests {

    private final ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void zipPdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals("Egorov, Maksim", pdf.author);
                }
            }
        }
    }

    @Test
    void zipXlsxFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS xls = new XLS(zis);
                    String value = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    Assertions.assertTrue(value.contains("XLS Test"));
                }
            }
        }
    }

    @Test
    void zipCsvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();

                    Assertions.assertEquals(7, data.size());
                    Assertions.assertArrayEquals(
                            new String[]{"FileType", "dependencies"},
                            data.get(0)
                    );
                }
            }
        }
    }
}