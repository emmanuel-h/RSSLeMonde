package androidtd.rssinternet;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by YourName on 27/11/16.
 */

public class ReadRss extends AsyncTask<Void,Void,Void> {

    Context context;
    ProgressDialog progressDialog;
    String address="http://www.lemonde.fr/rss/une.xml";
    URL url;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;

    public ReadRss (Context context, RecyclerView recyclerView){
        this.recyclerView=recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");

    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        MyAdapter adapter = new MyAdapter(context,feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new VerticalSpace(50));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        processXml(getData());
        return null;
    }

    private void processXml(Document data) {
        if(data != null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentChild = items.item(i);
                if(currentChild.getNodeName().equalsIgnoreCase("item")){
                    FeedItem item = new FeedItem();
                    NodeList itemChilds = currentChild.getChildNodes();
                    for (int j = 0; j <itemChilds.getLength(); j++) {
                        Node current = itemChilds.item(j);
                        if(current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        } else
                        if(current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        } else
                        if(current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        } else
                        if(current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        } else
                            if(current.getNodeName().equalsIgnoreCase("enclosure")){
                                String url = current.getAttributes().item(0).getTextContent();
                                item.setThumbnailURL(url);
                            }
                    }
                    feedItems.add(item);
                }
            }
            Collections.sort(feedItems, new FeedItemComparator());
        }
    }

    public Document getData(){

        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
