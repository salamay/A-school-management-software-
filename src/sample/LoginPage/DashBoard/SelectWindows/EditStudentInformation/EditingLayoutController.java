package sample.LoginPage.DashBoard.SelectWindows.EditStudentInformation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sample.Configuration.Configuration;
import sample.ConnectionError;
import sample.LoginPage.DashBoard.SelectWindows.Registeration.LoadingWindow;
import sample.LoginPage.LogInModel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class EditingLayoutController implements Initializable {
    private String studentid;
    public TableView<InformationEntity> tableView;
    public TableColumn<InformationEntity,String> studentnamecolumn;
    public TableColumn<InformationEntity,String> agecolumn;
    public TableColumn<InformationEntity,String> fathernamecolumn;
    public TableColumn<InformationEntity,String> mothernamecolumn;
    public TableColumn<InformationEntity,String> othernamecolumn;
    public TableColumn<InformationEntity,String>nextofkincolumn;
    public TableColumn<InformationEntity,String> addresscolumn;
    public TableColumn<InformationEntity,String> phonenumbercolumn;
    public TableColumn<InformationEntity,String> parentphonenumbercolumn;
    public TableColumn<InformationEntity,String> nicknamecolumn;
    public TableColumn<InformationEntity,String> hobbiescolumn;
    public TableColumn<InformationEntity,String> turnoncolumn;
    public TableColumn<InformationEntity,String> turnoffcolumn;
    public TableColumn<InformationEntity,String> clubcolumn;
    public TableColumn<InformationEntity,String> rolemodelcolumn;
    public TableColumn<InformationEntity,String> futureambitioncolumn;
    public TableColumn<InformationEntity,String> gendercolumn;
    public TableColumn<InformationEntity,String> studentclasscolumn;
    public TableColumn<InformationEntity,String> sessioncolumn;
    public TableColumn<InformationEntity,String> tagcolumn;
    public TableColumn<InformationEntity,Integer> idcolumn;
    public ImageView studentpicture;
    public ImageView fatherpicture;
    public ImageView motherpicture;
    public ImageView guardianPicture;
    private File file;
    private File MotherImageFile;
    private File FatherImageFile;
    private File GuardianImageFile;
    private BufferedImage bufferedImage;
    private BufferedImage bufferedImage2;
    private BufferedImage bufferedImage3;
    private BufferedImage bufferedImage4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(true);
        //This student id is used to fetch information by the Inner class
        this.studentid=EditStudentInformationLayoutWindow.studentid;
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        studentnamecolumn.setCellValueFactory(new PropertyValueFactory<>("studentname"));
        studentnamecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        studentnamecolumn.setOnEditCommit((e)->{
            editColumn(studentnamecolumn,e);
            e.getRowValue().setStudentname(String.valueOf(e.getNewValue()));
            tableView.refresh();
        });
        agecolumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        agecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        agecolumn.setOnEditCommit((e)->{
            editColumn(agecolumn,e);
            e.getRowValue().setAge(String.valueOf(e.getNewValue()));
            tableView.refresh();
        });

        fathernamecolumn.setCellValueFactory(new PropertyValueFactory<>("fathername"));
        fathernamecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fathernamecolumn.setOnEditCommit((e)->{
            editColumn(fathernamecolumn,e);
            e.getRowValue().setFathername(e.getNewValue());
            tableView.refresh();
        });

        mothernamecolumn.setCellValueFactory(new PropertyValueFactory<>("mothername"));
        mothernamecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mothernamecolumn.setOnEditCommit((e)->{
            editColumn(mothernamecolumn,e);
            e.getRowValue().setMothername(e.getNewValue());
            tableView.refresh();
        });

        othernamecolumn.setCellValueFactory(new PropertyValueFactory<>("guardianname"));
        othernamecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        othernamecolumn.setOnEditCommit((e)->{
            editColumn(othernamecolumn,e);
            e.getRowValue().setGuardianname(e.getNewValue());
            tableView.refresh();
        });

        sessioncolumn.setCellValueFactory(new PropertyValueFactory<>("session"));

        nextofkincolumn.setCellValueFactory(new PropertyValueFactory<>("nextofkin"));
        nextofkincolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nextofkincolumn.setOnEditCommit((e)->{
            editColumn(nextofkincolumn,e);
            e.getRowValue().setNextofkin(e.getNewValue());
            tableView.refresh();
        });

        addresscolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addresscolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addresscolumn.setOnEditCommit((e)->{
            editColumn(addresscolumn,e);
            e.getRowValue().setAddress(e.getNewValue());
            tableView.refresh();
        });

        phonenumbercolumn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));
        phonenumbercolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phonenumbercolumn.setOnEditCommit((e)->{
            editColumn(phonenumbercolumn,e);
            e.getRowValue().setPhoneno(e.getNewValue());
            tableView.refresh();
        });

        parentphonenumbercolumn.setCellValueFactory(new PropertyValueFactory<>("parentphoneno"));
        parentphonenumbercolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        parentphonenumbercolumn.setOnEditCommit((e)->{
            editColumn(parentphonenumbercolumn,e);
            e.getRowValue().setParentphoneno(e.getNewValue());
            tableView.refresh();
        });

        nicknamecolumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        nicknamecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nicknamecolumn.setOnEditCommit((e)->{
            editColumn(nicknamecolumn,e);
            e.getRowValue().setNickname(e.getNewValue());
            tableView.refresh();
        });

        hobbiescolumn.setCellValueFactory(new PropertyValueFactory<>("hobbies"));
        hobbiescolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hobbiescolumn.setOnEditCommit((e)->{
            editColumn(hobbiescolumn,e);
            e.getRowValue().setHobbies(e.getNewValue());
            tableView.refresh();
        });

        turnoncolumn.setCellValueFactory(new PropertyValueFactory<>("turnon"));
        turnoncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        turnoncolumn.setOnEditCommit((e)->{
            editColumn(turnoncolumn,e);
            e.getRowValue().setTurnon(e.getNewValue());
            tableView.refresh();
        });


        turnoffcolumn.setCellValueFactory(new PropertyValueFactory<>("turnoff"));
        turnoffcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        turnoffcolumn.setOnEditCommit((e)->{
            editColumn(turnoffcolumn,e);
            e.getRowValue().setTurnoff(e.getNewValue());
            tableView.refresh();
        });

        clubcolumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        clubcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        clubcolumn.setOnEditCommit((e)->{
            editColumn(clubcolumn,e);
            e.getRowValue().setClub(e.getNewValue());
            tableView.refresh();
        });

        rolemodelcolumn.setCellValueFactory(new PropertyValueFactory<>("rolemodel"));
        rolemodelcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rolemodelcolumn.setOnEditCommit((e)->{
            editColumn(rolemodelcolumn,e);
            e.getRowValue().setRolemodel(e.getNewValue());
            tableView.refresh();
        });

        futureambitioncolumn.setCellValueFactory(new PropertyValueFactory<>("futureambition"));
        futureambitioncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        futureambitioncolumn.setOnEditCommit((e)->{
            editColumn(futureambitioncolumn,e);
            e.getRowValue().setFutureambition(e.getNewValue());
            tableView.refresh();
        });

        gendercolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        gendercolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gendercolumn.setOnEditCommit((e)->{
            editColumn(gendercolumn,e);
            e.getRowValue().setGender(e.getNewValue());
            tableView.refresh();
        });
        studentclasscolumn.setCellValueFactory(new PropertyValueFactory<>("clas"));

        tagcolumn.setCellValueFactory(new PropertyValueFactory<>("tag"));
        tagcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tagcolumn.setOnEditCommit((e)->{
            editColumn(tagcolumn,e);
            e.getRowValue().setTag(e.getNewValue());
            tableView.refresh();
        });
        new RetrieveInformationThread().start();
    }
    public void editColumn(TableColumn<?, ?> column, TableColumn.CellEditEvent<InformationEntity, ?> e){
        //Getting field input
        System.out.println("[EditingLayoutController]:-->Getting filed input");
        String newValue=e.getNewValue().toString();
        String id=e.getRowValue().getId();
        String columnname=column.getText();
        System.out.println("[EditingLayoutController]:-->new value: "+newValue);
        System.out.println("[EditingLayoutController]:-->Student id: "+id);
        System.out.println("[EditingLayoutController]:-->column: "+columnname);
        if (newValue!=null && id!=null && columnname!=null && newValue.matches("^[A-Za-z[,. ]0-9]*$")){
            System.out.println("[EditingLayoutController]:-->Starting thread");
            new EditInformation(newValue,id,columnname,tableView).start();
        }else {
            new ConnectionError().Connection("Invalid symbols detected,please provide valid input");
        }

    }

    public void deleteStudent() throws IOException {
        ObservableList<InformationEntity> selecteditem=tableView.getSelectionModel().getSelectedItems();
        if (selecteditem.get(0)!=null&&selecteditem.get(0).getId()!=null){
            System.out.println("[EditingLayoutController]:Student id:"+selecteditem.get(0).getId());
            new LoadingWindow();
            new DeleteStudent(selecteditem.get(0).getId(),tableView).start();
        }else {
            new ConnectionError().Connection("Please select a student to delete");
        }
    }
    public void changeStudentButton(){
        //Image File Chooser

        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All images","*.*"),
                new FileChooser.ExtensionFilter("PNG","*.PNG"),
                new FileChooser.ExtensionFilter("jpg","*.jpg")
        );
        file=fileChooser.showOpenDialog(new Stage());
        System.out.println(file);
        if (file!=null){
            bufferedImage=null;
            try {
                bufferedImage= ImageIO.read(file);
                Image image= SwingFXUtils.toFXImage(bufferedImage,null);
                String id=tableView.getItems().get(0).getId();
                if (id!=null){
                    new SavePicture(image,file,studentpicture,"student",id).start();
                }else {
                    new ConnectionError().Connection("Student id is missing, try reloading information");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeFatherPicture() throws IOException {
        //        File Chooser
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Image","*.*"),
                new FileChooser.ExtensionFilter("PNG","*.PNG"),
                new FileChooser.ExtensionFilter("JPG","*.JPG"));
//      Image Processing
        FatherImageFile=fileChooser.showOpenDialog(new Stage());
        if (FatherImageFile!=null){
            BufferedImage bufferedImage=ImageIO.read(FatherImageFile);
            Image image=SwingFXUtils.toFXImage(bufferedImage,null);
            String id=tableView.getItems().get(0).getId();
            if (id!=null){
                new SavePicture(image,FatherImageFile,fatherpicture,"father",id).start();
            }else {
                new ConnectionError().Connection("Student id is missing, try reloading information");
            }

        }
    }
    public void changeMotherPicture() throws IOException {
        //        File Chooser
        System.out.println("Mother Button Clicked");
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Image","*.*"),
                new FileChooser.ExtensionFilter("PNG","*.PNG"),
                new FileChooser.ExtensionFilter("JPG","*.JPG"));
//        Image Processing
        MotherImageFile=fileChooser.showOpenDialog(new Stage());
        if (MotherImageFile!=null){
            BufferedImage bufferedImage=ImageIO.read(MotherImageFile);
            Image image=SwingFXUtils.toFXImage(bufferedImage,null);
            String id=tableView.getItems().get(0).getId();
            if (id!=null){
                new SavePicture(image,MotherImageFile,motherpicture,"mother",id).start();
            }else {
                new ConnectionError().Connection("Student id is missing, try reloading information");
            }
        }
    }

    public void changeGuardianPicture() throws IOException {
        //        File Chooser
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Image","*.*"),
                new FileChooser.ExtensionFilter("PNG","*.PNG"),
                new FileChooser.ExtensionFilter("JPG","*.JPG"));
//        Image Processing
        GuardianImageFile=fileChooser.showOpenDialog(new Stage());
        if (GuardianImageFile!=null){
            BufferedImage bufferedImage=ImageIO.read(GuardianImageFile);
            Image image=SwingFXUtils.toFXImage(bufferedImage,null);
            String id=tableView.getItems().get(0).getId();
            if (id!=null){
                new SavePicture(image,GuardianImageFile,guardianPicture,"guardian",id).start();
            }else {
                new ConnectionError().Connection("Cannot edit information,Some fields are missing");
            }
        }
    }


    //This class retrieve information of the student selected from the Listview for editing student information
    private class RetrieveInformationThread extends Thread{
        @Override
        public void run() {
            Platform.runLater(()->{
                try {
                    new LoadingWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if (Configuration.ipaddress!=null && Configuration.port!=null){

                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .build();
                Request request=new Request.Builder()
                        .url("http://"+Configuration.ipaddress+":"+Configuration.port+"/retrievestudentinformation/"+studentid)
                        .addHeader("Authorization","Bearer "+ LogInModel.token)
                        .build();
                Response response;
                try {
                    response=client.newCall(request).execute();
                    System.out.println("[EditingLayoutController]"+response);
                    if (response.code()==200){
                        ResponseBody responseBody=response.body();
                        System.out.println("[EditingLayoutController]"+responseBody);
                        /////Retrieving and processing body,The response contains images and string value
                        //Images is received as bytes and converted back from json to respective object
                        byte[] bytes=responseBody.bytes();
                        System.out.println("[EditingLayoutController]: byte retrieved from server");
                        String data=new String(bytes,"UTF-8");
                        System.out.println("[EditingLayoutController]"+responseBody);
                        System.out.println("[EditingLayoutController]:Converting Json body");
                        GsonBuilder builder=new GsonBuilder();
                        builder.setPrettyPrinting();
                        builder.serializeNulls();
                        Gson gson=builder.create();
                        InformationEntity informationEntity=gson.fromJson(data,InformationEntity.class);
                        System.out.println("[student byte]: "+ Arrays.toString(informationEntity.getStudent()));
                        Platform.runLater(()->{
                            LoadingWindow.window.close();
                            ObservableList<InformationEntity> info= FXCollections.observableArrayList();
                            info.add(informationEntity);
                            tableView.setItems(info);
                            tableView.refresh();
                        });
                        byte[] student=informationEntity.getStudent();
                        byte[] father=informationEntity.getFather();
                        byte[] mother=informationEntity.getMother();
                        byte[] other=informationEntity.getOther();
                        Path path= Paths.get(System.getProperty("user.dir")+"/MyChildSchool");
                        Files.createDirectories(path);
                        System.out.println("[EditingLayoutController]: Creating path on the Pc to store Images");
                        if (Files.exists(path)){
                            System.out.println("[EditingLayoutController]:Creating new Path");
                            System.out.println("[EditingLayoutController]:Processing student image");
                            File studentimage=new File(System.getProperty("user.dir")+"/MyChildSchool/student");
                            FileOutputStream sout=new FileOutputStream(studentimage);
                            sout.write(student);
                            if (studentimage!=null){
                                System.out.println("[EditingLayoutController]:student image is valid");
                                bufferedImage=ImageIO.read(studentimage);
                                Image s=SwingFXUtils.toFXImage(bufferedImage,null);
                                System.out.println("[EditingLayoutController]:Displaying student image");
                                Platform.runLater(()->{
                                    studentpicture.setImage(s);
                                });
                                System.out.println("[EditingLayoutController]:Processing student image successful");
                            }
                            else {
                                System.out.println("[EditingLayoutController]:student image is null cannot read file");
                            }
                            //////Processing Father Image
                            System.out.println("[EditingLayoutController]:Processing father image ");
                            File fatherimage=new File(System.getProperty("user.dir")+"/MyChildSchool/father");
                            FileOutputStream fout=new FileOutputStream(fatherimage);
                            fout.write(father);
                            if (fatherimage!=null){
                                System.out.println("[EditingLayoutController]:father image is valid");
                                bufferedImage2=ImageIO.read(fatherimage);
                                Image f=SwingFXUtils.toFXImage(bufferedImage2,null);
                                System.out.println("[EditingLayoutController]:Displaying Father image");
                                Platform.runLater(()->{
                                    fatherpicture.setImage(f);
                                });
                                System.out.println("[EditingLayoutController]:Processing father image successful");
                            }else {
                                System.out.println("[EditingLayoutController]:father image is null cannot read file");
                            }
                            ///Processing Mother image
                            System.out.println("[EditingLayoutController]:Processing mother image ");
                            File motherimage=new File(System.getProperty("user.dir")+"/MyChildSchool/mother");
                            FileOutputStream mout=new FileOutputStream(motherimage);
                            mout.write(mother);
                            if (motherimage!=null){
                                System.out.println("[EditingLayoutController]:mother image is valid");
                                bufferedImage3=ImageIO.read(motherimage);
                                Image m=SwingFXUtils.toFXImage(bufferedImage3,null);
                                System.out.println("[EditingLayoutController]:Displaying mother image");
                                Platform.runLater(()->{
                                    motherpicture.setImage(m);
                                });
                                System.out.println("[EditingLayoutController]:Processing mother image successful");
                            }
                            else {
                                System.out.println("[EditingLayoutController]:mother image is null cannot read file");
                            }

                            ///Processing Guardian image
                            System.out.println("[EditingLayoutController]:Processing Guardian image ");
                            File otherimage=new File(System.getProperty("user.dir")+"/MyChildSchool/mother");
                            FileOutputStream oout=new FileOutputStream(otherimage);
                            oout.write(other);
                            if (otherimage!=null){
                                System.out.println("[EditingLayoutController]:guardian image is valid");
                                bufferedImage4=ImageIO.read(otherimage);
                                Image o=SwingFXUtils.toFXImage(bufferedImage4,null);
                                System.out.println("[EditingLayoutController]:Displaying guardian image");
                                Platform.runLater(()->{
                                    guardianPicture.setImage(o);
                                });
                                System.out.println("[EditingLayoutController]:Processing guardian image successful");
                            }
                            else {
                                System.out.println("[EditingLayoutController]:guardian image is null cannot read file");
                            }

                        }else {
                            System.out.println("[EditingLayoutController]:path to store images does not exists" );
                        }
                        System.out.println("[class]"+informationEntity.getClass());
                        response.close();
                    }else {
                        String message=new String(response.body().bytes(),"UTF-8");
                        //Display alert dialog
                        Platform.runLater(()->{
                            boolean error=new ConnectionError().Connection(response.code()+":"+message);
                            if (error){
                                LoadingWindow.window.close();
                                EditstudentInformationWindow.StudentWindow.close();
                                System.out.println("[EditingLayoutController]--> Connection Error,Window close");
                                response.close();
                            }
                        });
                    }
                } catch (IOException e) {
                    //Display an Alert dialog
                    Platform.runLater(()->{
                        boolean error=new ConnectionError().Connection("Unable to establish,CHECK INTERNET CONNECTION");
                        if (error){
                            LoadingWindow.window.close();
                            EditstudentInformationWindow.StudentWindow.close();
                            System.out.println("[EditingLayoutController]--> Connection Error,Window close");
                        }
                    });
                    System.out.println("[EditingLayoutController]: Request failed");
                    e.printStackTrace();
                }
            }else{
                Platform.runLater(()->{
                    LoadingWindow.window.close();
                    new ConnectionError().Connection("Invalid configuration, please configure your software in the log in page");
                });
            }
        }
    }
}
