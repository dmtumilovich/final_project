package by.epam.rentacar.controller.util;

import by.epam.rentacar.controller.util.constant.PageParameters;
import by.epam.rentacar.controller.util.constant.RequestHeader;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

public class PathHelper {

    public static String getPreviousPage(HttpServletRequest request) {
        String refererPage = getRefererPage(request);
        return (refererPage == null || refererPage.isEmpty()) ? PageParameters.PAGE_MAIN : refererPage;
    }

    public static String getRefererPage(HttpServletRequest request) {
        return request.getHeader(RequestHeader.KEY_REFERER);
    }

    public static URI addParamToURI(String uri, String name, String value) {
        URI uriWithParam;

        try {
            uriWithParam = new URIBuilder(uri).addParameter(name, value).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while building uri with params!", e);
        }

        return uriWithParam;
    }

    public static URI addParamToReferer(HttpServletRequest request, String name, String value) {
        return addParamToURI(getRefererPage(request), name, value);
    }

}
