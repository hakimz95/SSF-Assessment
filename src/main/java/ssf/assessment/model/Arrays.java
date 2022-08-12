package ssf.assessment.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Arrays {
    private static final Logger logger = LoggerFactory.getLogger(Arrays.class);

    private static List<Data> fields = new ArrayList<>();

    public static List<Data> getFields() {
        return fields;
    }

    public static void setFields(List<Data> fields) {
        Arrays.fields = fields;
    }

    public static Arrays createJson(String json) throws IOException {
        logger.info("Array json");
        Arrays a = new Arrays();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader jr = Json.createReader(is);
            JsonObject jo = jr.readObject();
            JsonArray ja = jo.getJsonArray("Data");
            
            if (ja != null) {
                List<Data> output = new ArrayList<>();
                for (Object jf: ja) {
                    JsonObject joFields = (JsonObject) jf;
                    output.add(Fields.createJson(joFields));
                }
                logger.info("Data json established");
                Arrays.fields = output;
            }
        }
        return a;
    }

}
