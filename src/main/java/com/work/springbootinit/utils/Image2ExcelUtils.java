package com.work.springbootinit.utils;
//import com.squareup.okhttp.OkHttpClient;


import com.squareup.okhttp.*;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

public class Image2ExcelUtils {
    public static final String API_KEY = "ysV3oRqUdZYGpUtharvzJmpi";
    public static final String SECRET_KEY = "Woq8t5jonMK7Epd4HIAJjFQRNHf1P7PD";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        String imagePath = "E:\\project\\sunnyfan-backend\\sunnyfan-backend\\src\\main\\resources\\image\\img_26.png";
//        String imageBase64 = convertImageToBase64(imagePath);
        String imageBase64 = getFileContentAsBase64(imagePath, true);

        RequestBody body = RequestBody.create(mediaType, "image="+imageBase64+"&cell_contents=false&return_excel=true");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/ocr/v1/table?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www.form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();

//        System.out.println(response.body().string());


//        // 将返回的base64编码转为 Excel文件， 即输出FileOutputStream.write()

        String filePath = "E:\\project\\sunnyfan-backend\\sunnyfan-backend\\src\\main\\resources\\image\\";
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        String filename = currentTime.replace(":","-") + ".xlsx";
        convertBase64ToExcel(new JSONObject(response.body().string()).getString("excel_file"), filePath + filename);

//


    }

    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id="
        + API_KEY + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }

    /***
     * 获取文件base64编码
     * @param path
     * @param urlEncode 如果ContentType是application/x-www-form-urlencode时，为true
     * @return base64编码信息，不带文件头
     * @throws IOException
     */
    static String getFileContentAsBase64(String path, boolean urlEncode) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(path));
        String base64 = Base64.getEncoder().encodeToString(b);
        if (urlEncode){
            base64 = URLEncoder.encode(base64, "utf-8");

        }
        return base64;
    }

    static void convertBase64ToExcel(String base64String, String outputPath) {
        // 将base64编码转为字节流
        byte[] decodeBytes = Base64.getDecoder().decode(base64String);
        // 通过文件输出流将该字节流输出到指定文件路径

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
            fileOutputStream.write(decodeBytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert Base64 to Excel");
        }
    }

    static String getCurrentTime(){
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       return now.format(formatter);
    }

    static String convertImageToBase64(String imagePath){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;

        }catch(IOException e){
            throw new RuntimeException("Failed to convert image to Base64" ,e);
        }finally {
            {
                try{
                    baos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
