package services;

import models.AttendanceRecord;
import models.Course;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportService {
    private static final String[] HEADERS = { "كود الطالب", "اسم الطالب", "وقت الحضور", "حضور" };

    public boolean exportToExcel(Course course, String filePath) {
        if (course == null || filePath == null)
            return false;
        List<AttendanceRecord> records = course.getRecords();
        if (records.isEmpty()) {
            System.out.println("No records to export.");
            return false;
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            Workbook workbook = new Workbook(fos, "Mersad Attendance", "1.0");
            Worksheet sheet = workbook.newWorksheet("Attendance");

            sheet.width(0, 15);
            sheet.width(1, 30);
            sheet.width(2, 20);
            sheet.width(3, 12);

            // ── Row 0: Title ──────────────────────────────────────────
            sheet.value(0, 0, "كشف الحضور - " + course.getName());
            sheet.range(0, 0, 0, 3).merge();
            sheet.style(0, 0)
                    .bold()
                    .fontSize(16)
                    .horizontalAlignment("center")
                    .verticalAlignment("center")
                    .fillColor("0078D7")
                    .fontColor("FFFFFF")
                    .set();

            // ── Row 1: Headers ────────────────────────────────────────
            sheet.value(1, 0, HEADERS[0]);
            sheet.value(1, 1, HEADERS[1]);
            sheet.value(1, 2, HEADERS[2]);
            sheet.value(1, 3, HEADERS[3]);
            sheet.range(1, 0, 1, 3).style()
                    .bold()
                    .fontSize(12)
                    .borderStyle("thin")
                    .fillColor("DCE6F1")
                    .fontColor("000000")
                    .horizontalAlignment("center")
                    .verticalAlignment("center")
                    .set();

            // ── Rows 2+: Data ─────────────────────────────────────────
            for (int i = 0; i < records.size(); i++) {
                AttendanceRecord record = records.get(i);
                int row = i + 2;

                sheet.value(row, 0, record.getStudentId());
                sheet.value(row, 1, record.getStudentName());
                sheet.value(row, 2, record.getFormattedTime());
                sheet.value(row, 3, "حاضر");

                String rowFill = (i % 2 == 0) ? "F2F2F2" : "FFFFFF";

                // Columns 0–2: single chained call
                sheet.range(row, 0, row, 2).style()
                        .borderStyle("thin")
                        .fillColor(rowFill)
                        .fontColor("000000")
                        .horizontalAlignment("center")
                        .verticalAlignment("center")
                        .set();

                // Column 3 ("حاضر"): single chained call
                sheet.range(row, 3, row, 3).style()
                        .borderStyle("thin")
                        .fillColor("92D050")
                        .fontColor("FFFFFF")
                        .bold()
                        .horizontalAlignment("center")
                        .verticalAlignment("center")
                        .set();
            }

            workbook.finish();
            System.out.println("Exported to: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("Export error: " + e.getMessage());
            return false;
        }
    }

    public boolean exportBackup(Course course) {
        return exportToExcel(course, "data/attendance/" + course.getBackupFileName());
    }

    public boolean exportFinal(Course course) {
        File folder = new File("data/attendance");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return exportToExcel(course, "data/attendance/" + course.getExportFileName());
    }
}