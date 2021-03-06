package sample.LoginPage.DashBoard.Printing;

import sample.LoginPage.DashBoard.Admin.SchoolFee.getDebtors.Jasperprintdoc;
import javafx.application.Platform;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrintManager;
import sample.ConnectionError;
import sample.LoginPage.DashBoard.SelectWindows.Registeration.LoadingWindow;

import java.io.*;

public class PrinterManager extends Thread{

    private byte[] pdfdocumentbytes;
    private File pdffile;
    public PrinterManager(byte[] pdfdocumentbytes, File pdffile) {
        this.pdfdocumentbytes=pdfdocumentbytes;
        this.pdffile=pdffile;
    }

    @Override
    public void run() {
        System.out.println("[Printer manager]: Starting to print");
        System.out.println("[Printer manager]: Preparing file");
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(pdffile);
                fileOutputStream.write(pdfdocumentbytes);
                fileOutputStream.close();
                FileInputStream fileInputStream=new FileInputStream(pdffile);
                ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
                Jasperprintdoc jasperprintdoc=(Jasperprintdoc) objectInputStream.readObject();
                boolean result=JasperPrintManager.printReport(jasperprintdoc.jasperPrint,true);
                if (result){
                    new ConnectionError().Connection("Printed");
                }else {
                    new ConnectionError().Connection("Failed to print");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JRException e) {
                e.printStackTrace();
            }

//        if (pdfdocumentbytes!=null){
//            System.out.println("[Printer manager]: Preparing file");
//            try {
//
//                FileOutputStream fileOutputStream=new FileOutputStream(pdffile);
//                fileOutputStream.write(pdfdocumentbytes);
//                fileOutputStream.close();
//                FileInputStream fileInputStream=new FileInputStream(pdffile);
//                fileInputStream.close();
//                DocFlavor flavor=DocFlavor.INPUT_STREAM.AUTOSENSE;
//                PrintRequestAttributeSet asset=new HashPrintRequestAttributeSet();
//                asset.add(MediaSizeName.ISO_A4);
//                asset.add(Sides.DUPLEX);
//                PrintService defaultprinter=PrintServiceLookup.lookupDefaultPrintService();
//                PrintService[] printers=PrintServiceLookup.lookupPrintServices(flavor,null);
//                System.out.println("[Printer manager]: all Printers: "+printers.length);
//                for (int i=0;i<printers.length;i++){
//                    System.out.println("[Printer manager]: Printers: "+printers[i].getName());
//                }
//                if (printers.length==0){
//                    if (defaultprinter!=null){
//                        Platform.runLater(()->{
//                            try {
//                                new LoadingWindow();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        });
//                        System.out.println("[Printer manager]:default Printer name:"+defaultprinter.getName());
//                        Doc doc=new SimpleDoc(fileInputStream,flavor,null);
//                        DocPrintJob job=defaultprinter.createPrintJob();
//                        job.addPrintJobListener(new PrintJobListener() {
//                            @Override
//                            public void printDataTransferCompleted(PrintJobEvent pje) {
//                                System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Data Transfer completed");
//                                Platform.runLater(()->{
//                                    textArea.appendText(pje.getPrintEventType()+": Data Transfer completed\n");
//                                });
//
//                                closeL();
//                            }
//
//                            @Override
//                            public void printJobCompleted(PrintJobEvent pje) {
//                                System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": print Job completed");
//                                Platform.runLater(()->{
//                                    textArea.appendText(pje.getPrintEventType()+": Print job completed\n");
//                                });
//
//                                closeL();
//                            }
//
//                            @Override
//                            public void printJobFailed(PrintJobEvent pje) {
//                                System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Print job failed");
//                                Platform.runLater(()->{
//                                    textArea.appendText(pje.getPrintEventType()+": Print job failed\n");
//
//                                });
//                                closeL();
//                            }
//
//                            @Override
//                            public void printJobCanceled(PrintJobEvent pje) {
//                                System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Print job cancel");
//                                Platform.runLater(()->{
//                                    textArea.appendText(pje.getPrintEventType()+": Print job cancelled\n");
//                                });
//                                closeL();
//                            }
//
//                            @Override
//                            public void printJobNoMoreEvents(PrintJobEvent pje) {
//                                closeL();
//                            }
//
//                            @Override
//                            public void printJobRequiresAttention(PrintJobEvent pje) {
//                                closeL();
//                            }
//                        });
//                        if (job!=null){
//                            job.print(doc,asset);
//                            closeL();
//                        }else {
//                            closeL();
//                            return;
//                        }
//                    }else {
//                        closeL();
//                        System.out.println("[Printer manager]: No default printer");
//                    }
//
//                }else {
//                    System.out.println("[Printer manager]: Printers: Selecting printer");
//                    Platform.runLater(()->{
//                        PrintService service=ServiceUI.printDialog(null,200,200,printers,defaultprinter,flavor,asset);
//                        if (service!=null){
//                            try {
//                                Continue(service,flavor,asset,fileInputStream);
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        }else {
//                            closeL();
//                            new ConnectionError().Connection("Printing cancelled");
//                        }
//                    });
//                }
//            } catch (FileNotFoundException e) {
//                closeL();
//                e.printStackTrace();
//            } catch (IOException e) {
//                closeL();
//                e.printStackTrace();
//            } catch (PrintException e) {
//                closeL();
//                e.printStackTrace();
//                System.out.println("[Printer manager]: Unable to print");
//            }
//
//        }else {
//            closeL();
//            Platform.runLater(()->{
//                new ConnectionError().Connection("Document not available");
//            });
//        }

    }
    public void closeL(){
        Platform.runLater(()->{
            LoadingWindow.window.close();
        });
    }
//    public void Continue(PrintService service,DocFlavor flavor,PrintRequestAttributeSet asset,FileInputStream fileInputStream) throws FileNotFoundException {
//        Platform.runLater(()->{
//            try {
//                new LoadingWindow();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        Doc doc=new SimpleDoc(fileInputStream,flavor,null);
//        try {
//            DocPrintJob job1=service.createPrintJob();
//            if (job1!=null){
//                job1.addPrintJobListener(new PrintJobListener() {
//                    @Override
//                    public void printDataTransferCompleted(PrintJobEvent pje) {
//                        System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Data Transfer completed");
//                        Platform.runLater(()->{
//                            textArea.setText(pje.getPrintEventType()+": Data Transfer completed\n");
//                        });
//                        closeL();
//                    }
//
//                    @Override
//                    public void printJobCompleted(PrintJobEvent pje) {
//                        System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": print Job completed");
//                        textArea.setText(pje.getPrintEventType()+": Print job completed\n");
//                        closeL();
//                    }
//
//                    @Override
//                    public void printJobFailed(PrintJobEvent pje) {
//                        System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Print job failed");
//
//                        Platform.runLater(()->{
//                            textArea.setText(pje.getPrintEventType()+": Print job failed\n");
//                        });
//                        closeL();
//                    }
//
//                    @Override
//                    public void printJobCanceled(PrintJobEvent pje) {
//                        System.out.println("[Printer manager]: Status: "+pje.getPrintEventType()+": Print job cancel");
//                        Platform.runLater(()->{
//                            textArea.setText(pje.getPrintEventType()+": Print job cancelled\n");
//                        });
//                        closeL();
//                    }
//
//                    @Override
//                    public void printJobNoMoreEvents(PrintJobEvent pje) {
//                        closeL();
//                    }
//
//                    @Override
//                    public void printJobRequiresAttention(PrintJobEvent pje) {
//                        closeL();
//                    }
//                });
//                job1.print(doc,asset);
//                closeL();
//            }else {
//                return;
//            }
//
//        } catch (PrintException e) {
//            closeL();
//            e.printStackTrace();
//        }
//        System.out.println("[SchoolFeeWindowController]: Printed");
//    }
}
