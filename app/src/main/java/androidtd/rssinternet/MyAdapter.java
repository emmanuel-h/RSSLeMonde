package androidtd.rssinternet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YourName on 27/11/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<FeedItem> feedItems;
    Context context;

    public MyAdapter(Context context, ArrayList<FeedItem> feedItems){
        this.context=context;
        this.feedItems=feedItems;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FeedItem current = feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());
        holder.date.setText(current.getPubDateString());
        Picasso.with(context).load(current.getThumbnailURL()).into(holder.thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewsDetails.class);
                intent.putExtra("link",current.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,date;
        ImageView thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date_text);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
