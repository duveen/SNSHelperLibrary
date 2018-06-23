package kr.sharenshare.helper.bus;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PushEvent {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        PushEvent pushEvent;

        private Builder() {
            pushEvent = new PushEvent();
        }

        public Builder setPushType(String pushType) {
            pushEvent.pushType = pushType;
            return this;
        }

        public Builder setPushData(String key, Object val) {
            pushEvent.pushDatas.put(key, val);
            return this;
        }

        public Builder setPushDatas(Map<String, Object> datas) {
            pushEvent.pushDatas.putAll(datas);
            return this;
        }

        public PushEvent build() {
            return pushEvent;
        }
    }

    private String pushType;
    private HashMap<String, Object> pushDatas;

    private PushEvent() {
        pushDatas = new HashMap<>();
    }

    public String getPushType() {
        return pushType;
    }

    public Object getPushData(String key) {
        return pushDatas.get(key);
    }

    public HashMap<String, Object> getPushDatas() {
        return pushDatas;
    }

    @Override
    public String toString() {
        return String.format(Locale.KOREA, "PushEvent(Type: %s, Data: %s)", getPushType(), getPushDatas().toString());
    }
}
