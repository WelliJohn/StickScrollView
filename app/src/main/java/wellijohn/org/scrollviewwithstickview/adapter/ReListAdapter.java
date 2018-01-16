package wellijohn.org.scrollviewwithstickview.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wellijohn.org.scrollviewwithstickview.R;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:12
 * @email:
 * @desc:
 */
public class ReListAdapter extends RecyclerView.Adapter<ReListAdapter.ViewHolder> {


    private String[] foods = {"米饭", "招牌菜", "家常风味", "精美甜品", "饮料", "田园时蔬", "美食"};


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(foods[position]);
        if (position == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }
    }

    @Override
    public int getItemCount() {
        return foods.length;
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;


        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }
}
