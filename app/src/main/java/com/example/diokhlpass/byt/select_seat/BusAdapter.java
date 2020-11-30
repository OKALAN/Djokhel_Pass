package com.example.diokhlpass.byt.select_seat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diokhlpass.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//import com.google.firebase.database.core.Context;

public class BusAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {


    private OnSeatSelected mOnSeatSelected;
public static ArrayList<Integer> number_seat = new ArrayList<>();
    private String TAG;
    private // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();
    public   List<String> list = new ArrayList<>();





    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

    ImageView imgSeat;

    private final ImageView imgSeatSelected;
    private final ImageView booked_seat;
    private final TextView idSeat;


    public EdgeViewHolder(View itemView) {
        super(itemView);
        imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
        imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
        idSeat = (TextView) itemView.findViewById(R.id.idSeat);
        booked_seat =(ImageView)itemView.findViewById(R.id.img_seat_booked);



    }

}

private static class CenterViewHolder extends RecyclerView.ViewHolder {

    ImageView imgSeat;
    private final ImageView imgSeatSelected;
    private final TextView idSeat;
    private final ImageView booked_seat;

    public CenterViewHolder(View itemView) {
        super(itemView);
        imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
        imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
        idSeat = (TextView) itemView.findViewById(R.id.idSeat);
        booked_seat =(ImageView)itemView.findViewById(R.id.img_seat_booked);



    }

}

private static class EmptyViewHolder extends RecyclerView.ViewHolder {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }

}

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<AbstractItem> mItems;

    public BusAdapter(Context context, List<AbstractItem> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.seat_item, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.seat_item, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

        int type = mItems.get(position).getType();

        if (type == AbstractItem.TYPE_CENTER) {

            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    number_seat.add(position +1);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());



                }
            });


            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            holder.idSeat.setText(String.valueOf(position +1));

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;



            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    number_seat.add(position +1);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());


                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            holder.idSeat.setText(String.valueOf(position +1));

        }





        }

        public static ArrayList<Integer> numSeat(){
         return number_seat;
        }

       public List<String> bookedSeat(int day, int month, int year, String dep_des, String horaire){


           DocumentReference docRef =    db.collection("Bus0").document(day+"-"+month+"-"+year).collection(dep_des).document(horaire);
           docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
               @Override
               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   if (task.isSuccessful()) {
                       DocumentSnapshot document = task.getResult();
                       if (document.exists()) {

                           Map<String, Object> map = document.getData();
                           if (map != null) {
                               for (Map.Entry<String, Object> entry : map.entrySet()) {
                                   list.add(entry.getValue().toString());
                               }
                           }

                           //So what you need to do with your list
                           for (String s : list) {
                               Log.d("TAG", s);
                           }

                       } else {
                           Log.d(TAG, "No such document");


                       }
                   } else {
                       Log.d(TAG, "get failed with ", task.getException());
                   }
               }
           });
           return list;

       }
}

