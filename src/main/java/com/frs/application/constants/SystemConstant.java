package com.frs.application.constants;

public class SystemConstant {
    private SystemConstant() {
    }

    public static final String IMAGE_TYPE_REGEX = "image/(jpg|jpeg|png|gif|bmp)";
    public static final long MAX_LOGO_IMAGE_SIZE = 1024 * 1024 * 2;

    public class Account {

        private Account() {
        }


        public static final String USER_INFORMATION_PREFIX = "USER_INFORMATION_";
    }

    public class Jwt {
        private Jwt() {
        }
        public static final String TOKEN_CACHE_PREFIX = "TOKEN_";
    }

    public class Time {
        private Time() {
        }
        public static final String VIPER_TZ = "Asia/Ho_Chi_Minh";
    }

    public class Entity {
        public static final String NAME = "name";

        private Entity() {
        }

        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String USERNAME = "username";
        public static final String ACCOUNT_ID = "accountId";
        public static final String IS_DELETED = "isDeleted";
        public static final String REFRESH_TOKEN = "refreshToken";
    }
}
