package dnr2i.master.unicaen.fr.androidproject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Ad {

    private String id;
    private String title;
    private String description;
    private double price;
    private String pseudo;
    private String email;
    private String phone;
    private String city;
    private String postcode;
    private int date;
    private ArrayList<String> images;

    public Ad(String id, String title, String description, double price, String pseudo, String email, String phone, String city, String postcode, int date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pseudo = pseudo;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.postcode = postcode;
        this.date = date;
        this.images = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", date=" + date +
                '}';
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(JSONArray images) throws JSONException {
        if (images != null ){
            int len = images.length();
            for (int i = 0; i < len; i++) {
                this.images.add(images.get(i).toString());
            }
        }
    }
}
