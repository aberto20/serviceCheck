package controllers;


import play.mvc.*;

import views.html.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your service is up......"));
    }
    public static void checkServices(){
        final long[] i = {1};
        int delay = 5000;   // delay for 5 sec.
        int interval = 1000;  // iterate every sec.
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // Task here ...
                String url = "http://gcom.rw:8888";
                try {
                    URL siteURL = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) siteURL
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    connection.setConnectTimeout(45*1000);
                    connection.setReadTimeout(45*1000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        System.out.println(">Gcom System is Running ...<");
                        i[0] += 1;
                    }
                } catch (Exception e) {
                    if (i[0]!=0){
                        String[]getphone={"250785185421","250784676994","250788730942"};
                        String usernamePassword="0784676994:gcom@123";
                        String encoded = Base64.getEncoder().encodeToString(usernamePassword.getBytes());

                        for(int k=0;k<getphone.length;k++){
                            String urlSms="http://egtecs.com:8080/api/v2/sendSms/?number="+ URLEncoder.encode(getphone[k])+"&sender="+URLEncoder.encode("Gcom_Server")+"&message="+URLEncoder.encode("Gcom System is Down now at "+new SimpleDateFormat("HH:mm:ss").format(new Date())+" ,please info System admin or System developer .");
                            System.out.print(e.getMessage()+" ,");
                            System.out.println(">Gcom System is Down now ...<");
                            try {
                                URL siteURL = new URL(urlSms);
                                HttpURLConnection connection = (HttpURLConnection) siteURL
                                        .openConnection();
                                connection.setRequestProperty("Authorization",encoded);
                                connection.setRequestMethod("GET");
                                connection.connect();
                                connection.setConnectTimeout(5*1000);
                                connection.setReadTimeout(5*1000);
                                //Read the response.
                                InputStreamReader isr =
                                        new InputStreamReader(connection.getInputStream());
                                BufferedReader in = new BufferedReader(isr);
                                String responseString="";
                                String outputString="";
                                //Write the  response to a String.
                                while ((responseString = in.readLine()) != null) {
                                    outputString = outputString + responseString;
                                }
                                System.out.println(outputString);
                            }catch (Exception ee){
                                System.out.println(ee.getMessage()+" ,");
                            }

                        }
                    }
                    i[0]= 0;
                }

            }
        }, delay, interval);
    }

}
