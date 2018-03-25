package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 3/25/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.holder> {
    private Context context; //deklarasi context
    private List<itemTodo> item; //deklarasi list item
    int id; //deklarasi id

    //constructor nya
    public TodoAdapter(Context context, List<itemTodo> item, int id) { //menampilkan adapter
        this.context=context; //get context
        this.item=item; //get item
        this.id=id; //get id
    }

    //untuk menentukan view recycler nya
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_list_row, parent, false); //layout yang digunakan
        holder holder=new holder(view);//holder untuk menentukan posisi
        return holder;
    }

    @Override
    public void onBindViewHolder(TodoAdapter.holder holder, int position) { //untuk menentukan nilai objeknya sesuai dengan recycler
        itemTodo itemm=item.get(position); //mengatur posisi
        holder.td.setText(itemm.getTodo()); //get holder todo
        holder.ds.setText(itemm.getDesc()); //get holder desc
        holder.pr.setText(itemm.getPrior()); //get holder prior
        holder.card.setCardBackgroundColor(context.getResources().getColor(this.id));//set background
    }

    public itemTodo getItem(int position) { //mengatur posisi
        return item.get(position);
    }

    public void removeitem(int i) { //jika item di hapus
        item.remove(i); //item terhapus
        notifyItemRemoved(i); //menampilkan notifikasi terhapus
        notifyItemRangeChanged(i, item.size());
    }

    @Override
    public int getItemCount() { //item yang akan ditampilkan
        return item.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView td, ds, pr; //deklarasi todo desc prio
        CardView card; //deklarasi card view

        public holder(View itemView) {
            super(itemView);
            td=itemView.findViewById(R.id.todorv);//get id todo
            ds=itemView.findViewById(R.id.descrv); //get id desc
            pr=itemView.findViewById(R.id.priorv); //get id prio
            card=itemView.findViewById(R.id.cd); //get id cardview
        }
    }
}
