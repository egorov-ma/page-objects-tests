package ru.egorovma.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileTests {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

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

//    @Test
//    void csvFileParsingTest() throws Exception {
//        try (InputStream is = cl.getResourceAsStream("example.csv");
//             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
//
//            List<String[]> data = csvReader.readAll();
//            Assertions.assertEquals(2, data.size());
//            Assertions.assertArrayEquals(
//                    new String[]{"Selenide", "https://selenide.org"},
//                    data.get(0)
//            );
//            Assertions.assertArrayEquals(
//                    new String[]{"JUnit 5", "https://junit.org"},
//                    data.get(1)
//            );
//        }
//    }
}
