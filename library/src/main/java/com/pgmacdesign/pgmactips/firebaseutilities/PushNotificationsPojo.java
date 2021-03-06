package com.pgmacdesign.pgmactips.firebaseutilities;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Class for de/serializing push notification objects
 * Created by pmacdowell on 10/19/2016.
 *

 //Sample Below
 {
    "data":{
        "customTag1":"5817d057ccc869088c3c8c82",
        "customTag2":"testing",
        "customTag3":1482190375,
        "customTag4":"customChat",
        "customTag5":"581cbd34ccc869090c35579a"
    },
    "notification":{
        "badge":23,
        "body":"testing",
        "sound":"default",
        "title":"My Title"
    },
    "priority":"high",
    "to":"firebase_cloud_messaging_id_goes_here
 }

 */
public class PushNotificationsPojo {

    public PushNotificationsPojo(){
        //This needs to default to high for IOS Devices
        this.priority = "high";
    }

    //Variables
    @SerializedName("to")
    private String to;
    @SerializedName("pushNotificationTag")
    private Integer pushNotificationTag;
    @SerializedName("pushNotificationUUID")
    private String pushNotificationUUID;
    @SerializedName("data")
    private Map<String, Object> mapData;
    @SerializedName("notification")
    private CustomNotificationObject notification;
    @SerializedName("priority")
    private String priority;
    @SerializedName("expirationTime")
    private Long expirationTime;
    
    public Long getExpirationTime() {
        return expirationTime;
    }
    
    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }
    
    public CustomNotificationObject getNotification() {
        return notification;
    }

    public void setNotification(CustomNotificationObject notification) {
        this.notification = notification;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public Integer getPushNotificationTag() {
        return pushNotificationTag;
    }
    
    public void setPushNotificationTag(Integer pushNotificationTag) {
        this.pushNotificationTag = pushNotificationTag;
    }
    
    public String getPushNotificationUUID() {
        return pushNotificationUUID;
    }
    
    public void setPushNotificationUUID(String pushNotificationUUID) {
        this.pushNotificationUUID = pushNotificationUUID;
    }
    
    public Map<String, Object> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, Object> mapData) {
        this.mapData = mapData;
    }

    /**
     * Custom Notifications Object
     */
    public static class CustomNotificationObject {
        @SerializedName("body")
        private String body;
        @SerializedName("title")
        private String title;
        @SerializedName("sound")
        private String sound;
        @SerializedName("badge")
        private Integer badge;
        @SerializedName("otherData")
        private Map<String, Object> otherData;


        public Map<String, Object> getOtherData() {
            return otherData;
        }

        public void setOtherData(Map<String, Object> otherData) {
            this.otherData = otherData;
        }

        public Integer getBadge() {
            if(badge == null){
                badge = 0;
            }
            return badge;
        }

        public void setBadge(Integer badge) {
            this.badge = badge;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }
    }

}
