package org.Testdata;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;


public class testDataReader {


    public HashMap<String, String> getJsonData() throws IOException {
        String jsnContent= Arrays.toString(Files.readAllBytes(new File(System.getProperty("user.dir") + "/src/test/java/org/Testdata/testData.json").toPath()));

        ObjectMapper mapper  = new ObjectMapper();
        HashMap<String, String> lst=mapper.readValue(jsnContent, new TypeReference<HashMap<String,String>>(){});
        return lst;
    }





}
