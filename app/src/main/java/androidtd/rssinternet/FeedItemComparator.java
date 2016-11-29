package androidtd.rssinternet;

import java.util.Comparator;

/**
 * Created by YourName on 28/11/16.
 */

public class FeedItemComparator implements Comparator<FeedItem>{

    public int compare(FeedItem left, FeedItem right) {
        return right.getPubDate().compareTo(left.getPubDate());
    }
}
