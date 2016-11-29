package androidtd.rssinternet;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YourName on 27/11/16.
 */

public class FeedItem {
    String title;
    String link;
    String description;
    Date pubDate;
    String thumbnailURL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getPubDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM HH:mm");
        String datemin=dateFormat.format(pubDate);
        char min = datemin.charAt(0);
        char maj = Character.toUpperCase(min);
        String datemaj = maj + datemin.substring(1);
        return datemaj;
    }

    public void setPubDate(String pubDate) {
        Log.d("date",pubDate);
        String newPubDate = pubDate.substring(5);
        SimpleDateFormat date = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z");
        try {
            this.pubDate=date.parse(newPubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // this.pubDate = pubDate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int compareTo(FeedItem feedItem){
        return this.pubDate.compareTo(feedItem.getPubDate());
    }

}
