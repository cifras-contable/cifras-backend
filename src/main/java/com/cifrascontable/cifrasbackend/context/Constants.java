package com.cifrascontable.cifrasbackend.context;

public interface Constants {
    String CONTEXT_PATH = "/payoutsBackend";
    String APP_NAME = "payoutsBackend";
    String BASE_PACKAGE = "com.dlocal.payoutsBackend";
    String CONTROLLERS_PACKAGE = BASE_PACKAGE + ".controller";
    String LOOKOUT_API_PREFIX = "AP-";
    String HEADER_UOW = "X-UOW";
    int PROCESSOR_UNASSIGNED = 12;
    String CLIENT_HTTP_HEADER = "X-Client";

    enum RoundingType {
        ROUND, TRUNCATE
    }

    class MERCHANTS {
        public final static Long PAYONEER = Long.valueOf(1112);
    }
}
