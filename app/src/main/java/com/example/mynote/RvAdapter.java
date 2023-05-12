package com.example.mynote;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.databinding.EachRvBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RvAdapter extends ListAdapter<MNote,RvAdapter.viewHolder> {

    public RvAdapter(){
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<MNote> CALLBACK = new DiffUtil.ItemCallback<MNote>() {
        @Override
        public boolean areItemsTheSame(@NonNull MNote oldItem, @NonNull MNote newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MNote oldItem, @NonNull MNote newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
            && oldItem.getDescr().equals(newItem.getDescr()) && oldItem.getDate().equals(newItem.getDate());
        }
    };


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        MNote note = getItem(position);
        holder.binding.titleRv.setText(note.getTitle());
        holder.binding.dispRv.setText(note.getDescr());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.binding.dateTV.setText(dateFormat.format(note.getDate()));
       SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if(note.getTime() != null) {
            holder.binding.timeText.setText(timeFormat.format(note.getTime()));
        } else {
            holder.binding.timeText.setText("");
        }

//         Get the current date
        Date currentDate = Calendar.getInstance().getTime();

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);

        shape.setCornerRadii(new float[]{60, 60, 60, 60, 60, 60, 60, 60}); // Set the radius here

        // Compare the note date with the current date
        if (note.getDate().before(currentDate)) {
            shape.setColor(Color.parseColor("#0d0d0d"));
            holder.binding.status.setText(R.string.overdue);
        } else if (note.getDate().equals(currentDate)) {
            shape.setColor(Color.parseColor("#0c1950"));
            holder.binding.status.setText(R.string.today);
        } else {
            shape.setColor(Color.parseColor("#0c1950"));
            holder.binding.status.setText(R.string.ongoing);
        }
        holder.binding.rootLay.setBackground(shape);
    }




    public MNote getNote(int position){
        return getItem(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        EachRvBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachRvBinding.bind(itemView);
        }
    }
}
