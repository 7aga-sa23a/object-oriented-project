package haga_talga.page;

import haga_talga.app.Main;
import haga_talga.util.Formatter;
import haga_talga.util.QRGenerator;

public final class GenerateQRPage extends Page {

    public GenerateQRPage() {
    }

    @Override
    public String display() {
        Formatter.header("GENERATE QR CODE", "cyan", "single", "center", 140);
        System.out.println();
        
        QRGenerator.promptAndGenerate();

        System.out.println();
        Formatter.prompt("Press Enter to return to Dashboard...", "blue");
        Main.scanner.nextLine();

        return "DashboardPage";
    }
}
