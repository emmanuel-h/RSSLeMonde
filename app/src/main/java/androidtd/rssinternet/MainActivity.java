package androidtd.rssinternet;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
//test
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<FeedItem> feedItems = new ArrayList<>();
    RecyclerView recyclerView;
    ReadRss readRss;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        readRss = new ReadRss(this,recyclerView);
        readRss.execute();
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        return true;
    }

    @Override
    public void onRefresh() {
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ReadRss readRss2 = new ReadRss(this,recyclerView);
        readRss2.execute();
        swipeLayout.setRefreshing(false);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(this);
    }
}
