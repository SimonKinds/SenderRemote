package io.kindstrom.senderremote.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.kindstrom.senderremote.R;


public abstract class SingleTextItemAdapter<E> extends RecyclerView.Adapter<SingleTextItemAdapter.SingleTextItemViewHolder> {
    private final List<E> items;

    public SingleTextItemAdapter(List<E> items) {
        this.items = items;
    }

    @Override
    public SingleTextItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_text, parent, false);
        return new SingleTextItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SingleTextItemViewHolder holder, int position) {
        holder.text.setText(getText(items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    abstract protected String getText(E item);

    static class SingleTextItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;

        SingleTextItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
