package pavlosnicolaou.bookmarks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vl1s on 17/03/2017.
 */

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bookmarkName;
        TextView bookmarkUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            bookmarkName = (TextView) itemView.findViewById(R.id.bookmark_name);
            bookmarkUrl = (TextView) itemView.findViewById(R.id.bookmark_url);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_cell, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
