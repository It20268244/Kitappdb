package com.example.kitappdb;

public class MessageDB {

    String sellerID;
    String cusID;
    String message;
    String reply;
    String sendDate;
    String replyDate;
    String bookName;
    String childName;

    public MessageDB() {
    }

    public MessageDB(String sellerID, String cusID, String message, String reply, String sendDate, String replyDate, String bookName) {
        this.sellerID = sellerID;
        this.cusID = cusID;
        this.message = message;
        this.reply = reply;
        this.sendDate = sendDate;
        this.replyDate = replyDate;
        this.bookName = bookName;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getBookName() { return bookName; }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
