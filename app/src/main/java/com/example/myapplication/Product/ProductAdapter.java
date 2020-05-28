package com.example.myapplication.Product;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.myapplication.Constants.BaseUrlUpload;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;
    List<Product> objects;
    /**
     * @param context
     * @param resource
     * @param objects
     * */
    public ProductAdapter(Activity context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        View gridView = inflater.inflate(this.resource,null);

        TextView nameflower = (TextView) gridView.findViewById(R.id.name_flower);
        TextView priceflower = (TextView) gridView.findViewById(R.id.price_flower);
        ImageView imageflower = (ImageView) gridView.findViewById(R.id.image_grid_view);
        /** Set data to custumView*/
        final Product product = this.objects.get(position);
        nameflower.setText(product.getName());
        priceflower.setText(product.getPrice());
//        imageflower.setImageResource(flower.getImage());
        Picasso.with(getContext()).load(BaseUrlUpload+product.getImage()).into(imageflower);
        /**Set Event Onclick*/

        return gridView;
    }
    /** Show mesage*/


}
