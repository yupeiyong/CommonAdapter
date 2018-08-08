package com.example.administrator.commonadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private int _layoutId;
    private List<T>_datas;
    public CommonAdapter(List<T>datas,int lyaoutId){
        this._layoutId=lyaoutId;
        this._datas=datas;
    }
    @Override
    public int getCount() {
        if(_datas!=null)
            return _datas.size();
        return 0;
    }

    @Override
    public T getItem(int i) {
        if(_datas!=null)
            return _datas.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public abstract void bindView(ViewHolder holder,T itemObj);

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=ViewHolder.bind(viewGroup.getContext(),view,viewGroup,this._layoutId,i);
        bindView(holder,getItem(i));
        return holder.getItem();
    }
    public static class ViewHolder{
        private SparseArray<View>_viewItems;
        private int _position;
        private View _item;
        private Context _context;

        private ViewHolder(Context context,ViewGroup viewGroup,int layoutId){
            this._context=context;
            this._item= LayoutInflater.from(context).inflate(layoutId,viewGroup,false);
            this._viewItems=new SparseArray<View>();
        }
        public static ViewHolder bind(Context context,View view, ViewGroup viewGroup,int layoutId,int position){
            ViewHolder holder=null;
            if(view==null){
                holder=new ViewHolder(context,viewGroup,layoutId);
                holder._item.setTag(holder);
            }else{
                holder=(ViewHolder) view.getTag();
            }
            holder._position=position;
            return holder;
        }

        private <T extends View> T getViewItem(int id){
            T item=(T)_viewItems.get(id);
            if(item==null){
                item=_item.findViewById(id);
                _viewItems.put(id,item);
            }
            return item;
        }

        public int getPosition(){
            return _position;
        }

        public View getItem(){
            return _item;
        }

        public ViewHolder setText(int id,CharSequence text){
            View item=getViewItem(id);
            if(item instanceof TextView){
                ((TextView)item).setText(text);
            }
            return this;
        }

        public ViewHolder setImageSource(int id,int sourceId){
            View item=getViewItem(id);
            if(item instanceof ImageView){
                ((ImageView)item).setImageResource(sourceId);
            }else{
                item.setBackgroundResource(sourceId);
            }
            return this;
        }

        public ViewHolder setOnClickListener(int id, View.OnClickListener listener){
            View item=getViewItem(id);
            item.setOnClickListener(listener);
            return this;
        }

        public ViewHolder setVisibility(int id, int visible){
            getViewItem(id).setVisibility(visible);
            return this;
        }

        public ViewHolder setTag(int id,Object tag){
            getViewItem(id).setTag(tag);
            return this;
        }
    }
}
