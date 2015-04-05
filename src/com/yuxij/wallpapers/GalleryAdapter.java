package com.yuxij.wallpapers;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;


	public class GalleryAdapter extends BaseAdapter
    {
        //member variables
        private Context mContext;
        private ArrayList<Bitmap> mImageArray;

        //constructor
        public GalleryAdapter(Context context, ArrayList<Bitmap> mBitArray)
        {
            mContext = context;
            mImageArray = mBitArray;
        }

        public int getCount()
        {
            return mImageArray.size();
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        //returns the individual images to the widget as it requires them
        public View getView(int position, View convertView, ViewGroup parent)
        {
            final ImageView imgView = new ImageView(mContext);

            imgView.setImageBitmap(mImageArray.get(position));

            //put black borders around the image
            final RelativeLayout borderImg = new RelativeLayout(mContext);
            borderImg.setPadding(20, 20, 20, 20);
            borderImg.setBackgroundColor(0xff000000);//black
            borderImg.addView(imgView);
            return borderImg;
        }

    }//end of: class GalleryAdapter


