package dnr2i.master.unicaen.fr.androidproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Ad implements Parcelable {

    private String id;
    private String title;
    private String description;
    private double price;
    private String pseudo;
    private String email;
    private String phone;
    private String city;
    private String postcode;
    private long date;
    private ArrayList<String> images;

    public Ad(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        pseudo = in.readString();
        email = in.readString();
        phone = in.readString();
        city = in.readString();
        postcode = in.readString();
        date = in.readLong();
        images = in.readArrayList(null);
    }

    public Ad(JSONObject json) {
        try {
            id = json.getString("id");
            title = json.getString("titre");
            description = json.getString("description");
            price = json.getDouble("prix");
            pseudo = json.getString("pseudo");
            email = json.getString("emailContact");
            phone = json.getString("telContact");
            city = json.getString("ville");
            postcode = json.getString("cp");
            date = json.getLong("date");
            this.images = new ArrayList<>();
            this.setImages(json.getJSONArray("images"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Ad(String id, String title, String description, double price, String pseudo, String email, String phone, String city, String postcode, long date) {
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

    public static final Parcelable.Creator<Ad> CREATOR = new Parcelable.Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel source) {
            return new Ad(source);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

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

    public String getFormattedDate() {
        Date date = new Date(getDate() * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
                ", phone_icon='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", date=" + date +
                ", image=" + images +
                '}';
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(JSONArray images) throws JSONException {
        if (images != null) {
            int len = images.length();
            for (int i = 0; i < len; i++) {
                this.images.add(images.get(i).toString());
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeString(pseudo);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(city);
        parcel.writeString(postcode);
        parcel.writeLong(date);
        parcel.writeList(images);
    }
}
