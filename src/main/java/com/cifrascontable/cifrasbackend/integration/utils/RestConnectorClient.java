package com.cifrascontable.cifrasbackend.integration.utils;

import static com.cifrascontable.cifrasbackend.context.Constants.CLIENT_HTTP_HEADER;

import com.cifrascontable.cifrasbackend.integration.utils.exception.HttpRestConnectorException;
import com.cifrascontable.cifrasbackend.integration.utils.exception.RestConnectorException;
import com.cifrascontable.cifrasbackend.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

public class RestConnectorClient {

    public enum RequestType {
        PUT, DELETE, GET, POST, PATCH, HEAD
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConnectorClient.class);
    private static final TypeReference<Map<String, List<String>>> HEAD_TYPE_REFERENCE = new TypeReference<Map<String, List<String>>>() {
    };
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String CONTENT_TYPE = "Content-Type";
    private final OkHttpClient client;
    private final String contextPath;
    private final String endpoint;
    private final String serviceType;
    private final String xClient;

    private final JsonUtils jsonUtils;

    public RestConnectorClient(String xClient, String serviceType, String endpoint) {
        this(new OkHttpClient(), xClient, serviceType, endpoint, "", null);
    }

    public RestConnectorClient(String xClient, String serviceType, String endpoint, ObjectMapper objectMapper) {
        this(new OkHttpClient(), xClient, serviceType, endpoint, "", objectMapper);
    }

    public RestConnectorClient(String xClient, String serviceType, String endpoint, String contextPath) {
        this(new OkHttpClient(), xClient, serviceType, endpoint, contextPath, null);
    }

    public RestConnectorClient(String xClient, String serviceType, String endpoint, String contextPath, ObjectMapper objectMapper) {
        this(new OkHttpClient(), xClient, serviceType, endpoint, contextPath, objectMapper);
    }

    public RestConnectorClient(OkHttpClient client, String xClient, String serviceType, String endpoint, String contextPath) {
        this(client, xClient, serviceType, endpoint, contextPath, null);
    }

    public RestConnectorClient(OkHttpClient client, String xClient, String serviceType, String endpoint, String contextPath, ObjectMapper objectMapper) {
        Assert.notNull(serviceType, "serviceType could not be null");
        Assert.notNull(endpoint, "endpoint could not be null.");

        LOGGER.debug("Build RestConnectorClient. endpoint={} type={} context_path={}", endpoint, serviceType, contextPath);

        this.client = client;

        this.contextPath = contextPath != null ? contextPath : "";
        this.endpoint = endpoint.startsWith("http") ? endpoint : "http://" + endpoint;
        this.serviceType = serviceType;
        this.xClient = xClient;
        this.jsonUtils = objectMapper == null ? new JsonUtils() : new JsonUtils(objectMapper);
    }

    //*** GET ***

    public <R> R doGet(String serviceUrl, TypeReference<R> typeReference, String... uriParams) {
        return this.execute(serviceUrl, null, typeReference, RequestType.GET, null, uriParams).getBody();
    }

    public <R> R doGet(String serviceUrl, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, null, typeReference, RequestType.GET, headers, uriParams).getBody();
    }


    //*** POST ***

    public void doPost(String serviceUrl, Object body, String... uriParams) {
        this.execute(serviceUrl, body, null, RequestType.POST, null, uriParams);
    }

    public <R> R doPost(String serviceUrl, Object body, TypeReference<R> typeReference, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.POST, null, uriParams).getBody();
    }

    public <R> R doPost(String serviceUrl, Object body, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.POST, headers, uriParams).getBody();
    }

    public <R> Response<R> doPostResponse(String serviceUrl, Object body, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.POST, headers, uriParams);
    }

    //*** PUT ***

    public void doPut(String serviceUrl, Object body, String... uriParams) {
        this.execute(serviceUrl, body, null, RequestType.PUT, null, uriParams);
    }

    public <R> R doPut(String serviceUrl, Object body, TypeReference<R> typeReference, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.PUT, null, uriParams).getBody();
    }

    public <R> R doPut(String serviceUrl, Object body, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.PUT, headers, uriParams).getBody();
    }



    //*** PATCH ***

    public void doPatch(String serviceUrl, Object body, String... uriParams) {
        this.execute(serviceUrl, body, null, RequestType.PATCH, null, uriParams);
    }

    public <R> R doPatch(String serviceUrl, Object body, TypeReference<R> typeReference, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.PATCH, null, uriParams).getBody();
    }

    public <R> R doPatch(String serviceUrl, Object body, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.PATCH, headers, uriParams).getBody();
    }



    //*** DELETE ***

    public void doDelete(String serviceUrl, String... uriParams) {
        this.execute(serviceUrl, null, null, RequestType.DELETE, null, uriParams);
    }

    public <R> R doDelete(String serviceUrl, TypeReference<R> typeReference, String... uriParams) {
        return this.execute(serviceUrl, null, typeReference, RequestType.DELETE, null, uriParams).getBody();
    }

    public <R> R doDelete(String serviceUrl, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, null, typeReference, RequestType.DELETE, headers, uriParams).getBody();
    }


    public <R> R doDelete(String serviceUrl, Object body, TypeReference<R> typeReference, Map<String, String> headers, String... uriParams) {
        return this.execute(serviceUrl, body, typeReference, RequestType.DELETE, headers, uriParams).getBody();
    }

    //*** HEAD ***

    public Map<String, List<String>> doHead(String serviceUrl, String... uriVariables) {
        return this.execute(serviceUrl, null, HEAD_TYPE_REFERENCE, RequestType.HEAD, null, uriVariables).getHeaders();
    }

    public Map<String, List<String>> doHead(String serviceUrl, Map<String, String> headers, String... uriVariables) {
        return this.execute(serviceUrl, null, HEAD_TYPE_REFERENCE, RequestType.HEAD, headers, uriVariables).getHeaders();
    }

    protected OkHttpClient getClient() {
        return this.client;
    }

    //*** Auxiliary Methods ***
    private <R> Response<R> execute(String serviceUrl, Object body, TypeReference<R> typeReference, RequestType type, Map<String, String> headers, String[] uriParams) {

        String fullUrl = this.buildUrl(serviceUrl, uriParams);
        Date serviceDate = new Date();
        final StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        okhttp3.Response response = null;
        try {
            // Excecute
            try {
                Call call = this.buildCall(fullUrl, body, type, headers);
                response = call.execute();
            } catch (Exception ex) {
                String message = this.serviceType + " - RestConnector execution error";
                throw new RestConnectorException(message, ex);
            }

            // Process
            try {
                this.validateResponseStatus(response, serviceUrl);
                return this.processResponse(type, response, typeReference);
            } catch (IOException ex) {
                String message = this.serviceType + " - Deserialization error";
                throw new RestConnectorException(message, ex);
            }
        } finally {
            Optional.ofNullable(response).map(okhttp3.Response::body).ifPresent(ResponseBody::close);

            long serviceTime = stopwatch.getTotalTimeMillis();
            LOGGER.debug("{}: Time={}ms", fullUrl, serviceTime);

        }
    }

    private void validateResponseStatus(okhttp3.Response response, String url) throws IOException {

        int statusCode = response.code();

        if (!response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            String responseBodyAsString = null;
            if (responseBody != null) {
                responseBodyAsString = responseBody.string();
            }

            HttpRestConnectorException httpException = new HttpRestConnectorException(statusCode, response.headers(), responseBodyAsString);

            LOGGER.warn("{} <- {} BODY: {}", statusCode, url, responseBodyAsString);

            throw httpException;
        }
    }

    @SuppressWarnings({"unchecked"})
    private <R> Response<R> processResponse(RequestType type, okhttp3.Response response, TypeReference<R> typeReference) throws IOException {
        if (typeReference == null) {
            return new Response<>();
        }

        ResponseBody responseBody = response.body();
        Headers headers = response.headers();

        R body = null;
        if (responseBody != null) {
            String responseBodyString = responseBody.string();

            if (typeReference.getType().equals(String.class)) {
                body = (R) responseBodyString;
            } else {
                body = this.jsonUtils.deserialize(responseBodyString, typeReference);
            }
        }

        Response<R> result = new Response<>();
        result.setBody(body);
        result.setHeaders(headers.toMultimap());

        return result;
    }

    private RequestBody prepareBody(Object body, Map<String, String> headers){
        if (body == null) {
            return null;
        }

        String contentType = null;
        if(headers != null){
            contentType = headers.get(CONTENT_TYPE);
        }

        RequestBody requestBody;

        if(Objects.nonNull(contentType) && !contentType.contains(JSON_MEDIA_TYPE.subtype())
            && !(body instanceof RequestBody)){
            requestBody = RequestBody.create(MediaType.parse(contentType), body.toString());
        } else if (body instanceof RequestBody) {
            requestBody = (RequestBody) body;
        } else {
            String json = this.jsonUtils.serialize(body);
            requestBody = RequestBody.create(JSON_MEDIA_TYPE, json);
        }

        return requestBody;
    }

    private Call buildCall(String fullUrl, Object body, RequestType type, Map<String, String> headers) {
        RequestBody requestBody = prepareBody(body, headers);

        final Builder builder = new Request.Builder().url(fullUrl);

        switch (type) {
            case DELETE:
                if (requestBody == null) {
                    builder.delete();
                } else {
                    builder.delete(requestBody);
                }
                break;
            case GET:
                builder.get();
                break;
            case PUT:
                builder.put(requestBody);
                break;
            case POST:
                builder.post(requestBody);
                break;
            case PATCH:
                builder.patch(requestBody);
                break;
            case HEAD:
                builder.head();
                break;
            default:
                throw new IllegalArgumentException("RequestType " + type + " is not supported");
        }

        addAllHeaders(builder, headers);

        return this.client.newCall(builder.build());
    }

    private void addAllHeaders(Request.Builder builder, Map<String, String> headers) {
        builder.addHeader(CLIENT_HTTP_HEADER, this.xClient);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(builder::addHeader);
        }
    }

    protected void logFailure(String context, Throwable error) {
        LOGGER.warn("{} << {}", context, error.getMessage());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("", error);
        }
    }

    private String buildUrl(String serviceUrl, Object[] uriParams) {
        String urlSuffix = null;
        if (ArrayUtils.isNotEmpty(uriParams)) {
            String partialUrl = serviceUrl.replaceAll("\\{\\}", "%s");
            urlSuffix = String.format(partialUrl, uriParams);
        } else {
            urlSuffix = serviceUrl;
        }
        return this.endpoint + this.contextPath + urlSuffix;
    }


}
