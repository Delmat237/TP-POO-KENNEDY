package tracetech.tracetech.model;

import java.time.LocalDateTime;

public class StolenItem {
    private int id;
    private String type;
    private String mark;
    private String model;
    private String imei;
    private String serialNumber;
    private String description;
    private LocalDateTime theftDate;
    private LocalDateTime ReportDate;
    private String picturePath;
    private String proofOfPurchasePath;
    private int owner_id;
    private boolean  state; //retrouvé ou non

    public StolenItem(LocalDateTime ReportDate, String description, int id, String imei, String mark, int owner_id, String picture, String proofOfPurchasePath, String serialNumber,LocalDateTime theftDate, String type) {
        this.ReportDate = ReportDate;
        this.description = description;
        this.id = id;
        this.imei = imei;
        this.mark = mark;
        this.owner_id = owner_id;
        this.picturePath = picture;
        this.proofOfPurchasePath = proofOfPurchasePath;
        this.serialNumber = serialNumber;
        this.theftDate = theftDate;
        this.type = type;
        this.state=false;
    }

    public StolenItem() {
        //TODO Auto-generated constructor stub
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTheftDate() {
        return theftDate;
    }

    public void setTheftDate(LocalDateTime theftDate) {
        this.theftDate = theftDate;
    }

    public LocalDateTime getReportDate() {
        return ReportDate;
    }

    public void setReportDate(LocalDateTime ReportDate) {
        this.ReportDate = ReportDate;
    }
    public String getProofOfPurchasePath() {
        return proofOfPurchasePath;
    }

    public void setProofOfPurchasePath(String proofOfPurchasePath) {
        this.proofOfPurchasePath = proofOfPurchasePath;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    


}
