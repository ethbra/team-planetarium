package com.revature.planetarium.entities;


import java.util.Base64;

public class Planet {
    

    private int planetId;
    private String planetName;
    private int ownerId;
    private byte[] imageData;
    
    public int getPlanetId() {
        return planetId;
    }
    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }
    public String getPlanetName() {
        return planetName;
    }
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
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
        return "Planet [planetId=" + planetId + ", planetName=" + planetName + ", ownerId=" + ownerId + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + planetId;
        result = prime * result + ((planetName == null) ? 0 : planetName.hashCode());
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
        Planet other = (Planet) obj;
        if (planetId != other.planetId)
            return false;
        if (planetName == null) {
            if (other.planetName != null)
                return false;
        } else if (!planetName.equals(other.planetName))
            return false;
        return ownerId == other.ownerId;
    }

}
