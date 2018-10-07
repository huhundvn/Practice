package e.poor.practice.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import e.poor.practice.models.Category;
import e.poor.practice.interfaces.IoItemClickListener;
import e.poor.practice.R;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Category> mList;
    private IoItemClickListener mListener;

    public CustomAdapter(List<Category> list, IoItemClickListener listener) {
        this.mList = list;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, null, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Category temp = mList.get(i);
        ((ItemHolder) viewHolder).mTVTitle.setText(temp.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item)
        View mItem;
        @BindView(R.id.tv_title)
        TextView mTVTitle;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item)
        public void click() {
            mListener.onClickItem();
            Log.i("thai", "Clicked");
        }
    }
}
