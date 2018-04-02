package wellijohn.org.scrollviewwithstickview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import wellijohn.org.scrollviewwithstickview.R;
import wellijohn.org.scrollviewwithstickview.constant.Constant;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/3-18:12
 * @email:
 * @desc:
 */
public class RVRightListAdapter extends RecyclerView.Adapter<RVRightListAdapter.ViewHolder> {





    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rightitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(Constant.foods[position]);
        holder.iv.setImageResource(R.drawable.food);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击了",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constant.foods.length;
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
