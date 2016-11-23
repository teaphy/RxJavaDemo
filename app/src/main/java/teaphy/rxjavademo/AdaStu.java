package teaphy.rxjavademo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @autor Teaphy
 * Created at 2016/5/9.
 */
public class AdaStu extends RecyclerView.Adapter<AdaStu.MyViewHoler> {

    private Context mContext;
    private List<Student> mLists;

    public AdaStu(Context mContext) {
        this.mContext = mContext;

        mLists = new ArrayList<>();
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_stu, parent, false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        Student student = mLists.get(position);
        holder.tvName.setText(student.getName());
        holder.tvAge.setText(student.getAge());
        holder.tvNo.setText(student.getNo());
    }

    @Override
    public int getItemCount() {
        return null == mLists ? 0 : mLists.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_age)
        TextView tvAge;
        @BindView(R.id.tv_no)
        TextView tvNo;
        public MyViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addData(List<Student> stus) {
        mLists.clear();
        mLists.addAll(stus);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mLists.clear();
        notifyDataSetChanged();
    }

    public void addData(Student stu) {
        int pos = mLists.size();
        mLists.add(stu);
        notifyItemChanged(pos);
    }
}
