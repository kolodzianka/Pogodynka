package pl.kolodzanka.meteo;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Source {

    private static final String URL = "http://traffic.erzeszow.pl/device/meteo/data/1/1";

    private URI uri = new URI(URL);

    private String response = IOUtils.toString(uri, "UTF-8");


    public Source() throws URISyntaxException, IOException {
    }


    public String getResponse() {
        return response;
    }


}
