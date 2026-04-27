package services;

import models.Course;

public class BackupService {
    private final ExcelExportService excelService;
    private final int backupThreshold;
    private int lastBackupCount;

    public BackupService(ExcelExportService excelService) {
        this.excelService = excelService;
        this.backupThreshold = 5;
        this.lastBackupCount = 0;
    }

    public boolean checkAndBackup(Course course) {
        if (course == null) return false;
        int currentCount = course.getRecordCount();
        if (currentCount > 0 && currentCount % backupThreshold == 0 && currentCount != lastBackupCount) {
            lastBackupCount = currentCount;
            return createBackup(course);
        }
        return false;
    }

    public boolean createBackup(Course course) {
        if (course == null || course.getRecordCount() == 0) return false;
        System.out.println("\n[BACKUP] Creating backup at " + course.getRecordCount() + " records...");
        boolean success = excelService.exportBackup(course);
        if (success) {
            System.out.println("[BACKUP] Saved: " + course.getBackupFileName());
        }
        return success;
    }

    public void resetBackupCount() {
        lastBackupCount = 0;
    }

    public int getBackupThreshold() {
        return backupThreshold;
    }
}