package wellijohn.org.scrollviewwithstickview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wellijohn.org.scrollviewwithstickview.R;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:12
 * @email:
 * @desc:
 */
public class RVRightListAdapter extends RecyclerView.Adapter<RVRightListAdapter.ViewHolder> {


    private String[] foods={"糖醋里脊","酸辣土豆丝","豆腐","上汤菠菜","红烧肉"
            ,"茶树菇","干菜刀豆","开背虾","清炖牛肉","红烧鱼块","红烧肉","茶树菇"};


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rightitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(foods[position]);
        holder.iv.setImageResource(R.drawable.food);
    }

    @Override
    public int getItemCount() {
        return foods.length;
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;


        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
