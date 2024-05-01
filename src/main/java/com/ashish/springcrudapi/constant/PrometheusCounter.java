package com.ashish.springcrudapi.constant;

public class PrometheusCounter {

    public static final String CREATE_USER_TOTAL = "create_user.total";
    public static final String GET_USER_BY_ID_TOTAL = "get_user_by_id.total";
    public static final String GET_USERS_TOTAL = "get_users.total";
    public static final String UPDATE_USER_BY_ID_TOTAL = "update_user_by_id.total";
    public static final String DELETE_USER_BY_ID_TOTAL = "delete_user_by_id.total";

    public static final String TOTAL_NUMBER_OF_REQUESTS_MADE_TO = "Total number of requests made to ";
    public static final String TOTAL_REQUESTS_TO_POST_USER_DESCRIPTION = TOTAL_NUMBER_OF_REQUESTS_MADE_TO + "post user endpoint";
    public static final String TOTAL_REQUESTS_TO_GET_USERS_DESCRIPTION = TOTAL_NUMBER_OF_REQUESTS_MADE_TO + "get users endpoint";
    public static final String TOTAL_REQUESTS_TO_GET_USER_BY_ID_DESCRIPTION = TOTAL_NUMBER_OF_REQUESTS_MADE_TO + "get user by id endpoint";
    public static final String TOTAL_REQUESTS_TO_UPDATE_USER_BY_ID_DESCRIPTION =
        TOTAL_NUMBER_OF_REQUESTS_MADE_TO + "update user by id endpoint";
    public static final String TOTAL_REQUESTS_TO_DELETE_USER_BY_ID_DESCRIPTION =
        TOTAL_NUMBER_OF_REQUESTS_MADE_TO + "delete user by id endpoint";

}
