package com.example.hw09_bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter {
    public interface myCartAdapterListener {
        public void go(int position);
    }
    CartAdapter.myCartAdapterListener myCartAdapterListener;

    public CartAdapter(myCartAdapterListener cartAdapterListener) {
        this.myCartAdapterListener = cartAdapterListener;
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        public MyCartViewHolder(@NonNull View itemView){
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);


        }
        public void updateTextViews(MyCart myCart){
            textViewTitle.setText(myCart.cartTitle);
        }
    }
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent, false);

        return new CartAdapter.MyCartViewHolder(view);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        CartAdapter.MyCartViewHolder myCartViewHolder = (CartAdapter.MyCartViewHolder) holder;
        MyCart myCart = Cart.myCartBooks.get(position);
        myCartViewHolder.updateTextViews(myCart);
    }
    public int getItemCount(){
        return Cart.myCartBooks.size();
    }
}
