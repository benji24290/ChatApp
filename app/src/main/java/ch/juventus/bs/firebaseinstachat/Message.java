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

    public Message() {
    }

    public Message(String text, String name, String photoUrl, String fromId, String toId) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.fromId = fromId;
        this.toId = toId;

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

    public void  setToId(String toId){ this.toId =toId; }

}

