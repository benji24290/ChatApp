package ch.juventus.bs.firebaseinstachat;

/**
 * Created by root on 30.10.16.
 */

public class Message {

    private String text;
    private String name;
    private String photoUrl;
    private String fromId;
    private String toId;
    private String fromId_toId;

    public Message() {
    }

    public Message(String text, String name, String photoUrl, String fromId, String toId, String fromId_toId) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.fromId = fromId;
        this.toId = toId;
        this.fromId_toId = fromId_toId;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFromId(){ return fromId; }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId(){ return  toId; }

    public void  setToId(String toId){ this.toId = toId; }

    public String getFromId_toId(){return fromId_toId;}

    public  void setFromId_toId(String fromId_toId){ this.fromId_toId = fromId_toId; }

}

