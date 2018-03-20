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


    private String[] foods = {"糖醋里脊1", "酸辣土豆丝2", "豆腐3", "上汤菠菜4", "红烧肉5"
            , "茶树菇6", "干菜刀豆7", "开背虾8", "清炖牛肉9", "红烧鱼块10", "红烧肉11", "茶树菇12"
            , "茶树菇13", "干菜刀豆14", "开背虾15", "清炖牛肉16", "红烧鱼块17", "红烧肉18", "茶树菇19"};


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rightitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(foods[position]);
        holder.iv.setImageResource(R.drawable.food);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"点击了",Toast.LENGTH_LONG).show();
//            }
//        });
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
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
