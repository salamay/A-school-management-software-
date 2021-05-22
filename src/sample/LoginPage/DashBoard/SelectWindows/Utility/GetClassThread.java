package sample.LoginPage.DashBoard.SelectWindows.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import sample.Configuration.Configuration;
import sample.ConnectionError;
import sample.LoginPage.DashBoard.SelectWindows.Information.ClassModel;
import sample.LoginPage.DashBoard.SelectWindows.Information.ListViewNames;
import sample.LoginPage.DashBoard.SelectWindows.Information.SelectInformationSesssionAndClassWindow;
import sample.LoginPage.LogInModel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GetClassThread extends Thread {

    private JFXComboBox<String> clas;
    private ProgressIndicator pgb;
    private String endpoint;
    public GetClassThread(JFXComboBox<String> comb, ProgressIndicator progressBar,String endpoint) {
        this.clas = comb;
        this.pgb = progressBar;
        this.endpoint=endpoint;
    }

    @Override
    public void run() {

        System.out.println("[GetClassThread]: setting up okhttp client");
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        System.out.println("[GetClassThread]: setting up okhttp client request");
        Request request = new Request.Builder()
                .url("http://"+ Configuration.ipaddress +":"+Configuration.port+"/"+endpoint)
                .addHeader("Authorization", "Bearer " + LogInModel.token)
                .build();

        if (Configuration.ipaddress!=null || Configuration.port!=null){
            try {
                Response response = client.newCall(request).execute();
                System.out.println("[GetClassThread]: " + response);
                if (response.code() == 200) {
                    System.out.println("[GetClassThread]: class retrieved");
                    ResponseBody body = response.body();
                    try {
                        byte[] bytes = body.bytes();
                        //removing bracket from response
                        String data = new String(bytes, "UTF-8");
                        System.out.println(data);
                        GsonBuilder builder=new GsonBuilder();
                        builder.serializeNulls();
                        Gson gson=builder.create();
                        List<ClassModel> classModel= gson.fromJson(data,new TypeToken<List<ClassModel>>(){}.getType());
                        ArrayList<String> classes=new ArrayList<>();

                        for(int i=0;i<classModel.size();i++){
                            classes.add(classModel.get(i).getClasses());
                        }
                        Platform.runLater(() -> {
                            clas.getItems().addAll(classes);
                        });
                        System.out.println(data);
                        response.close();
                        Platform.runLater(() -> {
                            pgb.setProgress(100);
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    //when something wrong happens, the server send the cause of the problem as a response body
                    String message=new String(response.body().bytes(),"UTF-8");
                    //Display an Alert dialog
                    Platform.runLater(() -> {
                        boolean error = new ConnectionError().Connection(response.code() + ":"+message);
                        if (error) {
                            SelectInformationSesssionAndClassWindow.StudentWindow.close();
                            System.out.println("[GetClassThread]--> server error,unable to get classess");
                        }
                    });
                }
            } catch (IOException e) {
                //Display an Alert dialog
                Platform.runLater(() -> {
                    boolean error = new ConnectionError().Connection("Unable to establish connection,CHECK INTERNET CONNECTION");
                    if (error) {
                        SelectInformationSesssionAndClassWindow.StudentWindow.close();
                        System.out.println("[GetClassThread]--> Connection Error,Window close");
                    }
                });
                System.out.println("[GetClassThread]: Unable to get class from server");
                e.printStackTrace();
            }
        }else {
            Platform.runLater(()->{
                new ConnectionError().Connection("Invalid configuration, please configure your software in the log in page");
            });
        }

    }
}
