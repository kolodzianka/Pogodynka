package pl.kolodzanka.meteo;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ManySources {

    private static final String URL1 = "http://traffic.erzeszow.pl/device/meteo/data/1/";


    List<URI> uriWithMeteo;

    public List<URI> makeUriList() {

        uriWithMeteo = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            URI tmp = URI.create(URL1.concat(String.valueOf(i)));
            uriWithMeteo.add(tmp);
        }
        return uriWithMeteo;
    }

    public List convertUriListToResponse(List<URI> uriList) throws IOException {
        List <String> response = new ArrayList<>();
        for (int j = 0; j < uriList.size(); j++) {
            String tmp = IOUtils.toString(uriList.get(j), "UTF-8");
            response.add(tmp);
        }
        return response;
    }

}
