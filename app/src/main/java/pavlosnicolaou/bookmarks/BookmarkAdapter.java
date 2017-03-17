package pavlosnicolaou.bookmarks;

import android.content.Context;
import android.database.Cursor;
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

    private Cursor cursor;
    private Context context;

    public BookmarkAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    public void changeCursor(Cursor cursor) {
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = cursor;
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_cell, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cursor == null) {
            holder.bookmarkName.setText("");
            holder.bookmarkUrl.setText("");
            return;
        }
        cursor.moveToPosition(position);
        String title = cursor.getString(1);
        String url = cursor.getString(2);
        holder.bookmarkName.setText(title);
        holder.bookmarkUrl.setText(url);
    }

    @Override
    public int getItemCount() {
        if (cursor != null)
            return cursor.getCount();
        else
            return 0;
    }
    //recyrcler view is now ready
}
