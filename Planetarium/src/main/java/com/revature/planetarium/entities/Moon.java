package com.revature.planetarium.entities;


import java.util.Base64;

public class Moon {

    private int moonId;
    private String moonName;
    private int ownerId;
    private byte[] imageData;


    public Moon(){}

    public Moon(int id, String name, int planetId){
        this.moonId = id;
        this.moonName = name;
        this.ownerId = planetId;
    }
    
    public int getMoonId() {
        return moonId;
    }
    public void setMoonId(int moonId) {
        this.moonId = moonId;
    }
    public String getMoonName() {
        return moonName;
    }
    public void setMoonName(String moonName) {
        this.moonName = moonName;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public void setImageData(String base64ImageData){
        imageData = Base64.getDecoder().decode(base64ImageData);
    }

    public String getImageData(){
        if (imageData != null){
            return Base64.getEncoder().encodeToString(imageData);
        } else {
            return null;
        }
    }

    public byte[] imageDataAsByteArray(){
        if(imageData != null){
            return imageData;
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        return "Moon [moonId=" + moonId + ", moonName=" + moonName + ", ownerId=" + ownerId + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + moonId;
        result = prime * result + ((moonName == null) ? 0 : moonName.hashCode());
        result = prime * result + ownerId;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Moon other = (Moon) obj;
        if (moonId != other.moonId)
            return false;
        if (moonName == null) {
            if (other.moonName != null)
                return false;
        } else if (!moonName.equals(other.moonName))
            return false;
        if (ownerId != other.ownerId)
            return false;
        return true;
    }

}
